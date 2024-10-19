package factories;

import enums.Browser;
import exceptions.BrowserNotFoundException;
import implementations.ChromeSetting;
import implementations.EdgeSetting;
import implementations.FirefoxSetting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class WebDriverFactory {

    private final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public WebDriver getDriver() {
        logger.trace("Invoke of the getDriver method");
        WebDriver webDriver;
        switch (new BrowserNameValidator().checkBrowserName()) {
            case CHROME:
                webDriver = new ChromeDriver(new ChromeSetting().configureMode());
                break;
            case EDGE:
                webDriver = new EdgeDriver(new EdgeSetting().configureMode());
                break;
            case FIREFOX:
                webDriver = new FirefoxDriver(new FirefoxSetting().configureMode());
                break;
            default:
                logger.trace("Exiting the getDriver method");
                throw new BrowserNotFoundException();
        }
        logger.trace("Exiting the getDriver method");
        return webDriver;
    }
}
