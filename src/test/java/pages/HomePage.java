package pages;

import config.Config;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By LOG_IN_BUTTON = By.cssSelector("[data-qa-id='login']");
//    private final By FIRST_FEATURED_ITEM = By.cssSelector("#homefeatured>li:first-child");


    public void goTo() {
        driver.navigate().to(Config.getBaseURL());
    }

    public void navigateToLoginPage() {
        waitUntilVisible(LOG_IN_BUTTON);
        findAndClick(LOG_IN_BUTTON);
    }

}
