/**
 * 
 */
package Com.HybridTestAutomationFramework.core.platform;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author Kartheek
 *
 */
public class GridDriver {
	
	
	/**
	 * <p>
	 * creates a Firefox WebDriver in local mechine
	 * </p>
	 * @return Returns WebDriver object
	 */
	public  WebDriver getDriver()
	{
		
		return getDriver(BrowserFlavour.Firefox);
	}
	
	/**
	 * <p>
	 * creates a WebDriver in local machine based on parameter
	 * </p>
	 * @param pass browser number ex: GridDriver.Firefox
	 * @return Returns WebDriver object
	 */
	public  WebDriver getDriver(int browser)
	{
		WebDriver driver=null;
		DesiredCapabilities capabilities=null;
		switch (browser){
			case BrowserFlavour.Firefox: 
								driver= new FirefoxDriver(); 
								break;
			case BrowserFlavour.InternetExplorer:
								//System.setProperty("webdriver.ie.driver","C:\\Users\\o_nikitha\\Downloads\\IEDriverServer_Win32_2.35.3\\IEDriverServer.exe");
								driver = new InternetExplorerDriver();
								break;
			case BrowserFlavour.Chrome:
				System.out.println((new File(".")).getAbsolutePath());
								//System.setProperty("webdriver.chrome.driver","C:\\Users\\Kartheek\\git\\Titanic\\Drivers\\chromedriver.exe");
								capabilities = DesiredCapabilities.chrome();
								// Create object of ChromeOptions class
								ChromeOptions options = new ChromeOptions();
								 
								// add parameter which will disable the extension
								options.addArguments("--disable-extensions");
								options.addArguments("--test-type");
								capabilities.setCapability(ChromeOptions.CAPABILITY, options);
								driver = new ChromeDriver(capabilities);
								break;
		}
		return driver;
		
		
	}
	
	/**
	 * <p>
	 * creates a WebDriver in local machine based on parameter
	 * </p>
	 * @param pass browser number ex: GridDriver.Firefox
	 * @return Returns WebDriver object
	 * @throws MalformedURLException 
	 */
	public  RemoteWebDriver getDriver(int Browser, String Os) throws MalformedURLException
	{
		RemoteWebDriver driver=null;
		DesiredCapabilities capabilities=null;
		
		switch (Browser){
		case BrowserFlavour.Firefox: 
			 				capabilities = DesiredCapabilities.firefox();
			 				capabilities.setBrowserName("firefox");
			 				//capabilities.setPlatform(Platform.WINDOWS);
							driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities); 
							break;
		case BrowserFlavour.InternetExplorer:
			
							break;
		case BrowserFlavour.Chrome:
			capabilities = DesiredCapabilities.chrome();
			// Create object of ChromeOptions class
			ChromeOptions options = new ChromeOptions();
			 
			// add parameter which will disable the extension
			options.addArguments("--disable-extensions");
			options.addArguments("--test-type");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setBrowserName("chrome");
//			capabilities.setPlatform(Platform.WINDOWS);
		driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities); 
		
							break;
	}
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	return driver;
		
	}
	
	

}
