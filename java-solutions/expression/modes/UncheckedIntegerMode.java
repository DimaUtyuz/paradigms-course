package expression.modes;

public class UncheckedIntegerMode extends CheckedIntegerMode {
    public UncheckedIntegerMode() {
        check = false;
    }
}
