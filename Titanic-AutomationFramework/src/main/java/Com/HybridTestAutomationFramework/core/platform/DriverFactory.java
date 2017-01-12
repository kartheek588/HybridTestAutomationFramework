/**
 * 
 */
package Com.HybridTestAutomationFramework.core.platform;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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

			capabilities.setBrowserName("firefox");
			
			FirefoxProfile profile= getFirefoxProfile();
			try {
				driver = new RemoteWebDriver(new URL("http://"+serverIP+":4444/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	private FirefoxProfile getFirefoxProfile() {
		FirefoxProfile profile= new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", "C:\\Users\\Kartheek\\Desktop");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "images/jpeg, application/pdf,application/x-gzip");

		return profile;
	}

	private DesiredCapabilities getCapabilities(Browser browser) {
		DesiredCapabilities capabilities = null;
		String downloadFilepath ="C:\\Users\\Kartheek\\Desktop";

		switch (browser) {
		case IE:
			System.setProperty("webdriver.ie.driver", ".\\src\\main\\resources\\IEDriverServer.exe");
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", ".\\src\\main\\resources\\geckodriver.exe");
			capabilities = DesiredCapabilities.firefox();
			FirefoxProfile profile= new FirefoxProfile();
			//set default download
			profile.setPreference("browser.download.folderList", 2); // tells it not to use default Downloads directory
			profile.setPreference("browser.download.manager.showWhenStarting", false); //turns of showing download progress
			profile.setPreference("browser.download.dir",downloadFilepath ); //sets the directory for downloads
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream, images/jpeg, application/pdf,application/x-gzip"); //tells Firefox to automatically download the files of the selected mime-types
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
			capabilities = DesiredCapabilities.chrome();
			// Create object of ChromeOptions class
			ChromeOptions options = new ChromeOptions();

			// add parameter which will disable the extension
			options.addArguments("--disable-extensions");
			options.addArguments("--test-type");
			//default download
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("download.default_directory", downloadFilepath);
			options.setExperimentalOption("prefs", chromePrefs);
			
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			break;
		}

		return capabilities;
	}

}
