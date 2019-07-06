package CommonCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webObjectRepo.HeaderRepo;

public class SearchBar {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public void search(String searchterm) {

		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		WebDriverWait wait = new WebDriverWait(wd, 20);
		wait.until(ExpectedConditions.elementToBeClickable(header.SearchBar()));
		header.SearchBar().click();
		header.SearchBar().sendKeys(searchterm);
		header.SearchIcon().click();
	}
}
