package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	public static void main(String[] args) throws InterruptedException {
		
	
	WebDriverManager.chromedriver().setup();		
	ChromeDriver driver=new ChromeDriver();		
	driver.get(" http://leaftaps.com/opentaps/control/main");
	driver.manage().window().maximize();
	

	//  2. Enter UserName and Password Using Id Locator
	WebElement userName = driver.findElement(By.id("username"));
	userName.sendKeys("Demosalesmanager");
	
	WebElement password = driver.findElement(By.id("password"));
	password.sendKeys("crmsfa");

	//  3. Click on Login Button using Class Locator
	WebElement login = driver.findElement(By.className("decorativeSubmit"));
	login.click();

	//  4. Click on CRM/SFA Link
	WebElement crmLink = driver.findElement(By.linkText("CRM/SFA"));
	crmLink.click();
	
	//5. Click on contacts Button
	driver.findElement(By.linkText("Contacts")).click();
	
	//6. Click on Merge Contacts using Xpath Locator
	
	driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
	 
	//7. Click on Widget of From Contact
	driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click();
	
	Set<String> windows = driver.getWindowHandles();
	List<String> windowsList = new ArrayList(windows);
	driver.switchTo().window(windowsList.get(1));
	
	 
	 //8. Click on First Resulting Contact
	Thread.sleep(10000);
	driver.findElement(By.xpath("((//table[@class='x-grid3-row-table'])[1]//a)[1]")).click();
	driver.switchTo().window(windowsList.get(0));		
				
		

	 //9. Click on Widget of To Contact
	driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
	Set<String> windows1 = driver.getWindowHandles();
	List<String> windowsList1 = new ArrayList(windows1);
	driver.switchTo().window(windowsList1.get(1));
	 
	//10. Click on Second Resulting Contact
	Thread.sleep(10000);
	driver.findElement(By.xpath("((//table[@class='x-grid3-row-table'])[2]//a)[1]")).click();
	driver.switchTo().window(windowsList.get(0));	
	 //11. Click on Merge button using Xpath Locator
	driver.findElement(By.linkText("Merge")).click();
	 
	 // 12. Accept the Alert
	Alert alert1=driver.switchTo().alert();
	alert1.accept();
	 
	 //13. Verify the title of the page
	if(driver.getTitle().contains("opentaps"))
	System.out.println("Title is correctly displayed as "+driver.getTitle());
	driver.quit();
	
	}
}

