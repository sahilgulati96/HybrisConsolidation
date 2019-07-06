package sprint1;

import java.lang.reflect.Array;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonCode.LaunchTheSite;
import CommonCode.Lightbox;
import CommonCode.LoginToWebsite;
import CommonCode.Screenshots;
import testData.SelectionClass;
import webObjectRepo.HeaderRepo;
import webObjectRepo.PDPRepo;
import webObjectRepo.PLPRepo;
import webObjectRepo.WishListPageRepo;

public class WishlistFunctionalityTest {

	SelectionClass sc = new SelectionClass();
	String browser = "chrome";
	WebDriver wd;
	
	@BeforeMethod
	public void beforetest() throws InterruptedException {

		LaunchTheSite launch = new LaunchTheSite();
		launch.website("kayoutlet", browser);
		wd = launch.webdriver();
		sc.select("kayoutlet");

		String username = Array.get(sc.testdata.logincredentials(), 0).toString();
		String password = Array.get(sc.testdata.logincredentials(), 1).toString();
		LoginToWebsite login = new LoginToWebsite();
		login.assignSession(wd);
		login.loginToWebsite(username, password);
		
		Lightbox lb = new Lightbox();
		lb.assignSession(wd);
		lb.closeLightBox();

	}

	@Test(priority = 1)
	public void testAddToFavLink() {
		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		header.SearchBar().click();
		header.SearchBar().sendKeys("bracelet");
		header.SearchIcon().click();

		PLPRepo plp = new PLPRepo();
		plp.assignSession(wd);
		plp.viewDetailsButton().click();

		PDPRepo pdp = new PDPRepo();
		pdp.assignSession(wd);
		pdp.AddToWishlistLink().click();
		String productName = pdp.productName();
		System.out.println(productName);

		header.WishtlistLink().click();

		WishListPageRepo wishlist = new WishListPageRepo();
		wishlist.assignSession(wd);
		System.out.println(wishlist.ProductNameLinkText().getText());

		Assert.assertEquals(wishlist.ProductNameLinkText().getText(), productName,
				"Product does not match with the product added from pdp");

	}

	@Test(priority = 2)
	public void removeFromFav() {
		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		header.WishtlistLink().click();
		WishListPageRepo wishlist = new WishListPageRepo();
		wishlist.assignSession(wd);
		wishlist.removeFromFavButton().click();
		try {
			System.out.println("Remove from Wishlist is working");
			String expectedMessage = "You do not have any items in your Favorites";
			Assert.assertEquals(wishlist.emptyWishListMessage(), expectedMessage,
					"Some Product(s) are present in the wishlist try removing them");
		} catch (Exception e) {
			System.out.println("Run test once more to verify remove from Wishlist link");
			wishlist.removeFromFavButton().click();
			System.out.println("Remove from Wishlist is working");
			String expectedMessage = "You do not have any items in your Favorites";
			Assert.assertEquals(wishlist.emptyWishListMessage(), expectedMessage,
					"Some Product(s) are present in the wishlist try removing them");
		}

	}

	@AfterMethod
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		//wd.close();

	}
}
