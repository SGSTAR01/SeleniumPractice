package alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");

            driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[1]/button")).click();
            Alert alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            alert.accept();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
