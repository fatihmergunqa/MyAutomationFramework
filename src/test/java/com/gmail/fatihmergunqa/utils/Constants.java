package com.gmail.fatihmergunqa.utils;

/**
 * Utility class that provides constants used across the application.
 * This class contains configuration file paths, screenshot paths, and
 * commonly used Selenium-related wait times. All constants are static and final,
 * making them accessible globally without the need to instantiate the class.
 */
public class Constants {
    // Constants
    public static final String CONFIGURATION_FILEPATH =
            System.getProperty("user.dir") + "/src/test/resources/configs/configuration.properties";
    public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir") + "/target/screenshots/";

    // Selenium Constants
    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int EXPLICIT_WAIT_TIME = 30;
}
