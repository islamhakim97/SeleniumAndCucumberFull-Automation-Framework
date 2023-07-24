package com.OrangeHRM.pages;

import java.io.*;
import java.time.Duration;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.com.testBase;

public class LoginPage extends testBase {
	String CookieName;
	public LoginPage() throws IOException {
		super();
		PageFactory.initElements(GetDriver(),this);
	}
	//WebElements
    @FindBy(name="username")
    WebElement Username;
    @FindBy(name="password")
    WebElement Password;
    @FindBy(xpath="//button[@type='submit']")
	WebElement LoginBtn;
	@FindBy(xpath="//p[text()='Invalid credentials']")
	WebElement InvalidCredentials;
	// Forget Password Elements
	//@FindBy(linkText="Forgot your password?")
	@FindBy(xpath="//p[text()='Forgot your password? ']")
	WebElement ForgetPass;
	@FindBy(xpath="//img[@alt='company-branding']")
	WebElement Logo;

	
	
	

	//Methods
	public void getMainPage()
	{
		GetDriver().get(prop.getProperty("URL")) ;
		
		GetDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		GetDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}


    public HomePage performValidLogin(String mail,String pass) throws IOException
    {
	//	<!-- Edge has problems with JavaScript Executer-->
    	
       // getMainPage();
        GetDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//js.executeScript("arguments[0].style.border='3px solid purple'", Username); //--Java Script Executer fails when working with Edge so don't use it there because js loaded the element one more time after i have referred.
		Username.sendKeys(mail);
		//js.executeScript("arguments[0].style.border='3px solid purple'", Password);
		Password.sendKeys(pass);
	//	js.executeScript("arguments[0].style.border='3px solid purple'", LoginBtn);
		LoginBtn.click();
		
	
		return new HomePage();
    }
    public HomePage performLoginWithInvalidData(String mail,String pass) throws IOException
    {
    	//js.executeScript("arguments[0].style.border='3px solid purple'", Username);
		Username.sendKeys(mail);
		//js.executeScript("arguments[0].style.border='3px solid purple'", Password);
		Password.sendKeys(pass);
		//js.executeScript("arguments[0].style.border='3px solid purple'", LoginBtn);
		LoginBtn.click();
		GetDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	return new HomePage();
    }
    public boolean InvalidCredentialsIsDisplayed()
    {
    	
    	return InvalidCredentials.isDisplayed();
    }
    //Forgot password?
    public ResetPassword checkForgotPassword() throws IOException
    {
    	
		js.executeScript("arguments[0].style.border='3px solid purple'", ForgetPass);
		ForgetPass.click();
		GetDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return new ResetPassword();
		
    }
    
    //Check Logo
    public boolean checkLogo()
    {
    	js.executeScript("arguments[0].style.border='3px solid purple'", Logo);
    	return Logo.isDisplayed();
    }
  //Check URL
    public String getURL()
    {
    	return GetDriver().getCurrentUrl();
    }
    //Get Title
    public String getTitle()
    {
    	return GetDriver().getTitle();
    }
	//------------------[Cucumber Methods BDD]-------------------
	public void EnterDataThroughBDDWithDataDriven(String mail,String pass)
	{
		js.executeScript("arguments[0].style.border='3px solid purple'", Username);
		Username.sendKeys(mail);
		js.executeScript("arguments[0].style.border='3px solid purple'", Password);
		Password.sendKeys(pass);
	}
	public void ClickLoginBtnThroughBDD()
	{
		js.executeScript("arguments[0].style.border='3px solid purple'", LoginBtn);
		LoginBtn.click();
	}
	public void LoginToReadandStoreCookies(String mail,String pass) throws IOException
	{
		//Login to The Application with credentials to store cookie

		GetDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		js.executeScript("arguments[0].style.border='3px solid purple'", Username);
		Username.sendKeys(mail);
		js.executeScript("arguments[0].style.border='3px solid purple'", Password);
		Password.sendKeys(pass);
		js.executeScript("arguments[0].style.border='3px solid purple'", LoginBtn);
		LoginBtn.click();

		// create file named Cookies to store Login Information
		File file = new File("Cookies.data");
		try
		{
			// Delete old file if exists
			file.delete();
			file.createNewFile();
			FileWriter fileWrite = new FileWriter(file);
			BufferedWriter Bwrite = new BufferedWriter(fileWrite);
			// loop for getting the cookie information

			// loop for getting the cookie information
			for(Cookie ck : GetDriver().manage().getCookies())
			{
				Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
				Bwrite.newLine();
			}
			Bwrite.close();
			fileWrite.close();

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}


	}
	public String loginToTheApplicationUsingStoredCookies() throws IOException {
         GetDriver().manage().deleteAllCookies(); // delete all previous cookies before adding the new one
		try{

			File file = new File("Cookies.data"); // Open The File Which Contains Stored Cookies
			FileReader fileReader = new FileReader(file);
			BufferedReader Buffreader = new BufferedReader(fileReader);
			String strline;
			while((strline=Buffreader.readLine())!=null){ // Read The File
				StringTokenizer token = new StringTokenizer(strline,";");
				while(token.hasMoreTokens()){
					String name = token.nextToken();
					String value = token.nextToken();
					String domain = token.nextToken();
					String path = token.nextToken();
					Date expiry = null;
					CookieName=name;
					String val;
					if(!(val=token.nextToken()).equals("null")) //To Make Cookie Available For anew period of time if it was null
					{
						expiry = new Date(val);
					}
					Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
					Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure); //Build your Cookie
					System.out.println(ck);
					GetDriver().manage().addCookie(ck); // This will add the stored cookie to your current session
					GetDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
					js.executeScript("location.reload(true);");// Hard Reload from server not cache
					GetDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

		GetDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);// to wait 10 s For page to load
		//driver.navigate().refresh();//force refresh from cache
		//driver.execute_script("location.reload(true);");//hard refresh from the server

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//do hard refresh
		System.out.println(GetDriver().manage().getCookies());

		return CookieName;

	}

	public HomePage ChallengesLoginUsingStoredCookies() throws IOException {
		GetDriver().manage().deleteAllCookies(); // delete all previous cookies before adding the new one
		try{

			File file = new File("Cookies.data"); // Open The File Which Contains Stored Cookies
			FileReader fileReader = new FileReader(file);
			BufferedReader Buffreader = new BufferedReader(fileReader);
			String strline;
			while((strline=Buffreader.readLine())!=null){ // Read The File
				StringTokenizer token = new StringTokenizer(strline,";");
				while(token.hasMoreTokens()){
					String name = token.nextToken();
					String value = token.nextToken();
					String domain = token.nextToken();
					String path = token.nextToken();
					Date expiry = null;
					CookieName=name;
					String val;
					if(!(val=token.nextToken()).equals("null")) //To Make Cookie Available For anew period of time if it was null
					{
						expiry = new Date(val);
					}
					Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
					Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure); //Build your Cookie
					System.out.println(ck);
					GetDriver().manage().addCookie(ck); // This will add the stored cookie to your current session
					GetDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
					js.executeScript("location.reload(true);");// Hard Reload from server not cache
					GetDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

		GetDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);// to wait 10 s For page to load
		//driver.navigate().refresh();//force refresh from cache
		//driver.execute_script("location.reload(true);");//hard refresh from the server

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//do hard refresh
		System.out.println(GetDriver().manage().getCookies());

		return new HomePage();

	}


}

    

	


