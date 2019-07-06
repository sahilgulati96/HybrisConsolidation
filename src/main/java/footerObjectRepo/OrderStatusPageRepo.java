package footerObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderStatusPageRepo {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement emailAddressField() {
		//WebDriverWait wait = new WebDriverWait(wd, 20);
		//wait.until(ExpectedConditions.elementToBeClickable(By.name("emailAddrss")));
		return wd.findElement(By.name("emailAddrss"));
	}
	public WebElement orderNumberField() {
		return wd.findElement(By.id("guestuser.orderID"));
	}
	public WebElement ViewOrderStatusButton() {
		return wd.findElement(By.cssSelector("button.btn.btn-primary"));
	}
}
