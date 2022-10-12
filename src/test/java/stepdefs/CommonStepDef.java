package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import pages.AccountHomePage;
import pages.HomePage;
import pages.LoginPage;

public class CommonStepDef {
    private HomePage homePage = new HomePage();
    private AccountHomePage accountHomePage = new AccountHomePage();

    @Given("the user is on the log in page")
    public void userIsOnTheLogInPage() {
        homePage.goTo();
        homePage.navigateToLoginPage();
    }

//    @After
//    public void logUserOutIfNeeded(){
//        if (accountHomePage.isNotificationsIconPresent()){
//            accountHomePage.logOut();
//        }
//    }
}
