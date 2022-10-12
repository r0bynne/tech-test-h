package config;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    public static final WebDriver DRIVER = DriverFactory.startDriver();

    private static final Thread THREAD = new Thread(DRIVER::quit);

    static {
        Runtime.getRuntime().addShutdownHook(THREAD);
    }

}
