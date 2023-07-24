package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class HandlingUploadingFile {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://www.monsterindia.com/seeker/registration"); // Get The Website -->this website refuse any connection from selenium driver
        driver.manage().deleteAllCookies();
        // Adds the cookie into current browser context
        driver.manage().addCookie(new Cookie("WZRK_G", "196f8d6261b648148e5e94a824c88067"));
        // Upload a File
        WebElement UploadFileIcon=driver.findElement(By.className("mqfi-file-upload icon")); // Get The Upload TxtBox
        UploadFileIcon.sendKeys("C:\\Users\\ihakim\\Desktop\\islam.png"); // upload a pic , Change the location of your pic


        //driver.quite();


    }
}
