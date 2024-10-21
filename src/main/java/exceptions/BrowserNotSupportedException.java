package exceptions;

public class BrowserNotSupportedException extends RuntimeException {

    public BrowserNotSupportedException(String browserName) {
        super(String.format("\"%s\" browser is not supported", browserName));
    }
}
