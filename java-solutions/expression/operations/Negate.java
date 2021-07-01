package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public class Negate<T> extends UnaryOperation<T> {
    public Negate(GenericExpression<T> expression, Mode<T> mode) {
        super(expression, mode);
    }

    @Override
    public T calculate(T x) {
        return mode.negate(x);
    }

    @Override
    public String getSymbol() {
        return "-";
    }
}
