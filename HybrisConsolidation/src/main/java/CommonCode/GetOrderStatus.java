package CommonCode;

import org.openqa.selenium.WebDriver;

import footerObjectRepo.FooterLinkRepo;
import footerObjectRepo.OrderStatusPageRepo;

public class GetOrderStatus {

	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public void getOrderStatus(String emailAddress, String orderId) throws InterruptedException {
		FooterLinkRepo foot = new FooterLinkRepo();
		foot.assignSession(wd);
		foot.OrderStatus().click();
		Thread.sleep(3000);
		OrderStatusPageRepo osp = new OrderStatusPageRepo();
		osp.assignSession(wd);
		osp.emailAddressField().click();
		osp.emailAddressField().sendKeys(emailAddress);
		osp.orderNumberField().click();
		osp.orderNumberField().sendKeys(orderId);
		osp.ViewOrderStatusButton().click();
	}

}
