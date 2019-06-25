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
import webObjectRepo.CartPageRepo;
import webObjectRepo.PLPRepo;

public class ApplyPromoCodeOnCartPage {
	
	//HC-428 and HC-437
	
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
		public void applyInvalidPromoCode() throws InterruptedException {
			SearchBar search = new SearchBar();
			search.assignSession(wd);
			search.search(sc.testdata.searchterm());
			
			PLPRepo plp = new PLPRepo();
			plp.assignSession(wd);
			plp.viewDetailsButton().click();

			CommonActions ca = new CommonActions();
			ca.assignSession(wd);
			ca.AddtoShoppingBagButtonClick();
			ca.MiniCartIconClick();
			
			CartPageRepo crp = new CartPageRepo();
			crp.assignSession(wd);
			crp.promocodeField().click();
			crp.promocodeField().sendKeys(sc.testdata.invalidPromocode());
			crp.promocodeApplyButton().click();

}
}
