package Company_Name.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Company_Name.pageobjects.Landing_page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	protected Landing_page login;

	public WebDriver webcreation() throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Company_Name/resources/GlobalData.properties");

		prop.load(fis);

		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// String browsername = prop.getProperty("browser");

		// for only chromedriver
		if (browsername.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		}

		else if (browsername.equalsIgnoreCase("firefox")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		}

		else if (browsername.equalsIgnoreCase("safari")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("Edge")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		}

		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;

	}

	public List<HashMap<String, String>> getjsondata(String Filepath) throws IOException {

		String jsoncontent = FileUtils.readFileToString(new File(Filepath), StandardCharsets.UTF_8);

		// String hashmap json databind

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

//This test is for screen short

	public String getscreenshort(String testcaseName, WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File reportDirectory = new File(System.getProperty("user.dir"), "report");
		if (!reportDirectory.exists() && !reportDirectory.mkdirs()) {
			throw new IOException("Could not create screenshot directory: " + reportDirectory);
		}
		File destination = new File(reportDirectory, testcaseName + ".png");
		FileUtils.copyFile(source, destination);
		return destination.getAbsolutePath();
	}

	@BeforeMethod(alwaysRun = true)
	public Landing_page landingpage() throws IOException {
		driver = webcreation();

		login = new Landing_page(driver);

		login.goTo();

		return login;

	}

	@AfterMethod(alwaysRun = true)
	public void tryclose() {
		driver.close();
	}

}
