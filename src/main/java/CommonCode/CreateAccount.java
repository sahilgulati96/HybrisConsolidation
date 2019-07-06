package CommonCode;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import webObjectRepo.CreateAccountPageRepo;
import webObjectRepo.HeaderRepo;
import webObjectRepo.SignInPageRepo;

public class CreateAccount {
	WebDriver wd;
	String website = "jared";
	String browser = "chrome";
	String emailAddress = "sahiltest3@gmail.com";
	String pass = "Password@123";
	String firstName = "Sahil";
	String lastName = "Gulati";

	
	public void beforetest() throws InterruptedException {
		LaunchTheSite launch = new LaunchTheSite();
		launch.website(website, browser);
		wd = launch.webdriver();

		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		header.SignInLink().click();

		SignInPageRepo signin = new SignInPageRepo();
		signin.assignSession(wd);
		signin.CreateAccountButton().click();
		
		Lightbox lb = new Lightbox();
		lb.assignSession(wd);
		lb.closeLightBox();
		Thread.sleep(2000);
	}

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	@Test
	public void createAccount(String emailAddress) throws InterruptedException {
		beforetest();
		this.emailAddress = emailAddress;
		CreateAccountPageRepo account = new CreateAccountPageRepo();
		account.assignSession(wd);
		account.firstNameField().click();
		account.firstNameField().sendKeys(firstName);

		account.lastNameField().click();
		account.lastNameField().sendKeys(lastName);

		account.emailAddressField().click();
		account.emailAddressField().sendKeys(emailAddress);

		SoftAssert check = new SoftAssert();
		check.assertTrue(account.mobileNumberCheckbox().isSelected(),
				"This is a mobile number checkbox is not checked");

		account.mobileNumberField().click();
		account.mobileNumberField().sendKeys("1234567890");

		account.passwordField().click();
		account.passwordField().sendKeys(pass);

		account.confirmPasswordField().click();
		account.confirmPasswordField().sendKeys(pass);

		account.createAccountButton().click();
		aftertest(null);
	}

	
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		wd.close();

	}
}
