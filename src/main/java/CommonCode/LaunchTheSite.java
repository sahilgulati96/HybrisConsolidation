package CommonCode;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class LaunchTheSite {
	WebDriver webdriver;
	String url;

	public WebDriver webdriver() {
		return webdriver;
	}

	public String website(String website, String browser) throws InterruptedException {
		//Configure the browser and launch the website
		webdriver = Driver.launchbrowser(browser);
		webdriver.manage().window().maximize();
		webdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Website launch Selection
		if (website.equalsIgnoreCase("kay")) {
			url = "https://uat1.kay.com";
			webdriver.get(url);
			return url;
		} else if (website.equalsIgnoreCase("kayoutlet")) {
			url = "https://uat1.kayoutlet.com";
			webdriver.get(url);
			return url;
		} else if (website.equalsIgnoreCase("jared")) {
			url = "https://uat1.jared.com";
			webdriver.get(url);
			return url;
		} else {
			System.out.println("Please provide the correct website name");

			return null;
		}
	}

}
