package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

   public static WebDriver driver;

    public void setup(){
       driver=new ChromeDriver();
       driver.get("https://www.1mg.com/");
       driver.manage().window().maximize();

    }

    public void tearDown(){
//driver.quit();
    }
}
