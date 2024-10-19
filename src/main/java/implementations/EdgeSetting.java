package implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.edge.EdgeOptions;


public class EdgeSetting implements WebDriverSetting {

    private final Logger logger = LogManager.getLogger(EdgeSetting.class);

    @Override
    public EdgeOptions configureMode() {
        logger.trace("Invoke of the configureMode method");
        EdgeOptions edgeOptions = new EdgeOptions();
        switch (checkValidModeName()) {
            case HEADLESS:
                edgeOptions.addArguments("--headless");
                break;
            case MAXIMIZED:
                edgeOptions.addArguments("--start-maximized");
                break;
            case FULL_SCREEN:
                edgeOptions.addArguments("--start-fullscreen");
                break;
            default:
                logger.trace("Exiting the configureMode method");
                return edgeOptions;
        }
        logger.trace("Exiting the configureMode method");
        return edgeOptions;
    }
}
