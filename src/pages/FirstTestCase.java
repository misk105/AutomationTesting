package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTestCase {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfigReader.getChromePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLogin() {

        String url      = ConfigReader.getAppUrl();
        String email    = ConfigReader.getUserEmail();
        String password = ConfigReader.getUserPassword();

        HomePage    homePage    = new HomePage(driver);
        LoginPage   loginPage   = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Step 1: Navigate to the application
        driver.get(url);

        // Step 2: Verify homepage elements are displayed
        Assert.assertTrue(homePage.isLogoDisplayed(),"FAIL: Logo is not displayed");

        Assert.assertTrue(homePage.isAccountLinkDisplayed(),"FAIL: Account link is not displayed");

        Assert.assertTrue(homePage.isMyBagLinkDisplayed(),"FAIL: My Bag link is not displayed");

        // Step 3: Open the sign-in flow
        homePage.clickAccountLink();
        homePage.clickSignIn();

        // Step 4: Enter credentials and submit
        loginPage.login(email, password);

        // Step 5: Validate successful login
        Assert.assertTrue(accountPage.isLoggedIn(),"FAIL: Post-login account button is not visible — login may have failed");

        // Secondary check: a welcome message element is present in the dropdown
        Assert.assertTrue(accountPage.isWelcomeMessageDisplayed(),"FAIL: Welcome message element is not displayed after login");
    }
}