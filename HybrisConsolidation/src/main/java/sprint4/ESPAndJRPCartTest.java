package sprint4;

import java.lang.reflect.Array;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

public class ESPAndJRPCartTest {
	//HC-398 and HC-396
	
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
	public void JRPEligibleProduct() throws InterruptedException {
		SearchBar search = new SearchBar();
		search.assignSession(wd);
		search.search(sc.testdata.JRPProduct());

		CommonActions ca = new CommonActions();
		ca.assignSession(wd);
		ca.AddtoShoppingBagButtonClick();
		ca.MiniCartIconClick();

		CartPageRepo crp = new CartPageRepo();
		crp.assignSession(wd);
		crp.protectionPlanCheckbox().click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(crp.ValueofIndividualProducts() + crp.valueOfProtectionPlans(), crp.estimatedSubTotal(),
				"JRP/ESP price is not being added to the cart total");

	}
	
	@Test
	public void ESPEligibleProduct() throws InterruptedException {
		SearchBar search = new SearchBar();
		search.assignSession(wd);
		search.search(sc.testdata.ESPProduct());

		CommonActions ca = new CommonActions();
		ca.assignSession(wd);
		ca.AddtoShoppingBagButtonClick();
		ca.MiniCartIconClick();

		CartPageRepo crp = new CartPageRepo();
		crp.assignSession(wd);
		crp.protectionPlanCheckbox().click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(crp.valueOfProtectionPlans()==0) {
			Assert.assertEquals(true, false);
		}
		System.out.println("ESP"+crp.protectionPlanCheckbox());
		
		Assert.assertEquals(crp.ValueofIndividualProducts() + crp.valueOfProtectionPlans(), crp.estimatedSubTotal(),
				"JRP/ESP price is not being added to the cart total");

	}

	@Test
	public void nonESPAndJRPProducts() throws InterruptedException {
		SearchBar search = new SearchBar();
		search.assignSession(wd);
		search.search(sc.testdata.nonESPAndJRPProduct());

		CommonActions ca = new CommonActions();
		ca.assignSession(wd);
		ca.AddtoShoppingBagButtonClick();
		ca.MiniCartIconClick();

		CartPageRepo crp = new CartPageRepo();
		crp.assignSession(wd);
		
		try {
			crp.protectionPlanCheckbox();
			Assert.assertEquals(false, true, "ESP/JRP Checkbox is present for non-eligible products");
		}
		catch(NoSuchElementException e){
			System.out.println("Test Cases Pass for non-eligible products");
		}
		catch(Exception e1) {
			System.out.println(e1);
		}
	}

}
