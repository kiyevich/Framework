package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static WebDriver driver;


    private DriverSingleton(){}

    public static WebDriver getDriver(){
        if (null == driver){
            switch (System.getProperty("browser")){
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default: {
                   WebDriverManager.chromedriver().version("87").setup();
                    driver = new ChromeDriver();
//                    System.setProperty("webdriver.chrome.driver", "D://WebDriver/drivers/chromedriver.exe");
//                     driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }



        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
