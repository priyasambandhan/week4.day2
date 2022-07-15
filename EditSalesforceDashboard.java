package week4.day2;

import java.util.List;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditSalesforceDashboard {
	public static void main(String[] args) throws InterruptedException {
		
	
	//1. Login to https://login.salesforce.com
		WebDriverManager.chromedriver().setup();		
		ChromeDriver driver=new ChromeDriver();		
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30));
		driver.findElement(By.name("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.name("pw")).sendKeys("Password@123");
		driver.findElement(By.name("Login")).click();
		Thread.sleep(10000);
		
		//2. Click on the toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		//driver.findElement(By.xpath("//button[@class='slds-button slds-show']//div")).click();
		
		//3. Click View All and click Dashboards from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(3000);
		WebElement Db=driver.findElement(By.xpath("//p[text()='Dashboards']"));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();", Db);
		 js.executeScript("arguments[0].click();", Db);
	
		 //4. Click on the Dashboards tab
		 // 5. Search the Dashboard 'Salesforce Automation by Your Name'
		driver.findElement(By.xpath("//input[@class='search-text-field slds-input input uiInput uiInputText uiInput--default uiInput--input']")).sendKeys("Automation by Priya");
		Thread.sleep(2000);
					
		// 6. Click on the Dropdown icon and Select Edit
		driver.findElement(By.xpath("(//button[@class='slds-button slds-button_icon-border slds-button_icon-x-small'])[1]")).click();
		driver.findElement(By.xpath("//span[text()='Edit']")).click();
		Thread.sleep(5000);
	//	driver.findElement(By.xpath("//button[text()='Edit']")).click();*/
			
		// 7.Click on the Edit Dashboard Properties icon
		WebElement frame=driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[@title='Edit Dashboard Properties']")).click();
		Thread.sleep(5000);
		// 8. Enter Description as 'SalesForce' and click on save.
		driver.findElement(By.id("dashboardNameInput")).clear();
		driver.findElement(By.id("dashboardNameInput")).sendKeys("SalesForce Edit by Priya");
		// 9. Click on Done and  Click on save in the popup window displayed.
		driver.findElement(By.id("submitBtn")).click();
		// 10. Verify the Description.
		//driver.findElement(By.xpath("//button[@class='slds-button slds-button_icon slds-m-left_x-small']")).click();
		//String name=driver.findElement(By.xpath("//button[@class='slds-button slds-button_icon slds-m-left_x-small']/parent::span")).getText();
		driver.findElement(By.xpath("//button[@title='Edit Dashboard Properties']")).click();
		String name =driver.findElement(By.id("dashboardNameInput")).getText();
		System.out.println("name is"+name);
		if (name.contains("SalesForce Edit by Priya"))
				
			System.out.println("name is correctly updated");
		else
			System.out.println("name is not correctly updated");
	}
}
