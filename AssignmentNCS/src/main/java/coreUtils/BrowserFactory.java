package coreUtils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	public static WebDriver driver;
	public static Properties prop;
	private static String defaultConfigFile = "/src/test/resources/config/Config.properties";
	public static String projPath = System.getProperty("user.dir");
	
	//Constructor
	public BrowserFactory() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(projPath+defaultConfigFile);
			prop.load(fis);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	// initialization for browser
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			   WebDriverManager manager = WebDriverManager.chromedriver().driverVersion("123.0.6312.106");
		        manager.setup();

		        // Now you can get the actual driver path
		        String driverPath = manager.getDownloadedDriverPath();
		        System.out.println("Driver in use: " + driverPath);

			driver = new ChromeDriver();
			
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	//close browser session
	public static void closeBrowserSession() {
		driver.quit();
	}
}
