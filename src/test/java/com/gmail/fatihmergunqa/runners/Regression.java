package com.gmail.fatihmergunqa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Regression class that serves as the entry point for executing Cucumber regression tests.
 * Configured with the necessary options for running scenarios.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to the feature files
        glue = "com.gmail.fatihmergunqa.steps", // Package containing step definitions
        tags = "@Regression", // Tag to filter which scenarios to run

        plugin = {
                // Prints the Gherkin scenario seleniumsteps to the console
                "pretty",
                // Creates and saves a basic HTML report in the target folder
                "html:target/cucumber-default-html-reports/regression.html",
                // Stores all executed seleniumsteps in a JSON file
                "json:target/cucumber.json",
                // Saves failed test cases in a text file
                "rerun:target/failed.txt",
        }
)
public class Regression {
    // This class serves as a runner for executing regression tests. No additional code is required here.
}