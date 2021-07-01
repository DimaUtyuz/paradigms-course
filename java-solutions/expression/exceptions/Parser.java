package expression.exceptions;

import expression.GenericExpression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<T> {
    GenericExpression<T> parse(String expression) throws ParseException;
}