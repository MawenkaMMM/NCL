package stepDefintions;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import info.seleniumcucumber.methods.BaseTest;

public class UserStepDefinitions implements BaseTest {
	WebDriver driver;
	@Given("^I am on the homepage$")
	public void i_am_on_the_homepage() throws Throwable {
		
			System.setProperty("webdriver.chrome.driver",
			"C:\\Users\\Marysya\\Documents\\Selenium depensencies\\drivers\\chromedriver.exe");
					
			driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        driver.get("https://www.ncl.com/");
		}
	

	@Given("^navigated to Shore Excursion page$")
	public void navigated_to_Shore_Excursion_page() throws Throwable {
	driver.findElement(By.linkText("Explore")).click();
	driver.findElement(By.linkText("Shore Excursions")).click();
	}

	@Given("^I click find excursion$")
	public void i_click_find_excursion() throws Throwable {
		driver.findElement(By.xpath("//button[contains(text(),'FIND EXCURSIONS')]")).click();
		 
	
	}

	@Given("^Shore Excursion page is present$")
	public void shore_Excursion_page_is_present() throws Throwable {
		String currenURL = driver.getCurrentUrl();
		Assert.assertTrue(currenURL.contains("shore-excursions"));
	}

	@When("^price range is filtered to \\$(\\d+)-\\$(\\d+)$")
	public void price_range_is_filtered_to_$_$(int arg1, int arg2) throws Throwable {
		 WebElement slider = driver.findElement(By.xpath("(//span[@class='ui-slider-handle ui-corner-all ui-state-default'])[2]"));
		    Actions move = new Actions(driver);
	        Action action = (Action)move.dragAndDropBy(slider, 30, 0).build();
	        action.perform();
	}

	@Then("^Only shore excursions within range are displayed$")
	public void only_shore_excursions_within_range_are_displayed() throws Throwable {
		
		List<WebElement> prices=driver.findElements(By.xpath("//li[contains(text(),'Adult from')]"));
		for(WebElement price:prices) {
			String info=price.getText();
			String priceCleaned = info.replaceAll("[^0-9]","");
			priceCleaned = priceCleaned.substring(0, priceCleaned.length()-2);
			int actualPrice = Integer.parseInt(priceCleaned);
			
			Assert. assertTrue(actualPrice<=30);
		}
				 
	}


}
