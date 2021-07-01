package expression.modes;

import expression.MyMath;
import expression.exceptions.MathException;

public class Mod1009Mode implements Mode<Integer>{
    private final int mod = 1009;
    private final int[] inv = fill();

    private int[] fill() {
        int[] result = new int[mod];
        for (int i = 0; i < 1009; i++) {
            for (int j = 0; j < 1009; j++) {
                if (i * j % mod == 1) {
                    result[i] = j;
                }
            }
        }
        return result;
    }

    @Override
    public Integer add(Integer x, Integer y) {
        return MyMath.mod(x + y, mod);
    }

    @Override
    public Integer sub(Integer x, Integer y) {
        return MyMath.mod(x - y, mod);
    }

    @Override
    public Integer mul(Integer x, Integer y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        return MyMath.mod(x * y, mod);
    }

    @Override
    public Integer div(Integer x, Integer y) {
        if (y == 0) {
            throw new MathException("division by zero exception");
        }
        return MyMath.mod(mul(x, inv[y]), mod);
    }

    @Override
    public Integer negate(Integer x) {
        return MyMath.mod(-x,  mod);
    }

    @Override
    public Integer value(String x) {
        try {
            return MyMath.mod(Integer.parseInt(x), mod);
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
        return MyMath.mod(MyMath.abs(x), mod);
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
        return MyMath.mod(x % y, mod);
    }
}
