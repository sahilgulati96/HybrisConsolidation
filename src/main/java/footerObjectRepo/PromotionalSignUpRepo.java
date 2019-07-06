package footerObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PromotionalSignUpRepo {

	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement emailField() {
		return wd.findElement(By.id("user-email"));
	}

	public WebElement signUpButton() {
		return wd.findElement(By.cssSelector("form#signup-form button"));
	}

	public String errorMessage() {
		
		return wd.findElement(By.id("user-email-errors")).getText();
	}

	public String thankYouMessage() {
		
		return wd.findElement(By.cssSelector("div.join-list div:nth-of-type(2)")).getText();
	}

}
