package expression.exceptions;

public class CheckOverflow extends MathException {
    public CheckOverflow() {
        super("overflow");
    }

    public static void checkAbs(int x) {
        if (x < 0) {
            checkMultiply(x, -1);
        }
    }

    public static void checkAdd(int x, int y) {
        if (y > 0 && Integer.MAX_VALUE - y < x || y < 0 && Integer.MIN_VALUE - y > x) {
            throw new MathException("overflow");
        }
    }

    public static void checkSubtract(int x, int y) {
        if (y > 0 && Integer.MIN_VALUE + y > x || y < 0 && Integer.MAX_VALUE + y < x) {
            throw new MathException("overflow");
        }
    }

    public static void checkMultiply(int x, int y) {
        if (x > 0 && (y > 0 && x > Integer.MAX_VALUE / y || y < 0 && y < (Integer.MIN_VALUE / x)) ||
                x < 0 && (y > 0 && x < Integer.MIN_VALUE / y || y < 0 && y < (Integer.MAX_VALUE / x))) {
            throw new MathException("overflow");
        }
    }

    public static void checkDivide(int x, int y) {
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new MathException("overflow");
        }
    }
}
