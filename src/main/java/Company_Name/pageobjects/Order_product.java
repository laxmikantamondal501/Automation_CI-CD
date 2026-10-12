package Company_Name.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Order_product extends AbstractWait {

	WebDriver driver;

	public Order_product(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Order rows use <td>; <th> only matches the column heading ("Name").
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> myorder;

	public boolean orderproductcheck(String productsname) {
		waitforwebelements(myorder);

		Boolean order = myorder.stream()
				.anyMatch(correctorder -> correctorder.getText().trim().equalsIgnoreCase(productsname.trim()));
		return order;
	}

}
