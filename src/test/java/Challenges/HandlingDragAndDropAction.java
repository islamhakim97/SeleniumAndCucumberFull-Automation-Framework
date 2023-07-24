package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;


public class HandlingDragAndDropAction {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://jqueryui.com/droppable/");
        // Switch to Frame
        WebElement DemoFrame=driver.findElement(By.className("demo-frame")); // Get The Frame
        driver.switchTo().frame(DemoFrame); // Switch to Frame

        WebElement fromWhiteRectangle= driver.findElement(By.id("draggable"));// Get The Draggable Box
        WebElement ToGreyRectangle=driver.findElement(By.id("droppable"));//Get The Droppable Grey Rectangle


        //Creating an object of Actions class to build composite actions
        Actions builder = new Actions(driver);

        //Building drag and drop action
        Action dragAndDrop = builder.clickAndHold(fromWhiteRectangle)
                .moveToElement(ToGreyRectangle)
                .release(ToGreyRectangle)  // drag here
                .build();

        //Performing the drag and drop action
        dragAndDrop.perform();

        //driver.quite();


    }
}
