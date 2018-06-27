import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LocatorsAdvanced {

    private static WebDriver driver;

    @BeforeClass
    public static void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("http://yoniflenner.net/Xamples/ex_locators.html");
        driver.manage().timeouts().pageLoadTimeout(500 , TimeUnit.MILLISECONDS);
    }

    @Test
    public void findElements(){
        System.out.println(driver.findElement(By.id("locator_id")));
        System.out.println(driver.findElement(By.name("locator_name")));
        System.out.println(driver.findElement(By.tagName("p")));
        System.out.println(driver.findElement(By.className("locator_class")));
        System.out.println(driver.findElement(By.linkText("myLocator(5)")));
        System.out.println(driver.findElement(By.partialLinkText("locator")));
        //using ChroPath browser extension
        System.out.println(driver.findElement(By.cssSelector("div:nth-child(1) div.container div:nth-child(2) div:nth-child(1) > input.btn.btn-2:nth-child(18)")));
        System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")));
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }


}
