package hooks;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Generic_Utilities.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private WebDriver driver;
	private Properties p;

	@Before
	public void setup() throws IOException, InterruptedException {
		driver = BaseClass.initilizeBrowser();
		Thread.sleep(4000);
		p = BaseClass.getProperties();

		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@After
	public void tearDown() {
		BaseClass.quitDriver(); // safer and centralized cleanup
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {
		driver = BaseClass.getDriver();
		if (scenario.isFailed() && driver != null) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Failure Screenshot - " + scenario.getName());
			} catch (Exception e) {
				System.out.println("Screenshot capture failed: " + e.getMessage());
			}
		}
	}
}
