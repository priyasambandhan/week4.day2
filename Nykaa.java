package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.lang.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {
	
	public static void main(String[] args) throws InterruptedException {
		
		//1) Go to https://www.nykaa.com/		
		WebDriverManager.chromedriver().setup();		
		ChromeDriver driver=new ChromeDriver();		
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30));
	
		//2) Click Brands and Search L'Oreal Paris
		
		WebElement brand= driver.findElement(By.xpath("//a[text()='brands']"));
		Actions action=new Actions(driver);
		action.moveToElement(brand).perform();
		
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
		
		//3) Click L'Oreal Paris
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		
		//4) Check the title contains L'Oreal Paris
		if (driver.getTitle().contains("L'Oreal"))
			System.out.println("The title contains L'Oreal Paris");
		else
			System.out.println("The title does not contain L'Oreal Paris");
		
		//5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//button[@class=' css-n0ptfk']/span")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		//6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("(//div[@class='css-xdicx1'])[1]")).click();
		//driver.findElement(By.xpath("//span[text()='Hair']/parent::div/span[@class='side-count']")).click();
		//driver.findElement(By.xpath("//span[text()='Hair']/parent::div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class=' css-b5p5ep']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']/parent::div/following-sibling::div[@class='control-indicator checkbox ']")).click();
		//7) Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		//8)check whether the Filter is applied with Shampoo
		Thread.sleep(2000);
		List<WebElement> shampoo=driver.findElements((By.xpath("//div[@class='css-xrzmfa']")));
		String name="";
		boolean filter=false;
		for (int i = 0; i < shampoo.size(); i++) {
			name=shampoo.get(i).getText();
			if(name.contains("Shampoo"))
			{
				filter=true;
			}
		}
		if (filter==true)
			System.out.println("Filer Shampoo is applied correctly");
		else
			System.out.println("Filter Shampoo is not applied correctly");
		
//		9) Click on L'Oreal Paris Colour Protect Shampoo
		for (int i =0; i<shampoo.size(); i++) {
			name=shampoo.get(i).getText();
			if(name.contains("L'Oreal Paris Colour Protect Shampoo"))
			{
				shampoo.get(i).click();
				break;
			}
		}
	//	10) GO to the new window and select size as 175ml
		Set<String> allWindows = driver.getWindowHandles();
		List<String> list = new ArrayList(allWindows);
		driver.switchTo().window(list.get(1));
		WebElement obj=driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select size=new Select(obj);
		size.selectByIndex(1);
		
		
		
	//	11) Print the MRP of the product
		System.out.println((driver.findElement(By.xpath("(//div[@class='css-1d0jf8e'])[1]"))).getText());
	//	12) Click on ADD to BAG
		driver.findElement(By.xpath("//button[@type='button']/span[text()='Add to Bag']")).click();
	///	13) Go to Shopping Bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
	//	14) Print the Grand Total amount
		WebElement frame= driver.findElement(By.xpath("//iframe[@src='/mobileCartIframe?ptype=customIframeCart']"));
		driver.switchTo().frame(frame);
		String gTotal= driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		gTotal=gTotal.substring(1);
		System.out.println("Grand Total is "+gTotal);
		
		//	15) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
	//	16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
	//	17) Check if this grand total is the same in step 14
		String gTotal2=driver.findElement(By.xpath("(//div[@class='value']/span)[2]")).getText();

		
		if( Integer.valueOf(gTotal).equals(Integer.valueOf(gTotal2)))
		{
			System.out.println("The grad total is same in step14");
		}
		else
			System.out.println("Grand total is not the same in step14");
	//	18) Close all windows
		driver.switchTo().defaultContent();
		driver.quit();
	
	}
	
}
