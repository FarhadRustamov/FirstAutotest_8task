package implementations;

import enums.Mode;
import exceptions.NoSuchModeException;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.Arrays;

public interface WebDriverSetting {

    String BROWSER_MODE = System.getProperty("mode", "maximized").trim().toUpperCase();

    AbstractDriverOptions configureMode();

    default Mode checkValidModeName() {
        if (Arrays.stream(Mode.values()).map(Object::toString).anyMatch(s -> s.equals(BROWSER_MODE))) {
            return Mode.valueOf(BROWSER_MODE);
        } else {
            throw new NoSuchModeException();
        }
    }
}
