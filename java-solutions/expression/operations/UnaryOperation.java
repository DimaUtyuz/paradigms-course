package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public abstract class UnaryOperation<T> implements GenericExpression<T> {
    protected final GenericExpression<T> expression;
    protected final Mode<T> mode;

    public UnaryOperation(GenericExpression<T> expression, Mode<T> mode) {
        this.expression = expression;
        this.mode = mode;
    }

    public abstract T calculate(T x);
    public abstract String getSymbol();

    @Override
    public T evaluate(T x, T y, T z) {
        T value = expression.evaluate(x, y, z);
        return calculate(value);
    }

    @Override
    public String toString() {
        return getSymbol() + "(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        boolean needBrackets = expression instanceof BinaryOperation;
        return getSymbol() + (needBrackets ? "(" : "")
                + expression.toMiniString() + (needBrackets ? ")" : "");
    }
}
