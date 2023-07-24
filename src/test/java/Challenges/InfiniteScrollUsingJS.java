package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InfiniteScrollUsingJS {
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

        driver.get("https://the-internet.herokuapp.com/infinite_scroll"); // Get The Website

        //Get The Footer
        WebElement footer=driver.findElement(By.id("page-footer"));

        //InfinityScroll
        while(footer.isDisplayed()){
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }

    }

}
