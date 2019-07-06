package footerObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterRepo {

	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement privacyPolicy() {
		return wd.findElement(By.linkText("Privacy Policy"));
	}

	public WebElement coBrowse() {
		return wd.findElement(By.linkText("Start Cobrowse"));
	}

	public WebElement SiteMap() {
		return wd.findElement(By.linkText("Site Map"));
	}

	public WebElement CustomerSiteMap() {
		return wd.findElement(By.linkText("Customer SiteMap"));
	}

	public WebElement TermOfUse() {
		return wd.findElement(By.linkText("Terms of Use"));
	}
	public String CopyWriteText() {
		return wd.findElement(By.cssSelector(".copyright_year")).getText();
	}
}
