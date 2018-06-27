import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@FixMethodOrder(MethodSorters.JVM)

public class SwitchNavigate {

    private static WebDriver driver;
    private String defaultContentTitle = "Switch and Navigate";
    private String expectedNewTabText = "This is a new tab";
    private String newWindowText = "This is a new window";

    @BeforeClass
    public static void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "/Users/oshikzigler/Documents/Automation/Selenium/Drivers/Chrome/chromedriver");

        //Disables Chrome info bar
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");

        driver = new ChromeDriver(options);
        driver.manage().window().fullscreen();
        driver.get("http://yoniflenner.net/Xamples/ex_switch_navigation.html");
        driver.manage().timeouts().pageLoadTimeout(500 , TimeUnit.MILLISECONDS);

    }

    @Test
    public void alertTest(){

        driver.findElement(By.id("btnAlert")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert text is: " + alertText);
        alert.accept();
        assertTrue("Alert is not gone" , driver.findElement(By.id("output")).isDisplayed());
    }

    @Test
    public void promptTest(){

        driver.findElement(By.id("btnPrompt")).click();
        Alert prompt = driver.switchTo().alert();
        String promptText = prompt.getText();
        System.out.println("Prompt text is: " + promptText);
        prompt.sendKeys("Oshik Zigler");
        prompt.accept();
        assertTrue("Entered value is not displayed" , driver.findElement(By.id("output")).isDisplayed());
    }

    @Test
    public void confirmBoxTest(){

        driver.findElement(By.id("btnConfirm")).click();
        Alert confirmBox = driver.switchTo().alert();
        String confirmBoxText1 = confirmBox.getText();
        System.out.println("Confirm box text is: " + confirmBoxText1);
        confirmBox.accept();
        String confirmationMessage = driver.findElement(By.id("output")).getText();
        System.out.println("Confirmation message is: " + confirmationMessage);
        assertTrue("Confirmation text is not displayed" , driver.findElement(By.id("output")).isDisplayed());

        driver.findElement(By.id("btnConfirm")).click();
        confirmBox = driver.switchTo().alert();
        String confirmBoxText2 = confirmBox.getText();
        System.out.println("Confirm box text is: " + confirmBoxText2);
        confirmBox.dismiss();
        String rejectionMessage = driver.findElement(By.id("output")).getText();
        System.out.println("Rejection message is: " + rejectionMessage);
        assertTrue("Rejection text is not displayed" , driver.findElement(By.id("output")).isDisplayed());
    }

    @Test
    public void iFreameTest(){

        WebElement iFrameElement = driver.findElement(By.xpath("//iframe[@src='ex_switch_newFrame.html']"));
        driver.switchTo().frame(iFrameElement);
        System.out.println("iFrame text is: " + driver.findElement(By.id("iframe_container")).getText());
        driver.switchTo().defaultContent();
        assertEquals("Title is not correct" , defaultContentTitle , driver.findElement(By.id("title")).getText());
    }

    @Test
    public void newTabTest(){

        String firstTab = driver.getWindowHandle();
        driver.findElement(By.id("btnNewTab")).click();

        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(firstTab);
        driver.switchTo().window(newTab.get(0));
        System.out.println("New tab text is: " + driver.findElement(By.id("new_tab_container")).getText());
        assertEquals("New tab text doesn't match" , expectedNewTabText , driver.findElement(By.id("new_tab_container")).getText());

        driver.close();
        driver.switchTo().window(firstTab);
        assertEquals("Titles don't match" , defaultContentTitle , driver.findElement(By.id("title")).getText());
    }

    @Test
    public void newWindowTest(){

        String firstWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@href='ex_switch_newWindow.html']")).click();

        for (String winHandle: driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        System.out.println("Window text is: " + driver.findElement(By.id("new_window_container")).getText());
        assertEquals("Titles don't match" , newWindowText , driver.findElement(By.id("new_window_container")).getText());

        driver.close();
        driver.switchTo().window(firstWindow);
        assertEquals("Titles don't match" , defaultContentTitle , driver.findElement(By.id("title")).getText());
    }




    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
