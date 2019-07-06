package CommonCode;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testData.SelectionClass;
import webObjectRepo.CartPageRepo;
import webObjectRepo.CheckOutPageRepo;
import webObjectRepo.HeaderRepo;
import webObjectRepo.PDPRepo;
import webObjectRepo.PLPRepo;

public class CommonActions {

	WebDriver wd;
	SelectionClass sc = new SelectionClass();

	public void assignSession(WebDriver wd) {
		this.wd = wd;
	}

	public void AddtoShoppingBagButtonClick() {
		try {
			PDPRepo pdp = new PDPRepo();
			pdp.assignSession(wd);
			pdp.AddToShoppingBagButton().click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		} catch (NullPointerException e1) {
			wd.navigate().back();
			PLPRepo plp = new PLPRepo();
			plp.assignSession(wd);
			plp.viewDetailsButton().click();
			PDPRepo pdp = new PDPRepo();
			pdp.assignSession(wd);
			pdp.AddToShoppingBagButton().click();
		}
	}

	public void MiniCartIconClick() throws InterruptedException {
		HeaderRepo header = new HeaderRepo();
		header.assignSession(wd);
		Thread.sleep(2000);
		header.MiniCartIcon().click();
	}

	public void proceedToCheckOutButtonClick() {
		CartPageRepo crp = new CartPageRepo();
		crp.assignSession(wd);
		WebDriverWait wait = new WebDriverWait(wd, 20);
		wait.until(ExpectedConditions.elementToBeClickable(crp.proceedToCheckoutButton()));
		crp.proceedToCheckoutButton().click();
		try {
			crp.proceedToCheckoutButton().click();
		}catch(Exception e) {
			
		}
	}

	public void signInOnCheckout() {
		CheckOutPageRepo copr = new CheckOutPageRepo();
		copr.assignSession(wd);
		String username = Array.get(sc.testdata.logincredentials(), 0).toString();
		String password = Array.get(sc.testdata.logincredentials(), 1).toString();
		copr.username().click();
		copr.username().sendKeys(username);
		copr.password().click();
		copr.password().sendKeys(password);
	}

	public void checkoutAsGuestButtonClick() {
		CheckOutPageRepo copr = new CheckOutPageRepo();
		copr.assignSession(wd);
		copr.checkoutAsGuestButton().click();
	}

	public void stateDropDownSelection(Boolean taxBreakdown) {
		CheckOutPageRepo copr = new CheckOutPageRepo();
		copr.assignSession(wd);
		copr.stateDropDownDTC().click();
		Select dropdown = new Select(copr.stateDropDownDTC());
		List<WebElement> list = dropdown.getOptions();
		Random rand = new Random();
		int indexNumber = rand.nextInt(list.size()) + 1;
		if (taxBreakdown == true) {
			dropdown.selectByVisibleText("Tennessee");

		} else {
			dropdown.selectByIndex(indexNumber);
		}
	}

	public void shiptoAddressFormFill() {
		CheckOutPageRepo copr = new CheckOutPageRepo();
		copr.assignSession(wd);
		copr.firstNameDTC().click();
		copr.firstNameDTC().sendKeys("Sahil");
		copr.lastNameDTC().click();
		copr.lastNameDTC().sendKeys("Gulati");
		copr.addressLine1DTC().click();
		copr.addressLine1DTC().sendKeys("844  Haven Lane");
		copr.addressLine2DTC().click();
		copr.addressLine2DTC().sendKeys("");
		copr.cityDTC().click();
		copr.cityDTC().sendKeys("Potterville");
		stateDropDownSelection(false);
		copr.zipcodeDTC().click();
		copr.zipcodeDTC().sendKeys("48876");
		copr.phoneNumberDTC().click();
		copr.phoneNumberDTC().sendKeys("5176453454");
		copr.nextSelectShippingMethodButton().click();

	}

}
