"use strict";



let numByElement = new Map([
    ['x', 0],
    ['y', 1],
    ['z', 2]
]);

let AbstractElement = {
    toString() {
        return this.name.toString();
    },
    prefix() {
        return this.toString();
    },
    postfix() {
        return this.toString();
    }
}

function createElement(constr, evaluate, diff) {
    let Element = constr;
    Element.prototype = Object.create(AbstractElement);
    Element.prototype.constructor = Element;
    Element.prototype.evaluate = evaluate;
    Element.prototype.diff = diff;
    return Element;
}

let Const = createElement(
    function(name) {this.name = name;},
    function() {return this.name;},
    function() {return ZERO;}
);

let ZERO = new Const(0);
let ONE = new Const(1);
let TWO = new Const(2);

let Variable = createElement(
    function(name) {this.name = name; this.num = numByElement.get(name)},
    function(...args) {return args[this.num];},
    function(v) {return v === this.name ? ONE : ZERO;}
);

let AbstractOperation = {
    evaluate(x, y, z) {
        return this.calculate(...this.members.map(member => member.evaluate(x, y, z)));
    },
    toString() {
        return this.members.map(member => member.toString()).join(' ') + ' ' + this.name;
    },
    diff(v) {
        return this.createDiff(v, this.members.map(member => member.diff(v)));
    },
    prefix() {
        return '(' + this.name + ' ' + this.members.map(member => member.prefix()).join(' ') + ')';
    },
    postfix() {
        return '(' + this.members.map(member => member.postfix()).join(' ') + ' ' + this.name + ')';
    }
}

const argsForOperation = new Map();

function createOperation(calculate, name, createDiff) {
    let Operation = function(...members) {
        this.members = members;
    }
    Operation.prototype = Object.create(AbstractOperation);
    Operation.prototype.constructor = Operation;
    Operation.prototype.calculate = calculate;
    Operation.prototype.name = name;
    Operation.prototype.createDiff = createDiff;
    argsForOperation.set(name, [Operation, calculate.length]);
    return Operation;
}

let Add = createOperation(
    (a, b) => a + b,
    '+',
    function(v, diffMembers) {
        return new Add(diffMembers[0], diffMembers[1]);
    });

let Subtract = createOperation(
    (a, b) => a - b,
    '-',
    function(v, diffMembers) {
        return new Subtract(diffMembers[0], diffMembers[1]);
    });

let Multiply = createOperation(
    (a, b) => a * b,
    '*',
    function(v, diffMembers) {
        return new Add(
            new Multiply(
                diffMembers[0],
                this.members[1],
            ),
            new Multiply(
                this.members[0],
                diffMembers[1]
            ),
        );
    });

let Divide = createOperation(
    (a, b) => a / b,
    '/',
    function(v, diffMembers) {
        return new Divide(
            new Subtract(
                new Multiply(
                    diffMembers[0],
                    this.members[1],
                ),
                new Multiply(
                    this.members[0],
                    diffMembers[1]
                ),
            ),
            new Multiply(this.members[1], this.members[1])
        );
    });

let Negate = createOperation(
    a => -a,
    'negate',
    function(v, diffMembers) {
        return new Negate(diffMembers[0]);
    });

let Hypot = createOperation(
    (a, b) => a * a + b * b,
    'hypot',
    function(v, diffMembers) {
        return new Multiply(
            TWO,
            new Add(
                new Multiply(this.members[0], diffMembers[0]),
                new Multiply(this.members[1], diffMembers[1])
            )
        );
    });

let HMean = createOperation(
    (a, b) => 2 / (1 / a + 1 / b),
    'hmean',
    function(v) {
        let mean = new Divide(
            TWO,
            new Add(
                new Divide(ONE, this.members[0]),
                new Divide(ONE, this.members[1])
            )
        );
        return mean.diff(v);
    });

let ArithMean = createOperation(
    (...elements) => {
        let sum = elements.reduce((tmp, cur) => tmp + cur, 0);
        return sum / elements.length;
    },
    'arith-mean',
    function(v) {
        let sum = this.members.reduce((tmp, cur) => new Add(tmp, cur), ZERO);
        let result = new Divide(sum, new Const(this.members.length));
        return result.diff(v);
    });

let GeomMean = createOperation(
    (...elements) => {
        let mul = elements.reduce((tmp, cur) => tmp * cur, 1);
        return Math.pow(Math.abs(mul), 1 / elements.length);
    },
    'geom-mean',
    function(v) {
        let mul = this.members.reduce((tmp, cur) => new Multiply(tmp, cur), ONE);
        return new Divide(
            new Multiply(new GeomMean(...this.members), mul.diff(v)),
            new Multiply(new Const(this.members.length), mul)
        );
    });

