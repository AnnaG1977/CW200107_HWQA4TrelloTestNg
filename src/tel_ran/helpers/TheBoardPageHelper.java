package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class TheBoardPageHelper extends PageBase {
    @FindBy(css= ".placeholder")
    WebElement addAnotherList;
    @FindBy (css = ".list-name-input")
    WebElement enterListTitle;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement addListButton;
//    @FindBy(xpath = "//span[@class='js-add-a-card']")
//    WebElement addCardButton;

    @FindBy(xpath = "//input[@class='primary confirm mod-compact js-add-card']")
    WebElement addCardButton;

    @FindBy(css = "a.js-cancel-edit")
    WebElement cancelAddList;

    @FindBy(xpath ="//textarea[@placeholder='Enter a title for this card…']")
    WebElement enterTitleForThisCard;

    @FindBy(css = "a.js-cancel")
    WebElement cancelAddCard;

    @FindBy(xpath = "//h2")
    WebElement listHeader;
    @FindBy(xpath =  "//span[@class= 'js-add-another-card']")
    WebElement addAnotherCardButton;

    public TheBoardPageHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(addAnotherList,20);
    }

    public void waitUntilAddListButtonIsVisible(){
        waitUntilElementIsVisible(addListButton,20);
    }
    public void waitUntilAddAnotherCardButtonISVisible(){
        waitUntilElementIsVisible(addAnotherCardButton,10);
    }
    public void waitUntilAdAnotherCardButtonIsVisible(){
        waitUntilElementIsVisible(addAnotherCardButton,20);
    }

    public void addAnotherList(){
       // driver.findElement(By.cssSelector(".placeholder")).click();// add another list
        addAnotherList.click();
      //  driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List Anna");//Enter the title of the list
        enterValueToTheField(enterListTitle,"New List Anna4");
        //driver.findElement(By.xpath("//input[@type='submit']")).click();// add the list
        addListButton.click();
        waitUntilElementIsClickable(addCardButton,10);

      //  driver.findElements(By.xpath("//span[@class='js-add-a-card']")).get(0).click();
      //  addCardButton.click();
        cancelAddList.click();
    }
    public int quantityOfLists(){

      return driver.findElements(By.xpath("//h2")).size();


    }

    public  void enterTitleForThisCard(){

       // waitUntilElementIsClickable(By
       //         .xpath("//input[@class='primary confirm mod-compact js-add-card']"), 10);
        waitUntilElementIsClickable( addCardButton,10);
       // driver.findElement(By
      //          .xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys("text14012020");

        enterValueToTheField(enterTitleForThisCard,"new card");
       // driver.findElement(By
       //         .xpath("//input[@class='primary confirm mod-compact js-add-card']")).click();
        addCardButton.click();
       // waitUntilElementIsClickable(By.cssSelector("a.js-cancel"), 10);
        waitUntilElementIsClickable(cancelAddCard,10);
       // driver.findElement(By.cssSelector("a.js-cancel")).click();
        cancelAddCard.click();

    }


}
