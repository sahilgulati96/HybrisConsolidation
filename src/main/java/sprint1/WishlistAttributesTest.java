package sprint1;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonCode.LaunchTheSite;
import CommonCode.Lightbox;
import CommonCode.LoginToWebsite;
import CommonCode.Screenshots;
import webObjectRepo.MyAccountNavLinkRepo;
import webObjectRepo.WishListPageRepo;

public class WishlistAttributesTest {

	String browser = "chrome";
	WebDriver wd;

	@BeforeMethod
	public void beforetest() throws InterruptedException {

		LaunchTheSite launch = new LaunchTheSite();
		launch.website("kayoutlet", browser);
		wd = launch.webdriver();
		String username = "sahiltest2@gmail.com";
		String password = "Password@12345";
		LoginToWebsite login = new LoginToWebsite();
		login.assignSession(wd);
		login.loginToWebsite(username, password);
		
		Lightbox lb = new Lightbox();
		lb.assignSession(wd);
		lb.closeLightBox();

	}

	@AfterMethod
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		wd.close();

	}

	@Test
	public void verifyWishlistAttributes() {
		MyAccountNavLinkRepo myaccount = new MyAccountNavLinkRepo();
		myaccount.assignSession(wd);
		myaccount.wishlistLink().click();

		WishListPageRepo wishlist = new WishListPageRepo();
		wishlist.assignSession(wd);

		SoftAssert check = new SoftAssert();
		check.assertTrue(wishlist.addToCartButton().isDisplayed(), "Add to Shopping bag button is not displayed");
		check.assertTrue(wishlist.outOfStockButton().isDisplayed(), "Out of Stock button is not displayed");
		check.assertTrue(wishlist.removeFromFavButton().isDisplayed(), "Remove from Wishlist button is not displayed");
		check.assertTrue(wishlist.listingPrice().isDisplayed(), "Listing Price is not displayed");
		check.assertTrue(wishlist.ProductNameLinkText().isDisplayed(), "Product Name is not displayed");
		System.out.println(wishlist.ProductNameLinkText().getText());
		check.assertTrue(wishlist.starRating().isDisplayed(), "Star Rating is not displayed");
		check.assertTrue(wishlist.thumbnial().isDisplayed(), "Thumbnial button is not displayed");
		check.assertAll();

	}

}
