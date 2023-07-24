package BDD.Steps;

import com.Base.com.testBase;
import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class UserLoginDataDriven extends testBase {
    LoginPage loginpage  =new LoginPage();;
    HomePage homePage =new HomePage();
    public UserLoginDataDriven() throws IOException {
    }
    @When("I Entered User Data {string},{string}")
    public void i_entered_user_data(String username, String password) {
     loginpage.EnterDataThroughBDDWithDataDriven(username,password);
    }


    @And("I Click On LoginBtn")
    public void iClickOnLoginBtn() {
        loginpage.ClickLoginBtnThroughBDD();

    }

    @Then("Invalid Credentials Text is Displayed")
    public void invalidCredentialsTextIsDisplayed() {
       Boolean AR= loginpage.InvalidCredentialsIsDisplayed();
       Assert.assertTrue(AR,"Login Success , With invalid Username Or Password");
    }
}
