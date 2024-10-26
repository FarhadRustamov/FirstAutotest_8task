package pages;

import common.AbsCommon;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public abstract class AbsBasePage<T> extends AbsCommon {

    private String BASE_URL = System.getProperty("base.url", "https://otus.home.kartushin.su");
    private final String PATH;
    private final Logger logger = LogManager.getLogger(AbsBasePage.class);

    public AbsBasePage(WebDriver webDriver, String path) {
        super(webDriver);
        logger.trace("Invoke of the AbsBasePage constructor");
        this.PATH = path;
        logger.trace("Exiting the AbsBasePage constructor");
    }

    @Step("Open the page")
    public T open() {
        logger.trace("Invoke of the open method");
        BASE_URL = BASE_URL.endsWith("/") ? BASE_URL.substring(0, BASE_URL.length() - 1) : BASE_URL;
        webDriver.get(BASE_URL + PATH);
        logger.trace("Exiting the open method");
        return (T) this;
    }
}
