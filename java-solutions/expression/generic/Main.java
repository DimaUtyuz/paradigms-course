package expression.generic;

public class Main {
    public static void main(String[] args) {
        GenericTabulator genT = new GenericTabulator();
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]);
            }
            Object[][][] ans = genT.tabulate(args[0].substring(1), sb.toString(),
                    -2, 2, -2, 2, -2, 2);
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    for (int k = -2; k <= 2; k++) {
                        System.out.println(i + " " + j + " " + k + ": " + ans[i + 2][j + 2][k + 2]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
