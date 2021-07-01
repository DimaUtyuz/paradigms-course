package expression.generic;

import expression.GenericExpression;
import expression.exceptions.MathException;
import expression.modes.*;
import expression.exceptions.ParseException;
import expression.parser.ExpressionGenericParser;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private final Map<String, Mode<?>> modes = Map.of(
            "i", new CheckedIntegerMode(),
            "d", new DoubleMode(),
            "bi", new BigIntegerMode(),
            "u", new UncheckedIntegerMode(),
            "p", new Mod1009Mode(),
            "b", new ByteMode()
    );

    public Object[][][] tabulate(String mode, String expression,
                                 int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        if (mode == null || expression == null) {
            throw new NullPointerException();
        }
        if (!modes.containsKey(mode)) {
            throw new ParseException("Unsupported mode:" + mode, -1, "");
        } else {
            return fillTab(modes.get(mode), expression, x1, x2, y1, y2, z1, z2);
        }
    }

    private <T> Object[][][] fillTab(Mode<T> mode, String expression,
                                     int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        ExpressionGenericParser<T> parser = new ExpressionGenericParser<>(mode);
        GenericExpression<T> gExpression = parser.parse(expression);
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        result[i][j][k] = gExpression.evaluate(
                                mode.value(Integer.toString(x1 + i)),
                                mode.value(Integer.toString(y1 + j)),
                                mode.value(Integer.toString(z1 + k)));
                    } catch (MathException ignore) {
                    }
                }
            }
        }
        return result;
    }

}
