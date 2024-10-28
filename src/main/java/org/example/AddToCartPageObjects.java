package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static java.util.Collections.list;

public class AddToCartPageObjects {
    public WebDriver driver;

    public AddToCartPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Ensure driver is not null
    }

    @FindBy(xpath = "//input[@id='location-selector']")
    public WebElement locationTab;

    @FindBy(xpath = "(//div[@class='LocationDropDown__city-dropdown-container___jMbLr']//div//ul//li)[6]")
    public WebElement bengaluru;
    @FindBy(xpath = "(//div[@class='styles__nav_title___1QHO3'])[1]")
    public WebElement medicinesTab;
    @FindBy(xpath = "//span[@id='notify-visitors-notification-close-button_13453']")
    public WebElement closeAdTop;
    @FindBy(xpath = "//div[@class='style__close-icon___3FflV']")
    public WebElement close2ndAd;
    @FindBy(xpath = "//div[@class='UpdateCityModal__cancel-btn___2jWwS UpdateCityModal__btn___oMW5n']")
    public WebElement cancelButton;

    public void searchFlow() throws InterruptedException {
        closeAdTop.click();
        close2ndAd.click();
//        Thread.sleep(10000);
        cancelButton.click();
        locationTab.click();
driver.findElement(By.xpath("(//div[@class='LocationDropDown__city-dropdown-container___jMbLr']//div//ul//li)[6]")).click();
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bengaluru);
//        bengaluru.click();


    }
}
