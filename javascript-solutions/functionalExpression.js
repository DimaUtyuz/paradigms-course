"use strict";

const cnst = a => () => a;
const one = cnst(1);
const two = cnst(2);
const mapNumber = {
    'one': one,
    'two': two
};

const mapVariable = new Map([
    ['x',  0],
    ['y',  1],
    ['z',  2]
]);
const variable = name => {
    const num = mapVariable.get(name);
    return (...variables) => variables[num];
}

const operation = func => (...args) => (...variables) => func(...args.map(arg => arg(...variables)));

const negate = operation(a => -a);
const floor = operation(a => Math.floor(a));
const ceil = operation(a => Math.ceil(a));
const add = operation((a, b) => a + b);
const subtract = operation((a, b) => a - b);
const multiply = operation((a, b) => a * b);
const divide = operation((a, b) => a / b);
const madd = operation((a, b, c) => a * b + c);

const mapOperation = {
    'negate': [negate, 1],
    '_': [floor, 1],
    'floor': [floor, 1],
    '^': [ceil, 1],
    'ceil': [ceil, 1],
    '+': [add, 2],
    '-': [subtract, 2],
    '*': [multiply, 2],
    '/': [divide, 2],
    '*+': [madd, 3],
    'madd': [madd, 3]
}

const parse = str => {
    let elements = str.trim().split(/\s+/);
    let stack = [];
    for (const element of elements) {
        if (element in mapNumber) {
            stack.push(mapNumber[element]);
        } else if (mapVariable.has(element)) {
            stack.push(variable(element));
        } else if (element in mapOperation) {
            let op = mapOperation[element];
            stack.push(op[0](...stack.splice(-op[1])));
        } else {
            stack.push(cnst(+element));
        }
    }
    return stack.pop();
}
