package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public class Abs<T> extends UnaryOperation<T> {
    public Abs(GenericExpression<T> expression, Mode<T> mode) {
        super(expression, mode);
    }

    @Override
    public T calculate(T x) {
        return mode.abs(x);
    }

    @Override
    public String getSymbol() {
        return "abs";
    }
}
