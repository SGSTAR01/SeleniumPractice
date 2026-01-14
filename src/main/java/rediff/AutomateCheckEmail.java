package rediff;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class AutomateCheckEmail {
    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

        try {
            driver.get("https://www.rediff.com/");
            driver.findElement(By.linkText("Create Account")).click();
            Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[starts-with(@name,'name')]"))))
                    .sendKeys("Subham Ghosh");
            Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[starts-with(@name,'login')]"))))
                    .sendKeys("startroid");
            Objects.requireNonNull(wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[starts-with(@name,'btnchk')]")))).click();
            WebElement banner = Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("success"))));
            if (banner.getText().contains("Yippie!")){
                System.out.println("Username available.");
            }
            else {
                System.out.println("Username not available.");
            }
            driver.quit();
        }
        catch (Exception ignored){

        }
    }
}
