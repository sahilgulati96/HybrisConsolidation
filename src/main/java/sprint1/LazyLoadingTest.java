package sprint1;

import java.lang.reflect.Array;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
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

public class LazyLoadingTest {
	String website = "jared";
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

		try {
			HeaderRepo header = new HeaderRepo();
			header.assignSession(wd);
			header.SearchBar().click();
			header.SearchBar().sendKeys("bracelet");
			header.SearchIcon().click();
		} catch (ElementNotInteractableException e) {
			wd.navigate().refresh();
			HeaderRepo header = new HeaderRepo();
			header.assignSession(wd);
			header.SearchBar().click();
			header.SearchBar().sendKeys("bracelet");
			header.SearchIcon().click();
		}
	}

	@AfterMethod
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		//wd.close();

	}

	@Test
	public void lazyLoadTest() {
		// Executing a java script
		JavascriptExecutor js = (JavascriptExecutor) wd;
		// Scroll up to certain pixel
		// js.executeScript("window.scrollBy(0,6250)", "");
		// Scroll until last of the document
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)", "");
		String lazyloadurl = "&loadMore=";
		boolean expectedurl = wd.getCurrentUrl().contains(lazyloadurl);
		Assert.assertTrue(expectedurl, "Lazy Load failed");
	}

}
