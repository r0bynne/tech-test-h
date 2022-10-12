package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class AccountHomePage extends BasePage {

    private static final By NOTIFICATIONS_ICON = By.cssSelector("[data-qa-id='webnav-globalnav-notifications']");
    private static final By DISPLAY_NAME = By.className("hui-globaluseritem__display-name");
    private static final By LOG_OUT_LINK = By.cssSelector("[data-qa-id='webnav-usermenu-logout']");


    public boolean isNotificationsIconPresent() {
        try {
            return isElementVisible(NOTIFICATIONS_ICON);
        } catch (TimeoutException e){
            return false;
        }
    }

    public void logOut() {
        findHoverAndClick(DISPLAY_NAME, LOG_OUT_LINK);
    }
}
