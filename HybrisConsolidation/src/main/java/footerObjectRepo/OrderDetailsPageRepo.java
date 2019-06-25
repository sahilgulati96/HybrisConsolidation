package footerObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderDetailsPageRepo {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}
	public String OrderId() {
		String Ordernumberfull =wd.findElement(By.xpath("//div[@class='col-sm-6']")).getText();
		String orderid = Ordernumberfull.substring(8);
		return orderid;
	}

}
