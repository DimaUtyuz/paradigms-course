package expression.parser;

import expression.*;
import expression.modes.Mode;
import expression.exceptions.*;
import expression.operations.*;

import java.util.List;

public class ExpressionGenericParser<T> extends BaseParser implements GenericParser<T> {
    private final Mode<T> mode;
    private static final List<List <String>> levels = List.of(
            List.of("*", "/", "mod"),
            List.of("+", "-")
    );

    public ExpressionGenericParser(Mode<T> mode) {
        this.mode = mode;
    }

    @Override
    public GenericExpression<T> parse(String expression) throws ParseException {
        setSource(new StringSource(expression));
        GenericExpression<T> result = parse(levels.size() - 1);
        if (ch != END) {
            throw new ExtraneousCharactersException(getPosition(), getStr());
        }
        return result;
    }

    private GenericExpression<T> parse(int level) throws ParseException {
        if (level == -1) {
            return parseUnExpression();
        }
        skipWhitespace();
        GenericExpression<T> result = parse(level - 1);
        boolean flag = true;
        while (flag) {
            flag = false;
            skipWhitespace();
            for (String operation : levels.get(level)) {
                if (test(operation)) {
                    result = parseBinExpression(operation, result, parse(level - 1));
                    flag = true;
                }
            }
        }
        return result;
    }

    private GenericExpression<T> parseUnExpression() throws ParseException {
        skipWhitespace();
        if (isDigit()) {
            return parseConst(false);
        } else if (test('-')) {
            skipWhitespace();
            if (isDigit()) {
                return parseConst(true);
            } else {
                return new Negate<>(parseUnExpression(), mode);
            }
        } else if (test("abs")) {
            return new Abs<>(parseUnExpression(), mode);
        } else if (test("square")) {
            return new Square<>(parseUnExpression(), mode);
        } else if (test('(')) {
            GenericExpression<T> result = parse(levels.size() - 1);
            if (!test(')')) {
                throw new ParenthesisNotFoundException(ch, getPosition(), getStr());
            }
            return result;
        } else {
            skipWhitespace();
            StringBuilder sb = new StringBuilder();
            while (isVariable(ch)) {
                sb.append(ch);
                nextChar();
            }
            String result = sb.toString();
            switch (result) {
                case "x": return new Variable<>(result, mode);
                case "y": return new Variable<>(result, mode);
                case "z": return new Variable<>(result, mode);
                default: throw new VariableNotFoundException(result, getPosition(), getStr());
            }
        }
    }

    private GenericExpression<T> parseConst(boolean flag) throws ParseException {
        StringBuilder sb = new StringBuilder();
        if (flag) {
            sb.append("-");
        }
        while (isDigit()) {
            sb.append(ch);
            nextChar();
        }
        String result = sb.toString();
        try {
            return new Const<>(mode.value(result));
        } catch (NumberFormatException e) {
            throw new NumberNotFoundException(result, getPosition(), getStr());
        }
    }

    private BinaryOperation<T> parseBinExpression(String operation, GenericExpression<T> left,
                                               GenericExpression<T> right) throws ParseException {
        switch (operation) {
            case "+": return new Add<>(left, right, mode);
            case "-": return new Subtract<>(left, right, mode);
            case "*": return new Multiply<>(left, right, mode);
            case "/": return new Divide<>(left, right, mode);
            case "mod": return new Mod<>(left, right, mode);
            default: throw new OperationNotFoundException(operation, getPosition(), getStr());
        }
    }
}
