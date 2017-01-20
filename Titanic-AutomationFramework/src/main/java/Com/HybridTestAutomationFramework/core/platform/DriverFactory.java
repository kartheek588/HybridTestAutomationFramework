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
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import Com.HybridTestAutomationFramework.core.utilities.WaitUtilities;

/**
 * @author Kartheek
 *
 */
public class DriverFactory {
	private String serverIP;

	/**
	 * <p>
	 * creates a HtmlUnit WebDriver in local machine
	 * </p>
	 * 
	 * @return Returns WebDriver object
	 */

	public WebDriver getDriver() {

		return new HtmlUnitDriver(true);

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
	 *            browser number ex: Browser.Firefox
	 * @return Returns WebDriver object
	 */
	public WebDriver getDriver(Browser browser) {
		WebDriver driver = null;
		DesiredCapabilities capabilities = getCapabilities(browser);
		switch (browser) {
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", ".\\src\\main\\resources\\geckodriver.exe");
			driver = new FirefoxDriver(capabilities);
			break;
		case IE:
			System.setProperty("webdriver.ie.driver", ".\\src\\main\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
			driver = new ChromeDriver(capabilities);
			break;
		default:
			driver = new HtmlUnitDriver();
		}
		
		return driver;

	}

	/**
	 * <p>
	 * creates a WebDriver in local machine based on parameter
	 * </p>
	 * 
	 * @param browser
	 *            browser type ex: Browser.Firefox
	 * @param os
	 *            os type ex: OS.WINDOWS
	 * @return Returns WebDriver object
	 * @throws MalformedURLException
	 */
	public RemoteWebDriver getDriver(Browser browser, OS os) {
		RemoteWebDriver driver = null;
		DesiredCapabilities capabilities = getCapabilities(browser);

		switch (os) {
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
			break;
		case IE:
			capabilities.setBrowserName("Internet Explorer");
			break;
		case CHROME:
			capabilities.setBrowserName("chrome");
			break;
		}
		try {
			driver = new RemoteWebDriver(new URL("http://" + serverIP + ":4444/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		WaitUtilities.setImplicitWaits(driver, 60);

		return driver;

	}

	private DesiredCapabilities getCapabilities(Browser browser) {
		DesiredCapabilities capabilities = null;
		String downloadFilepath = "C:\\Users\\Kartheek\\Desktop";
		switch (browser) {
		case IE:

			capabilities = DesiredCapabilities.internetExplorer();
			// settings to ignore SSL certificate error
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		case FIREFOX:
			capabilities = DesiredCapabilities.firefox();
			FirefoxProfile profile = new FirefoxProfile();
			// profile settings for downloading files
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.dir", downloadFilepath);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"images/jpeg, application/pdf,application/x-gzip");
			profile.setPreference("browser.helperApps.neverAsk.openFile",
					"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.setPreference("browser.download.manager.focusWhenStarting", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			profile.setPreference("browser.download.manager.closeWhenDone", false);

			// settings to ignore SSL certificate error
			profile.setAcceptUntrustedCertificates(true);

			capabilities.setCapability(FirefoxDriver.PROFILE, profile);

			break;
		case CHROME:

			capabilities = DesiredCapabilities.chrome();
			// Create object of ChromeOptions class
			ChromeOptions options = new ChromeOptions();
			// add parameter which will disable the extension
			options.addArguments("--disable-extensions");
			options.addArguments("--test-type");

			// default download
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("browser.download.folderList", 2);
			chromePrefs.put("browser.download.manager.showWhenStarting", false);
			chromePrefs.put("browser.download.dir", downloadFilepath);
			chromePrefs.put("browser.helperApps.neverAsk.saveToDisk",
					"images/jpeg, application/pdf,application/x-gzip");
			chromePrefs.put("browser.helperApps.neverAsk.openFile",
					"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
			chromePrefs.put("browser.helperApps.neverAsk.saveToDisk",
					"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
			chromePrefs.put("browser.helperApps.alwaysAsk.force", false);
			chromePrefs.put("browser.download.manager.alertOnEXEOpen", false);
			chromePrefs.put("browser.download.manager.focusWhenStarting", false);
			chromePrefs.put("browser.download.manager.useWindow", false);
			chromePrefs.put("browser.download.manager.showAlertOnComplete", false);
			chromePrefs.put("browser.download.manager.closeWhenDone", false);
			options.setExperimentalOption("prefs", chromePrefs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			// settings to ignore SSL certificate error
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			break;
		}

		return capabilities;
	}

}
