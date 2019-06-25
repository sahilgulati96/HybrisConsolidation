package sprint1;

import java.lang.reflect.Array;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonCode.LaunchTheSite;
import CommonCode.Lightbox;
import CommonCode.LoginToWebsite;
import CommonCode.Screenshots;
import testData.SelectionClass;
import webObjectRepo.MyAccountNavLinkRepo;

public class OrderDetailsLinkTest {
	String website = "kay";
	String browser = "chrome";
	WebDriver wd;
	SelectionClass sc = new SelectionClass();

	@BeforeMethod
	public void beforetest() throws InterruptedException {

		LaunchTheSite launch = new LaunchTheSite();
		launch.website(website, browser);
		wd = launch.webdriver();
		sc.select(website);

		String username = Array.get(sc.testdata.logincredentials(), 0).toString();
		String password = Array.get(sc.testdata.logincredentials(), 1).toString();
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
	public void OrderDetailsPageElement() {
		MyAccountNavLinkRepo myaccount = new MyAccountNavLinkRepo();
		myaccount.assignSession(wd);
		myaccount.orderHistoryLink().click();
	}

}
