import java.io.FileInputStream;
import java.io.FileNotFoundException;



import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Votting {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 200);
		driver.get("http://misswheelchairworld.com/en/miss,12,rajalakshmi-sj.html");
		WebElement cookieclose=driver.findElement(By.xpath("//span[text()='close']"));
		cookieclose.click();
		Thread.sleep(1000);
		WebElement vote=driver.findElement(By.xpath("//button[@class='item-vote']"));
		vote.click();
		Thread.sleep(1000);
		
		String filepath="\\TestData\\mailid.xlsx";
		FileInputStream  fs=new FileInputStream(filepath);
		XSSFWorkbook wb=new XSSFWorkbook(fs);
		XSSFSheet sh1= wb.getSheetAt(0);
		
		for(int i=0;i<=sh1.getLastRowNum();i++){
		System.out.println(sh1.getRow(i).getCell(0).getStringCellValue());
		WebElement mailid=driver.findElement(By.xpath("//input[@id='address_email']"));
		mailid.sendKeys(sh1.getRow(i).getCell(0).getStringCellValue());
		WebElement vote2=driver.findElement(By.xpath("//div[@class='tC']/button[@class='item-vote']"));
		vote2.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Check your email and confirm your vote')]")));
		WebElement message=driver.findElement(By.xpath("//div[contains(text(),'Check your email and confirm your vote')]"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Check your email and confirm your vote')]/button")));
		WebElement OK=driver.findElement(By.xpath("//div[contains(text(),'Check your email and confirm your vote')]/button"));
		OK.click();
		Thread.sleep(1000);
		vote.click();
		Thread.sleep(1000);
		}
		driver.close();
		}
		

}
