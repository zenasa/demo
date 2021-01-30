package testngdemo;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class windowhandle {

	WebDriver driver;

	@Test
	public void Windowhandle() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver_win32\\chromedriver.exe\\");
		driver = new ChromeDriver();
		 
		driver.get("https://www.bankofamerica.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//Implicit wait method
		
		driver.findElement(By.id("navHomeLoans")).click();
		driver.findElement(By.id("findAHome")).click();
		driver.findElement(By.xpath("//a[@title='Opens New Construction in a new tab.']")).click();
		
		Set<String> handle = driver.getWindowHandles();
		Iterator <String>ite =handle.iterator();
		
		String parentwindowID = ite.next();
		
		System.out.println("ParentWindow ID" + parentwindowID );
		
        String childwindowID = ite.next();
		
		System.out.println("ParentWindow ID" + childwindowID );
		
		driver.switchTo().window(childwindowID);
		
		driver.findElement(By.linkText("Texas (2481)")).click();
		
		boolean ver = driver.findElement(By.xpath("//a[@href=\"/new-homes/AL\"]")).isDisplayed();
		Assert.assertTrue(ver);
		
		driver.close();
		driver.switchTo().window(parentwindowID);
		
		boolean sthp = driver.findElement(By.xpath("//a[@href=\"/new-homes/AL/marketfilter-Anniston\"]")).isDisplayed();
		Assert.assertTrue(sthp);
		
		Thread.sleep(5000);
		driver.quit();
		
		//It worked fine but if you run again xpath changes so need to redo the xpath.
	

		
}
}
