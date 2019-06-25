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
import footerObjectRepo.SocialMediaSharingRepo;
import testData.SelectionClass;

public class SocialMediaSharingIconTest {
	String website = "kay";
	String browser = "chrome";
	String customertype = "Guest";
	String expectedurl = "";
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

	// @Test
	public void facebookShare() {

		SocialMediaSharingRepo sms = new SocialMediaSharingRepo();
		sms.assignSession(wd);
		sms.facebookIcon().click();

		if (website.equalsIgnoreCase("jared")) {
			expectedurl = "https://www.facebook.com/JaredTheGalleriaOfJewelry";
		} else {
			expectedurl = "https://www.facebook.com/KayJewelers";
		}
		Assert.assertEquals(wd.getCurrentUrl(), expectedurl, "Url does not matches");
	}

	// @Test
	public void twitterShare() {

		SocialMediaSharingRepo sms = new SocialMediaSharingRepo();
		sms.assignSession(wd);
		sms.twitterIcon().click();

		if (website.equalsIgnoreCase("jared")) {
			expectedurl = "https://twitter.com/ThatsJared";
		} else {
			expectedurl = "https://twitter.com/KayJewelers";

		}
		Assert.assertEquals(wd.getCurrentUrl(), expectedurl, "Url does not matches");
	}

	// @Test
	public void youtubeShare() {
		;
		SocialMediaSharingRepo sms = new SocialMediaSharingRepo();
		sms.assignSession(wd);
		sms.youtubeIcon().click();

		if (website.equalsIgnoreCase("jared")) {
			expectedurl = "https://www.youtube.com/thatsjared";
		} else {
			expectedurl = "https://www.youtube.com/c/kayjewelers";
		}
		Assert.assertEquals(wd.getCurrentUrl(), expectedurl, "Url does not matches");
	}

	// @Test
	public void pininterestShare() {

		SocialMediaSharingRepo sms = new SocialMediaSharingRepo();
		sms.assignSession(wd);
		sms.pinInterestIcon().click();

		if (website.equalsIgnoreCase("jared")) {
			expectedurl = "https://www.pinterest.com/thatsjared/";
		} else {
			expectedurl = "https://www.pinterest.com/kayjewelers/";
		}
		Assert.assertEquals(wd.getCurrentUrl(), expectedurl, "Url does not matches");
	}

	@Test
	public void instagramShare() {

		SocialMediaSharingRepo sms = new SocialMediaSharingRepo();
		sms.assignSession(wd);

		if (website.equalsIgnoreCase("jared")) {
			expectedurl = "https://www.instagram.com/jaredthegalleriaofjewelry/";
		} else {
			expectedurl = "https://www.instagram.com/kayjewelers/";
		}

		if (website.equalsIgnoreCase("kayoutlet")) {
			try {
				sms.instagramIcon().click();
				Assert.assertEquals(wd.getCurrentUrl(), expectedurl, "Url does not matches");
			} catch (org.openqa.selenium.InvalidSelectorException e) {
				System.out.println("Instagram not present on website");
			}
		}

	}

}
