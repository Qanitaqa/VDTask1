package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
    
    public static WebDriver driver;
    public static Properties prop;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    
    public TestBase(){
        try {
            prop = new Properties();
            // Correctly construct the path to the properties file
            String configFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\Config\\config.properties";
            
            // Print the path for debugging
            System.out.println("Config file path: " + configFilePath);
            
            FileInputStream ip = new FileInputStream(configFilePath);
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Config file not found at " + System.getProperty("user.dir") + "\\src\\main\\java\\Config\\config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void initialization(){
        String browserName = prop.getProperty("browser");
        
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Dr.Nabeel Sabir Khan\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");    
            driver = new ChromeDriver(); 
        } else if(browserName.equals("FF")){
            System.setProperty("webdriver.gecko.driver", "path_to_geckodriver");    
            driver = new FirefoxDriver(); 
        }
        
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        // driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        // driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        
        driver.get(prop.getProperty("url"));
    }
}
