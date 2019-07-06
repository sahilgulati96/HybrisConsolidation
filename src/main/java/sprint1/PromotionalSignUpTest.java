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
import footerObjectRepo.PromotionalSignUpRepo;
import testData.SelectionClass;

public class PromotionalSignUpTest {
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
	public void promotionalSignUpPositive() throws InterruptedException {
		String thankYouMessage = "Thank you for signing up. We will be in touch!";

		PromotionalSignUpRepo psu = new PromotionalSignUpRepo();
		psu.assignSession(wd);
		psu.emailField().click();
		psu.emailField().sendKeys("sahilgulati340@gmail.com");
		Thread.sleep(2000);
		psu.signUpButton().click();

		Assert.assertEquals(psu.thankYouMessage(), thankYouMessage, "Message displayed is incorrect or signup fails");
	}

	@Test
	public void promotionalSignUpBlankEmail() throws InterruptedException {
		String errorMessage = "This value is required.";

		PromotionalSignUpRepo psu = new PromotionalSignUpRepo();
		psu.assignSession(wd);
		Thread.sleep(2000);
		psu.signUpButton().click();

		Assert.assertEquals(psu.errorMessage(), errorMessage, "Message displayed is incorrect");

	}

	@Test
	public void promotionalSignUpInvalidEmail() throws InterruptedException {
		String errorMessage = "This value should be a valid email.";

		PromotionalSignUpRepo psu = new PromotionalSignUpRepo();
		psu.assignSession(wd);
		psu.emailField().click();
		psu.emailField().sendKeys("sahilgulati340gmail.com");
		Thread.sleep(2000);
		psu.signUpButton().click();

		Assert.assertEquals(psu.errorMessage(), errorMessage, "Message displayed is incorrect");

	}
}
