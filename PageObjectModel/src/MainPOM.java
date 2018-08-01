import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class MainPOM {

    public static WebDriver driver;


    @BeforeClass
    public static void openBrowser() throws ParserConfigurationException, SAXException, IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get(getData("URL"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

    public static String getData(String nodeName) throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File("/Users/oshikzigler/Automation/SeleniumCourse/PageObjectModel/src/PageObjectModelData.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    @Test
    public void LoginTest() throws IOException, SAXException, ParserConfigurationException {
        LoginPagePOM.userName(driver).sendKeys(getData("Username"));
        LoginPagePOM.password(driver).sendKeys(getData("Password"));
        LoginPagePOM.submit(driver).click();
    }

    @Test
    public void FormTest() throws IOException, SAXException, ParserConfigurationException {
        FormPagePOM.occupation(driver).sendKeys(getData("Occupation"));
        FormPagePOM.age(driver).sendKeys(getData("Age"));
        FormPagePOM.location(driver).sendKeys(getData("Location"));
        FormPagePOM.clickMe(driver).click();
    }

    @Test
    public void BrowserTest() {
        //Find out how to compare String with value from XML

        BrowserPagePOM.clickMe(driver).isEnabled();
        String expectedButtonText = "Click Me!";
        Assert.assertEquals("Button text doesn't match", BrowserPagePOM.clickMe(driver).getText(), expectedButtonText);
        BrowserPagePOM.clickMe(driver).click();
    }
}










