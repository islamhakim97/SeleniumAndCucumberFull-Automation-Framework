package BDD.Steps;

import com.Base.com.testBase;
import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.collections4.Get;
import org.testng.Assert;

import java.io.IOException;

public class UserLogin extends testBase {
    LoginPage loginpage  =new LoginPage();;
    HomePage homePage =new HomePage();
    public UserLogin() throws IOException {
    }

    @Given("The User in The Login Page")
    public void the_user_in_the_login_page() throws IOException {

        loginpage.getMainPage();//get login Page
        Assert.assertTrue(GetDriver().getCurrentUrl().contains("orangehrmlive"));
    }
    @When("I Entered User Data And Click LoginBtn")
    public void i_entered_user_data_and_click_login_btn() throws IOException {
        loginpage.performValidLogin("admin","admin123");
    }

    @Then("The The Home Page is Displayed")
    public void the_the_home_page_is_displayed() {
        boolean Ar = homePage.DashboardisDisplayed();
        Assert.assertTrue(Ar, "Login Fail , In correct Username Or Password");
    }




}
