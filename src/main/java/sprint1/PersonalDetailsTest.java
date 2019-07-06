package sprint1;

import java.lang.reflect.Array;

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
import CommonCode.SignOut;
import testData.SelectionClass;
import webObjectRepo.MyAccountNavLinkRepo;
import webObjectRepo.PersonalDetailsSectionRepo;

public class PersonalDetailsTest {
	WebDriver wd;
	String website = "kayoutlet";
	String browser = "chrome";
	String username = "sahiltest3@gmail.com";
	String password = "Password@123";
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

		MyAccountNavLinkRepo myaccount = new MyAccountNavLinkRepo();
		myaccount.assignSession(wd);
		myaccount.personalDetailsLink().click();

	}

	@AfterMethod
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		wd.close();

	}

	@Test
	public void updatePhoneNumberCancelled() {

		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		String phoneNumber = pd.phoneNumberText();
		pd.updatePhoneNumberIcon().click();
		pd.updatePhoneNumberField().click();
		pd.updatePhoneNumberField().clear();
		pd.cancelButton().click();

		Assert.assertEquals(pd.phoneNumberText(), phoneNumber, "Phone Number is not same");

	}

	@Test
	public void updatePhoneNumberStringValues() {
		String newPhoneNumber = "Sahil Gulati";
		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updatePhoneNumberIcon().click();
		pd.updatePhoneNumberField().click();
		pd.updatePhoneNumberField().clear();
		pd.updatePhoneNumberField().sendKeys(newPhoneNumber);

		Assert.assertEquals(pd.updatePhoneNumberField().getText(), "", "Field is taking string values");
	}

	@Test
	public void updatePhoneNumberPositive() {
		String newPhoneNumber = "123-456-7890";
		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updatePhoneNumberIcon().click();
		pd.updatePhoneNumberField().click();
		pd.updatePhoneNumberField().clear();
		pd.updatePhoneNumberField().sendKeys(newPhoneNumber);
		SoftAssert check = new SoftAssert();
		check.assertTrue(pd.isMobileCheckedCheckbox().isSelected(), "CheckBox is unchecked");
		pd.updateButton().click();

		Assert.assertEquals(pd.phoneNumberText(), newPhoneNumber, "Phone Number is not updated");
	}

	@Test()
	public void updateDifferentEmailAndConfirmEmail() {
		String newemail = "sahiltest4@gmail.com";
		String confirmEmail = "sahiltest3@gmail.com";
		String errorMessage = "Email address fields do not match. Please reenter matching text in fields.";

		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updateEmailIcon().click();
		pd.newEmailAddressField().click();
		pd.newEmailAddressField().clear();
		pd.newEmailAddressField().sendKeys(newemail);
		pd.confirmEmailAddressField().click();
		pd.confirmEmailAddressField().clear();
		pd.confirmEmailAddressField().sendKeys(confirmEmail);
		pd.updateButton().click();
		try {
			Assert.assertEquals(pd.ConfirmEmailAddressFieldErrorMessage(), errorMessage,
					"Error message is incorrect for Confirm Email Address Field");
		} catch (NullPointerException e) {
			System.out.println("Check the WebElement in Repo");
			System.out.println("Check the screenshot to make sure it has not accepted email Address");
		}

	}

	@Test()
	public void updateInvalidEmail() {
		String invalidemail = "sahiltest4gmail.com";
		String errorMessage = "Please be certain you have entered a valid email address.";

		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updateEmailIcon().click();
		pd.newEmailAddressField().click();
		pd.newEmailAddressField().clear();
		pd.newEmailAddressField().sendKeys(invalidemail);
		pd.confirmEmailAddressField().click();
		pd.confirmEmailAddressField().clear();
		pd.confirmEmailAddressField().sendKeys(invalidemail);
		pd.updateButton().click();

		try {
			Assert.assertEquals(pd.NewEmailAddressFieldErrorMessage(), errorMessage,
					"Error message is incorrect for New Email Address Field");
			Assert.assertEquals(pd.ConfirmEmailAddressFieldErrorMessage(), errorMessage,
					"Error message is incorrect for Confirm Email Address Field");
		} catch (NullPointerException e) {
			System.out.println("Check the WebElement in Repo");
			System.out.println("Check the screenshot to make sure it has not accepted email Address");
		}

	}

	@Test()
	public void updateEmailCancel() {
		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updateEmailIcon().click();
		pd.newEmailAddressField().click();
		pd.newEmailAddressField().clear();
		pd.confirmEmailAddressField().click();
		pd.confirmEmailAddressField().clear();
		pd.cancelButton().click();
		wd.navigate().refresh();

		Assert.assertEquals(pd.emailAddressText(), username, "Email Address was updated or not captured");
	}

	@Test()
	public void updateEmailBlankFields() {
		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updateEmailIcon().click();
		pd.newEmailAddressField().click();
		pd.newEmailAddressField().clear();
		pd.confirmEmailAddressField().click();
		pd.confirmEmailAddressField().clear();
		pd.updateButton().click();

		String errorMessage = "This value is required.";
		SoftAssert check = new SoftAssert();
		check.assertEquals(pd.NewEmailAddressFieldErrorMessage(), errorMessage,
				"Error Message is not displayed or incorrect for New Email Address");
		check.assertEquals(pd.NewEmailAddressFieldErrorMessage(), errorMessage,
				"Error Message is not displayed or incorrect for Confirm Email Address");
		check.assertEquals(pd.NewEmailAddressFieldErrorMessage(), errorMessage,
				"Error Message is not displayed or incorrect for Password Address");
		check.assertAll();
	}

	@Test()
	public void updateEmailPositive() {
		String newemail = "sahiltest4@gmail.com";

		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updateEmailIcon().click();
		pd.newEmailAddressField().click();
		pd.newEmailAddressField().clear();
		pd.newEmailAddressField().sendKeys(newemail);
		pd.confirmEmailAddressField().click();
		pd.confirmEmailAddressField().clear();
		pd.confirmEmailAddressField().sendKeys(newemail);
		pd.passwordField().click();
		pd.passwordField().sendKeys(password);
		try {
			pd.updateButton().click();

			SignOut signout = new SignOut();
			signout.assignSession(wd);
			signout.signOut();

			LoginToWebsite login = new LoginToWebsite();
			login.assignSession(wd);
			login.loginToWebsite(newemail, password);

			MyAccountNavLinkRepo myaccount2 = new MyAccountNavLinkRepo();
			myaccount2.assignSession(wd);
			myaccount2.personalDetailsLink().click();

			pd.assignSession(wd);
			pd.updateEmailIcon().click();
			pd.newEmailAddressField().click();
			pd.newEmailAddressField().clear();
			pd.newEmailAddressField().sendKeys(username);
			pd.confirmEmailAddressField().click();
			pd.confirmEmailAddressField().clear();
			pd.confirmEmailAddressField().sendKeys(username);
			pd.passwordField().click();
			pd.passwordField().sendKeys(password);
			pd.updateButton().click();

		} catch (Exception e) {
			System.out.println("Failing the Script due to " + e.getMessage());
			Assert.assertEquals(true, false);
		}
	}

	@Test()
	public void updateNamePositive() {
		String firstname = "Sanjana";
		String lastname = "Srivastav";

		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updateNameButton().click();
		pd.firstName().click();
		pd.firstName().clear();
		pd.firstName().sendKeys(firstname);
		pd.lastName().click();
		pd.lastName().clear();
		pd.lastName().sendKeys(lastname);
		pd.saveButton().click();
		wd.navigate().refresh();
		SoftAssert check = new SoftAssert();
		check.assertEquals(pd.firstNameText(), firstname, "First Name updation Fails");
		check.assertEquals(pd.lastNameText(), lastname, "Last Name updation Fails");
		check.assertAll();
	}

	@Test()
	public void updateNameNegative() {
		PersonalDetailsSectionRepo pd = new PersonalDetailsSectionRepo();
		pd.assignSession(wd);
		pd.updateNameButton().click();
		pd.firstName().click();
		pd.firstName().clear();
		pd.lastName().click();
		pd.lastName().clear();
		pd.saveButton().click();

		SoftAssert check = new SoftAssert();
		check.assertEquals(pd.firstNameErrorMessage(), "Please enter a first name.",
				"Last Name error message is not displayed or displayed incorrectly");
		check.assertEquals(pd.lastNameErrorMessage(), "Please enter a last name.",
				"First Name error message is not displayed or displayed incorrectly");
		check.assertAll();

	}
}
