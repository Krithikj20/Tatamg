package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
    public WebDriver driver;

    public LoginPageObjects(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    }


