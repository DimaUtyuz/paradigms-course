package expression.operations;

import expression.GenericExpression;

import java.util.Objects;

public final class Const<T> implements GenericExpression<T> {
    private final T number;

    public Const(T number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return number;
    }
}
