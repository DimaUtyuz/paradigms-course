package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public class Add<T> extends BinaryOperation<T> {
    public Add(GenericExpression<T> left, GenericExpression<T> right, Mode<T> mode) {
        super(left, right, mode);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public boolean needsBrackets() {
        return false;
    }

    @Override
    public T calculate(T x, T y) {
        return mode.add(x, y);
    }
}
