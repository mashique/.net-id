package Com.Qaka.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickOn {

	
	
	public void KlickOn(WebDriver driver, int timeout, WebElement locator)
	
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

	public void sendkeys(WebDriver driver, int timeout, WebElement element, String value)
	
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
		
	}
}
