package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum BrowserType {

    CHROME {
        void setUpDriver() {
            WebDriverManager.chromedriver().setup();
        }

        WebDriver getDriver() {
            return new ChromeDriver();
        }
    },

    FIREFOX {
        void setUpDriver() {
            WebDriverManager.firefoxdriver().setup();
        }

        WebDriver getDriver() {
            return new FirefoxDriver();
        }
    },

    EDGE {
        void setUpDriver() {
            WebDriverManager.edgedriver().setup();
        }

        WebDriver getDriver() {
            return new EdgeDriver();
        }
    };

    abstract void setUpDriver();

    abstract WebDriver getDriver();

}
