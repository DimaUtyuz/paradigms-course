package expression;

public class MyMath {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return abs(a);
    }

    public static int mod(int x, int mod) {
        return (x % mod + mod) % mod;
    }

    public static int abs(int x) {
        return x > 0 ? x: -x;
    }

    public static int sqrt(int x) {
        return (int) Math.sqrt(x);
    }

    public static int sign(int x) {
        return Integer.compare(x, 0);
    }

    public static int max(int a, int b) {
        return a > b ? a: b;
    }

    public static int min(int a, int b) {
        return a < b ? a: b;
    }
}
