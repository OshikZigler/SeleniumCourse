import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverObjectMethods {

    private static WebDriver driver;

    @BeforeClass
    public static void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://www.houzz.com");
        driver.manage().timeouts().pageLoadTimeout(500 , TimeUnit.MILLISECONDS);
        driver.navigate().refresh();

    }

    @Test
    public void verifyTitleAndUrl() {
        String expectedTitleName = "Houzz - Home Design, Decorating and Remodeling Ideas and Inspiration, Kitchen and Bathroom Design";
        String expectedUrlAddress = "https://www.houzz.com/";
        String actualTitleName = driver.getTitle();
        String actualUrlAddress = driver.getCurrentUrl();

        Assert.assertEquals("Website Titles don't match", expectedTitleName, actualTitleName);
        Assert.assertEquals("URL is not correct", expectedUrlAddress, actualUrlAddress);
    }


    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }


}
