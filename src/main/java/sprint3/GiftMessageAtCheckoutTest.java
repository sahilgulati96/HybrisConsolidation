package sprint3;

import java.lang.reflect.Array;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonCode.CommonActions;
import CommonCode.LaunchTheSite;
import CommonCode.Lightbox;
import CommonCode.LoginToWebsite;
import CommonCode.Screenshots;
import CommonCode.SearchBar;
import testData.SelectionClass;
import webObjectRepo.PLPRepo;

public class GiftMessageAtCheckoutTest {
	// HC-416

	String website = "kayoutlet";
	String browser = "chrome";
	String customertype = "registered";
	SelectionClass sc = new SelectionClass();
	WebDriver wd;
	Boolean checkout = false;

	@BeforeMethod
	public void beforetest() throws InterruptedException {

		LaunchTheSite launch = new LaunchTheSite();
		launch.website(website, browser);
		wd = launch.webdriver();
		sc.select(website);

		if (customertype.equalsIgnoreCase("Registered") && checkout == false) {
			String username = Array.get(sc.testdata.logincredentials(), 0).toString();
			String password = Array.get(sc.testdata.logincredentials(), 1).toString();
			LoginToWebsite login = new LoginToWebsite();
			login.assignSession(wd);
			login.loginToWebsite(username, password);

			Lightbox lb = new Lightbox();
			lb.assignSession(wd);
			lb.closeLightBox();
			
			Thread.sleep(2000);

		}

		SearchBar search = new SearchBar();
		search.assignSession(wd);
		search.search(sc.testdata.searchterm());

		PLPRepo plp = new PLPRepo();
		plp.assignSession(wd);
		plp.viewDetailsButton().click();

		Lightbox lb = new Lightbox();
		lb.assignSession(wd);
		lb.closeLightBox();

		Thread.sleep(2000);

		CommonActions ca = new CommonActions();
		ca.assignSession(wd);
		ca.AddtoShoppingBagButtonClick();
		ca.MiniCartIconClick();
		ca.proceedToCheckOutButtonClick();
		
		Thread.sleep(2000);
	

		if (customertype.equalsIgnoreCase("Registered") && checkout == true) {
			ca.signInOnCheckout();
		} else {
			ca.checkoutAsGuestButtonClick();
		}
		ca.shiptoAddressFormFill();
	}

	@AfterMethod
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		// wd.close();

	}

	@Test
	public void test() {
		System.out.println("wow");
	}

}
