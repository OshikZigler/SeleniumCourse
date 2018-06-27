import com.aventstack.extentreports.ExtentTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
@FixMethodOrder(MethodSorters.JVM)


public class ExtentReports
{
    private static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeClass
    public static void openBrowser() throws ParserConfigurationException, SAXException, IOException
    {
        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get(getData("URL"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //extent = new ExtentReports("/Users/oshikzigler/Documents/Automation/Selenium/Extent Reports/TestRunReport.html");

    }

    @Test
    public void setData() throws ParserConfigurationException, SAXException, IOException
    {
        driver.findElement(By.id("weight")).sendKeys(getData("Weight"));
        driver.findElement(By.name("height")).sendKeys(getData("Hight"));
        driver.findElement(By.id("calculate_data")).click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("bmi_means"), "That"));

        String ActualResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
        assertEquals(getData("ExpectedResultBMI"),ActualResult);
        assertEquals(getData("ExpectedResultMean"),driver.findElement(By.id("bmi_means")).getAttribute("value"));

    }

    @AfterClass
    public static void closeBrowser()
    {
        driver.quit();
    }

    public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
    {
        File fXmlFile = new File("/Users/oshikzigler/Automation/SeleniumCourse/src/TestData.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }
}
