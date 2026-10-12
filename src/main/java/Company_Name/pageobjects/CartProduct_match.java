package Company_Name.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartProduct_match extends AbstractWait{
	
	WebDriver driver;
	public CartProduct_match(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cartSection h3")
	List <WebElement> correctorders;
	
	
	
	By spinner=By.cssSelector(".ngx-spinner-overlay");

	public boolean cartproductcheck(String productsname) {
		
	
	Boolean correct = correctorders.stream()
			.anyMatch(correctorder -> correctorder.getText().equalsIgnoreCase(productsname));
	return correct;
	
	}
	
	public Checkout_product checkoutproduct() {
		Checkout_product checkout=new Checkout_product(driver);
		return checkout;
	}



}
