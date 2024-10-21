package exceptions;

public class NoSuchFieldException extends RuntimeException {

    public NoSuchFieldException() {
        super("No such field");
    }
}
