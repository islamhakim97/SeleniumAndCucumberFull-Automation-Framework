package Challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class JSMouseHoverDisplayAccountsListsWindow {

    //Hover Using JS over Account&Lists  and Open Its Window
    public static WebDriver driver;

    @Test
    public void testouseOver() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://www.amazon.com/");

        WebElement element = driver.findElement(By.id("nav-link-accountList"));
        mouseHoverJScript(element);//Hover Using JS over Account&Lists
        //Open Account&Lists Windows
        clickJScript(element);// Click on Account&Lists To Open Its Window  Using JS
    }

    public void mouseHoverJScript(WebElement HoverElement) {
        try {
            if (isElementPresent(HoverElement)) {

                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                ((JavascriptExecutor) driver).executeScript(mouseOverScript,
                        HoverElement);

            } else {
                System.out.println("Element was not visible to hover " + "\n");

            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + HoverElement
                    + "is not attached to the page document"
                    + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + HoverElement + " was not found in DOM"
                    + e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while hovering"
                    + e.getStackTrace());
        }

    }

    public void clickJScript(WebElement clickElement) {
        try {
            if (isElementPresent(clickElement)) {

                String ClickScript = "arguments[0].click();";
                ((JavascriptExecutor) driver).executeScript(ClickScript,
                        clickElement);

            } else {
                System.out.println("Element was not visible to Click " + "\n");

            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + clickElement
                    + "is not attached to the page document"
                    + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + clickElement + " was not found in DOM"
                    + e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while Clicking"
                    + e.getStackTrace());
        }

    }


    public static boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            if (element.isDisplayed()
                    || element.isEnabled())
                flag = true;
        } catch (NoSuchElementException e) {
            flag = false;
        } catch (StaleElementReferenceException e) {
            flag = false;
        }
        return flag;
    }

}
