package com.Base.com;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.OrangeHRM.util.WebListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testBase extends AbstractTestNGCucumberTests {
	//public static WebDriver driver; // Glopal driver

	//Thread Local variable to Share The Driver between Multiple Threads , each Thread has  a separate copy of web Driver  to run in parallel safely
	public static final ThreadLocal <WebDriver> driver = new ThreadLocal<>(); // Create anew Thread Local Variable

	public static WebDriver challengeDriver;
	public static ChromeDriver MockGeoLocationDriver;
	public static JavascriptExecutor js;
	public static JavascriptExecutor jscript;//For ChallengesWeb Driver
	public static Properties prop;
	public static EventFiringWebDriver e_driver; // For logging Purpose
	public static WebListener weblistener;

	//Extent Report Variables
	public static ExtentReports extent;//
	//Thread Local Variable Gor Html Report
	public static ExtentTest logger;// to log each test in the Report
	public static ThreadLocal<ExtentTest> extent_test = new ThreadLocal<ExtentTest>();// Thread local variable for extentTest
    public static ThreadLocal<WebDriver> tdriver=new ThreadLocal<WebDriver>();//For synchronized WebDriver - Thread To share The Driver Between Multiple Files-Allure Listener
	public testBase() throws IOException {

		loadProperties();  // By Making This method  Synchronized , we make only one Thread can access this method at any given time .

		/*prop = new Properties();
		String projectPath=System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(
				projectPath+"/Data/config.properties");
		prop.load(fis);*/

	}

   // Good Candidate to be a thread safe method , to avoid the scenario that n number of thread all trying to access the same file in  the same time .

	public synchronized void loadProperties() throws IOException {
		prop = new Properties();
		String projectPath=System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(
				projectPath+"/Data/config.properties");
		prop.load(fis);
	}

	//Will Return a copy of the web driver Object.
	public static WebDriver GetDriver(){
		return driver.get();
	}

	public WebDriver initialization(String browser) throws IOException {
		if(browser.equalsIgnoreCase("Grid-linux-firefox"))
		{
			String nodeURL="http://192.168.10.166:5555/wd/hub"; // changes every time
			//DesiredCapabilities cap= DesiredCapabilities.firefox(); //working in eclipse not in IntelliJ
			DesiredCapabilities cap= new DesiredCapabilities();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.LINUX);
			String projectPath=System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",projectPath+"/drivers/chromedriver.exe ");
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\ihakim\\HubDrivers\\chromedriver.exe");// as i will operate the standalone server from this location

			//Thread Variable Driver
			driver.set(new RemoteWebDriver(new URL(nodeURL),cap));
			//driver= new RemoteWebDriver(new URL(nodeURL),cap);
			
		}else if(browser.equalsIgnoreCase("Grid-windows-chrome"))
		{
			String nodeURL=" http://192.168.10.163:5555/wd/hub";// changes every time
			//DesiredCapabilities cap= DesiredCapabilities.firefox();//working in eclipse not in IntelliJ
			DesiredCapabilities cap= new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
			String projectPath=System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",projectPath+"/drivers/chromedriver.exe "); // as i will operate the standalone server from this location "C:\\Users\\ihakim\\HubDrivers"

			//Thread Variable Driver
			driver.set(new RemoteWebDriver(new URL(nodeURL),cap));
			//driver= new RemoteWebDriver(new URL(nodeURL),cap);
			
		}else if (browser.equalsIgnoreCase("chrome")) {
			 WebDriverManager.chromedriver().setup();
     		 //driver = new ChromeDriver();

			//Set Thread Local Variable

			driver.set(ThreadGuard.protect(new ChromeDriver())); //Thread Guard Used to make sure that driver is only called by the thread created it (Optional - Java Only).


		} else if (browser.equalsIgnoreCase("firefox")) {
              WebDriverManager.firefoxdriver().setup();

			//Thread Variable Driver
			driver.set(new FirefoxDriver());
            // driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			//Thread Variable Driver
			driver.set(new EdgeDriver());
			//driver = new EdgeDriver();

			//headless Browser Testing
		} else if (browser.equalsIgnoreCase("headless")) {
		    DesiredCapabilities caps=new DesiredCapabilities();
		    caps.setJavascriptEnabled(true);
		    String projectPath=System.getProperty("user.dir");
		    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,projectPath+"/drivers/phantomjs.exe ");
		    String[] PhantomjsArgs = {"--web-security=no","--ignore-ssl-errors=yes"};
		    caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, PhantomjsArgs);
			//Thread Variable Driver
			driver.set(new PhantomJSDriver(caps));

			 //driver = new PhantomJSDriver(caps);
		
		}else if (browser.equalsIgnoreCase("chrome-headless")) {
			 WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver","D:\\Web Automation Using Selenium\\BrowsersDrivers\\chromedriver.exe");
		   ChromeOptions options=new ChromeOptions();
		   options.addArguments("--headless");
		   options.addArguments("--window-size=1920,1080");

			//Thread Variable Driver
			driver.set(new ChromeDriver(options));
		  // driver = new ChromeDriver(options);
		}else if (browser.equalsIgnoreCase("safari")) {
			/*
			 * Configure safari driver Manually first in your MacbookAir : (Open terminal :
			 * [run 'cd usr/bin , safaridriver --enble', enable remote automation on
			 * safari-Deceloper , close any safari browser before start testing as safari
			 * not permit to instantiate more than safari browser in the same time] )
			 */

			//Thread Variable Driver
			driver.set(new SafariDriver());
			//driver = new SafariDriver();
		}
		
		// For web Driver listener
		e_driver = new EventFiringWebDriver(GetDriver()); //e_driver = new EventFiringWebDriver(driver);
		weblistener = new WebListener();
		e_driver.register(weblistener);
		driver.set( e_driver); //driver = e_driver;
		tdriver.set(GetDriver());// tdriver.set(driver);-- For Allure report Listener
		// driver.get(prop.getProperty("URL")) ;
		GetDriver().manage().window().maximize();
		GetDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js = ((JavascriptExecutor) GetDriver());

		return getDriver();
	}

	public void closeBrowser(){
		GetDriver().quit(); // Driver is now thread Local Variable , is quiting the webDriver Session
		driver.remove();
	}
	public static synchronized WebDriver getDriver()
	{
		return tdriver.get();
	}

	///////////////////////////////////////////////////////Mock GeoLocation///////////////////////////////////////////////
	public void initializeMockGeoLocationDriver() // just selenium4
	{
		WebDriverManager.chromedriver().setup();
		MockGeoLocationDriver = new ChromeDriver();//MockGeoLocation ChromeDriver Selenium4 as there is a huge difference between selenium4and3 in drivers
		MockGeoLocationDriver.manage().window().maximize();
	}

}
