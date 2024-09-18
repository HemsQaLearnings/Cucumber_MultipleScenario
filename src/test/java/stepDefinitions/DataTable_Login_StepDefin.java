package stepDefinitions;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataTable_Login_StepDefin {
	// Pre-condition - Given
		// Actions - When
		// Validations - Then
		public WebDriver driver;
	@Given("the user should be in homepage")
	public void the_user_should_be_in_homepage() throws InterruptedException {
		// implement the method with selenium java test script

				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				// Enter username
				driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
				Thread.sleep(4000);
				// Enter password
				driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
				Thread.sleep(4000);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(2000);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module')]")));
	            String gettext = header.getText();
	            if (gettext.equalsIgnoreCase("Dashboard")) {
	                System.out.println(gettext + " page is displayed");
	                
	            } else {
	                System.out.println("home page is not displayed");
	            }
	}

	@When("the user click on pim module button")
	public void the_user_click_on_pim_module_button() throws InterruptedException {
	   driver.findElement(By.xpath("//ul[@class='oxd-main-menu']/li[2]/a")).click();
	   Thread.sleep(2000);
	}

	@When("the user click on add employee button")
	public void the_user_click_on_add_employee_button() throws InterruptedException {
	
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		 Thread.sleep(2000);
	}

	@When("enter all the required fields")
	public void enter_all_the_required_fields(DataTable dataTable) throws InterruptedException {
		
		Map<String, String> dataMap = dataTable.asMap(String.class,String.class);
		//fn
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(dataMap.get("FirtName"));
		 
		//mn
		driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(dataMap.get("MiddleName"));
		//ln
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(dataMap.get("lastName"));
		//empid
		WebElement emp_id = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
		emp_id.click(); Thread.sleep(2000);
		emp_id.clear(); Thread.sleep(2000);
		emp_id.sendKeys(dataMap.get("Employeeid"));
	
		
	}

	@Then("click on save button")
	public void click_on_save_button() throws InterruptedException {
		//save
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(4000);
		driver.quit();
		
	}
}
