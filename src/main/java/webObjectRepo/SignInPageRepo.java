package webObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPageRepo {
	//Object Repo for the page https://uat1.kay.com/login
	
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement emailAddressField() {
		return wd.findElement(By.id("j_username"));
	}

	public WebElement passwordField() {
		return wd.findElement(By.id("j_password"));
	}

	public WebElement SignInButton() {
		return wd.findElement(By.cssSelector("button.btn.btn-primary.btn-block.btn-login"));
	}

	public WebElement CreateAccountButton() {
		return wd.findElement(By.cssSelector(".btn:nth-child(2)"));
	}

	public WebElement googleLoginButton() {
		return wd.findElement(By.cssSelector("button.loginBtn.loginBtn--google.go-button.go-login"));
	}

	public WebElement facebookLoginButton() {
		return wd.findElement(By.cssSelector("button.loginBtn.loginBtn--facebook.fb-button.fb-login"));
	}

}
