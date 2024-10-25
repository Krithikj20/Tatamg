package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class AdBypass {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    public void AdBypass() {
        // Set up Chrome with ad-blocking configurations
        ChromeOptions options = new ChromeOptions();

        // Add ad-blocking extensions (example path)
        // options.addExtensions(new File("path/to/adblock.crx"));

        // Disable notifications, popups, and advertisements
        options.addArguments(
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-advertisements",
                "--disable-extensions-except=/path/to/adblock.crx"
        );

        // Disable images to reduce ad load
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        options.setExperimentalOption("prefs", prefs);

        // Initialize WebDriver with options
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    public void closePopupAds() {
        try {
            // Get all window handles
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            // Close popup windows if there are multiple tabs
            if (tabs.size() > 1) {
                for (int i = tabs.size() - 1; i > 0; i--) {
                    driver.switchTo().window(tabs.get(i));
                    driver.close();
                }
                // Switch back to main window
                driver.switchTo().window(tabs.get(0));
            }
        } catch (Exception e) {
            System.err.println("Error handling popup ads: " + e.getMessage());
        }
    }




    private void removeFloatingAds() {
        try {
            // Remove fixed-position elements that might be ads
            js.executeScript(
                    "var elements = document.querySelectorAll('*');" +
                            "for(var i=0; i<elements.length; i++){" +
                            "   var style = window.getComputedStyle(elements[i]);" +
                            "   if(style.position === 'fixed' || style.position === 'sticky'){" +
                            "       var text = elements[i].innerText.toLowerCase();" +
                            "       if(text.includes('ad') || text.includes('sponsored') || text.includes('advertisement')){" +
                            "           elements[i].remove();" +
                            "       }" +
                            "   }" +
                            "}"
            );

            // Remove overlay divs
            js.executeScript(
                    "var overlays = document.querySelectorAll('div[class*=\"overlay\"], div[id*=\"overlay\"]');" +
                            "overlays.forEach(function(overlay) { overlay.remove(); });"
            );
        } catch (Exception e) {
            System.err.println("Error removing floating ads: " + e.getMessage());
        }
    }


}