package sprint1;

import java.lang.reflect.Array;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonCode.LaunchTheSite;
import CommonCode.Lightbox;
import CommonCode.LoginToWebsite;
import CommonCode.Screenshots;
import CommonCode.SearchBar;
import testData.SelectionClass;
import webObjectRepo.PDPRepo;
import webObjectRepo.PLPRepo;

public class ItemSearchTest {

	WebDriver wd;
	String website = "kay";
	String browser = "chrome";
	String customertype = "Guest";
	String searchterm = "";
	SelectionClass sc = new SelectionClass();

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
	public void plpElements() {
		searchterm = "bracelet";
		SearchBar search = new SearchBar();
		search.assignSession(wd);
		search.search(searchterm);

		PLPRepo plp = new PLPRepo();
		plp.assignSession(wd);

		SoftAssert check = new SoftAssert();
		check.assertTrue(plp.thumbnial().isDisplayed(), "Thumbnial is not displayed");
		check.assertTrue(plp.listingPrice().isDisplayed(), "Listing Price is not displayed");
		check.assertTrue(plp.ProductNameLinkText().isDisplayed(), "Product Name is not displayed");
		check.assertTrue(plp.starRating().isDisplayed(), "Star Rating is not displayed");
		check.assertTrue(plp.viewDetailsButton().isDisplayed(), "view Details Button is not displayed");

		check.assertAll();

	}

	@Test
	public void invalidSearchResultPage() {
		searchterm = "dfghjk";
		SearchBar search = new SearchBar();
		search.assignSession(wd);
		search.search(searchterm);
		String expectedmessage = "0 Items found for keyword " + searchterm;
		String actualmessage = wd.findElement(By.cssSelector(".headline")).getText();
		//System.out.println(actualmessage);
		Assert.assertEquals(actualmessage, expectedmessage);
	}

	@Test
	public void searchSKU() {
		searchterm = "999111368902";
		SearchBar search = new SearchBar();
		search.assignSession(wd);
		search.search(searchterm);
		PDPRepo pdp = new PDPRepo();
		pdp.assignSession(wd);
		Assert.assertEquals(pdp.itemID(), searchterm, "PDP is not displayed");
	}

	@Test
	public void singleProductSearch() {
		searchterm = "999111368902";
		SearchBar search = new SearchBar();
		search.assignSession(wd);
		search.search(searchterm);
		PDPRepo pdp = new PDPRepo();
		pdp.assignSession(wd);
		Assert.assertTrue(pdp.AddToShoppingBagButton().isDisplayed(), "Add to Shopping bag is not displayed");
	}

}