let HarmMean = createOperation(
    (...elements) => {
        let result = elements.reduce((sum, cur) => sum + 1 / cur, 0);
        return elements.length / result;
    },
    'harm-mean',
    function(v) {
        let sum = this.members.reduce((tmp, cur) => new Add(tmp, new Divide(ONE, cur)), ZERO);
        let result = new Divide(new Const(this.members.length), sum);
        return result.diff(v);
    });

function createParseException(name, getMessage) {
    let ParseException = function(result, position, str) {
        this.message = getMessage(result);
        if (position !== -1) {
            this.message += ' at position ' + position + '\n' + str + '\n'
                            + '_'.repeat(position > 0 ? position - 1: 0) + '^'
                            + '_'.repeat(str.length > position ? str.length - position : 0);
        }
    }
    ParseException.prototype = Object.create(Error.prototype);
    ParseException.prototype.name = name;
    ParseException.prototype.constructor = ParseException;
    return ParseException;
}

let ExtraCharacterException = createParseException(
    'ExtraCharacterException',
    function(result) {return "Extra characters were found \"" + result + "\"";}
)

let VariableNotFoundException = createParseException(
    'VariableNotFoundException',
    function(result) {return "The variable \"" + result + "\" doesn't exist";}
)

let BracketNotFoundException = createParseException(
    'BracketNotFoundException',
    (result) => "Expected closing bracket, found \"" + result + "\""
)

let OperationNotFoundException = createParseException(
    'OperationNotFoundException',
    function(result) {return "The operation \"" + result + "\" doesn't exist";}
)

let PartOfExpressionNotFoundException = createParseException(
    'PartOfExpressionNotFoundException',
    function() {return "The part of the expression wasn't found";}
)

function Parser(direction, needBrackets, str) {
    let elements = str.split(/\s+|([()])/).filter(x => x);
    str = elements.join(' ');
    let position = (direction === 'right') ? -1 : str.length + 2;
    let openingBracket = (direction === 'right') ? '(' : ')';
    let closingBracket = (direction === 'right') ? ')' : '(';
    let level = 0;
    this.getFirst = (direction === 'right') ? () => elements[0] : () => elements[elements.length - 1];
    this.delete = (direction === 'right') ?
        () => {position += this.getFirst().length + 1; return elements.shift()} :
        () => {position -= this.getFirst().length + 1; return elements.pop()};
    this.sort = (direction === 'left') ? (arr) => arr.reverse() : (arr) => arr;
    this.parse = function() {
        if (elements.length === 0) {
            throw new PartOfExpressionNotFoundException(0, position, str);
        }
        if (this.getFirst() !== openingBracket && needBrackets ||
                !needBrackets && !argsForOperation.has(this.getFirst())) {
            let element = this.delete();
            if (level === 0 && elements.length > 0) {
                throw new ExtraCharacterException(this.getFirst(), position, str);
            }
            if (numByElement.has(element)) {
                return new Variable(element);
            } else if (!isNaN(+element)) {
                return new Const(+element);
            } else {
                throw new VariableNotFoundException(element, position, str);
            }
        }
        if (needBrackets) {
            this.delete();
        }
        let element = this.delete();
        if (argsForOperation.has(element)) {
            level++;
            let members = [];
            let operation = argsForOperation.get(element);
            let counter = 0;
            while ((counter < operation[1] || operation[1] === 0) && this.getFirst() !== closingBracket) {
                members.push(this.parse());
                counter++;
            }
            if (counter !== operation[1] && operation[1] !== 0) {
                throw new PartOfExpressionNotFoundException(0, position, str);
            }
            if (needBrackets && (elements.length === 0 || this.getFirst() !== closingBracket)) {
                throw new BracketNotFoundException('nothing', position, str);
            }
            if (needBrackets) {
                this.delete();
            }
            level--;
            if (level === 0 && elements.length > 0) {
                throw new ExtraCharacterException(this.getFirst(), position, str);
            }
            return new operation[0](...this.sort(members));
        } else {
            throw new OperationNotFoundException(element, position, str);
        }
    }
}

const parsePrefix = str => {
    return new Parser('right', true, str).parse();
}

const parsePostfix = str => {
    return new Parser('left', true, str).parse();
}

const parse = str => {
    return new Parser('left', false, str).parse();
}
