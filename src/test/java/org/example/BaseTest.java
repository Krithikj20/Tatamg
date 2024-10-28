package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    // @Parameters("browser")
    @BeforeTest
    // public void setUp(String browser) {
    public void setUp() {
//
//
//        if(browser.equalsIgnoreCase("firefox")) {
//
////Initializing the firefox driver
//            driver = new FirefoxDriver();
//
//        }else if (browser.equalsIgnoreCase("chrome")) {
//
//            //Initialize the chrome driver
//            driver = new ChromeDriver();
//
//        }

        ChromeOptions options= new ChromeOptions();
        options.addArguments("--disable-geolocation");
    driver = new ChromeDriver(options);
    driver.get("https://www.1mg.com/");
//        AdBypass adBypass = null;
//        try {
//            adBypass = new AdBypass();
//            adBypass.bypassAds("https://www.1mg.com/");
//            // Add a delay to keep the browser open (for testing)
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            System.err.println("Error in main: " + e.getMessage());
//        } finally {
//            if (adBypass != null) {
//                adBypass.quit();
//            }
//        }
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @AfterTest
    public void tearDown()
    {

   //     driver.quit();

    }


}


