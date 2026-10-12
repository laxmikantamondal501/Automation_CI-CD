package Company_Name.pageobjects;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product_to_cart extends AbstractWait{

	WebDriver driver;
	

	public Product_to_cart(WebDriver driver) {
		
		super(driver);		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	
	
	//pagefactory
	
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	By productsBy=By.cssSelector(".mb-3");

	By addtocart =By.cssSelector(".card-body button:last-of-type");
	By toaster=By.id("toast-container");
	By spinner=By.cssSelector(".ngx-spinner-overlay");
	
	
	//action methods
	
	public  List<WebElement> getProducts() {
		
		waitforelements(productsBy);
		return products;
	}
	
	
	public WebElement getproductsby(String productname) {
		
		WebElement prod = getProducts().stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(productname))
				.findFirst().orElseThrow(() -> new RuntimeException("Product not found: " + productname));
		
		return prod;
		
		
	}
	
	public void addtocartproduct(String product) {
		WebElement prod=getproductsby(product);
		prod.findElement(addtocart).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		waitforelements(toaster);
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		waitforelemenetdisappear(spinner);
		
	}
	
}
