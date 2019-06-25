package sprint1;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonCode.CreateAccount;
import CommonCode.LaunchTheSite;
import CommonCode.Screenshots;
import webObjectRepo.CreateAccountPageRepo;
import webObjectRepo.HeaderRepo;
import webObjectRepo.SignInPageRepo;

public class CreateAccountTest {

	WebDriver wd;
	String website = "jared";
	String browser = "chrome";
	String pass = "Password@123";
	String firstName = "Sahil";
	String lastName = "Gulati";

	@BeforeMethod
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
	}

	@AfterMethod
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		wd.close();

	}

	public String emailAddressGenerator() {
		// Generating random string for email name
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String emailName = RandomStringUtils.random(length, useLetters, useNumbers);

		// Generating random String for domain name
		int lengthofdomain = 5;
		String domainName = RandomStringUtils.random(lengthofdomain, useLetters, useNumbers);

		// Concat to get final email address
		String emailId = emailName + "@" + domainName + ".com";
		System.out.println("Email id used to register account is : " + emailId);
		return emailId;
	}

	@Test
	public void createAccount() throws InterruptedException {
		CreateAccount create = new CreateAccount();
		create.assignSession(wd);
		create.createAccount(emailAddressGenerator());

		String creationmessage = "Thank you for registering.";
		String actualmessage = wd.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[3]/div[1]/div/div"))
				.getText();
		SoftAssert check = new SoftAssert();
		check.assertEquals(true, actualmessage.contains(creationmessage));
		check.assertAll();

	}

	@Test
	public void allFieldBlanksTest() {

		String expectederror = "This field is required.";
		String firstNameerror = wd.findElement(By.id("registerfirstName-errors")).getText();
		String LastNameerror = wd.findElement(By.id("registerlastName-errors")).getText();
		String Emailerror = wd.findElement(By.id("registeremail-errors")).getText();
		String PhoneNumbererror = wd.findElement(By.id("registermobileNumber-errors")).getText();
		String Passworderror = wd.findElement(By.id("pwd-errors")).getText();
		String ConfirmPassworderror = wd.findElement(By.id("checkPwd-errors")).getText();
		
		CreateAccountPageRepo account = new CreateAccountPageRepo();
		account.assignSession(wd);
		account.createAccountButton().click();
		
		SoftAssert check = new SoftAssert();
		check.assertEquals(firstNameerror, expectederror, "First Name field validation fails");
		check.assertEquals(LastNameerror, expectederror, "Last Name field validation fails");
		check.assertEquals(Emailerror, expectederror, "Email field validation fails");
		check.assertEquals(PhoneNumbererror, expectederror, "Phone Number field validation fails");
		check.assertEquals(Passworderror, expectederror, "Password field validation fails");
		check.assertEquals(ConfirmPassworderror, expectederror, "ConfirmPassword field validation fails");
		check.assertAll();
	}

	@Test
	public void existingEmailValidation() {
		CreateAccountPageRepo account = new CreateAccountPageRepo();
		account.assignSession(wd);
		account.firstNameField().click();
		account.firstNameField().sendKeys(firstName);

		account.lastNameField().click();
		account.lastNameField().sendKeys(lastName);

		account.emailAddressField().click();
		account.emailAddressField().sendKeys("sahiltest2@gmail.com");

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

		Assert.assertEquals(wd.findElement(By.id("email.errors")).getText(), "Account already exists with this email.",
				"Account Created with existing email");

	}

	@Test
	public void passwordValidation() {
		CreateAccountPageRepo account = new CreateAccountPageRepo();
		account.assignSession(wd);
		account.firstNameField().click();
		account.firstNameField().sendKeys(firstName);

		account.lastNameField().click();
		account.lastNameField().sendKeys(lastName);

		account.emailAddressField().click();
		account.emailAddressField().sendKeys(emailAddressGenerator());

		SoftAssert check = new SoftAssert();
		check.assertTrue(account.mobileNumberCheckbox().isSelected(),
				"This is a mobile number checkbox is not checked");

		account.mobileNumberField().click();
		account.mobileNumberField().sendKeys("1234567890");

		pass = "password";
		account.passwordField().click();
		account.passwordField().sendKeys(pass);

		account.confirmPasswordField().click();
		account.confirmPasswordField().sendKeys(pass);

		account.createAccountButton().click();

		Assert.assertEquals(wd.findElement(By.id("pwd-errors")).getText(),
				"Password does not meet minimum requirements.", "Password Validation Fails");
	}

	@Test
	public void differentPasswordAndConfirmPasswordValidation() {
		CreateAccountPageRepo account = new CreateAccountPageRepo();
		account.assignSession(wd);
		account.firstNameField().click();
		account.firstNameField().sendKeys(firstName);

		account.lastNameField().click();
		account.lastNameField().sendKeys(lastName);

		account.emailAddressField().click();
		account.emailAddressField().sendKeys(emailAddressGenerator());

		SoftAssert check = new SoftAssert();
		check.assertTrue(account.mobileNumberCheckbox().isSelected(),
				"This is a mobile number checkbox is not checked");

		account.mobileNumberField().click();
		account.mobileNumberField().sendKeys("1234567890");

		account.passwordField().click();
		account.passwordField().sendKeys(pass);

		account.confirmPasswordField().click();
		account.confirmPasswordField().sendKeys("Password@123456");

		account.createAccountButton().click();

		Assert.assertEquals(wd.findElement(By.id("checkPwd-errors")).getText(),
				"New Password and Confirm New Password fields must match.", "Password Validation Fails");

	}
}
