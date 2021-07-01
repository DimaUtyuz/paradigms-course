package expression.operations;

import expression.GenericExpression;
import expression.modes.Mode;

import java.util.Objects;

public abstract class BinaryOperation<T> implements GenericExpression<T> {
    private final GenericExpression<T> left;
    private final GenericExpression<T> right;
    protected final Mode<T> mode;

    public BinaryOperation(GenericExpression<T> left, GenericExpression<T> right, Mode<T> mode) {
        this.left = left;
        this.right = right;
        this.mode = mode;
    }
    public abstract T calculate(T x, T y);
    public abstract String getSymbol();
    public abstract int getPriority();
    public abstract boolean needsBrackets();

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return addBrackets(left.toString() + " " + getSymbol() + " " + right.toString(), true);
    }

    @Override
    public String toMiniString() {
        return addBrackets(left.toMiniString(), checkPriorityBrackets(left)) +
                " " + getSymbol() + " " +
                addBrackets(right.toMiniString(), checkPriorityBrackets(right) || checkNeedsBrackets(right));
    }

    private boolean checkPriorityBrackets(GenericExpression<T> expression) {
        if (expression instanceof BinaryOperation) {
            return ((BinaryOperation<T>) expression).getPriority() < getPriority();
        } else {
            return false;
        }
    }

    private boolean checkNeedsBrackets(GenericExpression<T> expression) {
        if (expression instanceof BinaryOperation) {
            BinaryOperation<T> binaryOperation = (BinaryOperation<T>) expression;
            return binaryOperation.getPriority() == getPriority()
                    && (this.needsBrackets()
                        || this.getPriority() == 1 && binaryOperation.needsBrackets());
        } else {
            return false;
        }
    }

    private String addBrackets(String expression, boolean flag) {
        if (flag) {
            return "(" + expression + ")";
        } else {
            return expression;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, getClass());
    }
}
