package implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeSetting implements WebDriverSetting {

    private final Logger logger = LogManager.getLogger(ChromeSetting.class);

    @Override
    public ChromeOptions configureMode() {
        logger.trace("Invoke of the configureMode method");
        ChromeOptions chromeOptions = new ChromeOptions();
        switch (checkValidModeName()) {
            case HEADLESS:
                chromeOptions.addArguments("--headless");
                break;
            case MAXIMIZED:
                chromeOptions.addArguments("--start-maximized");
                break;
            case FULL_SCREEN:
                chromeOptions.addArguments("--start-fullscreen");
                break;
            default:
                logger.trace("Exiting the configureMode method");
                return chromeOptions;
        }
        logger.trace("Exiting the configureMode method");
        return chromeOptions;
    }
}
