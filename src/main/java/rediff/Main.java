package rediff;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.executeCdpCommand("Network.enable", new HashMap<>());
        Map<String, Object> params = new HashMap<>();
        params.put("offline",false);
        params.put("latency", 400);
        params.put("downloadThroughput", 50_000);
        params.put("uploadThroughput",20_000);
        driver.executeCdpCommand("Network.emulateNetworkConditions", params);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        try {
            driver.get("https://www.rediff.com/");
            driver.findElement(By.linkText("Create Account")).click();

            String parentWindow = driver.getWindowHandle();
            By terms = By.linkText("terms and conditions");
            WebElement termsLink =wait.until(ExpectedConditions.presenceOfElementLocated(terms));
            driver.executeScript("arguments[0].scrollIntoView({block:'end'});",termsLink);
            driver.executeScript("arguments[0].click();",termsLink);

            Set<String> allWindows = driver.getWindowHandles();

            for (String window : allWindows) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            driver.findElement(By.xpath("//div[@class='floatR']/input")).click();
            driver.switchTo().window(parentWindow);
            driver.findElement(By.linkText("Rediff Home")).click();
            driver.quit();

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
