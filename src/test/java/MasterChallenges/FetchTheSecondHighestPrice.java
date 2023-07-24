package MasterChallenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class FetchTheSecondHighestPrice {
    static HashMap<Float,String>hashMap=new HashMap<>();
    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // launch chrome

        driver.manage().window().maximize(); // Maximize The Window
        driver.manage().deleteAllCookies();

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://www.saucedemo.com/inventory.html"); // Get The Website
        //Login To The Inventory
        loginToInventory(driver);


        // Find All links In The Page

           List<WebElement> listofItems = driver.findElements(By.className("inventory_item"));
        for (WebElement item : listofItems ){
            //Item Description
          String itemName= item.findElement(By.className("inventory_item_name")).getText();
          System.out.println(itemName);
           //Item Price
          String itemPrice= item.findElement(By.className("inventory_item_price")).getText();
         //Extract Float Number From The Price String

          float itmPrice = Float.parseFloat(itemPrice.replaceAll("[^\\d.]", ""));
          System.out.println(itmPrice);
          //Add (Item,Price) To Map And Sort it

            hashMap.put(itmPrice,itemName);
        }
        fetchTheSecondHighestPrice();
        driver.quit();
        }

public  static void loginToInventory(WebDriver driver)
{
    WebElement Username=driver.findElement(By.id("user-name"));
    WebElement Password =driver.findElement(By.id("password"));
    WebElement loginBtn=driver.findElement(By.id("login-button"));
    Username.sendKeys("standard_user");
    Password.sendKeys("secret_sauce");
    loginBtn.click();
}
public static void fetchTheSecondHighestPrice (){
    //Sort Price

    Set<Float> keys=hashMap.keySet();
    ArrayList<Float>listSortProductByPrice=new ArrayList<Float>(keys); //Set The ArrayList To The Keys Of HashMap
    Collections.sort(listSortProductByPrice);//Sort The List Using Collections
    System.out.println("Arry List Of Prices, size="+listSortProductByPrice.size());
    for(int i=0;i<listSortProductByPrice.size();i++)
    {
        System.out.println(listSortProductByPrice.get(i));
    }
    //Fetch The Lowest Price

    float getLowestPrice= listSortProductByPrice.get(0);
    System.out.println("The Lowest Price is : "+getLowestPrice);//7.99
    //Fetch The SecondHighest Price

    float getTheSecondHighestPrice=listSortProductByPrice.get(listSortProductByPrice.size()-2);
    System.out.println("TheSecondHighestPrice is : "+getTheSecondHighestPrice); //29.99
    //Fetch The Highest Price

    float getHighestPrice= listSortProductByPrice.get(listSortProductByPrice.size()-1);
    System.out.println("The Highest Price is : "+getHighestPrice); //49.99

}

}
