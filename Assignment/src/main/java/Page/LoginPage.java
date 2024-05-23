package Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Base.TestBase;

public class LoginPage extends TestBase {
    
    // Page Factory - OR:
    @FindBy(name = "user-name")
    WebElement username;
    
    @FindBy(name = "password")
    WebElement password;
    
    @FindBy(xpath = "//*[@id='login-button']")
    WebElement loginBtn;
    
    @FindBy(xpath = "//button[contains(text(),'Sign Up')]")
    WebElement signUpBtn;
    
    @FindBy(xpath = "//*[@id=\"login_button_container\"]")
    //WebElement crmLogo;
    WebElement errorMessage;
    
    // Initializing the Page Objects:
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    
    public String validateLoginPageTitle() {
        return driver.getTitle();
    }
    
    public HomePage login(String un, String pwd) {
        username.sendKeys(un);
        password.sendKeys(pwd);
        // loginBtn.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginBtn);
        
        return new HomePage();
    }
 // Method to attempt login with invalid credentials
    public void loginWithInvalidPassword(String un, String pwd) {
        username.sendKeys(un);
        password.sendKeys(pwd);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginBtn);
    }
    
    // Method to get the error message
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
