/**
 * 
 */
package Com.HybridTestAutomationFramework.core.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Kartheek
 *
 */
public class ActionUtilties {
	
	/* @Param file_Upload send the locator of element which stores the file path after choosing a file
	 * @Param submitButtonLocator is optional. submit need to be performed in case of null. 
	 */
	
	public static void uploadFile(WebDriver driver, By fileUploadLocator, By submitButtonLocator, String filePath)
	{
		driver.findElement(fileUploadLocator).sendKeys(filePath);
		if (submitButtonLocator !=null)
			driver.findElement(submitButtonLocator).click();
	}
	
	public static void downloadFile(String downloadPath)
	{
		
	}

}
