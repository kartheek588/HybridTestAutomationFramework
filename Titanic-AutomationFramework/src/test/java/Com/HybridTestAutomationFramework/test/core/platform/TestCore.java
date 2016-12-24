package Com.HybridTestAutomationFramework.test.core.platform;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import Com.HybridTestAutomationFramework.core.platform.BrowserFlavour;
import Com.HybridTestAutomationFramework.core.platform.GridDriver;

public class TestCore {

	public static void main(String[] args) throws MalformedURLException {
		System.out.println(System.getProperty("os.name"));
		//RemoteWebDriver driver = (new GridDriver()).getDriver(BrowserFlavour.Chrome,"");
		WebDriver driver = (new GridDriver()).getDriver(BrowserFlavour.Chrome);
		driver.get("https://www.google.com/");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();

	}

}
