package Company_Name.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractWait {

	WebDriver driver;

	public AbstractWait(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// @FindBy(css="[routerlink*='cart']")

	@FindBy(css = ".btn.btn-custom[routerlink='/dashboard/cart']")

	// @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;

	@FindBy(css = "button[routerlink*='myorders']")
	WebElement myorder;

	public void waitforelements(By findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));

	}

	public void waitforwebelements(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void waitforwebelements(List<WebElement> Lweb) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfAllElements(Lweb));
	}

	public void waitforelemenetdisappear(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}

	// action methods of cart
	public CartProduct_match gotocart() throws InterruptedException {

		Thread.sleep(2000);
		cart.click();

		CartProduct_match productmatch = new CartProduct_match(driver);

		return productmatch;

	}

	public Order_product goto_order() {
		myorder.click();
		Order_product productmatch = new Order_product(driver);

		return productmatch;

	}

	public void closedriver() {
		driver.close();
		
	}
	

}
