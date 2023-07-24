package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ScrollToSpecificElementeUsingJScript {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // launch chrome

        JavascriptExecutor js = (JavascriptExecutor) driver; // Casting Our Driver As JS Executer.
        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://courses.letskodeit.com/practice"); // Get The Website
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement Iframe=driver.findElement(By.id("courses-iframe"));
        js.executeScript("arguments[0].style.border='3px solid purple'", Iframe);

        driver.switchTo().frame(Iframe);//Switch to The Iframe
        //Scroll down till SeleniumCourse is visible
        WebElement SeleniumCourse=driver.findElement(By.xpath("//a[@href='/courses/selenium-webdriver-advanced']"));
        js.executeScript("arguments[0].scrollIntoView(true);", SeleniumCourse);
        js.executeScript("arguments[0].style.border='3px solid purple'", SeleniumCourse);
    }
}
