package expression.exceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    boolean hasNext(int d);
    char next();
    char next(int d);
    int getPosition();
}
