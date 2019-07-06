package webObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAccountPageRepo {
	// Object Repo for https://uat1.kay.com/register
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement emailAddressField() {
		return wd.findElement(By.name("email"));
	}

	public WebElement passwordField() {
		return wd.findElement(By.name("pwd"));
	}

	public WebElement confirmPasswordField() {
		return wd.findElement(By.name("checkPwd"));
	}

	public WebElement createAccountButton() {
		return wd.findElement(By.cssSelector("button.btn.btn-primary"));
	}

	public WebElement firstNameField() {
		return wd.findElement(By.name("firstName"));
	}

	public WebElement lastNameField() {
		return wd.findElement(By.name("lastName"));
	}

	public WebElement mobileNumberField() {
		return wd.findElement(By.name("mobileNumber"));
	}

	public WebElement mobileNumberCheckbox() {
		return wd.findElement(By.name("isMobileChecked"));
	}

	public WebElement googleLoginButton() {
		return wd.findElement(By.cssSelector("button.loginBtn.loginBtn--google.go-button.go-login"));
	}

	public WebElement facebookLoginButton() {
		return wd.findElement(By.cssSelector("button.loginBtn.loginBtn--facebook.fb-button.fb-login"));
	}
}
