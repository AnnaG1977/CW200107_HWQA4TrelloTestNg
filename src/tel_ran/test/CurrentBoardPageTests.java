package tel_ran.test;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import tel_ran.helpers.BoardsPageHelper;
import tel_ran.helpers.HomePageHelper;
import tel_ran.helpers.LoginPageHelper;
import tel_ran.helpers.TheBoardPageHelper;

import java.util.List;

public class CurrentBoardPageTests extends TestBase {
    public static final String BOARD = "New";
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardPage;
    TheBoardPageHelper theBoardPage;

    @BeforeMethod
    public void initTest(){

       // homePage = new HomePageHelper(driver);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);

       // loginPage = new LoginPageHelper(driver);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);

      //  boardPage = new BoardsPageHelper(driver);
        boardPage = PageFactory.initElements(driver,BoardsPageHelper.class);

      //  theBoardPage = new TheBoardPageHelper(driver);
        theBoardPage = PageFactory.initElements(driver,TheBoardPageHelper.class);



        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD);
        boardPage.waitUntilPageIsLoaded();

    }

   @Test
    public void createNewList()  {
       boardPage.openBoard(BOARD);
       theBoardPage.waitUntilPageIsLoaded();
       int q = theBoardPage.quantityOfLists(); // quantity before added
       theBoardPage.addAnotherList();
       int qEnd = theBoardPage.quantityOfLists(); // quantity after added
       Assert.assertEquals(q+1,qEnd);
    }
    @Test
    public void  addFirstCardInNewList () /*throws InterruptedException*/ {
        //----Open 'QA 4 Auto' board
       // driver.findElement(By.xpath("//div[@title='QA4Haifa']/..")).click();
        boardPage.openBoard("QA4Haifa");

       // waitUntilElementIsClickable(By.cssSelector(".placeholder"), 30);
        theBoardPage.waitUntilPageIsLoaded();

        //--------Get qantity of 'Add another card' buttons at the beginning----
        int quantityAddAnotherButtonBeg = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        //-----Add a new list------
        theBoardPage.addAnotherList();
       /* driver.findElement(By.cssSelector(".placeholder")).click();
          waitUntilElementIsVisible(By.cssSelector(".list-name-input"), 10);
          driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List14012020");
        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"), 10);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        waitUntilElementIsClickable(By.cssSelector("a.js-cancel-edit"), 10);
        driver.findElement(By.cssSelector("a.js-cancel-edit")).click();
        waitUntilElementIsVisible(By.cssSelector(".placeholder"), 10);*/


        //----- Get the last 'Add card' button----

      //  waitUntilAllElementsAreVisible(By.xpath("//span[@class = 'js-add-a-card']"), 15);
      theBoardPage.waitUntilAdAnotherCardButtonIsVisible();

        List<WebElement> listAddCardButtons = driver.findElements(By.xpath("//span[@class = 'js-add-a-card']"));

        int sizeLstAddCardButtons = listAddCardButtons.size();
        WebElement lastAddCardButton = listAddCardButtons.get(sizeLstAddCardButtons - 1);


        //----Add a first card for any new list
        lastAddCardButton.click();


       /* waitUntilElementIsClickable(By
                .xpath("//input[@class='primary confirm mod-compact js-add-card']"), 10);
        driver.findElement(By
                .xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys("text14012020");
        driver.findElement(By
                .xpath("//input[@class='primary confirm mod-compact js-add-card']")).click();

        waitUntilElementIsClickable(By.cssSelector("a.js-cancel"), 10);
        driver.findElement(By.cssSelector("a.js-cancel")).click();*/
        theBoardPage.enterTitleForThisCard();
        //--------Get quantity of 'Add another card' buttons at the end----
       // waitUntilAllElementsAreVisible(By.xpath("//span[@class= 'js-add-another-card']"), 10);
        theBoardPage.waitUntilAdAnotherCardButtonIsVisible();
        int quantityAddAnotherButtonEnd = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        Assert.assertEquals(quantityAddAnotherButtonBeg + 1, quantityAddAnotherButtonEnd);
    }
    @Test
    public void deleteList() /*throws InterruptedException */{
       // ------------------ open  board with name "New"
        driver.findElement(By.xpath("//div[@title='New']/..")).click();

        waitUntilElementIsClickable(By.cssSelector(".placeholder"),10);
       //------------ list recalculation
        List<WebElement> listEvents = driver.findElements(
                By.xpath("//div[@id= 'board']//h2[@class='list-header-name-assist js-list-name-assist']/.."));
        int counter =0;
        for (WebElement element : listEvents) { counter++;}

        System.out.println("before  lists " + counter);
        //------- there isn't a list. add  new list
        if (counter==0){
            driver.findElement(By.cssSelector(".placeholder")).click();// add another column

            waitUntilElementIsClickable(By.cssSelector(".placeholder"),10);
            driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List");//Enter the title of the list
            driver.findElement(By.xpath("//input[@type='submit']")).click();// add the list

            waitUntilElementIsClickable(By.cssSelector(".placeholder"),10);
        }
        // ----- delete list -----
        WebElement dots = driver.findElement(By.xpath
                ("//a[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']"));
        dots.click();

        waitUntilElementIsClickable(By.xpath("//a[@class='js-close-list']"),10);
        WebElement closeList = driver.findElement(By.xpath("//a[@class='js-close-list']"));
        closeList.click();
       // -----------check that the list has been removed
        List<WebElement> listEventsAfter = driver.findElements(
                By.xpath("//div[@id= 'board']//h2[@class='list-header-name-assist js-list-name-assist']/.."));
        counter = 0;
        for (WebElement element : listEventsAfter) { counter++;}

        System.out.println("after  lists " + counter);
    }
    @Test
    public void CopyList(){
        // ------------------ open  board with name "New"
        driver.findElement(By.xpath("//div[@title='New']/..")).click();

        waitUntilElementIsClickable(By.cssSelector(".placeholder"),10);
        int q = driver.findElements(By.cssSelector(".js-list-name-input")).size();
        if (q==0){
            driver.findElement(By.cssSelector(".placeholder")).click();// add another column
            driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List Anna");//Enter the title of the list
            driver.findElement(By.xpath("//input[@type='submit']")).click();// add the list
            waitUntilElementIsClickable(By.xpath("//span[@class='js-add-a-card']"),10);
            driver.findElements(By.xpath("//span[@class='js-add-a-card']")).get(0).click();
        }

        q = driver.findElements(By.cssSelector(".js-list-name-input")).size();
        driver.findElement(By.xpath("//a[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']"))
                .click();
        waitUntilElementIsClickable(By.cssSelector(".js-copy-list"),10);
        driver.findElement(By.xpath("//a[@class='js-copy-list']")).click();
        waitUntilElementIsClickable(By.cssSelector(".primary wide js-submit"),10);
        driver.findElement(By.xpath("//input[@class='primary wide js-submit']")).click();
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),10);
        int qEnd = driver.findElements(By.cssSelector(".js-list-name-input")).size();
        List<WebElement> listName = driver.findElements(
                By.xpath("//textarea[@class='list-header-name mod-list-name js-list-name-input']"));
        String name1 = listName.get(0).getText();
        String name2 = listName.get(1).getText();

        System.out.println("name1 " + name1);
        System.out.println("name2 " +name2);

        int count =0;
        if (listName.get(0).getText().equals(name2)) count++;
        if (qEnd==q+1)count++;
        Assert.assertEquals(count,2, "There isn't copy of list");
    }
}
