package com.gmail.fatihmergunqa.steps;

import com.gmail.fatihmergunqa.testbase.Base;
import com.gmail.fatihmergunqa.utils.Commons;
import com.gmail.fatihmergunqa.utils.Configs;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Hooks class that contains setup and teardown methods for Cucumber scenarios.
 * Inherits common utilities from the Commons class.
 */
public class Hooks extends Commons {

    /**
     * before method that runs before each UI scenario.
     * Initializes the WebDriver and sets up the browser environment.
     */
    @Before
    public void before(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@API") || scenario.getSourceTagNames().contains("@Database")) {
            return;
        }

        Base.setUp();
    }

    /**
     * after method that runs after each UI scenario.
     * Takes a screenshot if the scenario fails and attaches it to the Cucumber report.
     *
     * @param scenario The current Cucumber scenario.
     */
    @After
    public void after(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@API") || scenario.getSourceTagNames().contains("@Database")) {
            return;
        }

        // Take a screenshot of the current scenario
        byte[] screenshot = takeScreenShot(scenario.getName(), scenario.isFailed());

        // Attach the screenshot to the scenario report
        scenario.attach(screenshot, "image/" + Configs.getProperty("screenshot_format"),
                scenario.getName());

        // Terminate the browser session
        Base.tearDown();
    }
}