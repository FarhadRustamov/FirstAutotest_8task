package factories;

import exceptions.BrowserNotSupportedException;
import exceptions.NoSuchExecutionEnvException;
import implementations.ChromeSetting;
import implementations.EdgeSetting;
import implementations.FirefoxSetting;
import remote.RemoteWebDriverSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class WebDriverFactory {

    private final Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private final String BROWSER_NAME = System.getProperty("browser", "chrome");
    private final String EXECUTION_ENV = System.getProperty("executionEnv", "remote");
    private final String REMOTE_SERVER_URL = System.getProperty("remoteServerUrl", "http://193.104.57.173/wd/hub");

    public WebDriver getDriver() {
        logger.trace("Invoke of the getDriver method");
        WebDriver webDriver;
        switch (EXECUTION_ENV) {
            case "local":
                webDriver = getLocalDriver();
                break;
            case "remote":
                webDriver = getRemoteDriver();
                break;
            default:
                logger.trace("Exiting the getDriver method");
                throw new NoSuchExecutionEnvException();
        }
        logger.trace("Exiting the getDriver method");
        return webDriver;
    }

    public WebDriver getLocalDriver() {
        logger.trace("Invoke of the getLocalDriver method");
        WebDriver webDriver;
        switch (BROWSER_NAME.trim().toLowerCase()) {
            case "chrome":
                webDriver = new ChromeDriver(new ChromeSetting().configureMode());
                break;
            case "edge":
                webDriver = new EdgeDriver(new EdgeSetting().configureMode());
                break;
            case "firefox":
                webDriver = new FirefoxDriver(new FirefoxSetting().configureMode());
                break;
            default:
                logger.trace("Exiting the getLocalDriver method");
                throw new BrowserNotSupportedException(BROWSER_NAME);
        }
        logger.trace("Exiting the getLocalDriver method");
        return webDriver;
    }

    public WebDriver getRemoteDriver() {
        logger.trace("Invoke of the getRemoteDriver method");
        try {
            logger.trace("Exiting the getRemoteDriver method");
            return new RemoteWebDriver(new URL(REMOTE_SERVER_URL), new RemoteWebDriverSettings().configureCapabilities());
        } catch (MalformedURLException e) {
            logger.trace("Exiting the getRemoteDriver method");
            throw new RuntimeException(e);
        }
    }
}
