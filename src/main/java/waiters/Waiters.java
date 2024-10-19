package waiters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Waiters {

    private final WebDriver webDriver;
    private final int waiterTimeoutSec = Integer.parseInt(System.getProperty("waiter.timeout", "5"));
    private final Logger logger = LogManager.getLogger(Waiters.class);

    public Waiters(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public boolean waitForCondition(ExpectedCondition expectedCondition) {
        logger.trace("Invoke of the waitForCondition method");
        try {
            new WebDriverWait(webDriver, Duration.ofSeconds(waiterTimeoutSec)).until(expectedCondition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        } finally {
            logger.trace("Exiting the waitForCondition method");
        }
    }

    public boolean checkVisibility(WebElement webElement) {
        logger.trace("Invoke of the checkVisibility method");
        logger.trace("Exiting the checkVisibility method");
        return waitForCondition(ExpectedConditions.visibilityOf(webElement));
    }
}
