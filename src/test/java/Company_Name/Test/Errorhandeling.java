package Company_Name.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Company_Name.Testcomponents.Retry;
import Company_Name.Testcomponents.BaseTest;
import Company_Name.pageobjects.CartProduct_match;
import Company_Name.pageobjects.Checkout_product;
import Company_Name.pageobjects.Order_product;
import Company_Name.pageobjects.Product_to_cart;

public class Errorhandeling extends BaseTest {

	String productsname = "iphone 13 pro";
	//String payment_type = "Credit Card";

	@Test(groups = { "error" }, retryAnalyzer= Retry.class)
	public void login() throws InterruptedException, IOException {

		Product_to_cart productscatelog = login.logincredential("laxmikanta6295@gmail.com", "Ankita@0760");

		// div[aria-label='Incorrect email or password.']

		// div[aria-label='Incorrect email or password.']
		// div[@aria-label='Incorrect email or password.']

		// document.querySelector("div[aria-label='Incorrect email or password.']")
		
		//this is a new comments

		Assert.assertEquals(login.error(), "Incorrect email or password");
		
		

	}

	@Test(dataProvider="getdata3")
	public void submit(HashMap<String,String> input) throws InterruptedException, IOException {

		Product_to_cart productscatelog = login.logincredential(input.get("email"), input.get("password"));
		System.out.println("Successfully Login.........");

		List<WebElement> product = productscatelog.getProducts();

		productscatelog.addtocartproduct(input.get("Productsname"));

		CartProduct_match productmatch = productscatelog.gotocart();
		boolean correct = productmatch.cartproductcheck(input.get("Productsname"));
		AssertJUnit.assertTrue(correct);
		System.out.println("correct product is added....");

		Checkout_product checkout = productmatch.checkoutproduct();

		checkout.checkout();

		boolean payment = checkout.paymentgetwayselect(input.get("Payment_type"));
		AssertJUnit.assertTrue(payment);

		checkout.carddetails();

		System.out.println("Card details added succesfully");

		checkout.country("India");
		String order_success = checkout.ordersucc();

		System.out.println(order_success);
		
		


	}

	@Test(dependsOnMethods = { "submit" })
	public void ordercheck() {

		Product_to_cart productscatelog = login.logincredential("laxmikanta6295@gmail.com", "Ankita@70760");

		Order_product order_page = productscatelog.goto_order();

		boolean orderFound = order_page.orderproductcheck("iphone 13 pro");
		Assert.assertTrue(orderFound, "Order not found in order history: " + "iphone 13 pro");
		System.out.println("iphone 13 pro" + " order successfully....");

		

	}

	@DataProvider
	public Object[][] getdata() {

		return new Object[][] { { "laxmikanta6295@gmail.com", "Ankita@70760", "iphone 13 pro", "Creadit Card" },
				{ "laxmikanta123@gmail.com", "Ankita@70760", "iphone 13 pro", "Creadit Card" } };

	}
	
	@DataProvider
	public Object[][] getdata2() {
		
		HashMap<String,String> data=new HashMap<String,String>();
		
		data.put("email","laxmikanta6295@gmail.com");
		data.put("password","Ankita@70760");
		data.put("Productsname","iphone 13 pro");
		data.put("Payment_type","Credit Card");
		
	
		

		//return new Object[][] { { "laxmikantamondal501@gmail.com", "Ankita@70760", "iphone 13 pro", "Creadit Card" },
				//{ "laxmikanta123@gmail.com", "Ankita@70760", "iphone 13 pro", "Creadit Card" } };
				
		return new Object[][] {{data},{data}};

	}
	
	@DataProvider
	public Object[][] getdata3() throws IOException {
		
		
	
		List<HashMap<String, String>> data=getjsondata(System.getProperty("user.dir")+"//src//test//java//Company//data//Purches_data.json");
		
	
		

		//return new Object[][] { { "laxmikantamondal501@gmail.com", "Ankita@70760", "iphone 13 pro", "Creadit Card" },
				//{ "laxmikanta123@gmail.com", "Ankita@70760", "iphone 13 pro", "Creadit Card" } };
				
		return new Object[][] {{data.get(0)}};
		}

	}


