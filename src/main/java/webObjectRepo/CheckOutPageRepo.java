package webObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutPageRepo {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement username() {
		return wd.findElement(By.id("j_username"));
	}

	public WebElement password() {
		return wd.findElement(By.id("j_password"));
	}

	public WebElement signInAndContinueButton() {
		return wd.findElement(By.cssSelector(".btn-default"));
	}

	public WebElement checkoutAsGuestButton() {
		return wd.findElement(By.cssSelector(".btn-primary"));
	}

	public WebElement firstNameDTC() {
		return wd.findElement(By.id("address.firstName"));
	}

	public WebElement lastNameDTC() {
		return wd.findElement(By.id("address.surname"));
	}

	public WebElement addressLine1DTC() {
		return wd.findElement(By.id("address.line1"));
	}

	public WebElement addressLine2DTC() {
		return wd.findElement(By.id("address.line2"));
	}

	public WebElement cityDTC() {
		return wd.findElement(By.id("address.townCity"));
	}

	public WebElement stateDropDownDTC() {
		return wd.findElement(By.id("address.region"));
	}

	public WebElement zipcodeDTC() {
		return wd.findElement(By.id("address.zipcode"));
	}

	public WebElement phoneNumberDTC() {
		return wd.findElement(By.id("address.phone"));
	}

	public WebElement nextSelectShippingMethodButton() {
		return wd.findElement(By.id("addressSubmit"));
	}
}
