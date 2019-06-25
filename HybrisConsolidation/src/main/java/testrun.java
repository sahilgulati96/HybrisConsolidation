import java.lang.reflect.Array;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import CommonCode.LaunchTheSite;
import CommonCode.LoginToWebsite;
import testData.SelectionClass;

public class testrun {
	WebDriver wd;
	String website = "kayoutlet";
	SelectionClass sc = new SelectionClass();

	@Test
	public void test() throws InterruptedException {
		LaunchTheSite launch = new LaunchTheSite();
		launch.website(website, "chrome");
		wd = launch.webdriver();
		sc.select(website);

		String username = Array.get(sc.testdata.logincredentials(), 0).toString();
		String password = Array.get(sc.testdata.logincredentials(), 1).toString();
		LoginToWebsite login = new LoginToWebsite();
		login.assignSession(wd);
		login.loginToWebsite(username, password);
		
		
		WebElement emailid = wd.findElement(By.id("email"));
		emailid.click();
		emailid.sendKeys("hello");
		

	}

}
