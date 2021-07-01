package expression.parser;

import expression.GenericExpression;
import expression.exceptions.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface GenericParser<T> {
    GenericExpression<T> parse(String expression) throws ParseException;
}