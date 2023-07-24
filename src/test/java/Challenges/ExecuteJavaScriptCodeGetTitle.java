package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ExecuteJavaScriptCodeGetTitle {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver(); // launch chrome
        driver = new FirefoxDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver; // Casting Our Driver As JS Executer.
        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://opensource-demo.orangehrmlive.com/"); // Get The Website
        //1] Print WebPage Title Using JavaScript
        String titleText = js.executeScript("return document.title;").toString();
        System.out.println("Title is :" + titleText);
    }
}
