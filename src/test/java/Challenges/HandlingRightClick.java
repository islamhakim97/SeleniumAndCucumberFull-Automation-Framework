package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class HandlingRightClick {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://demo.guru99.com/test/simple_context_menu.html"); // Get The Website

        //Right-Click on The Span
        WebElement RightClickMe= driver.findElement(By.xpath("//span[contains(text(),'right click me')]"));// Get The Frame
        Actions action =new Actions(driver);
        action.contextClick(RightClickMe).perform(); // Perform Right-Click To The Span.


        //driver.quite();


    }
}
