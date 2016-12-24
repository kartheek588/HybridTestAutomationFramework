package Com.HybridTestAutomationFramework.core.platform;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFlavour {
	//Browser 
	public static final int Firefox =1;
	public static final int InternetExplorer =2;
	public static final int Chrome =3;
	
	public static DesiredCapabilities getCapabilities(int Browser)
	{
		DesiredCapabilities capabilities=null;
		
		switch(Browser)
		{
		case InternetExplorer:
			System.setProperty("webdriver.ie.driver",".\\src\\main\\resources\\IEDriverServer.exe");
		case Firefox:
			System.setProperty("webdriver.gecko.driver",".\\src\\main\\resources\\geckodriver.exe");
			capabilities =  DesiredCapabilities.firefox();
			break;
		case Chrome:
			System.setProperty("webdriver.chrome.driver",".\\src\\main\\resources\\chromedriver.exe");
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
