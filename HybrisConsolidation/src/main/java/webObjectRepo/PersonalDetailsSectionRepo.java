package webObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalDetailsSectionRepo {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement firstName() {
		return wd.findElement(By.id("profile.firstName"));
	}

	public String firstNameText() {
		return wd.findElement(By.id("profile.firstName.text")).getText();
	}

	public String firstNameErrorMessage() {
		return wd.findElement(By.id("profilefirstName-errors")).getText();
	}

	public WebElement lastName() {
		return wd.findElement(By.id("profile.lastName"));
	}

	public String lastNameText() {
		return wd.findElement(By.id("profile.lastName.text")).getText();
	}

	public String lastNameErrorMessage() {
		return wd.findElement(By.id("profilelastName-errors")).getText();
	}

	public WebElement saveButton() {
		return wd.findElement(By.cssSelector("button.btn.btn-primary.btn-wide.submit-update"));
	}

	public WebElement updateNameButton() {
		WebDriverWait wait = new WebDriverWait(wd, 20);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("button.btn.btn-primary.btn-wide.profile-edit")));
		wd.navigate().refresh();
		try {
			return wd.findElement(By.cssSelector("button.btn.btn-primary.btn-wide.profile-edit"));
		} catch (Exception e) {
			wd.navigate().refresh();
			return wd.findElement(By.cssSelector("button.btn.btn-primary.btn-wide.profile-edit"));
		}
	}

	public WebElement updateEmailIcon() {
		return wd.findElement(By.xpath("//a[@href='update-email']"));
	}
	public String emailAddressText() {
		return wd.findElement(By.cssSelector("div.email .detail-value")).getText();
	}
	
	public WebElement updatePhoneNumberIcon() {
		return wd.findElement(By.xpath("//a[@href='add-phone']"));
	}
	public String phoneNumberText() {
		return wd.findElement(By.cssSelector("div.phone .detail-value")).getText();
	}

	public WebElement updatePasswordIcon() {
		return wd.findElement(By.xpath("//a[@href='update-password']"));
	}

	public WebElement newEmailAddressField() {
		return wd.findElement(By.id("profile.email"));
	}

	public String NewEmailAddressFieldErrorMessage() {
		return wd.findElement(By.id("profileemail-errors")).getText();
	}

	public WebElement confirmEmailAddressField() {
		return wd.findElement(By.id("profile.checkEmail"));
	}

	public String ConfirmEmailAddressFieldErrorMessage() {
		return wd.findElement(By.id("profilecheckEmail-errors")).getText();
	}

	public WebElement passwordField() {
		return wd.findElement(By.id("profile.pwd"));
	}

	public String PasswordFieldErrorMessage() {
		return wd.findElement(By.id("password-errors")).getText();
	}

	public WebElement updateButton() {
		return wd.findElement(By.cssSelector("button.btn.btn-primary.btn-responsive"));
	}

	public WebElement cancelButton() {
		return wd.findElement(By.cssSelector(".btn-default"));
	}

	public WebElement updatePhoneNumberField() {
		return wd.findElement(By.id("updatePhone.phoneNumber"));
	}

	public WebElement isMobileCheckedCheckbox() {
		return wd.findElement(By.name("isMobileChecked"));
	}

	public WebElement existingPasswordField() {
		return wd.findElement(By.name("currentpwd"));
	}

	public WebElement newPasswordField() {
		return wd.findElement(By.name("pwd"));
	}

	public WebElement newConfirmPasswordField() {
		return wd.findElement(By.name("checkPwd"));
	}
}
