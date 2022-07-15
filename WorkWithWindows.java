package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkWithWindows {
	
	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();		
		ChromeDriver driver=new ChromeDriver();		
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		
		//Click button to open home page in New Window
		driver.findElement(By.id("home")).click();
		
		//Find the number of opened windows
		driver.findElement(By.xpath("//button[contains(text(),'Multiple')]")).click();
		Thread.sleep(5000);
		Set<String> windows1 = driver.getWindowHandles();
		List<String> windowsList1 = new ArrayList(windows1);
		System.out.println("Number of windows opened "+windowsList1.size());
		
		System.out.println(driver.switchTo().window(windowsList1.get(windowsList1.size()-1)).getTitle());
		//driver.switchTo().window(windowsList1.get(1)).close();
		driver.switchTo().window(windowsList1.get(0));
		
		//wait for 5 seconds
		driver.findElement(By.xpath("//button[contains(text(),'Wait')]")).click();;
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(10000);
		Set<String> windows2 = driver.getWindowHandles();
		List<String> windowsList2 = new ArrayList(windows2);
		System.out.println("Number of windows opened "+windows2.size());
		System.out.println(driver.switchTo().window(windowsList2.get(windowsList2.size()-1)).getTitle());
		
		driver.switchTo().window(windowsList2.get(0));
		
		//close all except this window
		driver.findElement(By.xpath("//button[contains(text(),'Do not')]")).click();
		Thread.sleep(5000);
		Set<String> windows3 = driver.getWindowHandles();
		List<String> windowsList3 = new ArrayList(windows3);
		System.out.println("Number of windows opened "+windows3.size());
		System.out.println(driver.switchTo().window(windowsList3.get(windowsList3.size()-1)).getTitle());
		/*for(int i=0; i<windowsList3.size()-1;i++)
		{
		
		driver.switchTo().window(windowsList3.get(i)).close();
		
		}*/
	
	}

}
