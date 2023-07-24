package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class HandlingHiddenElements {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://courses.letskodeit.com/practice"); // Get The Website

        //HiddenTxtBox
        WebElement DisplayedText= driver.findElement(By.id("displayed-text"));
        WebElement HideTxtBox= driver.findElement(By.id("hide-textbox"));// HideTxtBox
        HideTxtBox.click();
        System.out.println("Is Text Displayed:"+DisplayedText.isDisplayed());
        //ShowTxtBox
        WebElement ShowTxtBox= driver.findElement(By.id("show-textbox"));// ShowTxtBox
        ShowTxtBox.click();
        System.out.println("Is Text Displayed:"+DisplayedText.isDisplayed());



        //driver.quite();


    }
}
