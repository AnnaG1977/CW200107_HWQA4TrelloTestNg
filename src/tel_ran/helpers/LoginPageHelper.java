package tel_ran.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageHelper extends PageBase {

    public LoginPageHelper(WebDriver driver){
        super(driver);
    }
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.id("login"), 30);
    }
    public void enterAtlLogin(String login) {
        WebElement loginInput = driver.findElement(By.className("form-field"));
        loginInput.click();
        loginInput.clear();
        loginInput.sendKeys(login);
    }
    public void clickLoginWithAtlassian() {
        waitUntilElementIsClickable(By.id("login"),10);
        driver.findElement(By.id("login")).click();
    }
    public void clickContinueButton() {
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("login-submit")).click();//for a button  continue
    }
    public void enterAtlPasswordAndLogin(String password) {
        waitUntilElementIsClickable(By.id("password"),5); // for a field  password
        waitUntilElementIsClickable(By.id("login-submit"),5); //for a button  enter
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-submit")).click();
    }
    public void enterAtlPassword(String password) {
        waitUntilElementIsClickable(By.id("password"),10); // for a field  password
        waitUntilElementIsClickable(By.id("login"),10); //for a button  enter
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
    }
    public void loginToTrelloAsAtlassian(String login, String password){
        this.enterAtlLogin(login);
        this.clickLoginWithAtlassian();
        this.clickContinueButton();
        this.enterAtlPasswordAndLogin(password);
    }

    public void waitPasswordError() {
        waitUntilElementIsVisible(By
                .xpath("//div[@id = 'login-error']/span"),10);
    }
    public boolean verifyIfPasswordErrorIsCorrect(){
        return driver.findElement(By.xpath("//div[@id = 'login-error']/span")).getText()
                .contains("Неверный адрес электронной почты и/или пароль.\n" +
                        "Требуется помощь, чтобы войти?");
    }
    public void waitLoginErrorWithAccount(){
        waitUntilElementIsVisible(By.xpath("//p[@class='error-message']"),20);
    }
    public boolean verifyIfLoginErrorWithAccount(){
        return driver.findElement(By.xpath("//p[@class='error-message']")).getText()
                .contains("Аккаунт");
    }
}