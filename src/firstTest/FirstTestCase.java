package firstTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTestCase {

    @Test
    public void testLogin() {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\2022\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to the URL
        driver.get("https://certwcs.frontgate.com/?aka_bypass=5C73514EE7A609054D81DE61DD9CA3D6");        
 
        // Step 2: Verify the logo is displayed
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.t-header__logo")));
        Assert.assertTrue(logo.isDisplayed(), "FAIL: Logo is not displayed");
 
        // Step 3: Verify "My Account" and "My Bag" links are displayed
        WebElement accountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.c-button.t-header__my-account")));
        Assert.assertTrue(accountLink.isDisplayed(), "FAIL: Sign In link is not displayed");
 
        WebElement myBagLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.c-button.qa-header__cart")));
        Assert.assertTrue(myBagLink.isDisplayed(), "FAIL: My Bag link is not displayed");
        
        // Step 4: Click on "My Account" button
        accountLink.click();
        
        // Step 5: Click on "Sign In / Register" button
        WebElement signIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign In / Register")));
        signIn.click();
        
        // Step 6: Enter valid email and password and click "SIGN IN"
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        email.sendKeys("m.hneef@student.aaup.edu");
        
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#password")));
        password.sendKeys("Mi@12345678");
        
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.login-button.btn.btn-primary")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

        
        // Step 7: Verify the user is logged in successfully
        WebElement myAccountBtnAfterLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.c-button.t-header__my-account.t-header__fades-in.is--tertiary-btn")));
        Actions actions = new Actions(driver);
        actions.moveToElement(myAccountBtnAfterLogin).perform();
        
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Welcome Misk')]")));
        Assert.assertTrue(account.isDisplayed(), "FAIL: My account is not logged in");
        
        // Step 8: Close the browser
        driver.quit();
    }
}