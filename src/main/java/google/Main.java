package google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        Thread.sleep(2000);

        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys("java");
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        List<WebElement> list1 = driver.findElements(By.xpath("//ul[@role='listbox']/li"));
        System.out.println(list1);
        for (WebElement li : list1){
            if (li.getText().equalsIgnoreCase("java compiler")){
                actions.moveToElement(li).click().build().perform();
                break;
            }
        }
    }
}
