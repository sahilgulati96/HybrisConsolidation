package footerObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterLinkRepo {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement OrderStatus() {
		return wd.findElement(By.linkText("Order Status"));
	}

}
