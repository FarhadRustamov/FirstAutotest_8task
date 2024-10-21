package factories;

import exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WebDriverManagerFactory {

    private final String BROWSER_NAME = System.getProperty("browser", "chrome");
    private final Logger logger = LogManager.getLogger(WebDriverManagerFactory.class);

    public void getWebDriverManager() {
        logger.trace("Invoke of the getWebDriverManager method");
        switch (BROWSER_NAME) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            default:
                logger.trace("Exiting the getWebDriverManager method");
                throw new BrowserNotSupportedException(BROWSER_NAME);
        }
        logger.trace("Exiting the getWebDriverManager method");
    }
}
