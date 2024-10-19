package exceptions;

public class BrowserNotFoundException extends RuntimeException {

    public BrowserNotFoundException() {
        super("Browser is not supported");
    }
}
