package smartbazaar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            driver.get("https://www.smartbazaar.co.uk/");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@class='sp-image']")));
            Objects.requireNonNull(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='brave_popup__close__button']")))).click();
            Objects.requireNonNull(wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown")))).click();
            Actions actions = new Actions(driver);
            WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Login")));
            actions.scrollToElement(login).click().build().perform();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
