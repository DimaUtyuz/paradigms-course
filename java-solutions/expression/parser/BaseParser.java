package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class BaseParser {
    public static final char END = '\0';
    private CharSource source;
    protected char ch = 0xffff;

    protected void setSource(final CharSource source) {
        this.source = source;
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : END;
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(String expected) {
        for (int i = 0; i < expected.length(); i++) {
            if (!source.hasNext(i) || source.next(i) != expected.charAt(i)) {
                return false;
            }
        }
        if (Character.isLetter(source.next(expected.length() - 1)) && source.hasNext(expected.length()) &&
                (Character.isLetter(source.next(expected.length())) ||
                        Character.isDigit(source.next(expected.length())))) {
            return false;
        }
        expect(expected);
        return true;
    }

    protected void expect(final char c) {
        nextChar();
    }

    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected boolean isDigit() {
        return between('0', '9');
    }

    protected boolean isVariable(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '$' || c == '_';
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }

    protected int getPosition() {
        return source.getPosition();
    }

    protected String getStr() {
        return source.getStr();
    }
}
