import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.JVM)

public class ErrorHandling {

    private static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeClass
    public static void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");

        //Disables Chrome info bar
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");

        driver = new ChromeDriver(options);
        driver.manage().window().fullscreen();
        driver.get("http://yoniflenner.net/Xamples/ex_synchronization.html");
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.MILLISECONDS);

    }

    @Test
    public void tryCatchTest() {

        WebElement checkbox = driver.findElement(By.id("checkbox"));
        driver.findElement(By.id("btn")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("message")));

        try {
            Assert.assertTrue(checkbox.isDisplayed());

        } catch (StaleElementReferenceException staleException) {

            System.out.println("Error: Element is no longer in DOM");
        }
    }

    //Will return an exception
    @Test
    public void noTryCathTest(){

        driver.navigate().refresh();
        WebElement checkbox = driver.findElement(By.id("checkbox"));
        driver.findElement(By.id("btn")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("message")));

        if (checkbox.isDisplayed()){
            System.out.println("Element is still in DOM");
        }
        else {
            System.out.println("Error: Element is no longer in DOM");
        }

    }


    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
