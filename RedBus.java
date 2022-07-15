package week4.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();		
		ChromeDriver driver=new ChromeDriver();		
		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30));
		
		//Enter From -Madiwala Bangalore
		driver.findElement(By.id("src")).sendKeys("Madiwala, Bangalore");
		driver.findElement(By.xpath("//li[@data-id='66008']")).click();
		
		//Enter To Koyambedu Chennai
		driver.findElement(By.id("dest")).sendKeys("Koyambedu, Chennai");
		driver.findElement(By.xpath("//li[@data-id='66065']")).click();
		
		//Select the Date 10-Jun-2022
		driver.findElement(By.id("onward_cal")).click();
		driver.findElement(By.xpath("//table[@class='rb-monthTable first last']//tr[5]/td[text()='17']")).click();
		System.out.println("date selected");
		
		//Click Search buses
		
		driver.findElement(By.id("search_btn")).click();
		
		Thread.sleep(2000);
		
		//Click After 6pm under Departure time
		driver.findElement(By.xpath("//label[@for='dtAfter 6 pm']")).click();
		//Click Sleeper under Bus types
		Thread.sleep(2000);
			
		 WebElement sleeper= driver.findElement(By.xpath("//label[@for='bt_SLEEPER']"));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();", sleeper);
		 js.executeScript("arguments[0].click();", sleeper);
		
		//Select the Primo
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//li[@class='bannerTiles fl'])[1]")).click();
		//Get the number of buses found
		String noOfBuses=driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
		System.out.println("Number of Buses found are: "+noOfBuses);
		int noOfBusesInt=Integer.valueOf(noOfBuses.replace(" Buses", ""));
		
		//Get the Bus fare and sort them in ascending order
		
		List<WebElement> list1= new ArrayList();
		List<Integer> busFare= new ArrayList();
		for (int i=0;i<noOfBusesInt;i++)
				
		{
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			list1.add(driver.findElement(By.xpath("(//div[@class='fare d-block'])["+(i+1)+"]/span")));
			busFare.add(Integer.valueOf(list1.get(i).getText()));
		}
		
			
		Collections.sort(busFare);
		System.out.println("The sorted bus fare: "+busFare);
			
		//Close the application
		
		driver.close();
	}
}

