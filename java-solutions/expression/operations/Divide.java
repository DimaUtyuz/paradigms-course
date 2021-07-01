package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public class Divide<T> extends BinaryOperation<T> {
    public Divide(GenericExpression<T> left, GenericExpression<T> right, Mode<T> mode) {
        super(left, right, mode);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public boolean needsBrackets() {
        return true;
    }

    @Override
    public T calculate(T x, T y) {
        return mode.div(x, y);
    }
}
