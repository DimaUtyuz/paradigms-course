package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

public class Square<T> extends UnaryOperation<T> {
    public Square(GenericExpression<T> expression, Mode<T> mode) {
        super(expression, mode);
    }

    @Override
    public T calculate(T x) {
        return mode.square(x);
    }

    @Override
    public String getSymbol() {
        return "square";
    }
}
