package webObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderRepo {

	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement WishtlistLink() {
		return wd.findElement(By.xpath("//ul[@class='nav-links pull-right']//a[contains(.,'Favorites')]"));
	}

	public WebElement SignInLink() {
		try {
			return wd.findElement(By.linkText("Sign In"));
		} catch (Exception e) {
			return wd.findElement(
					By.xpath("//*[@id=\"main-container\"]/div[2]/header/div[1]/div[1]/div/div/div[2]/ul/li[1]/a[1]"));
		}
	}

	public WebElement logoImage(String website) {
		if (website.equalsIgnoreCase("kay")) {
			return wd.findElement(By.xpath("//div[@class='col-sm-4 company-logo']//a[@href='/']"));
		} else if (website.equalsIgnoreCase("kayoutlet")) {
			return wd.findElement(By.xpath("//div[@class='col-md-4 print-logo']//a[@href='/']"));
		} else if (website.equalsIgnoreCase("jared")) {
			return wd.findElement(By.xpath("//div[@class='col-sm-4 company-logo']//a[@href='/']"));
		} else {
			System.out.println("Wrong website name");
			return null;
		}
	}

	public WebElement FindAStoreLink() {
		return wd.findElement(By.linkText("Find A Store"));
	}

	public WebElement GetPrequalifiedNowLink() {
		return wd.findElement(By.linkText("Get Prequalified Now"));
	}

	public WebElement MiniCartIcon() {
		return wd.findElement(By.cssSelector("a.shopping-bag-icon-outer"));
	}

	public WebElement SearchBar() {
		WebDriverWait wait = new WebDriverWait(wd, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("js-site-search-input")));
		return wd.findElement(By.id("js-site-search-input"));
	}

	public WebElement SearchIcon() {
		return wd.findElement(By.id("search_form_SearchBox_submit"));
	}

	public WebElement myAccountLink() {
		return wd.findElement(
				By.xpath("//a[contains(.,'My Account')]"));
	}

	public WebElement signOutLink() {
		return wd.findElement(By.xpath("//a[.='Sign Out']"));
	}

}
