package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageHelper extends PageBase {

    public HomePageHelper(WebDriver driver){
        super( driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//a[@href='/login']"), 50);
    }

    public void openLoginPage() {
        WebElement passwordInput = driver.findElement(By.xpath("//a[@href='/login']"));
        passwordInput.click();
    }
}
