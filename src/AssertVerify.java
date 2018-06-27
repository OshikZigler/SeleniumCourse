import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AssertVerify {

    private static WebDriver driver;
    private String expectedTitle = "Body Mass Index Calculator";
    private String expectedBMI = "25";
    private String expectedMessage = "That you are healthy.";
    private int expectedButtonWidth = 121;
    private int expectedButtonHeight = 33;
    private int expectedButtonX = 1036;
    private int expectedButtonY = 266;
    private String expectedButtonText = "Calculate BMI";
    private String expectedButtonTagName = "input";


    @BeforeClass
    public static void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");

        //Disables Chrome info bar
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");

        driver = new ChromeDriver(options);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://blog.yoniflenner.net/bmi");
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.MILLISECONDS);
    }

    @Test
    public void Test1_BMItest() throws InterruptedException {

        String actualTitle = driver.getTitle();
        assertEquals("Title doesn't match", expectedTitle, actualTitle);
        driver.findElement(By.id("weight")).sendKeys("76");
        driver.findElement(By.id("hight")).sendKeys("176");
        driver.findElement(By.id("calculate_data")).click();

        String actualBMI = driver.findElement(By.id("bmi_result")).getAttribute("value");
        String actualMessage = driver.findElement(By.id("bmi_means")).getAttribute("value");
        assertEquals("BMI doesn't match", expectedBMI, actualBMI);
        assertEquals("Message doesn't match", expectedMessage, actualMessage);


    }

    @Test
    public void Test2_buttonCalc() {

        int actualButtonWidth = driver.findElement(By.id("calculate_data")).getSize().width;
        int actualButtonHeight = driver.findElement(By.id("calculate_data")).getSize().height;
        System.out.println("Button width is:" + actualButtonWidth + " and button height is:" + actualButtonHeight);
        assertEquals("Button width is wrong", expectedButtonWidth, actualButtonWidth);
        assertEquals("Button height is wrong", expectedButtonHeight, actualButtonHeight);

        int actualButtonX = driver.findElement(By.id("calculate_data")).getLocation().getX();
        int actualButtonY = driver.findElement(By.id("calculate_data")).getLocation().getY();
        System.out.println("Button X position is:" + actualButtonX + " and button Y position is:" + actualButtonY);
        assertEquals("Button X position is wrong ", expectedButtonX, actualButtonX);
        assertEquals("Button Y position is wrong ", expectedButtonY, actualButtonY);

        assertTrue("Button is not enabled", driver.findElement(By.id("calculate_data")).isEnabled());
        assertTrue("Button is not displayed", driver.findElement(By.id("calculate_data")).isDisplayed());
        assertFalse("Button is selected", driver.findElement(By.id("calculate_data")).isSelected());

        String actualButtonText = driver.findElement(By.id("calculate_data")).getAttribute("value");
        assertEquals("Button text is wrong", expectedButtonText, actualButtonText);
        String actualButtonTagName = driver.findElement(By.id("calculate_data")).getTagName();
        assertEquals("Button tag is not 'input'", expectedButtonTagName, actualButtonTagName);

        //assertTrue("Error message is visible" , driver.findElement(By.id("validation")).isDisplayed());

    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }


}
