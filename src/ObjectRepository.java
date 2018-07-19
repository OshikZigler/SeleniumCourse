import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ObjectRepository {
    //Object repository in internal class
    public static By UserNameByXpath = By.xpath("//input[@name='username']");
    public static By PasswordByXpath = By.xpath("//input[@name='password']");
    public static By SubmitById = By.id("submit");

    private static WebDriver driver;
    public static Properties properties;

    @BeforeClass
    public static void openBrowser() throws ParserConfigurationException, SAXException, IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("http://blog.yoniflenner.net/demo/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

    public static void LoadProperties() throws IOException{
        File src = new File("ObjectRepository_Properties");
        FileInputStream fileInputStream =new FileInputStream(src);
        Properties pro = new Properties();
        pro.load(fileInputStream);
    }

    @Test
    public void Submit() {
        //Usage of object repository
        driver.findElement((ObjectRepository.UserNameByXpath)).sendKeys("Oshik");
        driver.findElement((ObjectRepository.PasswordByXpath)).sendKeys("Pass123");
        driver.findElement((ObjectRepository.SubmitById)).click();

        //Closing alert dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.navigate().refresh();

        //Usage of properties
        driver.findElement(By.xpath(properties.getProperty("demo.login.username"))).sendKeys("Oshik");

    }

}


