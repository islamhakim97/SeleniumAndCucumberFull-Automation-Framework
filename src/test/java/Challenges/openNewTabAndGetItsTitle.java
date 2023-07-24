package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class openNewTabAndGetItsTitle {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://courses.letskodeit.com/practice"); // Get The Website
        //1] Switch To a new Tab
        WebElement newTab= driver.findElement(By.id("opentab"));// Get Tab btn
        newTab.click(); // Open The new Tab

        //2-Print All titles on The Pop-Up Window
        Set<String> handler = driver.getWindowHandles();

        Iterator<String> it = handler.iterator();// Iterator is like a key , Points To The Set Of Handlers For Windows

        String parentWindowId = it.next(); //Parent Window id
        System.out.println("parent window id:"+ parentWindowId);


        String childWindowId = it.next(); // Child Window Id
        System.out.println("Child window id:"+childWindowId);

        driver.switchTo().window(childWindowId);

        System.out.println("child window pop up title: "+driver.getTitle());
        driver.close(); //Close The New Tab

        driver.switchTo().window(parentWindowId);


        //System.out.println("parent window title"+driver.getTitle());

        //driver.quite();


    }
}
