package expression.exceptions;

public class NumberNotFoundException extends ParseException {
    public NumberNotFoundException(String result, int position, String str) {
        super("The number \"" + result + "\" doesn't exist", position, str);
    }
}