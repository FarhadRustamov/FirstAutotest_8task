package registration_form_tests;

import enums.Field;
import enums.LangLevel;
import extensions.Extensions;
import factories.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(Extensions.class)
public class Form_Test {

    private WebDriver webDriver = null;

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
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        String name = registrationPage.getFaker().name().fullName();
        String email = registrationPage.getFaker().internet().emailAddress();
        String password = registrationPage.getFaker().internet().password();
        LocalDate birthdate = LocalDate.now();
        LangLevel langLevel = LangLevel.NATIVE;

        registrationPage.open();
        registrationPage.fillInField(Field.NAME, name)
                .fillInField(Field.EMAIL, email)
                .fillInField(Field.PASSWORD, password)
                .fillInField(Field.CONFIRM_PASSWORD, password)
                .checkPasswordAndConfirmAreEqual()
                .fillInField(Field.BIRTHDATE, DateTimeFormatter.ofPattern("dd-MM-yyyy").format(birthdate))
                .selectLangLevel(langLevel)
                .clickRegButton()
                .assertOutput(name, email, birthdate.toString(), langLevel.toString().toLowerCase());
    }
}
