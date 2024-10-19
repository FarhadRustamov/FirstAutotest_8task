package exceptions;

public class NoSuchBrowserNameException extends RuntimeException {

    public NoSuchBrowserNameException() {
        super("No such browser name");
    }
}
