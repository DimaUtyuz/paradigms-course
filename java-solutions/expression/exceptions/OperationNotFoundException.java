package expression.exceptions;

public class OperationNotFoundException extends ParseException {
    public OperationNotFoundException(String operation, int position, String str) {
        super("The operation \"" + operation + "\" doesn't exist", position, str);
    }
}