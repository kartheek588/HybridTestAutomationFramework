package Com.HybridTestAutomationFramework.test.core.platform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import Com.HybridTestAutomationFramework.core.platform.Browser;
import Com.HybridTestAutomationFramework.core.platform.DriverFactory;
import Com.HybridTestAutomationFramework.core.platform.OS;

public class TestDriverFactory {
	@Test
	public void testDefaultDriver()
	{
		WebDriver driver= (new DriverFactory()).getDriver();
		driver.get("https://www.google.com");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	@Test
	public void testChromeDriver()
	{
		WebDriver driver= (new DriverFactory()).getDriver(Browser.CHROME);
		driver.get("https://www.google.com");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	@Test
	public void testIEDriver()
	{
		WebDriver driver= (new DriverFactory()).getDriver(Browser.IE);
		driver.get("https://www.google.com");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	@Test
	public void testFirefoxDriver()
	{
		WebDriver driver= (new DriverFactory()).getDriver(Browser.FIREFOX);
		driver.get("https://www.google.com");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	@Test(groups={"Grid"})
	public void testWindowsFirefoxDriver()
	{	
		DriverFactory df=new DriverFactory();
		df.setServerIP("35.165.84.206");
		RemoteWebDriver driver= df.getDriver(Browser.FIREFOX,OS.WINDOWS);
		driver.get("https://www.google.com");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	@Test(groups={"Grid"})
	public void testWindowsChromeDriver()
	{
		DriverFactory df=new DriverFactory();
		df.setServerIP("35.165.84.206");
		RemoteWebDriver driver= df.getDriver(Browser.CHROME,OS.WINDOWS);
		driver.get("https://www.google.com");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	@Test(groups={"Grid"})
	public void testWindowsIEDriver()
	{
		DriverFactory df=new DriverFactory();
		df.setServerIP("35.165.84.206");
		RemoteWebDriver driver= df.getDriver(Browser.IE,OS.WINDOWS);
		driver.get("https://www.google.com");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
