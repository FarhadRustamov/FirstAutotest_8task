package implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxOptions;


public class FirefoxSetting implements WebDriverSetting {

    private final Logger logger = LogManager.getLogger(FirefoxSetting.class);

    @Override
    public FirefoxOptions configureMode() {
        logger.trace("Invoke of the configureMode method");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        switch (checkValidModeName()) {
            case HEADLESS:
                firefoxOptions.addArguments("--headless");
                break;
            case MAXIMIZED:
                firefoxOptions.addArguments("--start-maximized");
                break;
            case FULL_SCREEN:
                firefoxOptions.addArguments("--start-fullscreen");
                break;
            default:
                logger.trace("Exiting the configureMode method");
                return firefoxOptions;
        }
        logger.trace("Exiting the configureMode method");
        return firefoxOptions;
    }
}
