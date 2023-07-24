package Challenges;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.devtools.v102.emulation.Emulation;
import org.openqa.selenium.devtools.v104.emulation.Emulation;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.com.testBase;

public class MockGeoLocationTest extends testBase {



    public MockGeoLocationTest() throws IOException {
        super();
    }
    @BeforeMethod()
    public void setup() throws IOException

    {
        initializeMockGeoLocationDriver();
    }

    @AfterMethod()
    public void tearDown() throws IOException 																									// status[pass|fail|skipped]

    {
        MockGeoLocationDriver.quit();
    }
    @Test(priority=1)
    public void EmulateGeoLocation_DevTools()//Selenium4 APIs New Feature
    {

        DevTools devTools =MockGeoLocationDriver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                Optional.of(13.4501),
                Optional.of(1)));
        MockGeoLocationDriver.get("https://my-location.org/");// Launch That website at That Specified Location.
        MockGeoLocationDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test(priority=2)
    public void MockGeoLocation()//Selenium4 APIs New Feature
    {
        Map coordinates = new HashMap();
        {{
            coordinates.put("latitude",32.746940);
            coordinates.put("longitude",-97.092400);
            coordinates.put("accuracy",1);
        }};
        MockGeoLocationDriver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        MockGeoLocationDriver.get("https://www.where-am-i.co/");// Launch That website at That Specified Location.
        MockGeoLocationDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



}

