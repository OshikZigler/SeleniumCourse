import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserPage {

    private static WebElement element = null;

    public static WebElement clickMe(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@type='button']"));

        return element;
    }
}
