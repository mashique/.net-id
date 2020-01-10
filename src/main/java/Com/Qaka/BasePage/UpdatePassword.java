package Com.Qaka.BasePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdatePassword {

	@SuppressWarnings("finally")
	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
						
		Properties pr = new Properties();
		FileInputStream fs = new FileInputStream("C:\\Users\\mashique\\workspace\\Com.Qaka.net.id\\src\\main\\java\\Config\\Properties\\config.properties");
		pr.load(fs);
		
		
		driver.get("https://mashique:@2Charter@login.esso-uat.charter.com:8443/nidp//app/login?sid=3&option=credential");
		
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("mashique");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Charter@1");
		driver.findElement(By.xpath("/html/body/div/div[1]/div/form/p[3]/input")).click();
		driver.get("https://chtrgwy-uat.corp.chartercom.com/static/identityPortal/beta/");
		
			
		//Read data from Excel sheet
		
		File src = new File("D:\\Softwares\\Update net password in charter QA.xlsx");
		FileInputStream fs1= new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fs1);
		XSSFSheet Sh1= wb.getSheetAt(0);
		
		int norow= Sh1.getLastRowNum();
		
		norow = norow +1;
		
		String account = null;
		String passw =null;
		
		
		for(int i=1; i<norow; i++)
		
		{
			account= Sh1.getRow(i).getCell(0).getStringCellValue();
			passw=Sh1.getRow(i).getCell(1).getStringCellValue();
			
		
		//Search Account number
			
	
			WebDriverWait wait = new WebDriverWait(driver, 90);
			WebElement we =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Search']")));
			we.click();
			
			Thread.sleep(4000);
			
		driver.findElement(By.xpath("//*[@id='billerAccountNumber']")).sendKeys(account);
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/div/form/fieldset/div[3]/button[1]")).click();
	
		Thread.sleep(15000);
		try{
			
		driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[8]/div/div/button/span[2]")).click();
		driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[8]/div/div/ul/div/span[1]")).click();
		
		//Enter Required info
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/form/fieldset/div/div[8]/div/div/input")).sendKeys(passw);
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/form/fieldset/div/div[9]/div/div/input")).sendKeys(passw);
		
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/form/div/button[1]")).click();
				
		
		WebElement wb1 =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Identity has been updated successfully.']")));

		
		Sh1.getRow(i).createCell(2).setCellValue(wb1.getText());
		FileOutputStream fout =new FileOutputStream("D:\\Softwares\\Update net password in charter QA.xlsx");
		wb.write(fout);
		}
		catch(Exception e){
			
			continue;
			
		}
		
		}
		
		
	}

}
