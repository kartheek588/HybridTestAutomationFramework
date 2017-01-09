/**
 * 
 */
package Com.HybridTestAutomationFramework.core.platform;

import java.net.MalformedURLException;
import java.net.URL;

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
public class DriverFactory {
	private String serverIP;

	/**
	 * <p>
	 * creates a Chrome WebDriver in local machine
	 * </p>
	 * 
	 * @return Returns WebDriver object
	 */
	
	public WebDriver getDriver() {

		return getDriver(Browser.CHROME);
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	/**
	 * <p>
	 * creates a WebDriver in local machine based on parameter
	 * </p>
	 * 
	 * @param pass
	 *            browser number ex: DriverFactory.Firefox
	 * @return Returns WebDriver object
	 */
	public WebDriver getDriver(Browser browser) {
		WebDriver driver = null;
		DesiredCapabilities capabilities = getCapabilities(browser);
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriver(capabilities);
			break;
		case IE:
			driver = new InternetExplorerDriver(capabilities);
			break;
		case CHROME:
			driver = new ChromeDriver(capabilities);
			break;
		}
		driver.manage().window().maximize();
		return driver;

	}

	
	/**
	 * <p>
	 * creates a WebDriver in local machine based on parameter
	 * </p>
	 * 
	 * @param pass
	 *            browser number ex: DriverFactory.Firefox
	 * @return Returns WebDriver object
	 * @throws MalformedURLException
	 */
	public RemoteWebDriver getDriver(Browser browser, OS os)  {
		RemoteWebDriver driver = null;
		DesiredCapabilities capabilities = getCapabilities(browser);
		
		switch(os)
		{
			case WINDOWS:  
				capabilities.setPlatform(Platform.WINDOWS);
				break;
			case UBUNTU:
				capabilities.setPlatform(Platform.LINUX);
				break;
		}
		switch (browser) {
		case FIREFOX:
			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			break;
		case IE:

			break;
		case CHROME:
			capabilities = DesiredCapabilities.chrome();
			// Create object of ChromeOptions class
			ChromeOptions options = new ChromeOptions();

			// add parameter which will disable the extension
			options.addArguments("--disable-extensions");
			options.addArguments("--test-type");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setBrowserName("chrome");
			break;
		}
		try {
			driver = new RemoteWebDriver(new URL("http://"+serverIP+":4444/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		// WaitUtilities.setImplicitWaits(driver, 60);

		return driver;

	}
	private DesiredCapabilities getCapabilities(Browser browser) {
		DesiredCapabilities capabilities = null;

		switch (browser) {
		case IE:
			System.setProperty("webdriver.ie.driver", ".\\src\\main\\resources\\IEDriverServer.exe");
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", ".\\src\\main\\resources\\geckodriver.exe");
			capabilities = DesiredCapabilities.firefox();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
			capabilities = DesiredCapabilities.chrome();
			// Create object of ChromeOptions class
			ChromeOptions options = new ChromeOptions();

			// add parameter which will disable the extension
			options.addArguments("--disable-extensions");
			options.addArguments("--test-type");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			break;
		}

		return capabilities;
	}

}
