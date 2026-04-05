package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    
    private final By accountButtonAfterLogin = By.cssSelector("button.c-button.t-header__my-account.t-header__fades-in.is--tertiary-btn");
    private final By welcomeText = By.xpath("//*[contains(@class,'welcome') or contains(text(),'Welcome')]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoggedIn() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(accountButtonAfterLogin)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWelcomeMessageDisplayed() {
        var accountBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(accountButtonAfterLogin)
        );
        new Actions(driver).moveToElement(accountBtn).perform();

        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(welcomeText)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}