package pages;

import config.Config;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.cssSelector("[data-qa-id='email-input']");
    private static final By PASSWORD_INPUT = By.cssSelector("[data-qa-id='password-input']");
    private static final By LOG_IN_BUTTON = By.cssSelector("[data-qa-id='login-btn']");
    private static final By LOG_IN_ERROR = By.cssSelector("[data-qa-id='error-display']");


    public void login(String emailAddress, String password) {
        enterText(emailAddress, EMAIL_INPUT);
        enterText(password, PASSWORD_INPUT);
        findAndClick(LOG_IN_BUTTON);
    }

    public boolean isLoginErrorPresent() {
        return isElementVisible(LOG_IN_ERROR);
    }

    public void loginWithout(String missingCredential) {
        if (missingCredential.equals("email")){
            login("", Config.getValidPassword());
        } else if (missingCredential.equals("password")){
            login("robynneesmith@gmail.com", "");
        } else {
            throw new RuntimeException("Invalid missing credential provided: " + missingCredential);
        }
    }

    public boolean isLoginButtonDisabled() {
        return !driver.findElement(LOG_IN_BUTTON).isEnabled();
    }

    public void enterValidPassword() {
        clearFieldAndEnterText(Config.getValidPassword(), PASSWORD_INPUT);
        findAndClick(LOG_IN_BUTTON);
    }
}
