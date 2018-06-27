import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
@FixMethodOrder(MethodSorters.JVM)

public class Synchronization {

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
    public void explicitlyWaitTest() {

        driver.findElement(By.id("rendered")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish2")));
        String message = driver.findElement(By.id("finish2")).getText();
        System.out.println("1st Element text: " + message);
    }

    @Test
    public void sleepWaitTest() throws InterruptedException {

        driver.findElement(By.id("hidden")).click();
        String message = driver.findElement(By.id("loading1")).getText();
        Thread.sleep(3000);
        System.out.println("2nd Element text: " + message);
    }

    @Test
    public void implicitlyWaitTest() {

        driver.findElement(By.id("btn")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String message = driver.findElement(By.id("message")).getText();
        System.out.println("3rd Element text: " + message);

    }


    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }


}
