package tel_ran.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tel_ran.helpers.HomePageHelper;

public class TestBase {
    public static final String LOGIN = "annagorodetskaya@yandex.ru";
    public static final String PASSWORD = "bkmrf24092011";
    WebDriver driver;
    HomePageHelper homePage;
    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        //homePage =new HomePageHelper(driver);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);

        //===========Enter to Trello====
        driver.get("https://trello.com/");
        driver.manage().window().maximize();
        /*waitUntilElementIsClickable(By.xpath ("//a[@href='/login']"),50);*/
        homePage.waitUntilPageIsLoaded();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(By locator,int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
