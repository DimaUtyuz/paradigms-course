package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public class Multiply<T> extends BinaryOperation<T> {
    public Multiply(GenericExpression<T> left, GenericExpression<T> right, Mode<T> mode) {
        super(left, right, mode);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public boolean needsBrackets() {
        return false;
    }

    @Override
    public T calculate(T x, T y) {
        return mode.mul(x, y);
    }
}
