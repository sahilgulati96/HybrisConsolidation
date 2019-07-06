package sprint1;

import java.lang.reflect.Array;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonCode.GetOrderStatus;
import CommonCode.LaunchTheSite;
import CommonCode.Lightbox;
import CommonCode.LoginToWebsite;
import CommonCode.Screenshots;
import footerObjectRepo.OrderDetailsPageRepo;
import testData.SelectionClass;

public class GetOrderStatusTest {
	
	String website = "kayoutlet";
	String browser = "chrome";
	String customertype = "Guest";
	SelectionClass sc = new SelectionClass();
	WebDriver wd;

	@BeforeMethod
	public void beforetest() throws InterruptedException {

		LaunchTheSite launch = new LaunchTheSite();
		launch.website(website, browser);
		wd = launch.webdriver();
		sc.select(website);

		if (customertype.equalsIgnoreCase("Registered")) {
			String username = Array.get(sc.testdata.logincredentials(), 0).toString();
			String password = Array.get(sc.testdata.logincredentials(), 1).toString();
			LoginToWebsite login = new LoginToWebsite();
			login.assignSession(wd);
			login.loginToWebsite(username, password);
			
			Lightbox lb = new Lightbox();
			lb.assignSession(wd);
			lb.closeLightBox();
					
		}
	}

	@AfterMethod
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		wd.close();

	}
	
	@Test
	public void getOrderStatusPositive() throws InterruptedException {
		String emailAddress =Array.get(sc.testdata.orderstatusdata(), 0).toString();
		String orderId = Array.get(sc.testdata.orderstatusdata(), 1).toString();
		
		GetOrderStatus gos = new GetOrderStatus();
		gos.assignSession(wd);
		gos.getOrderStatus(emailAddress, orderId);
		
		OrderDetailsPageRepo odp = new OrderDetailsPageRepo();
		odp.assignSession(wd);
		Assert.assertEquals(odp.OrderId(), orderId, "OrderId does not match with the order searched");
		
	}

}
