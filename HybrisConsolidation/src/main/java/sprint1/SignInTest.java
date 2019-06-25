package sprint1;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonCode.LaunchTheSite;
import CommonCode.Lightbox;
import CommonCode.LoginToWebsite;
import CommonCode.Screenshots;
import testData.SelectionClass;
import webObjectRepo.HeaderRepo;
import webObjectRepo.SignInPageRepo;

public class SignInTest {
	WebDriver wd;
	String website = "kayoutlet";
	String browser = "chrome";
	String username = "";
	String password = "";
	SelectionClass sc = new SelectionClass();

	@BeforeMethod
	public void beforetest() throws InterruptedException {
		LaunchTheSite launch = new LaunchTheSite();
		launch.website(website, browser);
		wd = launch.webdriver();
		sc.select(website);

	}

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	@Test
	public void loginTest() {

		LoginToWebsite login = new LoginToWebsite();
		login.assignSession(wd);
		login.loginToWebsite(Array.get(sc.testdata.logincredentials(), 0).toString(),
				Array.get(sc.testdata.logincredentials(), 1).toString());
		
		Lightbox lb = new Lightbox();
		lb.assignSession(wd);
		lb.closeLightBox();
		
		String title = "Account Page | " + website;
		String expectedTitle = wd.getTitle();
		System.out.println("Title is: " + expectedTitle);
		Assert.assertEquals(title, expectedTitle);

	}

	// @Test
	public void facebookLoginTest() {
		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		header.SignInLink().click();

		SignInPageRepo fblogin = new SignInPageRepo();
		fblogin.assignSession(wd);
		fblogin.facebookLoginButton().click();

		Set<String> windows = wd.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String mainWindow = it.next();
		System.out.println(mainWindow);
		String fbWindow = it.next();
		System.out.println(fbWindow);
		wd.switchTo().window(fbWindow);
		wd.findElement(By.id("email")).sendKeys("sahilgulati340");
		wd.findElement(By.id("pass")).sendKeys("Sahil2635898");
		wd.findElement(By.id("u_0_0")).click();
		wd.switchTo().window(mainWindow);

		String title = "Account Page | " + website;
		String expectedTitle = wd.getTitle();
		System.out.println("Title is: " + expectedTitle);
		Assert.assertEquals(title, expectedTitle);
	}

	// @Test
	public void googleLoginTest() {
		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		header.SignInLink().click();

		SignInPageRepo googlelogin = new SignInPageRepo();
		googlelogin.assignSession(wd);
		googlelogin.googleLoginButton().click();

		Set<String> windows = wd.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String mainWindow = it.next();
		System.out.println(mainWindow);
		String googleWindow = it.next();
		System.out.println(googleWindow);
		wd.switchTo().window(googleWindow);
		System.out.println("Hi");
		wd.findElement(By.name("identifier")).sendKeys("sahilgulati340");
		wd.findElement(By.id("identifierNext")).click();
		wd.findElement(By.name("password")).sendKeys("Sahil2635898");
		wd.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")).click();
		wd.switchTo().window(mainWindow);

		String title = "Account Page | " + website;
		String expectedTitle = wd.getTitle();
		System.out.println("Title is: " + expectedTitle);
		Assert.assertEquals(title, expectedTitle);
	}

	@AfterMethod
	public void aftertest(ITestResult result) {
		Screenshots pic = new Screenshots();
		pic.assignSession(wd);
		pic.screenshot(result);
		wd.close();

	}
}
