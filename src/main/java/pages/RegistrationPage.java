package pages;

import enums.Field;
import enums.LangLevel;
import exceptions.NoSuchFieldException;
import exceptions.NoSuchLangLevelException;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Step("Fill in the {field} field")
    public RegistrationPage fillInField(Field field, String values) { // специально делаю не(!) через шаблонный локатор, так как в противном случае каждый элемент будет вызываться отдельно через файндЭлемент(), а я хочу получить сразу все поля-элементы в Лист-е
        logger.trace("Invoke of the fillInField method");
        int fieldNumber;
        switch (field) {
            case NAME:
                fieldNumber = 0;
                break;
            case EMAIL:
                fieldNumber = 1;
                break;
            case PASSWORD:
                fieldNumber = 2;
                break;
            case CONFIRM_PASSWORD:
                fieldNumber = 3;
                break;
            case BIRTHDATE:
                fieldNumber = 4;
                break;
            default:
                throw new NoSuchFieldException();
        }
        fieldElements.get(fieldNumber).sendKeys(values);
        logger.trace("Exiting the fillInField method");
        return this;
    }

    @Step("Check whether password and confirm_password are equal")
    public RegistrationPage checkPasswordAndConfirmAreEqual() {
        logger.trace("Invoke of the checkPasswordAndConfirmAreEqual method");
        Assertions.assertEquals(fieldElements.get(2).getAttribute("value"), fieldElements.get(3).getAttribute("value"), "Passwords are not equal");
        logger.trace("Exiting the checkPasswordAndConfirmAreEqual method");
        return this;
    }

    @Step("Select the language level")
    public RegistrationPage selectLangLevel(LangLevel langLevel) {
        logger.trace("Invoke of the selectLangLevel method");
        Assertions.assertTrue(langLevelElements.get(0).isSelected(), "Selection is already made"); // проверяю, что изначально выбран пункт "Не выбран"
        langSelectionElement.click(); // до клика не проверяю что поа-ап открыт или нет, так как это невозможно в силу того что данному поп-апу нет соответствующего отдельного элемента в доме
        for (WebElement element: langLevelElements) {
            Assertions.assertTrue(waiters.checkVisibility(element), "All selections are not shown"); // поэтому после клика проверяю, что просто все уровни знания языка отображаются в UI. Кстати, до клика они тоже все отображаются, то есть isDisplayed на все элементы возвращает true
        }
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
        langLevelElements.get(levelNumber).click();
        Assertions.assertTrue(waiters.waitForCondition(ExpectedConditions.elementToBeSelected(langLevelElements.get(levelNumber))), "Level has not been selected");
        logger.trace("Exiting the selectLangLevel method");
        return this;
    }

    @Step("Click the \"Sign up\" button")
    public RegistrationPage clickRegButton() {
        logger.trace("Invoke of the clickRegButton method");
        Assertions.assertFalse(outputElement.isDisplayed(), "Output should not be shown"); // проверяю, что данных нет, но без вейтеров, так как нет необходимости в вейтерах
        registerButtonElement.click();
        Assertions.assertTrue(waiters.checkVisibility(outputElement), "Output is not shown");
        logger.trace("Exiting the clickRegButton method");
        return this;
    }

    @Step("Check the output")
    public void assertOutput(String name, String email, String birthdate, String langLevel) {
        logger.trace("Invoke of the assertOutput method");
        Assertions.assertEquals(String.format(
                "Имя пользователя: %s\n" +
                        "Электронная почта: %s\n" +
                        "Дата рождения: %s\n" +
                        "Уровень языка: %s", name, email, birthdate, langLevel), outputElement.getText(), "Output is not correct");
        logger.trace("Exiting the assertOutput method");
    }
}
