package webObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishListPageRepo {
	WebDriver wd;
	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public WebElement outOfStockButton() {
		return wd.findElement(By.cssSelector("button.out-of-stock.btn.btn-default.btn-block.red"));
	}

	public WebElement removeFromFavButton() {
		return wd.findElement(By.id("favoritesForm remove-form"));
	}

	public WebElement addToCartButton() {
		return wd.findElement(By.id("addToCartButton"));
	}

	public WebElement thumbnial() {

		return wd.findElement(By.cssSelector("a.thumb"));
	}

	public WebElement ProductNameLinkText() {

		return wd.findElement(By.cssSelector(".elipsis"));
	}

	public WebElement listingPrice() {

		return wd.findElement(By.className("price"));
	}

	public WebElement starRating() {

		return wd.findElement(By.className("rating-ringsize-wrapper"));
	}

	public String emptyWishListMessage() {
		return wd.findElement(By.cssSelector(".detail")).getText();
	}
}
