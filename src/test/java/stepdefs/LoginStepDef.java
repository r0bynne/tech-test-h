package stepdefs;

import config.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountHomePage;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;


public class LoginStepDef {
    private final LoginPage loginPage = new LoginPage();
    private final AccountHomePage accountHomePage = new AccountHomePage();
    private final HomePage homePage = new HomePage();
    private final String validEmailAddress = Config.getEmailAddress();
    private final String validPassword = Config.getValidPassword();

    @When("the user attempts to login with valid credentials")
    public void userAttemptsToLoginWithValidCredentials() {
        loginPage.login(validEmailAddress, validPassword);
    }

    @When("^the user attempts to login with an invalid (EMAIL|PASSWORD)$")
    public void userAttemptsToLoginWithInvalidCredentials(InvalidCredentialsType invalidCredentialsType) {
        loginPage.login(invalidCredentialsType.emailAddress, invalidCredentialsType.password);
    }

    @Then("the user is logged in successfully")
    public void userIsLoggedInSuccessfully() {
        assertTrue("User is logged in successfully", accountHomePage.isNotificationsIconPresent());
        accountHomePage.logOut();
    }

    @When("^the user attempts to log in without a (email|password)$")
    public void userAttemptsToLogInWithoutACredential(String missingCredential) {
        loginPage.loginWithout(missingCredential);
    }

    @Then("the user receives an error message")
    public void userReceivesAnErrorMessage() {
        assertTrue("Error message is present", loginPage.isLoginErrorPresent());
    }

    @And("the log in button is disabled")
    public void logInButtonIsDisabled() {
        assertTrue("Log in button is disabled", loginPage.isLoginButtonDisabled());
    }

    @Given("the user has logged in")
    public void userHasLoggedIn() {
        loginPage.login(validEmailAddress, validPassword);
        assertTrue("User is logged in successfully", accountHomePage.isNotificationsIconPresent());
    }

    @When("the user logs out")
    public void userLogsOut() {
        accountHomePage.logOut();
    }

    @Then("the user can log in again")
    public void userCanLogInAgain() {
        homePage.navigateToLoginPage();
        loginPage.login(validEmailAddress, validPassword);
        assertTrue("User is logged in successfully", accountHomePage.isNotificationsIconPresent());
        accountHomePage.logOut();
    }

    @When("the user enters a correct password")
    public void userEntersACorrectPassword() {
        loginPage.isLoginErrorPresent();
        loginPage.enterValidPassword();
    }

    private enum InvalidCredentialsType {
        EMAIL("notanemail", Config.getValidPassword()),
        PASSWORD(Config.getEmailAddress(), "fakeP455w0rd");

        private final String emailAddress;
        private final String password;

        InvalidCredentialsType(String emailAddress, String password) {
            this.emailAddress = emailAddress;
            this.password = password;
        }
    }
}
