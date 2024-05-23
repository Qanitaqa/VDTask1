package Testcases;

import Base.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Page.HomePage;
import Page.LoginPage;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homepage;
   
    public LoginPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void loginPageTitleTest() {
        String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title, "Swag Labs");
    }

    @Test(priority = 2)
    public void invalidLoginTest() {
        loginPage.loginWithInvalidPassword(prop.getProperty("validUsername"), "invalidPassword");
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service"; // Update this based on the actual message
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message did not match.");
    }
    

    @Test(priority = 3)
    public void loginTest() {
        homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL did not match. Login might have failed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
