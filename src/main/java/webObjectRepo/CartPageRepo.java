package webObjectRepo;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPageRepo {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement protectionPlanCheckbox() {
		return wd.findElement(By.name("addToCartPlanSub"));
	}

	public float cartTotal() {
		String total = wd.findElement(By.cssSelector(".top-cart-totals")).getText();
		String priceonly = total.substring(18).replaceAll(",", "");
		// String priceonly = priceonly2.replaceAll(",", "");
		// String priceonly = priceonly3.replaceAll("$", "");
		return Float.valueOf(priceonly);
	}

	public float ValueofIndividualProducts() {
		List<WebElement> list = wd.findElements(By.className("item-price"));
		System.out.println("Total number of products in the cart :" + list.size());
		Iterator<WebElement> productsList = list.iterator();
		String productpricestring = null;
		float sum = 0;
		for (int i = 1; i <= list.size(); i++) {
			productpricestring = productsList.next().getText();
			String productpriceSubString = productpricestring.substring(8).replaceAll(",", "");
			float productprice = Float.valueOf(productpriceSubString);
			System.out.println(productprice);
			sum = productprice + sum;
		}
		return sum;
	}

	public float estimatedSubTotal() {
		String total = wd.findElement(By.cssSelector("div.cart-totals-body > div:nth-of-type(2) > .col-xs-4")).getText()
				.substring(1).replaceAll(",", "");
		System.out.println(total);
		float estimatedTotal = Float.valueOf(total);
		return estimatedTotal;
	}

	public float valueOfProtectionPlans() {
		List<WebElement> list2 = wd.findElements(By.className("prod-plan-price"));
		System.out.println("Total number of ESP/JRP products in the cart :" + list2.size());
		Iterator<WebElement> productsList = list2.iterator();
		String productpricestring1 = null;
		float sum = 0;
		for (int i = 1; i <= list2.size(); i++) {
			productpricestring1 = productsList.next().getText();
			String productpriceSubString2 = productpricestring1.substring(8).replaceAll(",", "");
			float productprice1 = Float.valueOf(productpriceSubString2);
			System.out.println(productprice1);
			sum = productprice1 + sum;
		}
		return sum;
	}
	
	public WebElement proceedToCheckoutButton() {
		return wd.findElement(By.cssSelector(".row:nth-child(2) .btn-primary"));
	}
	
	public WebElement promocodeField() {
		return wd.findElement(By.id("promoCode"));
	}
	public WebElement promocodeApplyButton() {
		return wd.findElement(By.id("promo-btn"));
	}
}
