package factories;

import enums.Browser;
import exceptions.BrowserNotFoundException;
import exceptions.NoSuchBrowserNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class BrowserNameValidator {

    private final String BROWSER_NAME = System.getProperty("browser", "chrome").trim().toUpperCase();
    private final Logger logger = LogManager.getLogger(BrowserNameValidator.class);

    public Browser checkBrowserName() {
        logger.trace("Invoke of the checkBrowserName method");
        if (Arrays.stream(Browser.values()).map(Object::toString).anyMatch(s -> s.equals(BROWSER_NAME))) {
            logger.trace("Exiting the checkBrowserName method");
            return Browser.valueOf(BROWSER_NAME);
        } else {
            logger.trace("Exiting the checkBrowserName method");
            throw new NoSuchBrowserNameException();
        }
    }
}
