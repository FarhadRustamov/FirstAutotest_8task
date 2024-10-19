package pages;

import enums.LangLevel;
import exceptions.NoSuchLangLevelException;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class RegistrationPage extends AbsBasePage {

    private final Logger logger = LogManager.getLogger(RegistrationPage.class);

    @FindBy(css = "input[id]")
    private List<WebElement> fieldElements; // 0 - name; 1 - email; 2 - password; 3 - confirm_password; 4 - birthdate
    @FindBy(css = "select#language_level")
    private WebElement langSelectionElement;
    @FindBy(css = "option[value]")
    private List<WebElement> langLevelElements; // 0 - not selected; 1 - beginner; 2 - intermediate; 3 - advanced; 4 - native
    @FindBy(css = "input[type='submit']")
    private WebElement registerButtonElement;
    @FindBy(css = "div#output")
    private WebElement outputElement;

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver, "/form.html");
    }

    @Step("Заполнить поле с именем")
    public RegistrationPage fillInNameField(String values) {
        logger.trace("Invoke of the fillInNameField method");
        fieldElements.get(0).sendKeys(values);
        logger.trace("Exiting the fillInNameField method");
        return this;
    }

    @Step("Заполнить поле с почтой")
    public RegistrationPage fillInEmailField(String values) {
        logger.trace("Invoke of the fillInEmailField method");
        fieldElements.get(1).sendKeys(values);
        logger.trace("Exiting the fillInEmailField method");
        return this;
    }

    @Step("Заполнить поле с паролем")
    public RegistrationPage fillInPasswordField(String values) {
        logger.trace("Invoke of the fillInPasswordField method");
        fieldElements.get(2).sendKeys(values);
        logger.trace("Exiting the fillInPasswordField method");
        return this;
    }

    @Step("Заполнить поле подтверждения пароля")
    public RegistrationPage fillInAndCheckConfirmPasswordField(String values) {
        logger.trace("Invoke of the fillInConfirmPasswordField method");
        fieldElements.get(3).sendKeys(values);
        Assertions.assertEquals(fieldElements.get(2).getAttribute("value"), fieldElements.get(3).getAttribute("value"), "Пароли не совпадают");
        logger.trace("Exiting the fillInConfirmPasswordField method");
        return this;
    }

    @Step("Внести дату в поле с календарем")
    public RegistrationPage fillInBirthdateField(String values) {
        logger.trace("Invoke of the fillInBirthdateField method");
        fieldElements.get(4).sendKeys(values);
        logger.trace("Exiting the fillInBirthdateField method");
        return this;
    }

    @Step("Выбрать уровень знания языка")
    public RegistrationPage chooseLangLevel(LangLevel langLevel) {
        logger.trace("Invoke of the chooseLangLevel method");
        langSelectionElement.click(); // до клика не проверяю, так как нет необходимости - isDisplayed еще до клика возвращает true - как будто поп-ап с уровнями уже открыт
        Assertions.assertTrue(waiters.checkVisibility(langLevelElements.get(0)), "Поп-ап не открылся");
        int levelNumber;
        switch (langLevel) {
            case NOT_SELECTED:
                levelNumber = 0;
                break;
            case BEGINNER:
                levelNumber = 1;
                break;
            case INTERMEDIATE:
                levelNumber = 2;
                break;
            case ADVANCED:
                levelNumber = 3;
                break;
            case NATIVE:
                levelNumber = 4;
                break;
            default:
                throw new NoSuchLangLevelException();
        }
        Assertions.assertTrue(langLevelElements.get(0).isSelected()); // проверяю, что изначально уровень не выбран
        langLevelElements.get(levelNumber).click();
        Assertions.assertTrue(langLevelElements.get(levelNumber).isSelected(), "Выбор не сохранился"); // проверяю, что мой выбор сохранился, но без вейтера, так как выбор происходит моментально
        logger.trace("Exiting the chooseLangLevel method");
        return this;
    }

    @Step("Нажать кнопку \"Зарегистрироваться\"")
    public RegistrationPage clickRegButton() {
        logger.trace("Invoke of the clickRegButton method");
        Assertions.assertFalse(outputElement.isDisplayed(), "Данные видны, а не должны"); // проверяю, что данных нет, но без вейтеров, так как нет необходимости в вейтерах
        registerButtonElement.click();
        Assertions.assertTrue(waiters.checkVisibility(outputElement), "Данные не видны, а должны быть видны");
        logger.trace("Exiting the clickRegButton method");
        return this;
    }

    @Step("Проверка совпадения данных")
    public void assertOutput(String name, String email, String birthdate, String langLevel) {
        logger.trace("Invoke of the assertOutput method");
        Assertions.assertEquals(String.format(
                "Имя пользователя: %s\n" +
                        "Электронная почта: %s\n" +
                        "Дата рождения: %s\n" +
                        "Уровень языка: %s", name, email, birthdate, langLevel), outputElement.getText(), "Данные не совпадают");
        logger.trace("Exiting the assertOutput method");
    }
}
