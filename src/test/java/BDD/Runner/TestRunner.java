package BDD.Runner;

import com.Base.com.testBase;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.io.IOException;

@CucumberOptions(features = "src/test/java/BDD/Features",
        glue={"BDD.Steps"},

        plugin={"pretty","html:target/cucumber-html-report"})
public class TestRunner extends testBase {

    public TestRunner() throws IOException {

    }
    @BeforeMethod
    public void setUp() throws IOException {
        initialization("chrome");
    }
    @AfterMethod
    public void tearDown()
    {
        closeBrowser();//thread local driver quite method

        //driver.quit();
    }
}
