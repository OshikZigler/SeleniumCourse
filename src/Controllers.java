import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.JVM)
public class Controllers {

    private static WebDriver driver;

    @BeforeClass
    public static void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");

        //Disables Chrome info bar
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");

        driver = new ChromeDriver(options);
        driver.manage().window().fullscreen();
        driver.get("http://yoniflenner.net/Xamples/ex_controllers.html");
        driver.manage().timeouts().pageLoadTimeout(500 , TimeUnit.MILLISECONDS);
    }

    @Test
    public void basicInfo() {
        driver.findElement(By.name("firstname")).sendKeys("Oshik");
        driver.findElement(By.name("lastname")).sendKeys("Zigler");
        driver.findElement(By.id("sex-0")).click();
        driver.findElement(By.id("exp-4")).click();
    }

    @Test
    public void datePicker() {
        driver.findElement(By.id("datepicker")).click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        List<WebElement> days = dateWidget.findElements(By.tagName("td"));

        for (WebElement day : days) {
            if (day.getText().equals("28")) {
                day.click();
            }
        }
    }

    @Test
    public void extraInfo() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.id("profession-1")).click();
//        ToDo add a photo from Google drive
//        driver.findElement(By.id("photo")).click();
        driver.findElement(By.id("tool-2")).click();
        driver.findElement(By.id("continents")).click();
        Select myContinent = new Select(driver.findElement(By.id("continents")));
        myContinent.selectByIndex(1);
        Select seleniumCommands = new Select(driver.findElement(By.id("selenium_commands")));
        seleniumCommands.selectByVisibleText("Navigation Commands");

        driver.findElement(By.id("submit")).submit();
    }

    @Test
    public void getDataFromURL() {
        String URL = driver.getCurrentUrl();
        if (URL.contains("Oshik") && URL.contains("Zigler")) {
            System.out.println("URL contains both private and last names");
        }

        //BONUS EXERCISE

    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
