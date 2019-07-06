package footerObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SocialMediaSharingRepo {
	
	WebDriver wd;
	
	public void assignSession(WebDriver wd) {
		this.wd=wd;
	}
	public WebElement facebookIcon() {
		return wd.findElement(By.cssSelector("div.ext-links [href='https://www.facebook.com/KayJewelers']"));
	}
	public WebElement youtubeIcon() {
		return wd.findElement(By.cssSelector("div.ext-links [href='https://www.youtube.com/c/kayjewelers']"));
	}
	public WebElement pinInterestIcon() {
		return wd.findElement(By.cssSelector("div.ext-links [href='https://www.pinterest.com/kayjewelers/']"));
	}
	public WebElement twitterIcon() {
		return wd.findElement(By.cssSelector("div.ext-links [href='https://twitter.com/KayJewelers']"));
	}
	public WebElement instagramIcon() {
		return wd.findElement(By.cssSelector("div.ext-links [href='https://www.instagram.com/kayjewelers/']']"));
	}

}
