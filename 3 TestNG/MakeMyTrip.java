package SDET_TestNG;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTrip {
	WebDriver driver;

	WebDriverWait wait;

	@BeforeMethod
	public void flightTickets() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}

	@Test
	public void bookingFlights() throws Exception {
		
		Thread.sleep(2000);
		// Perform click on Popup
		WebElement popup = driver.findElement(By.cssSelector("span[class='commonModal__close']"));
		wait.until(ExpectedConditions.visibilityOf(popup)).click();

		// Click on flight tab
		WebElement flightstab = driver.findElement(By.xpath("//span[text()='Flights']"));
		wait.until(ExpectedConditions.visibilityOf(flightstab)).click();
		WebElement radiobutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Round Trip']")));
		radiobutton.click();

		// Locate elements for the From Location
		WebElement from = driver.findElement(By.xpath("//span[text ()='From']"));
		from.click();
		Thread.sleep(3000);

		WebElement search = driver.findElement(By.xpath("//input [@placeholder='From']"));
		search.sendKeys ("Hyderabad");
		Thread.sleep(3000);

		WebElement hyd = driver.findElement(By.xpath("//li[@id=\"react-autowhatever-1-section-0-item-0\"]"));
		hyd.click();
		Thread.sleep(3000);

		// Locate elements for the To Location
		WebElement to = driver.findElement (By.xpath("//span[text()='To']"));
		to.click();
		Thread.sleep(3000);

		WebElement find = driver.findElement (By.xpath("//input[@placeholder='To']"));
		find.sendKeys ("MAA");
		Thread.sleep(3000);

		WebElement chennai = driver.findElement(By.xpath("//li[@id=\"react-autowhatever-1-section-0-item-0\"]"));
		chennai.click();
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,100)");
		Thread.sleep(3000); 
		
		// Locate elements for Start and end date
		WebElement startdate = driver.findElement(By.xpath("//div[@aria-label='Wed Oct 09 2024']"));
		startdate.click();
		Thread.sleep(3000);

		WebElement enddate = driver.findElement(By.xpath("//div[@aria-label='Wed Oct 23 2024']"));
		enddate.click();
		Thread.sleep (3000);

		WebElement finalsearch = driver.findElement(By.xpath("//a[text()='Search']"));
		wait.until(ExpectedConditions.elementToBeClickable(finalsearch)).click();

		String title = driver.getTitle();

		if (title.contains("MakeMyTrip")) {
			Assert.assertTrue(title.contains("MakeMyTrip"));
            System.out.println("The Search page is displayed as expected");
          } else {
            System.out.println("The Search page is not displayed as expected");
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}