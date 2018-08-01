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
    public static By LogoutById = By.id("logout");

    private static WebDriver driver;
    public static Properties pro;

    @BeforeClass
    public static void openBrowser() throws ParserConfigurationException, SAXException, IOException {

        LoadProperties();

        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("http://blog.yoniflenner.net/demo/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void LoadProperties() throws IOException{

        File src = new File("Resources/ObjectRepositoryProperties.properties");
        FileInputStream fis =new FileInputStream(src);
        pro = new Properties();
        pro.load(fis);
    }

    @AfterClass
    public static void closeBrowser() {

        driver.quit();
    }


    @Test
    public void Submit() {
        //Usage of object repository - ERROR
        driver.findElement(ObjectRepository.UserNameByXpath).sendKeys("oshik");
        driver.findElement(ObjectRepository.PasswordByXpath).sendKeys("pass1234");
        driver.findElement(ObjectRepository.SubmitById).click();

        //Closing alert dialog and refreshing page
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.navigate().refresh();

        //Usage of object repository - SUCCESS
        driver.findElement(ObjectRepository.UserNameByXpath).sendKeys("admin");
        driver.findElement(ObjectRepository.PasswordByXpath).sendKeys("demo");
        driver.findElement(ObjectRepository.SubmitById).click();
        driver.findElement(ObjectRepository.LogoutById).click();



        //Unable to use properties file (I DON'T KNOW WHY!!!)

        /*driver.findElement(By.name(pro.getProperty("demo.login.username"))).sendKeys("admin");
        driver.findElement(By.name(pro.getProperty("demo.login.password"))).sendKeys("demo");
        driver.findElement(By.id(pro.getProperty("demo.login.submit"))).click();*/

    }

}


