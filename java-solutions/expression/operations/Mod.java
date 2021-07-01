package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public class Mod<T> extends BinaryOperation<T> {
    public Mod(GenericExpression<T> left, GenericExpression<T> right, Mode<T> mode) {
        super(left, right, mode);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "mod";
    }

    @Override
    public boolean needsBrackets() {
        return false;
    }

    @Override
    public T calculate(T x, T y) {
        return mode.mod(x, y);
    }
}
