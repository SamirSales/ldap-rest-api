package oi.github.samirsales.demoLDAP.domain.exception;

@SuppressWarnings("unused")
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException() {
        super("Entity not found.");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
