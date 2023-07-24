package Challenges;

import java.io.IOException;
import java.lang.reflect.Method;

import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.util.TestUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Base.com.testBase;
import com.OrangeHRM.pages.LoginPage;
import com.OrangeHRM.pages.ResetPassword;
import AllureReport.AllureListener;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({AllureListener.class})//add it to each class u want to run with allure Listeners
public class loginThroughCookiesTest extends testBase {



    LoginPage loginPage;
    String date,name;

    public loginThroughCookiesTest() throws IOException {
    }

    @Parameters({ "Browser" })
    @BeforeMethod()
    public void setup(Method method, String browser) throws IOException
    {
        initialization(browser);
        loginPage = new LoginPage();// after you initialize your driver
        loginPage.getMainPage();
        date=TestUtils.TCstime();
        name =method.getName()+date;//Assign Date and Time To TCName
        TestUtils.LogTCsNamesToREport(name);
    }

    @AfterMethod()
    public void tearDown(Method method, ITestResult result) throws IOException // ITestResult is TestNG listener to log test status[pass|fail|skipped]
    {
        name =method.getName()+date;//Assign Date and Time To picName
        //  Take SnapShot:
        TestUtils.TakePicture(name);
        // ** Log Test Status to the Report:
        TestUtils.LogTestStatusToExtentReport(result,name);
       // thread local driver quite method

        closeBrowser();
       // driver.quit();



    }

    // Generate Cookie.data File and Store Cookie in it
    @Test(priority = 1,groups= {"Regression"})
    @Description("Verify Storing Login Cookies In File") // allure Report Notations , seen by allure Report
    @Epic("Epic002")
    @Feature("Feature2:Generate Cookie.data File")
    @Story("Story:Generate Cookie.data File")
    @Step("Verify Storing Cookie ")
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginAndStoringCookiesInFile() throws IOException {

        String validUser = prop.getProperty("username");
        String validpass = prop.getProperty("password");
        loginPage.LoginToReadandStoreCookies(validUser,validpass);//To Store Cookie in cookie.data File
    }

    // Read The StoredCookie from cookie.data to login  through stored cookies.
    @Test(priority = 2,groups= {"Regression"}) // [1-Fail]  // make it fail to see it failed in allure
    @Description("Verify  Login through Stored Cookies ") // allure Report Notations , seen by allure Report
    @Epic("Epic002")
    @Feature("Feature2:Login through stored cookies")
    @Story("Story:Read Cookie.data File")
    @Step("Verify Login using Stored Cookie ")
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginThroughStoredCookies() throws IOException {
        //U have to log in and store cookies first , so u can use it later to login .
        String cookieName=loginPage.loginToTheApplicationUsingStoredCookies();//Read and add cookie to current session to log in with it .

        Assert.assertEquals(cookieName,"orangehrm","Cookie not set properly ");//(Ar,);

    }


}
