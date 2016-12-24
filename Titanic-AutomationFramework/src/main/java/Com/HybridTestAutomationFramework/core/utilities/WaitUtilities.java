package Com.HybridTestAutomationFramework.core.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtilities {

	public static void setImplicitWaits(WebDriver driver, int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

	public static void setBlindWait(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//may need to add in driver class 
	public static void clickWhenReady(WebDriver driver,By locator, int timeout) {

		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		element.click();

	}

	// may need to add in driver class as getwhenVisible
	public static WebElement waitForWebElementPresence(WebDriver driver, By locator,int sec)
	{
		return (new WebDriverWait(driver, sec)).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// wait for page to load
	public static void waitForPage(WebDriver driver)
	{
		
	}
}
