package expression.modes;

import expression.MyMath;
import expression.exceptions.MathException;

public class ByteMode implements Mode<Byte> {

    @Override
    public Byte add(Byte x, Byte y) {
        return (byte) (x + y);
    }

    @Override
    public Byte sub(Byte x, Byte y) {
        return (byte) (x - y);
    }

    @Override
    public Byte mul(Byte x, Byte y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        return (byte) (x * y);
    }

    @Override
    public Byte div(Byte x, Byte y) {
        if (y == 0) {
            throw new MathException("division by zero exception");
        }
        return (byte) (x / y);
    }

    @Override
    public Byte negate(Byte x) {
        return (byte) (-x);
    }

    @Override
    public Byte value(String x) {
        try {
            return (byte) Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    @Override
    public Byte zero() {
        return 0;
    }

    @Override
    public Byte abs(Byte x) {
        return (byte) MyMath.abs(x);
    }

    @Override
    public Byte square(Byte x) {
        return mul(x, x);
    }

    @Override
    public Byte mod(Byte x, Byte y) {
        if (y == 0) {
            throw new MathException("division by zero exception");
        }
        return (byte) (x % y);
    }
}
