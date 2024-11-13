package exceptions;

public class NoSuchExecutionEnvException extends RuntimeException {
    public NoSuchExecutionEnvException() {
        super("No such execution environment");
    }
}
