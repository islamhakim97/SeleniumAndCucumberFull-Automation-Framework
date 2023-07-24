package MasterChallenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrokenLinks {
    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //driver.get("https://www.amazon.com/");
        driver.get("http://the-internet.herokuapp.com"); // Get The Website
        // Find All links In The Page
        List<WebElement> listofLinks = driver.findElements(By.tagName("a"));
        for (WebElement link : listofLinks ){
            //Check if All Links on Page Are Working
            System.out.println( "link :"+link.getAttribute("href") +"  IS Working : " + isLinkWorking(driver,link.getAttribute("href") ) +" : " +link.getText());


        }


        driver.quit();


    }
    //Check If All Links On Page Are Working
    public static boolean isLinkWorking(WebDriver driver, String hrefString) throws Exception{
        boolean isWorking = true;
        URL url = new URL(hrefString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String response = "";
        try {
            connection.connect();
            response = connection.getResponseMessage();
           // System.out.println(response);
            connection.disconnect();
        } catch (Exception e){
            isWorking = false;
        }
        return isWorking;
    }
}
