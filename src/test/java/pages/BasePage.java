package pages;

import config.DriverManager;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePage {

    public static WebDriver driver;
    public static Wait<WebDriver> wait;
    public JavascriptExecutor jstExecutor;

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final By PAGE_HEADING = By.className("page-heading");

    public BasePage() {
        driver = DriverManager.DRIVER;
        //todo not sure if this works with fluent wait. new wait might be needed every time it is used
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    void waitUntilVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    void enterText(String text, By locator) {
        driver.findElement(locator).sendKeys(text);
    }

    void clearFieldAndEnterText(String text, By locator) {
        WebElement inputField = driver.findElement(locator);
        inputField.clear();
        if(inputField.getText().length() != 0){
            inputField.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        }
        inputField.sendKeys(text);
    }

    void findAndClick(By locator) {
        driver.findElement(locator).click();
    }

    public void findHoverAndClick(By hoverElement, By clickElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(hoverElement)).build().perform();

        actions.moveToElement(driver.findElement(clickElement));
        actions.click().build().perform();
    }

    public boolean doesElementExist(By locator) {
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /* this method is implemented when the element is always present in the DOM
    to validate that the element is displayed
     */
    public boolean isElementVisible(By locator) {
        try {
            waitUntilVisible(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return driver.findElement(locator).isDisplayed();
    }

    public void selectDropdownOptionByText(String visibleText, By locator) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(visibleText);
    }

    public static String generateRandomAlphaNumericString(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageHeadingText() {
        return getText(PAGE_HEADING);
    }

    public static Map<String, String> convertSingleRowTableToMap(DataTable table) {
        List<List<String>> tableAsLists = table.asLists();
        List<String> headerList = tableAsLists.get(0);
        List<String> informationList = tableAsLists.get(1);
        Map<String, String> headerToInformation = new HashMap<>();
        for (int i = 0; i < headerList.size(); i++) {
            headerToInformation.put(headerList.get(i), informationList.get(i));
        }
        return headerToInformation;
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }
}
