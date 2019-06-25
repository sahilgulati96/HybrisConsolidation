package CommonCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Lightbox {
	
	WebDriver wd;
	
	public void assignSession(WebDriver wd) {
		this.wd=wd;
	}
	public void closeLightBox() {
		try {
		wd.findElement(By.cssSelector(".close")).click();
	}
		catch(Exception e) {
		System.out.println(e.getMessage());	
		}
	}
}
