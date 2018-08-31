import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Actions {

    public static WebDriver driver;


    @BeforeClass
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("http://atidcollege.co.il/Xamples/ex_actions.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /*@Test
    public void Test1_DragAndDrop(){
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        Actions actions  = new Actions(driver);
        actions.dr
    }*/




    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
