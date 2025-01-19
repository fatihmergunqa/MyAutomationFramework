package gmail.fatihmergunqa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Failed class that serves as the entry point for re-running failed Cucumber scenarios.
 * Configured with the necessary options for executing only the scenarios that previously failed.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed.txt", // Path to the file containing the failed scenarios
        glue = "gmail.fatihmergunqa.steps", // Package containing step definitions

        plugin = {
                // Prints the Gherkin scenario seleniumsteps to the console
                "pretty",
                // Creates and saves a basic HTML report in the target folder
                "html:target/cucumber-default-html-reports/failed.html",
                // Saves failed test cases in a text file
                "rerun:target/failed.txt",
        }
)
public class Failed {
    // This class serves as a runner for executing previously failed tests. No additional code is required here.
}