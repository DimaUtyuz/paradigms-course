package expression.exceptions;

public class ParenthesisNotFoundException extends ParseException {
    public ParenthesisNotFoundException(char ch, int position, String str) {
        super("Expected ')', found '" + ch + "'", position, str);
    }
}