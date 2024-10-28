package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdBypass {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public AdBypass() {
        ChromeOptions options = new ChromeOptions();

        // Add additional Chrome arguments for better ad handling
        options.addArguments(
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-infobars",
                "--disable-extensions",
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );

        // Disable images and notifications
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
        js = (JavascriptExecutor) driver;

        // Maximize window for better interaction
//        driver.manage().window().maximize();
    }

    public void bypassAds(String url) {
        try {
           // driver.get(url);
            Thread.sleep(2000); // Wait for initial load

            // Handle various types of ads
            handleLocationPopup();
            //handleMainAd();
            handleOverlayAds();
            handleFloatingAds();


        } catch (Exception e) {
            System.err.println("Error in bypassAds: " + e.getMessage());
        }


        }

    private void handleLocationPopup() {
        try {
            // Wait and click location popup close button if present
            By locationPopup = By.xpath("//div[contains(@class, 'style__close')]");
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(locationPopup));
            closeButton.click();
        } catch (TimeoutException e) {
            System.out.println("Location popup not found or already closed");
        } catch (Exception e) {
            System.err.println("Error handling location popup: " + e.getMessage());
        }
    }

   // private void handleMainAd() {
//        try {
//            // List of possible ad close button selectors
//            List<String> adCloseSelectors = List.of(
//                    "//div[contains(@class, 'close-icon')]",
//                    "//div[contains(@class, 'dismiss')]",
//                    "//div[contains(@class, 'close')]//img",
//                    "//button[contains(@class, 'close')]",
//                    "//span[contains(@class, 'close')]",
//                    "//*[contains(@class, 'CrossIcon')]"
//            );
//
//            for (String selector : adCloseSelectors) {
//                try {
//                    WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
//                    closeButton.click();
//                    Thread.sleep(500); // Short wait after clicking
//                    break; // Exit loop if successful
//                } catch (TimeoutException ignored) {
//                    // Continue to next selector if current one fails
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Error handling main ad: " + e.getMessage());
//        }
//    }

    private void handleOverlayAds() {
        try {
            // Remove overlay elements
            js.executeScript(
                    "var overlays = document.querySelectorAll('div[class*=\"overlay\"], div[class*=\"modal\"], div[class*=\"popup\"]');" +
                            "overlays.forEach(function(overlay) {" +
                            "    overlay.remove();" +
                            "});"
            );

            // Remove backdrop/modal background
            js.executeScript(
                    "var backdrops = document.querySelectorAll('div[class*=\"backdrop\"], div[class*=\"modal-backdrop\"]');" +
                            "backdrops.forEach(function(backdrop) {" +
                            "    backdrop.remove();" +
                            "});"
            );

            // Reset body styles that might be added by modals
            js.executeScript(
                    "document.body.style.overflow = 'auto';" +
                            "document.body.style.position = 'static';"
            );
        } catch (Exception e) {
            System.err.println("Error handling overlay ads: " + e.getMessage());
        }
    }

    private void handleFloatingAds() {
        try {
            // Remove fixed-position elements that might be ads
            js.executeScript(
                    "var elements = document.querySelectorAll('*');" +
                            "for (var i = 0; i < elements.length; i++) {" +
                            "    var style = window.getComputedStyle(elements[i]);" +
                            "    if (style.position === 'fixed' || style.position === 'sticky') {" +
                            "        var text = (elements[i].innerText || '').toLowerCase();" +
                            "        if (text.includes('ad') || text.includes('sponsored') || " +
                            "            text.includes('advertisement') || text.includes('offer')) {" +
                            "            elements[i].remove();" +
                            "        }" +
                            "    }" +
                            "}"
            );

            // Remove specific ad-related elements
            js.executeScript(
                    "var adElements = document.querySelectorAll(" +
                            "'[class*=\"ad-\"], [class*=\"advertisement\"], " +
                            "[id*=\"google_ads\"], [id*=\"banner\"], " +
                            "[class*=\"promotion\"], [class*=\"offer\"]');" +
                            "adElements.forEach(function(el) { el.remove(); });"
            );
        } catch (Exception e) {
            System.err.println("Error removing floating ads: " + e.getMessage());
        }
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

}