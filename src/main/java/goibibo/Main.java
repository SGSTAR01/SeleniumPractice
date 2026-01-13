package goibibo;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.goibibo.com/");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[@role='presentation']")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/p")).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            By tooltipinfo = By.xpath("//div[@class='tooltipInfo']");
            WebElement tooltip = wait.until(ExpectedConditions.presenceOfElementLocated(tooltipinfo));
            if (tooltip.isDisplayed()) {
                driver.findElement(By.xpath("//*[@id=\"root\"]")).click();
                Thread.sleep(1000);
            }
        } catch (TimeoutException ignored) {

        }

        //From
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='fromCity']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder=\"From\"]")).sendKeys("India");
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        List<WebElement> list1 = driver.findElements(By.xpath("//ul[@role='listbox']/li"));

        for (WebElement li : list1){
            WebElement sp =li.findElement(By.tagName("span"));
            if (sp.getText().equalsIgnoreCase("CCU")){
                actions.moveToElement(li).click().build().perform();
                break;
            }
        }
        //To
        driver.findElement(By.xpath("//input[@id='toCity']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder=\"To\"]")).sendKeys("india");
        Thread.sleep(1000);
        List<WebElement> list2 = driver.findElements(By.xpath("//ul[@role='listbox']/li"));

        for (WebElement li : list2){
            WebElement sp =li.findElement(By.tagName("span"));
            if (sp.getText().equalsIgnoreCase("DEL")){
                actions.moveToElement(li).click().build().perform();
                break;
            }
        }



    }
}
