import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LocatorsBasic {

    private static WebDriver driver;

    @BeforeClass
    public static void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://www.houzz.com");
        driver.manage().timeouts().pageLoadTimeout(500 , TimeUnit.MILLISECONDS);
    }

    @Test
    public void findElements() {

        //Printing 1st locator
        System.out.println(driver.findElement(By.id("navSignIn")));
        //Printing 2nd locator
        System.out.println(driver.findElement(By.linkText("Sign In")));

    }

    @Test
    public void linksTest() {
        List<WebElement> links = driver.findElements(By.tagName("link"));
        List<WebElement> houzzLinks = driver.findElements(By.partialLinkText("Houzz"));
        System.out.println("Number of total links in page: " + links.size());
        System.out.println("Number of links containing 'Houzz: " + houzzLinks.size());

    }


    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }


}
