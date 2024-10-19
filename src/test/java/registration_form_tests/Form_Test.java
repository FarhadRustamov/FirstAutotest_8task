package registration_form_tests;

import com.github.javafaker.Faker;
import enums.LangLevel;
import factories.WebDriverFactory;
import factories.WebDriverManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Form_Test {

    private WebDriver webDriver = null;

    @BeforeAll
    public static void beforeAll() {
        new WebDriverManagerFactory().getWebDriverManager();
    }

    @BeforeEach
    public void setUp() {
        webDriver = new WebDriverFactory().getDriver();
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void checkValidData() {
        String name = Faker.instance().name().fullName();
        String email = Faker.instance().internet().emailAddress();
        String password = Faker.instance().internet().password();
        LocalDate birthdate = LocalDate.now();
        LangLevel langLevel = LangLevel.NATIVE;

        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.open();
        registrationPage.fillInNameField(name)
                .fillInEmailField(email)
                .fillInPasswordField(password)
                .fillInAndCheckConfirmPasswordField(password)
                .fillInBirthdateField(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(birthdate))
                .chooseLangLevel(langLevel)
                .clickRegButton()
                .assertOutput(name, email, birthdate.toString(), langLevel.toString().toLowerCase());
    }
}
