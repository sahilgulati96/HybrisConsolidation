package CommonCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

	public static WebDriver launchbrowser(String browser) throws InterruptedException {
		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\sahil.a.gulati\\OneDrive - Accenture\\Testing Stream Training\\Automation Training\\chromedriver74.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--incognito");
			return new ChromeDriver(options);
		}
		if (browser.equalsIgnoreCase("ie")) {

			System.setProperty("webdriver.ie.driver",
					"C:\\\\Users\\\\sahil.a.gulati\\\\OneDrive - Accenture\\\\Testing Stream Training\\\\Automation Training\\IEDriverServer32.exe");
			return new InternetExplorerDriver();
		} else
			System.out.println("Invalid Browser please use other");
		return null;

	}

}
