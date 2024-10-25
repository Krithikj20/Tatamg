package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPageObjects {
    public WebDriver driver;

    public AddToCartPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Ensure driver is not null
    }

    @FindBy(xpath = "//input[@id='location-selector']")
    public WebElement locationTab;

    @FindBy(xpath = "//input[@value='Bengaluru']")
    public WebElement bengaluru;
    @FindBy(xpath = "(//div[@class='styles__nav_title___1QHO3'])[1]")
    public WebElement medicinesTab;

    public void searchFlow() {
 //       medicinesTab.click();
        AdBypass ads=new AdBypass();
        ads.closePopupAds();
       locationTab.click();
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bengaluru);
//        bengaluru.click();

    }
}