package Challenges;

import com.Base.com.testBase;
import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.pages.LoginPage;
import com.OrangeHRM.util.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class HandlingKeyboardEvents extends testBase {
 LoginPage loginPage;
 HomePage homePage;

    public HandlingKeyboardEvents() throws IOException {
    }
    @BeforeMethod()
    public void setup() throws IOException
    {
        initialization("chrome");
        loginPage = new LoginPage();// after you initialize your driver
        loginPage.getMainPage();
        homePage= loginPage.ChallengesLoginUsingStoredCookies();//Read and add cookie to current session to log in with it  and Get HomePage.
        // In Case There Is An Error Just Rerun LoginthroughCookies Script To Create A new Valid Cookie
        // The Run This Script
    }

    @AfterMethod()
    public void tearDown() throws IOException
    {

       // driver.quit();

    }
@Test // open Recruitment  in new tab Using Keyboard Events -- Run cookies script first to get a cookie
    public void testOpenRecruitementLinkUsingKeyboard() throws IOException, AWTException {

        //Open Recruitment Tab Using Keyboard Events

        homePage.HandlingKeyboardEvents();


    }

}
