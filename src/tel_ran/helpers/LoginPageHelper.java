package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {

    @FindBy(id = "user")
    WebElement userField;

    @FindBy(id="login")
    WebElement loginButton;

    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Войти')]")
    WebElement theSecondLoginButton;

    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Продолжить')]")
    WebElement continueButton;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(xpath = "//div[@id = 'login-error']/span")
   WebElement passwordError;
    @FindBy(xpath = "//p[@class='error-message']")
    WebElement loginErrorWithAccount;

    public LoginPageHelper(WebDriver driver){
        super(driver);
    }
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginButton, 30);
    }

    public void enterAtlLogin(String login) {
//        WebElement loginInput = driver.findElement(By.className("form-field"));
//        userField.click();
//        userField.clear();
//        userField.sendKeys(login);
        enterValueToTheField(userField,login);
    }

    public void clickLoginWithAtlassian() {
        waitUntilElementIsClickable(loginButton,10);
        loginButton.click();
    }
    public void clickContinueButton() {
        waitUntilElementIsClickable(continueButton,30);
        continueButton.click();
    }
    public void enterAtlPasswordAndLogin(String password) {
//        waitUntilElementIsClickable(By.id("password"),5); // for a field  password
//        waitUntilElementIsClickable(By.id("login-submit"),5); //for a button  enter
        waitUntilElementIsClickable(passwordField,5);
        waitUntilElementIsClickable(continueButton,5);
//        driver.findElement(By.id("password")).sendKeys(password);
//        driver.findElement(By.id("login-submit")).click();
        enterValueToTheField(passwordField,password);
        theSecondLoginButton.click();
    }

    public void loginToTrelloAsAtlassian(String login, String password){
        this.enterAtlLogin(login);
        this.clickLoginWithAtlassian();
        this.clickContinueButton();
        this.enterAtlPasswordAndLogin(password);
    }

    public void waitPasswordError() {
        waitUntilElementIsVisible(passwordError,10);
    }
    public boolean verifyIfPasswordErrorIsCorrect(){
        return passwordError.getText()
                .contains("Неверный адрес электронной почты и/или пароль.\n" +
                        "Требуется помощь, чтобы войти?");
    }
    public void waitLoginErrorWithAccount(){
        waitUntilElementIsVisible(loginErrorWithAccount,20);
    }
    public boolean verifyIfLoginErrorWithAccount(){
        return loginErrorWithAccount.getText()
                .contains("Аккаунт");
    }

    public void enterAtlPassword(String password) {
        waitUntilElementIsClickable(By.id("password"),5); // for a field  password
//        waitUntilElementIsClickable(By.id("login-submit"),5); //for a button  enter
        waitUntilElementIsClickable(passwordField,5);
        waitUntilElementIsClickable(continueButton,5);
//        driver.findElement(By.id("password")).sendKeys(password);
//        driver.findElement(By.id("login-submit")).click();
        enterValueToTheField(passwordField,password);
        loginButton.click();
    }
}
