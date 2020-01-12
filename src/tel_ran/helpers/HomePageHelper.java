package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper extends PageBase {
    @FindBy (xpath = "//a[@href='/login']" )
    WebElement loginIcon;

    public HomePageHelper(WebDriver driver){
        super( driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginIcon, 50);
    }

    public void openLoginPage() {
        loginIcon.click();
    }
}
