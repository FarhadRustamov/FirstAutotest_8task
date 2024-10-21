package extensions;

import factories.WebDriverManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class Extensions implements BeforeAllCallback {

    private final Logger logger = LogManager.getLogger(Extensions.class);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        logger.trace("Invoke of the beforeAll method");
        new WebDriverManagerFactory().getWebDriverManager();
        logger.trace("Exiting the beforeAll method");
    }
}
