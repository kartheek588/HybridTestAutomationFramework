package Com.HybridTestAutomationFramework.core;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;

import Com.HybridTestAutomationFramework.core.platform.BrowserFlavour;
import Com.HybridTestAutomationFramework.core.platform.DriverFactory;

public class HybridDriver {
	private WebDriver driver;
	protected HybridDriver()
	{
		driver = (new DriverFactory()).getDriver(BrowserFlavour.Firefox);
	}
	public void close() {
		driver.close();
		
	}
	public WebElement findElement(By locator) {
		
		return driver.findElement(locator);
	}
	public List<WebElement> findElements(By locator) {
		
		return driver.findElements(locator);
	}
	public void get(String URL) {
		driver.get(URL);
		
	}
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	public String getPageSource() {
		
		return driver.getPageSource();
	}
	public String getTitle() {
		return driver.getTitle();
	}
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}
	public Set<String> getWindowHandles() {
		
		return driver.getWindowHandles();
	}
	public Options manage() {
		
		return driver.manage();
	}
	public Navigation navigate() {

		return driver.navigate();
	}
	public void quit() {
		driver.quit();
		
	}
	public TargetLocator switchTo() {
		
		return driver.switchTo();
	}
		
}
