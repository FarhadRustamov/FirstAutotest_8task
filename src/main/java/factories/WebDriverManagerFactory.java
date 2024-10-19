package factories;

import enums.Browser;
import exceptions.BrowserNotFoundException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WebDriverManagerFactory {

    private final Logger logger = LogManager.getLogger(WebDriverManagerFactory.class);

    public void getWebDriverManager() {
        logger.trace("Invoke of the getWebDriverManager method");
        switch (new BrowserNameValidator().checkBrowserName()) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
            default:
                logger.trace("Exiting the getWebDriverManager method");
                throw new BrowserNotFoundException();
        }
        logger.trace("Exiting the getWebDriverManager method");
    }
}
