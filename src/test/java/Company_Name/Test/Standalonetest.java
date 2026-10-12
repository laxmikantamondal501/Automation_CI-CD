package Company_Name.Test;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Company_Name.Testcomponents.BaseTest;
import Company_Name.pageobjects.CartProduct_match;
import Company_Name.pageobjects.Checkout_product;
import Company_Name.pageobjects.Landing_page;
import Company_Name.pageobjects.Product_to_cart;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Standalonetest extends BaseTest{
	@Test
	public void submit()throws InterruptedException, IOException {

		

		String productsname = "iphone 13 pro";
		String payment_type="Credit Card";
		
		Product_to_cart productscatelog=login.logincredential("laxmikanta6295@gmail.com","Ankita@70760");
		System.out.println("Successfully Login.........");
		
		List <WebElement> product=productscatelog.getProducts();		
		
		productscatelog.addtocartproduct(productsname);
		
		CartProduct_match productmatch=productscatelog.gotocart();
		boolean correct=productmatch.cartproductcheck(productsname);
		AssertJUnit.assertTrue(correct);
		System.out.println("correct product is added....");
		
		Checkout_product checkout=productmatch.checkoutproduct();
		
		checkout.checkout();
		
		boolean payment=checkout.paymentgetwayselect(payment_type);
		AssertJUnit.assertTrue(payment);
		
		checkout.carddetails();
		
		System.out.println("Card details added succesfully");
		
		checkout.country("India");
		String order_success=checkout.ordersucc();
		
		
		System.out.println(order_success);
		
		
		
		
		//-------------------------------------------------------------------------------------------//
				/*					completed											*/
		//-------------------------------------------------------------------------------------------//
		
		
		

	}

	/*public static void login(WebDriver driver) {
		driver.findElement(By.id("userEmail")).sendKeys("laxmikanta6295@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ankita@70760");
		driver.findElement(By.id("login")).click();
		
		
	}*/
	
	
	
	

	/*public static void addtocart(WebDriver driver, String productsname) {

		

		Product_to_cart productscatelog=new Product_to_cart(driver);
		List <WebElement> product=productscatelog.getProducts();		

		productscatelog.getproductsby(product);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

	}

	public static String checkout(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.cssSelector(".totalRow button")).click();
		List<WebElement> paymentmethods = driver.findElements(By.cssSelector(".payment__type"));

		boolean creditCardFound = paymentmethods.stream()
				.anyMatch(paymentmethod -> paymentmethod.getText().contains("Credit Card"));

		Assert.assertTrue(creditCardFound, "Credit Card option was not found");

		driver.findElement(By.cssSelector("input[value*='4542']")).clear();

		driver.findElement(By.cssSelector("input[value*='4542']")).sendKeys("4542 9931 9292 2293");

		WebElement months = driver.findElement(By.xpath("(//select[@class='input ddl'])[1]"));
		Select month = new Select(months);
		month.selectByContainsVisibleText("06");

		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("123");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Discover card");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("input[name='coupon']")).click();
		//By countryResults = By.xpath("//p[@class='mt-1 ng-star-inserted']");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(countryResults));
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		
		List <WebElement> selectcountry=driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		
		WebElement indiaOption = selectcountry.stream()
			    .filter(country -> country.getText().trim().contentEquals("India"))
			    .findFirst()
			    .orElseThrow(() -> new RuntimeException("India not found"));
		
		indiaOption.click();
		
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();	
		String order=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		return order;

	}

	public static Boolean checkcorrectproductadded(WebDriver driver, String productsname) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		By spinner = By.cssSelector(".ngx-spinner-overlay");
		By cartButton = By.xpath("//button[@routerlink='/dashboard/cart']");

		wait.until(d -> {
		    try {
		        boolean overlayVisible = d.findElements(spinner)
		                .stream()
		                .anyMatch(WebElement::isDisplayed);

		        if (overlayVisible) {
		            return false;
		        }

		        d.findElement(cartButton).click();
		        return true;
		    } catch (org.openqa.selenium.ElementClickInterceptedException e) {
		        return false;
		    }
		});
		//By spinner = By.cssSelector(".ngx-spinner-overlay");

		//By cartButton = By.xpath("//button[@routerlink='/dashboard/cart']");
		//By cartButton = By.cssSelector("button[routerlink='/dashboard/cart']");
		//By cartButton = By.cssSelector("button[routerlink*='cart']");
		//By cartButton = By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
		
		wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));

		List<WebElement> correctorders = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean correct = correctorders.stream()
				.anyMatch(correctorder -> correctorder.getText().equalsIgnoreCase(productsname));

		Assert.assertTrue(correct);
		return correct;*/
	

	}
