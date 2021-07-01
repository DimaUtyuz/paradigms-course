package expression.exceptions;

public class VariableNotFoundException extends ParseException {
    public VariableNotFoundException(String result, int position, String str) {
        super("The variable \"" + result + "\" doesn't exist", position, str);
    }
}