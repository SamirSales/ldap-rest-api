package oi.github.samirsales.demoLDAP.domain.exception;

@SuppressWarnings("unused")
public class IllegalValueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalValueException() {
        super("Illegal value");
    }

    public IllegalValueException(String message) {
        super(message);
    }
}
