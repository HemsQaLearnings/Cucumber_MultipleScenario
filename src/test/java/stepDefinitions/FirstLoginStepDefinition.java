package stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FirstLoginStepDefinition {
	// Pre-condition - Given
	// Actions - When
	// Validations - Then
	public WebDriver driver;

	@Given("the user is on OrangeHrm Login Page")
	public void the_user_is_on_orange_hrm_login_page() throws InterruptedException {
		// implement the method with selenium java test script

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@When("^the user enters (.*) and (.*)$") // (.*) it can accept any values if we specify dot star
	// make sure ^ cap sysmbol and $ sysmbol is added
	public void the_user_enters_username_and_password(String username, String password) throws InterruptedException {
		// Enter username
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		Thread.sleep(4000);
		// Enter password
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		Thread.sleep(4000);
	}

	@When("the user clicks on login button")
	public void the_user_clicks_on_login_button() throws InterruptedException {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);

	}

	@Then("the user should be redirected to homepage")
	public void the_user_should_be_redirected_to_homepage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module')]")));
            String gettext = header.getText();
            if (gettext.equalsIgnoreCase("Dashboard")) {
                System.out.println(gettext + " page is displayed");
                
            } else {
                System.out.println("home page is not displayed");
            }
        } catch (Exception e) {
            System.out.println("Exception caught: home page is not displayed " + e.getMessage());
        } finally {
            driver.quit();
        }
	}

}
