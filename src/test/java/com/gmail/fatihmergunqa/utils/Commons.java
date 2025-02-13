package com.gmail.fatihmergunqa.utils;

import com.gmail.fatihmergunqa.testbase.API;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * Utility class for common web actions using Selenium WebDriver.
 * Provides methods for interacting with elements, handling alerts, and working with JavaScriptExecutor.
 * It extends the PageInitializer class to inherit WebDriver setup, teardown functionality and, Page Element objects.
 */
public class Commons extends API {
    /**
     * Sends text to a specified WebElement after clearing it.
     *
     * @param element The WebElement to send text to.
     * @param text    The text to input.
     */
    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Waits for the WebElement to be clickable and clicks it.
     *
     * @param element The WebElement to click.
     */
    public static void click(WebElement element) {
        waitForClickability(element).click();
    }

    /**
     * Clicks on a WebElement from a list whose visible text matches the specified text.
     *
     * @param elementList The list of WebElements.
     * @param visibleText The visible text to match.
     */
    public static void click(List<WebElement> elementList, String visibleText) {
        for (WebElement element : elementList) {
            String elementVisibleText = element.getText().trim();

            if (elementVisibleText.equals(visibleText)) {
                click(element);
            }
        }
    }

    /**
     * Selects a value from a dropdown by value.
     *
     * @param element The dropdown WebElement.
     * @param value   The value to select.
     */
    public static void selectDropdown(WebElement element, String value) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Selects an option from a dropdown by index.
     *
     * @param element The dropdown WebElement.
     * @param index   The index of the option to select.
     */
    public static void selectDropdown(WebElement element, int index) {
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---- Actions class utility methods ----

    /**
     * Performs a click action on a WebElement using Actions class.
     *
     * @param element The WebElement to click.
     */
    public static void actionsClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.click(element).perform();
    }

    /**
     * Performs a double click action on a WebElement using Actions class.
     *
     * @param element The WebElement to double-click.
     */
    public static void actionsDoubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    /**
     * Performs a right click action on a WebElement using Actions class.
     *
     * @param element The WebElement to right-click.
     */
    public static void actionsRightClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    /**
     * Drags and drops a WebElement onto another using Actions class.
     *
     * @param source The source WebElement to drag.
     * @param target The target WebElement to drop.
     */
    public static void actionsDragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }

    // ---- Alert handling methods ----

    /**
     * Accepts the current alert if present.
     */
    public static void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dismisses the current alert if present.
     */
    public static void dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the text from the current alert if present.
     *
     * @return The text of the alert, or null if no alert is present.
     */
    public static String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();

            return alert.getText();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * Sends text to the current alert if present.
     *
     * @param alertText The text to send to the alert.
     */
    public static void sendAlertText(String alertText) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(alertText);
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    // ---- Wait and JavaScriptExecutor methods ----

    /**
     * Gets an instance of WebDriverWait.
     *
     * @return A WebDriverWait object.
     */
    public static WebDriverWait getWaitObject() {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
    }

    /**
     * Waits for a WebElement to be clickable and returns it.
     *
     * @param element The WebElement to wait for.
     * @return The clickable WebElement.
     */
    public static WebElement waitForClickability(WebElement element) {
        return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for a WebElement to be visible and returns it.
     *
     * @param element The WebElement to wait for.
     * @return The visible WebElement.
     */
    public static WebElement waitForVisibility(WebElement element) {
        return getWaitObject().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the page title to match the specified title.
     *
     * @param title The title to wait for.
     */
    public static void waitForTitle(String title) {
        getWaitObject().until(ExpectedConditions.titleIs(title));
    }

    /**
     * Gets an instance of JavascriptExecutor.
     *
     * @return A JavascriptExecutor object.
     */
    public static JavascriptExecutor getJSObject() {
        return (JavascriptExecutor) driver;
    }

    /**
     * Performs a JavaScript click on a WebElement.
     *
     * @param element The WebElement to click.
     */
    public static void jsClick(WebElement element) {
        getJSObject().executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls the page to the specified WebElement using JavaScript.
     *
     * @param element The WebElement to scroll to.
     */
    public static void scrollToElement(WebElement element) {
        getJSObject().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scrolls down the page by a specified number of pixels using JavaScript.
     *
     * @param pixels The number of pixels to scroll down.
     */
    public static void scrollDown(int pixels) {
        getJSObject().executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    /**
     * Scrolls up the page by a specified number of pixels using JavaScript.
     *
     * @param pixels The number of pixels to scroll up.
     */
    public static void scrollUp(int pixels) {
        getJSObject().executeScript("window.scrollBy(0, -arguments[0]);", pixels);
    }

    // ---- Utility methods ----

    /**
     * Pauses the execution for a specified number of seconds.
     *
     * @param seconds The number of seconds to wait.
     */
    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current timestamp in the format "yyyy_MM_dd_HH_mm_ss".
     *
     * @return The current timestamp.
     */
    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return sdf.format(new Date());
    }

    /**
     * Takes a screenshot and returns it as a byte array.
     *
     * @return A byte array of the screenshot.
     */
    public static byte[] takeScreenShot() {
        TakesScreenshot ssDriver = (TakesScreenshot) driver;

        return ssDriver.getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Takes a screenshot and saves it to a specified folder based on whether the test failed or passed.
     * Also returns the screenshot as a byte array.
     *
     * @param fileName The name of the screenshot file.
     * @param failed   Whether the screenshot is for a failed test.
     * @return A byte array of the screenshot.
     */
    public static byte[] takeScreenShot(String fileName, boolean failed) {
        TakesScreenshot ssDriver = (TakesScreenshot) driver;
        String resultsFolder = failed ? "failed/" : "passed/";
        String screenshotPath = Constants.SCREENSHOT_FILEPATH + resultsFolder + fileName + "_" + getTimeStamp() +
                "." + Configs.getProperty("screenshot_format");

        try {
            File screenshotDestination = new File(screenshotPath);
            FileUtils.copyFile(ssDriver.getScreenshotAs(OutputType.FILE), screenshotDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ssDriver.getScreenshotAs(OutputType.BYTES);
    }
}