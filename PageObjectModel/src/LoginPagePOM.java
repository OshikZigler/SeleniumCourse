import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPagePOM {

    private static WebElement element = null;

    public static WebElement userName(WebDriver driver) {
        element = driver.findElement(By.name("username2"));

        return element;
    }

    public static WebElement password(WebDriver driver) {
        element = driver.findElement(By.name("password2"));

        return element;
    }

    public static WebElement submit(WebDriver driver) {
        element = driver.findElement(By.id("submit"));

        return element;
    }

}
