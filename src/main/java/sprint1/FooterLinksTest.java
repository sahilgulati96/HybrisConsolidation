package sprint1;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

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
import footerObjectRepo.FooterRepo;
import testData.SelectionClass;

public class FooterLinksTest {
	String website = "kay";
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
	public void privacypolicy() {
		String url = wd.getCurrentUrl();
		FooterRepo foot = new FooterRepo();
		foot.assignSession(wd);
		foot.privacyPolicy().click();

		String expectedurl = url + "privacy-policy";
		String actualurl = wd.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl);
	}

	@Test
	public void cobrowse() {
		FooterRepo foot = new FooterRepo();
		foot.assignSession(wd);
		foot.coBrowse().click();

		// Code for getting local time from the system
		String formattedDate = getCurrentTimeUsingCalendar();
		LocalTime time = LocalTime.parse(formattedDate);

		// Define a boolean to set chat is available or not
		Boolean ChatAvailable = isBetween(time, LocalTime.of(23, 30), LocalTime.of(06, 30));
		System.out.println("Chat is available now : " + ChatAvailable);

		if (ChatAvailable = true) {
			Assert.assertTrue(foot.coBrowse().isEnabled(), "Cobrowse not working as expected");
		} else {
			Assert.assertEquals(foot.coBrowse().isEnabled(), false, "Cobrowse not working as expected");
		}
	}

	@Test
	public void termOfUse() {
		String url = wd.getCurrentUrl();
		FooterRepo foot = new FooterRepo();
		foot.assignSession(wd);
		foot.TermOfUse().click();

		String expectedurl = url + "terms-and-conditions";
		String actualurl = wd.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl);
	}

	@Test
	public void customerSiteMap() {
		String url = wd.getCurrentUrl();
		FooterRepo foot = new FooterRepo();
		foot.assignSession(wd);
		foot.CustomerSiteMap().click();

		String expectedurl = url + "sitemap";
		String actualurl = wd.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl);
	}

	@Test
	public void siteMap() {
		String url = wd.getCurrentUrl();
		FooterRepo foot = new FooterRepo();
		foot.assignSession(wd);
		foot.SiteMap().click();

		String expectedurl = url + "sitemap";
		String actualurl = wd.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl);
	}

	@Test
	public void copywriteText() {
		FooterRepo foot = new FooterRepo();
		foot.assignSession(wd);
		foot.CopyWriteText();
	}

	public String getCurrentTimeUsingCalendar() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		System.out.println("Current time of the day using Calendar - 24 hour format: " + formattedDate);
		return formattedDate;

	}

	// Method for specifying start time and end time for chat window
	private boolean isBetween(LocalTime candidate, LocalTime start, LocalTime end) {
		return !candidate.isBefore(start) && !candidate.isAfter(end); // Inclusive.
	}
}