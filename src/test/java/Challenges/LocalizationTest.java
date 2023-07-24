package Challenges;

import com.Base.com.testBase;
import com.OrangeHRM.pages.localizationPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LocalizationTest extends testBase {
    localizationPage locPage;
    public LocalizationTest() throws IOException {
    }
    @BeforeTest
    public void setup() throws IOException {
      initialization("chrome");
      locPage=new localizationPage();
      GetDriver().get("https://www.bluetooth.com/");
      GetDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test(priority = 1)
    public void CheckWebsiteInEnglish()
    {
        String AR=locPage.getPageTitleinEng();
        String ER=prop.getProperty("titleInEng");
        Assert.assertEquals(AR,ER,"Localization not set properly");

    }

    @Test(priority = 2)
    public void CheckWebsiteInDeutsch()
    {
        String AR=locPage.getPageTitleinDeutsch();
        String ER=prop.getProperty("titleInDeutsch");
        Assert.assertEquals(AR,ER,"Localization not set properly");

    }
    @AfterTest
    public void tearDown()
    {
        GetDriver().close();
    }
}
