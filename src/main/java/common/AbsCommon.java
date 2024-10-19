package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;


public abstract class AbsCommon {

    private final Logger logger = LogManager.getLogger(AbsCommon.class);

    protected WebDriver webDriver;
    protected Waiters waiters;

    public AbsCommon(WebDriver webDriver) {
        logger.trace("Invoke of the AbsCommon constructor");
        this.webDriver = webDriver;
        waiters = new Waiters(webDriver);
        PageFactory.initElements(webDriver, this);
        logger.trace("Exiting the AbsCommon constructor");
    }
}
