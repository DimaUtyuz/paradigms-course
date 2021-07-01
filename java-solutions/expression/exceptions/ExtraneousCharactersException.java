package expression.exceptions;

public class ExtraneousCharactersException extends ParseException {
    public ExtraneousCharactersException(int position, String str) {
        super("Extraneous characters were found", position, str);
    }
}