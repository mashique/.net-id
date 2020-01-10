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

public class basePage {

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
						
		Properties pr = new Properties();
		FileInputStream fs = new FileInputStream("C:\\Users\\mashique\\workspace\\Com.Qaka.net.id\\src\\main\\java\\Config\\Properties\\config.properties");
		pr.load(fs);
		
		
		driver.get("https://mashique:Charter!2@login.esso-uat.charter.com:8443/nidp//app/login?sid=3&option=credential");
		
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("mashique");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Charter!2");
		driver.findElement(By.xpath("/html/body/div/div[1]/div/form/p[3]/input")).click();
		driver.get("https://chtrgwy-uat.corp.chartercom.com/static/identityPortal/current/");
		
			
		//Read data from Excel sheet
		
		File src = new File("D:\\Softwares\\Create net user id in charter QA.xlsx");
		FileInputStream fs1= new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fs1);
		XSSFSheet Sh1= wb.getSheetAt(0);
		
		int norow= Sh1.getLastRowNum();
		
		norow = norow +1;
		
		String account = null;
		String user = null;
		String email = null;
		String passw =null;
		
		for(int i=1; i<norow; i++)
		
		{
			account= Sh1.getRow(i).getCell(0).getStringCellValue();
			user= Sh1.getRow(i).getCell(1).getStringCellValue();
			email=Sh1.getRow(i).getCell(2).getStringCellValue();
			passw=Sh1.getRow(i).getCell(3).getStringCellValue();
			
		
		//Search Account number
			
	
			WebDriverWait wait = new WebDriverWait(driver, 90);
			WebElement we =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Search']")));
			we.click();
			
	
		driver.findElement(By.xpath("//*[@id='billerAccountNumber']")).sendKeys(account);
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/div/div/form/fieldset/div[2]/button[1]")).click();
	
		Thread.sleep(15000);
				
		driver.findElement(By.xpath("//*[text()='Tasks']")).click();
		driver.findElement(By.xpath("//*[text()='Create Identity']")).click();
		
		//Enter Required info
		
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/fieldset/div/div[1]/div/div/input")).sendKeys(user);
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/fieldset/div/div[4]/div/div/input")).sendKeys(email);
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/fieldset/div/div[5]/div/div/input")).sendKeys("8888888888");
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/fieldset/div/div[6]/div/div/select")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/fieldset/div/div[6]/div/div/select/option[2]")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/fieldset/div/div[7]/div/div/input")).sendKeys("Red");
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/fieldset/div/div[8]/div/div/input")).sendKeys(passw);
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/fieldset/div/div[9]/div/div/input")).sendKeys(passw);
		
		driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/form/div/button[1]")).click();
				
		
		WebElement wb1 =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Identity has been created successfully']")));

		
		Sh1.getRow(i).createCell(4).setCellValue(wb1.getText());
		FileOutputStream fout =new FileOutputStream("D:\\Softwares\\Create net user id in charter QA.xlsx");
		wb.write(fout);
		
		}
		
		
	}

}
