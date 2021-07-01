package expression.exceptions;

public class ParseException extends Exception {
    public ParseException(String message, int position, String str) {
        super(position == -1 ? message : message + " at position " + position + "\n"
                + str + "\n"
                + "_".repeat(position - 1) + "^" + "_".repeat(str.length() - position));
    }
}
