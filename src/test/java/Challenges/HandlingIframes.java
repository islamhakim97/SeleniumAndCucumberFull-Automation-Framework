package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;



public class HandlingIframes {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://courses.letskodeit.com/practice");
        WebElement Iframe= driver.findElement(By.id("courses-iframe"));// Get The Frame

        driver.switchTo().frame(Iframe); // Switch To The Frame

        WebElement course=driver.findElement(By.xpath("//h4[contains(text(),'JavaScript for beginners')]")); // Get The Course

        course.click();

        //driver.quite();


    }
}
