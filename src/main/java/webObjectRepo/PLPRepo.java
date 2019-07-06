package webObjectRepo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PLPRepo {

	WebDriver wd;

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	
	public WebElement selectedProduct() {

		WebDriverWait wait = new WebDriverWait(wd, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("product-item")));

		// Create a List of web elements for the items shown on PLP usually 36 in number
		List<WebElement> products = wd.findElements(By.className("product-item"));

		// Print total number of webelements
		System.out.println(products.size());
		// Running the iterator to select a random value for the product to be selected
		// on plp
		Iterator<WebElement> productsList = products.iterator();

		// Generate Random Number
		Random rand = new Random();
		int randomNumber = rand.nextInt(products.size()) + 1;
		//System.out.println("Random number generated is : " + randomNumber);

		// Iterator till random number to assign a product to webelement selectedProduct
		WebElement selectedProduct = null;
		for (int i = 0; i <= randomNumber; i++) {
			selectedProduct = productsList.next();

		}
		return selectedProduct;
	}

	public WebElement thumbnial() {
		return selectedProduct().findElement(By.cssSelector("a.thumb.main-thumb"));
	}

	public WebElement ProductNameLinkText() {
		return wd.findElement(By.cssSelector("ul.product-listing > li:nth-of-type(1) .tablet-tile-bottom > .name"));
	}

	public WebElement listingPrice() {
		return wd.findElement(By.cssSelector(
				"ul.product-listing > li:nth-of-type(1) div:nth-of-type(3) div:nth-of-type(3) > div:nth-of-type(1) > div:nth-of-type(1)"));
	}

	public WebElement starRating() {
		return wd.findElement(
				By.cssSelector("ul.product-listing > li:nth-of-type(1) .tablet-tile-bottom .rating-stars"));
	}

	public WebElement viewDetailsButton() {

		// List of viewDetailsButton
		List<WebElement> products = wd
				.findElements(By.cssSelector("a.btn.btn-primary.btn-block.visible-md.visible-lg"));

		 System.out.println(products.size());
		// Iterate webelement to select random value
		Iterator<WebElement> productsList = products.iterator();
		Random rand = new Random();
		int randomNumber = rand.nextInt(products.size()) + 1;
		System.out.println("Random number generated is : " + randomNumber);

		// Loop over to store the value in the webelement
		WebElement selectedProduct = null;
		for (int i = 0; i <= randomNumber; i++) {
			selectedProduct = productsList.next();

		}
		return selectedProduct;
	}
}
