package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HandlingMultipleWindows {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo"); // Get The Website
        WebElement FollowOnTwitter= driver.findElement(By.xpath("//a[@title='Follow @Lambdatesting on Twitter']"));// Get Follow Button
        FollowOnTwitter.click(); // 1-Click on The Btn

        //2-Print All titles on The Pop-Up Window
        Set<String> handler = driver.getWindowHandles();

        Iterator<String> it = handler.iterator();// Iterator is like a key , Points To The Set

        String parentWindowId = it.next(); //Parent Window id
        System.out.println("parent window id:"+ parentWindowId);


        String childWindowId = it.next(); // Child Window Id
        System.out.println("Child window id:"+childWindowId);

        driver.switchTo().window(childWindowId);

        System.out.println("child window pop up title"+driver.getTitle());
       //Get list of web-elements with tagName  - a
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));//Get All Links in The Window

        //Traversing through the list and printing its text along with link address
        for(WebElement link:allLinks){
            System.out.println(link.getText() + " - " + link.getAttribute("title"));//link.getAttribute("href")
        }

        driver.switchTo().window(parentWindowId);


        System.out.println("parent window title"+driver.getTitle());

        //driver.quite();


    }
}
