package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By logo          = By.cssSelector("div.t-header__logo");
    private final By accountLink   = By.cssSelector("button.c-button.t-header__my-account");
    private final By myBagLink     = By.cssSelector("button.c-button.qa-header__cart");
    private final By signInLink    = By.linkText("Sign In / Register");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
    }

    public boolean isAccountLinkDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountLink)).isDisplayed();
    }

    public boolean isMyBagLinkDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(myBagLink)).isDisplayed();
    }

    public void clickAccountLink() {
        wait.until(ExpectedConditions.elementToBeClickable(accountLink)).click();
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
    }
    
    public void verifyHomePageLoaded() {
        Assert.assertTrue(isLogoDisplayed(), "Logo is not displayed");
        Assert.assertTrue(isAccountLinkDisplayed(), "Account link is not displayed");
        Assert.assertTrue(isMyBagLinkDisplayed(), "My Bag link is not displayed");
    }

    public void goToLoginPage() {
        clickAccountLink();
        clickSignIn();
    }
}