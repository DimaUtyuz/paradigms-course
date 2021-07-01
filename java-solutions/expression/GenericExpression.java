package expression;

import expression.exceptions.MathException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface GenericExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z) throws MathException;
}
