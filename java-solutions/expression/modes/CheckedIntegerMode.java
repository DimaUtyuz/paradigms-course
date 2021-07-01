package expression.modes;

import expression.MyMath;
import expression.exceptions.*;

public class CheckedIntegerMode implements Mode<Integer> {
    boolean check = true;

    @Override
    public Integer add(Integer x, Integer y) {
        if (check) {
            CheckOverflow.checkAdd(x, y);
        }
        return x + y;
    }

    @Override
    public Integer sub(Integer x, Integer y) {
        if (check) {
            CheckOverflow.checkSubtract(x, y);
        }
        return x - y;
    }

    @Override
    public Integer mul(Integer x, Integer y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        if (check) {
            CheckOverflow.checkMultiply(x, y);
        }
        return x * y;
    }

    @Override
    public Integer div(Integer x, Integer y) {
        if (check) {
            CheckOverflow.checkDivide(x, y);
        }
        if (y == 0) {
            throw new MathException("division by zero exception");
        }
        return x / y;
    }

    @Override
    public Integer negate(Integer x) {
        if (check) {
            CheckOverflow.checkSubtract(0, x);
        }
        return -x;
    }

    @Override
    public Integer value(String x) {
        try {
            return Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    @Override
    public Integer zero() {
        return 0;
    }

    @Override
    public Integer abs(Integer x) {
        if (check) {
            CheckOverflow.checkAbs(x);
        }
        return MyMath.abs(x);
    }

    @Override
    public Integer square(Integer x) {
        return mul(x, x);
    }

    @Override
    public Integer mod(Integer x, Integer y) {
        if (y == 0) {
            throw new MathException("division by zero exception");
        }
        return x % y;
    }
}
