package Company_Name.pageobjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landing_page  extends AbstractWait{

	WebDriver driver;
	

	public Landing_page(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement username=driver.findElement(By.id("userEmail"));
	
	//pagefactory
	
	@FindBy(id="userEmail")
	WebElement username;
	
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement Submit;
	
	
	//div[aria-label='Incorrect email or password.']
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errormessage;
	
	
	//Now i have to put data into this elements using send key
	
	public Product_to_cart logincredential(String name,String password) {
		
		username.sendKeys(name);
		
		userPassword.sendKeys(password);
		
		Submit.click();
		
		Product_to_cart productscatelog=new Product_to_cart(driver);
		
		return productscatelog;
	}
	
	public String error() {
		
		waitforwebelements(errormessage);
		return errormessage.getText();
		
		}
	
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
}

