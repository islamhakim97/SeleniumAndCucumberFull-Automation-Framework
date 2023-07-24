package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class CalendarByDatePickerDropDown {
    static WebDriver driver;
   //Calendar Challenge 2
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // launch chrome

        JavascriptExecutor js = (JavascriptExecutor) driver; // Casting Our Driver As JS Executer.
        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://jqueryui.com/resources/demos/datepicker/dropdown-month-year.html"); // Get The Website

        //Open datePicker
        WebElement datePicker = driver.findElement(By.id("datepicker"));
        datePicker.click();
        //------------------- 5 Jun 2023 ----------------------

        //Choose Year From Dropdown
        WebElement year = driver.findElement(By.className("ui-datepicker-year"));
        Select selectyear=new Select(year);
        selectyear.selectByVisibleText("2023");

        // choose month from dropdown
        WebElement month = driver.findElement(By.className("ui-datepicker-month"));
        Select selectmonth=new Select(month);
        selectmonth.selectByVisibleText("Jun");
        // choose day
        WebElement day = driver.findElement(By.xpath("//a[@data-date='5']"));
        day.click();


    }


}
