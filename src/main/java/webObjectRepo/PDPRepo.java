package webObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonCode.Lightbox;

public class PDPRepo {
	WebDriver wd;

	public void assignSession(WebDriver wd) {
		WebDriverWait wait = new WebDriverWait(wd, 20);
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addToCartButton")));
		}
		catch(Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("outOfStockButton")));	
		}
		this.wd = wd;
	}

	public String itemID() {
		return wd.findElement(By.cssSelector("b")).getText();
	}

	public WebElement AddToShoppingBagButton() {
		try {
			try {
		
			return wd.findElement(By.id("addToCartButton"));
		}
		catch(ElementClickInterceptedException expection3) {
			Lightbox lb = new Lightbox();
			lb.assignSession(wd);
			lb.closeLightBox();
			return null;
		}
	}
		catch(NoSuchElementException exception1) {
			try {
			System.out.println("Item is out of Stock try another item");	
			return wd.findElement(By.id("outOfStockButton"));
		}
			catch(Exception exception2) {
			System.out.println("Can not find Add to Shopping bag button");	
			exception2.printStackTrace();
			return null;	
			}
		}
		
	}
	


	public WebElement AddToWishlistLink() {
		return wd.findElement(By.cssSelector("button.btn.btn-default.btn-block.btn-favorite.js-add-to-fav"));
	}

	public String ItemId() {
		return wd.findElement(By.cssSelector("div.pdp-left b")).getText();
	}

	public String productName() {
		return wd.findElement(By.cssSelector(".name")).getText();
	}

	public WebElement ESPandJRpCheckbox() {
		return wd.findElement(By.id("addToCartPlanSub"));
	}
}
