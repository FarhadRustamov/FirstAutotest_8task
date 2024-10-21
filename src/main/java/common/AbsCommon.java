package common;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;


public abstract class AbsCommon {

    private final Logger logger = LogManager.getLogger(AbsCommon.class);

    protected WebDriver webDriver = null;
    protected Waiters waiters = null;
    protected Faker faker = null;

    public AbsCommon(WebDriver webDriver) {
        logger.trace("Invoke of the AbsCommon constructor");
        this.webDriver = webDriver;
        waiters = new Waiters(webDriver);
        faker = new Faker();
        PageFactory.initElements(webDriver, this);
        logger.trace("Exiting the AbsCommon constructor");
    }

    public Faker getFaker() {
        return faker;
    }
}
