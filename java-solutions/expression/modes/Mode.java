package expression.modes;

public interface Mode<T>  {
    T add(T x, T y);
    T sub(T x, T y);
    T mul(T x, T y);
    T div(T x, T y);
    T negate(T x);
    T value(String x);
    T zero();
    T abs(T x);
    T square(T x);
    T mod(T x, T y);
}
