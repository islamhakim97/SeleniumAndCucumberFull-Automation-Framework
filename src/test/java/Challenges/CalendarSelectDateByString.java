package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CalendarSelectDateByString {
    static WebDriver driver;
   //Calendar Challenge 1 --> trytestingthis.com
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // launch chrome

        JavascriptExecutor js = (JavascriptExecutor) driver; // Casting Our Driver As JS Executer.
        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://trytestingthis.netlify.app/"); // Get The Website
        WebElement Calendar = driver.findElement(By.id("day"));

        //1] Get Calendar

       String dateVal = "10/26/2023";
       //Select Date
       selectDate(dateVal,Calendar);

    }

    public static void selectDate(String dateVal,WebElement Calendar)
    {
        String dateArr[] = dateVal.split("/");
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < dateArr.length; i++) {
            sb.append(dateArr[i]);
        }
        String str = sb.toString();
        System.out.println(str);
        Calendar.sendKeys(str); // 10/26/2023";Converted To Be ---> // 10262023
    }

}
