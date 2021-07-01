package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public class Subtract<T> extends BinaryOperation<T> {
    public Subtract(GenericExpression<T> left, GenericExpression<T> right, Mode<T> mode) {
        super(left, right, mode);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public boolean needsBrackets() {
        return true;
    }

    @Override
    public T calculate(T x, T y) {
        return mode.sub(x, y);
    }
}
