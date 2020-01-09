package tel_ran.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tel_ran.helpers.BoardsPageHelper;
import tel_ran.helpers.HomePageHelper;
import tel_ran.helpers.LoginPageHelper;

public class LoginPageTests extends TestBase {

    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardPage;

    @BeforeMethod
    public void initTests(){
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardPage = new BoardsPageHelper(driver);
    }
    @Test
    public void loginToTrelloPositive()  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD);
        boardPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardPage.verifyIfBoardsIconIsDisplayed());
        Assert.assertTrue(boardPage.verifyIfPersonalBoardsHeaderIsDisplayed());
    }
    @Test
    public void loginIncorrectPassNegative()  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD+1);
        loginPage.waitPasswordError();
        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(),"Error massage is not correct");
    }
    @Test
    public void loginIncorrectLoginNegative()  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterAtlLogin("aqqqqq");
        loginPage.enterAtlPassword(PASSWORD);
        loginPage.waitLoginErrorWithAccount();
        Assert.assertTrue(loginPage.verifyIfLoginErrorWithAccount());
    }

}
