package com.gmail.fatihmergunqa.testbase;

import com.gmail.fatihmergunqa.utils.Configs;
import com.gmail.fatihmergunqa.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * Base class for initializing and managing the WebDriver instance.
 * Provides setup and teardown methods for browser automation tests.
 */
public class Base {
    // WebDriver instance for controlling the browser
    public static WebDriver driver;

    /**
     * Sets up the WebDriver instance based on the browser specified in the configuration file.
     * It maximizes the browser window, sets implicit wait time, and navigates to the specified URL.
     */
    public static void setUp() {
        // Read properties from the configuration file
        Configs.readProperties(Constants.CONFIGURATION_FILEPATH);
        String browser = Configs.getProperty("browser");

        // Initialize the WebDriver based on the browser type
        driver = switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver();
            case "safari" -> new SafariDriver();
            case "firefox" -> new FirefoxDriver();
            default -> null;
        };

        // Maximize the browser window and set implicit wait time
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
        // Navigate to the specified URL
        driver.get(Configs.getProperty("url"));

        // Initialize page objects and other necessary components
        PageInitializer.initialize();
    }

    /**
     * Closes the WebDriver instance and terminates the browser session.
     * This method should be called after the tests are completed.
     */
    public static void tearDown() {
        // Quit the browser if the WebDriver instance is not null
        if (driver != null) {
            driver.quit();
        }
    }
}