package config;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver startDriver() {
        String browserTypeArgument = System.getProperty("browser");
        BrowserType browserType = browserTypeArgument != null ? BrowserType.valueOf(browserTypeArgument.toUpperCase()) : BrowserType.CHROME;
        browserType.setUpDriver();
        return browserType.getDriver();
    }
}
