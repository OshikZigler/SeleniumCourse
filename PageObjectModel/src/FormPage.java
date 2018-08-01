import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage {
    private static WebElement element = null;

    public static WebElement occupation(WebDriver driver) {
        element = driver.findElement(By.id("occupation"));

        return element;
    }

    public static WebElement age(WebDriver driver) {
        element = driver.findElement(By.id("age"));

        return element;
    }

    public static WebElement location(WebDriver driver) {
        element = driver.findElement(By.id("location"));

        return element;
    }

    public static WebElement clickMe(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@value='Click Me']"));
        return element;
    }
}

