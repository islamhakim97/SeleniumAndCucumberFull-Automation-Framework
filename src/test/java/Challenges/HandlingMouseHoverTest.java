package Challenges;

import com.Base.com.testBase;
import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class HandlingMouseHoverTest extends testBase {
 LoginPage loginPage;
 HomePage homePage;

    public HandlingMouseHoverTest() throws IOException {
    }
    @BeforeTest()
    public void CreateAndStoreCookie() throws IOException {
        initialization("chrome");
        loginPage = new LoginPage();// after you initialize your driver
        loginPage.getMainPage();
        loginPage.LoginToReadandStoreCookies("admin","admin123");//

    }
    @BeforeMethod()
    public void setup() throws IOException
    {

        homePage= loginPage.ChallengesLoginUsingStoredCookies();//Read and add cookie to current session to log in with it  and Get HomePage.

    }

    @AfterMethod()
    public void tearDown() throws IOException
    {

       // driver.quit();

    }
@Test
    public void testOpenRecruitementLinkUsingKeyboard() throws IOException {

        //Open Recruitment Tab Using Mouse Hover
           homePage.handlingMouseOver(); //Move To vacancies Tab Using Mouse Hover

    }
    
}
