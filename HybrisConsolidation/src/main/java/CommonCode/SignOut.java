package CommonCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import webObjectRepo.HeaderRepo;

public class SignOut {
	WebDriver wd;
	
	public void assignSession(WebDriver wd) {
		this.wd=wd;
	}
	
	public void signOut() {
		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		Actions mouse = new Actions(wd);
		mouse.moveToElement(header.myAccountLink()).moveToElement(header.signOutLink()).click().build().perform();
	}

}
