package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class HandlingDoubleClick {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
       // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://api.jquery.com/dblclick/"); // Get The Website
        WebElement Iframe= driver.findElement(By.xpath("//iframe[@height='250']"));// Get The Frame

        driver.switchTo().frame(Iframe); // Switch To The Frame Contains The Div
        WebElement Div= driver.findElement(By.tagName("div"));// Get The Div To DoubleClick
        System.out.println("We Recognized Div");//Element Was Found
        Actions action =new Actions(driver);
        action.doubleClick(Div).build().perform(); // Perform double Click To Toggle The Colour Of Div.


        //driver.quite();


    }
}
