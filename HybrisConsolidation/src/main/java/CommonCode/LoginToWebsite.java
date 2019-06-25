package CommonCode;

import org.openqa.selenium.WebDriver;

import webObjectRepo.HeaderRepo;
import webObjectRepo.SignInPageRepo;

public class LoginToWebsite {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public void loginToWebsite(String username, String password) {
		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		header.SignInLink().click();

		SignInPageRepo signin = new SignInPageRepo();
		signin.assignSession(wd);
		signin.emailAddressField().click();
		signin.emailAddressField().sendKeys(username);
		signin.passwordField().click();
		signin.passwordField().sendKeys(password);
		signin.SignInButton().click();

	}

}
