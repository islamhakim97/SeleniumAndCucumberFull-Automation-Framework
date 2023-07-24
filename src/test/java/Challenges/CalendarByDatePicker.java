package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalendarByDatePicker {
    static WebDriver driver;
   //Calendar Challenge 3 -->
    @BeforeMethod
    public  void Setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // launch chrome

        JavascriptExecutor js = (JavascriptExecutor) driver; // Casting Our Driver As JS Executer.
        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://webdriveruniversity.com/Datepicker/index.html"); // Get The Website





    }
    @Test
    public  void testDatePicker2020()
    {
        //------------------------------------"25 December 2020  (25/11/2020)"----------------------------------------
        String date ="25/11/2020";
        setDatePicker(date);
    }
    @Test
    public  void testDatePicker2023()
    {
        //------------------------------------"25 December 2023 (25/11/2023)"----------------------------------------
        String date ="25/11/2023";
        setDatePicker(date);
    }
    @AfterMethod
    public  void tearDown()
    {
        //driver.quit();
    }
    public static void setDatePicker(String date)
    {
        //Open datePicker
        WebElement datePicker = driver.findElement(By.className("glyphicon-calendar"));
        datePicker.click();

        //Next Link->Button To Move Next In Calendar
        WebElement nextLink = driver.findElement(By.xpath("//div[@class='datepicker-months']//table//thead//tr//th[@class='next']"));
        //nextLink.click();
        //Med Link ->Button To Click In Center Of Calendar Header
        WebElement medLink = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
        medLink.click();
        //Previous Link->Button To Move Previous In Calendar «
        WebElement prevLink = driver.findElement(By.xpath("//div[@class='datepicker-months']//table//thead//tr//th[@class='prev']"));
        //prevLink.click();

        //Split The Date
        String dateArr[]=date.split("/");
        //Get the year difference between current year and year to set in calander

        int estimatedYear=Integer.parseInt(dateArr[2]);
        System.out.println(estimatedYear);
        int currentYear=Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(currentYear);
        int yearDiff = estimatedYear-currentYear; // 2020 -2022 =3 & 2024-2022 =-2
        System.out.println(yearDiff);
        if(yearDiff!=0){

            //if you have to move next year

            if(yearDiff>0){

                for(int i=0;i< yearDiff;i++){

                    System.out.println("nextYear Diff->"+i);

                    nextLink.click();

                }

            }

            //if you have to move previous year

            else if(yearDiff<0){

                for(int i=0;i< (yearDiff*(-1));i++){

                    System.out.println("prevYear Diff->"+i);

                    prevLink.click();

                }

            }

        }
        //Get All Months To Select The Correct one

        List<WebElement> list_AllMonthElements =driver.findElements(By.className("month"));// Element
        int month=Integer.parseInt(dateArr[1]);//Get Month
        for(int i=0;i<list_AllMonthElements.size();i++)
        {
            System.out.println(list_AllMonthElements.get(i).getText());
        }
        //Choose Month

        WebElement Month=list_AllMonthElements.get(month);//index changes and there is as it as there is a plus month added, is start from 0
        System.out.println(Month.getText());
        Month.click();
        //get all days from calendar to select the correct one

        List<WebElement> list_AllDays = driver.findElements(By.className("day"));
        for(int i = 0; i< list_AllDays.size(); i++) {
            //obtain text
            String day = list_AllDays.get(i).getText();
            System.out.println("Text is: " + day);
        }
        //Choose Day to get 25 ur index is from 0->24 , Equation differs from screen to another
        if(estimatedYear==2020) {
            WebElement Day = list_AllDays.get(Integer.parseInt((dateArr[0])) + 1); //+1 =24, to start count from day 1 , to get the correct index
            System.out.println(Day.getText());
            Day.click();
        }
        else if(estimatedYear==2023)
        {
            WebElement Day = list_AllDays.get(Integer.parseInt((dateArr[0])) + 4); //+4 =24, to start count from day 1 , to get the correct index
            System.out.println(Day.getText());
            Day.click();
        }

    }


}
