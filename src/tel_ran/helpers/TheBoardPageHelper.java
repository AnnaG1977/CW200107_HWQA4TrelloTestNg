package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TheBoardPageHelper extends PageBase {
    public TheBoardPageHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),20);
    }
    public void addAnotherList(){
        driver.findElement(By.cssSelector(".placeholder")).click();// add another list
        driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List Anna");//Enter the title of the list
        driver.findElement(By.xpath("//input[@type='submit']")).click();// add the list
        waitUntilElementIsClickable(By.xpath("//span[@class='js-add-a-card']"),10);
        driver.findElements(By.xpath("//span[@class='js-add-a-card']")).get(0).click();
    }
    public int quantityOfLists(){
        return driver.findElements(By.xpath("//h2")).size();
    }

}
