package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

import java.util.Objects;

public class Variable<T> implements GenericExpression<T> {
    private final String name;
    private final Mode<T> mode;

    public Variable(String name, Mode<T> mode) {
        this.name = name;
        this.mode = mode;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> mode.zero();
        };
    }
}
