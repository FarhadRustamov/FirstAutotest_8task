package remote;

import implementations.ChromeSetting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoteWebDriverSettings {

    private final Logger logger = LogManager.getLogger(RemoteWebDriverSettings.class);
    private final String BROWSER_NAME = System.getProperty("browser", "chrome");
    private final String BROWSER_VERSION = System.getProperty("browser.version", "124.0");

    public DesiredCapabilities configureCapabilities() {
        logger.trace("Invoke of the configureCapabilities method");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(BROWSER_NAME);
        desiredCapabilities.setVersion(BROWSER_VERSION);
        if (desiredCapabilities.getBrowserName().equals("chrome")) {
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, new ChromeSetting().configureMode());
        }
        desiredCapabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Farhad's tests");
            /* How to set session timeout */
            put("sessionTimeout", "15m");
            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});
            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            /* How to enable video recording */
            put("enableVideo", false);
        }});
        logger.trace("Exiting the configureCapabilities method");
        return desiredCapabilities;
    }
}
