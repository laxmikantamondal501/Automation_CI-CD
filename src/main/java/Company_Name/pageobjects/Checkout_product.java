package Company_Name.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Checkout_product extends AbstractWait {
	
	WebDriver driver;
	
	
	public Checkout_product(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	By checkoutby=By.cssSelector(".totalRow button");
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	@FindBy(css=".totalRow button")
	WebElement Checkout;
	
	
	
	
	public void checkout() {
		
		waitforelements(checkoutby);
		Checkout.click();
	}

	By findby=By.cssSelector(".payment__type");
	
	@FindBy(css=".payment__type")
	List<WebElement> paymentmethod;
	
	
	public boolean paymentgetwayselect(String methodtype) {
		
		waitforelements(findby);
		    String expected = methodtype.trim();

		    return paymentmethod.stream()
		        .map(element -> element.getText().trim())
		        .anyMatch(text -> text.equalsIgnoreCase(expected));
		
	}
	
	//driver.findElement(By.cssSelector("input[value*='4542']")).clear();
	@FindBy(css="input[value*='4542']")
	WebElement cardnum;
	
	//WebElement months = driver.findElement(By.xpath("(//select[@class='input ddl'])[1]"));
	@FindBy (xpath="(//select[@class='input ddl'])[1]")
	WebElement expmonth;
	
	//Select month = new Select(months);
	//month.selectByContainsVisibleText("06");
	
	@FindBy(xpath="(//input[@type='text'])[2]")
	WebElement cvv;
	
	
	//(//input[@type='text'])[2]
	@FindBy(xpath="(//input[@type='text'])[3]")
	WebElement cardname;
	
	@FindBy(css=".input[name='coupon']")
	WebElement coupon;
	
	
	public void carddetails() {
		
		cardnum.clear();
		cardnum.sendKeys("4542 9931 9292 2293");
		
		Select month=new Select(expmonth);
		month.selectByContainsVisibleText("6");
		
		cvv.sendKeys("1234");
		
		cardname.sendKeys("Discover");
		
		coupon.sendKeys("rahulshettyacademy");
		
		
		
		
	}
	
	//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
	
	By cnt_findby=By.cssSelector("input[placeholder='Select Country']");
	By submitby=By.xpath("//a[@class='btnn action__submit ng-star-inserted']");
	By ordermsg=By.cssSelector(".hero-primary");
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement cnt;
	
	//List <WebElement> selectcountry=driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	List<WebElement> countries;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submit;
	
	@FindBy(css=".hero-primary")
	WebElement successmsg;
	
	public void country(String countryName) {
		
		waitforelements(cnt_findby);
		cnt.sendKeys(countryName.substring(0, Math.min(3, countryName.length())));
		
		WebElement indiaOption = countries.stream()
			    .filter(country -> country.getText().trim().equalsIgnoreCase(countryName))
			    .findFirst()
			    .orElseThrow(() -> new RuntimeException(countryName + " not found"));
		
		indiaOption.click();
		waitforelements(submitby);
		
		submit.click();
		
	}
	
	//driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();	
	//String order=driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	public String ordersucc() {
		
		waitforelements(ordermsg);
		String ordermsg=successmsg.getText();		
		return ordermsg;
		
	}
	
	

	
}
