package expression.modes;

import expression.exceptions.MathException;

import java.math.BigInteger;

public class BigIntegerMode implements Mode<BigInteger>{

    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger sub(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger mul(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger div(BigInteger x, BigInteger y) {
        if (y.compareTo(BigInteger.valueOf(0)) == 0) {
            throw new MathException("division by zero exception");
        }
        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger value(String x) {
        try {
            return new BigInteger(x);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    @Override
    public BigInteger zero() {
        return BigInteger.valueOf(0);
    }

    @Override
    public BigInteger abs(BigInteger x) {
        return x.abs();
    }

    @Override
    public BigInteger square(BigInteger x) {
        return mul(x, x);
    }

    @Override
    public BigInteger mod(BigInteger x, BigInteger y) {
        if (y.compareTo(BigInteger.valueOf(0)) == 0) {
            throw new MathException("division by zero exception");
        }
        try {
            return x.mod(y);
        } catch (ArithmeticException e) {
            throw new MathException(e.getMessage());
        }
    }
}
