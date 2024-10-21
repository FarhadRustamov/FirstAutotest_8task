package exceptions;

public class NoSuchModeException extends RuntimeException {

    public NoSuchModeException() {
        super("No such mode for the browser");
    }
}
