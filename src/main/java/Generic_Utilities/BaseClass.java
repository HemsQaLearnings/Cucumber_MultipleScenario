package Generic_Utilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

	public static WebDriver driver;
	public static Properties p;
	public static Logger logger = LogManager.getLogger(BaseClass.class);

	// Return the active driver
	public static WebDriver getDriver() {
		return driver;
	}

	// Load properties file only once
	public static Properties getProperties() throws IOException {

			FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\commandata.properties");
			p = new Properties();
			p.load(file);
			return p;
		
	}

	// Get formatted system date
	public String getSystemDateInFormat() {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH-mm-SS");
		return dateformat.format(new Date());
	}

	// Quit the driver
	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
			logger.info("Browser closed successfully.");
		}
	}

	// Initialize browser (local or remote)
	public static WebDriver initilizeBrowser() throws IOException {
		p = getProperties();
		String executionEnv = p.getProperty("execution_env").toLowerCase();
		String browser = p.getProperty("browser").toLowerCase();
		String os = p.getProperty("os").toLowerCase();

		if (executionEnv.equals("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// OS handling
			switch (os) {
				case "windows":
					capabilities.setPlatform(Platform.WINDOWS);
					break;
				case "mac":
					capabilities.setPlatform(Platform.MAC);
					break;
				case "linux":
					capabilities.setPlatform(Platform.LINUX);
					break;
				default:
					System.out.println("Invalid OS in properties file");
					return null;
			}

			// Browser handling
			switch (browser) {
				case "chrome":
					capabilities.setBrowserName("chrome");
					break;
				case "edge":
					capabilities.setBrowserName("MicrosoftEdge");
					break;
				case "firefox":
					capabilities.setBrowserName("firefox");
					break;
				default:
					System.out.println("Invalid browser in properties file");
					return null;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		} else if (executionEnv.equals("local")) {
			switch (browser) {
				case "chrome":
					driver = new ChromeDriver();
					break;
				case "edge":
					driver = new EdgeDriver();
					break;
				case "firefox":
					driver = new FirefoxDriver();
					break;
				default:
					System.out.println("Invalid browser in properties file");
					return null;
			}
		} else {
			System.out.println("Invalid execution environment");
			return null;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		return driver;
	}
}
