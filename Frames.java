package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {
	public static void main(String[] args) {
		
	WebDriverManager.chromedriver().setup();		
	ChromeDriver driver=new ChromeDriver();		
	driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
	driver.manage().window().maximize();
	
	// Switching the context inside a frame
	WebElement frameElement1 = driver.findElement(By.xpath("(//iframe)[1]"));
	driver.switchTo().frame(frameElement1);
	driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("HandleFrames");
	
	WebElement frameElement2 = driver.findElement(By.id("frame3"));
	driver.switchTo().frame(frameElement2);
	driver.findElement(By.id("a")).click();
	driver.switchTo().defaultContent();
		
	WebElement frameElement3 = driver.findElement(By.xpath("//iframe[@id='frame2']"));
	driver.switchTo().frame(frameElement3);
	WebElement dropdown= driver.findElement(By.id("animals"));
	Select list=new Select(dropdown);
	list.selectByVisibleText("Avatar");
	
	driver.switchTo().defaultContent();
	
}
}
