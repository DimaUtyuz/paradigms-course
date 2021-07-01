package expression.modes;

public class DoubleMode implements Mode<Double>{

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double sub(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double mul(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double div(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public Double value(String x) {
        try {
            return Double.parseDouble(x);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    @Override
    public Double zero() {
        return (double) 0;
    }

    @Override
    public Double abs(Double x) {
        return x > 0 ? x: -x;
    }

    @Override
    public Double square(Double x) {
        return mul(x, x);
    }

    @Override
    public Double mod(Double x, Double y) {
        return x % y;
    }
}
