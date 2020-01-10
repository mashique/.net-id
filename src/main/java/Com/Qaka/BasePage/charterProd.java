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

public class charterProd {

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
						
		Properties pr = new Properties();
		FileInputStream fs = new FileInputStream("C:\\Users\\mashique\\workspace\\Com.Qaka.net.id\\src\\main\\java\\Config\\Properties\\config.properties");
		pr.load(fs);
		
		
		driver.get("https://mashique:Charter!1@login.sso.charter.com/nidp//app/login?sid=0&option=credential");
		
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("mashique");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Charter!1");
		driver.findElement(By.xpath("/html/body/div/div[1]/div/form/p[3]/input")).click();
		driver.get("https://chtrgwy.corp.chartercom.com/static/identityPortal/current/");
		
			
		//Read data from Excel sheet
		
		File src = new File("D:\\Softwares\\Create net user id in charter Prod.xlsx");
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
			
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='billerAccountNumber']")).sendKeys(account);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Find Account']")).click();
	
		Thread.sleep(10000);
				
		//driver.findElement(By.xpath("//*[text()='Tasks']")).click();
		driver.findElement(By.xpath("//*[text()='Create']")).click();
		
		//Enter Required info
		
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys(user);
		driver.findElement(By.xpath("//*[@id='contactEmail']")).sendKeys(email);
		driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/form/div[1]/div[2]/div[2]/div[2]/div/div/div/input")).sendKeys("8888888888");
		driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/form/div[1]/div[2]/div[2]/div[3]/div/div/div/select")).click();
		driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/form/div[1]/div[2]/div[2]/div[3]/div/div/div/select/option[2]")).click();
		driver.findElement(By.xpath("//*[@id='securityQuestionAnswer']")).sendKeys("St. Louis");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(passw);
		driver.findElement(By.xpath("//*[@id='passwordConfirmation']")).sendKeys(passw);
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/form/div[2]/div/div/button[1]")).click();
				
		
		WebElement wb1 =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Identity Created Successfully']")));

		
		Sh1.getRow(i).createCell(4).setCellValue(wb1.getText());
		FileOutputStream fout =new FileOutputStream("D:\\Softwares\\Create net user id in charter Prod.xlsx");
		wb.write(fout);
		
		}
		
		
	}

}
