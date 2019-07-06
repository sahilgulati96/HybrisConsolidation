package webObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountNavLinkRepo {

	WebDriver wd;
	
	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement orderHistoryLink() {
		return wd.findElement(By.xpath("//a[.='Order History']"));
	}

	public WebElement addressBookLink() {
		return wd.findElement(By.cssSelector("ul.myaccount-nav-list [href='/my-account/address-book']"));
	}

	public WebElement digitalWalletLink() {
		return wd.findElement(By.cssSelector("a[title='Digital Wallet']"));
	}

	public WebElement plccCardLink() {
		return wd.findElement(By.xpath("//a[.='My Kay Card']"));
	}

	public WebElement personalDetailsLink() {
		return wd.findElement(By.xpath("//a[.='Personal Details']"));
	}

	public WebElement linkFacebookAndGoogleLink() {
		return wd.findElement(By.xpath("//a[.='Link Facebook & Google']"));
	}

	public WebElement wishlistLink() {
		return wd.findElement(By.xpath("//a[.='Favorites']"));
	}
}
