package com.appiumtest;

import java.io.File;
import java.net.URL;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumDemo {
	public static AppiumDriver<MobileElement> driver;
	public static File appDir = new File("src/test/java");
	public static File app = new File(appDir, "nopstationCart_4.40.apk");

	public static void main(String[] args) {
		appiumTest1();

	}


	private static void scrollDown2(){
		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.100);
		int scrollEnd = (int) (dimension.getHeight() * 0.01);
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(0,scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
				.moveTo(PointOption.point(0,scrollEnd))
				.release().perform();
	}
	private static void scrollDown(){
		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.57);
		int scrollEnd = (int) (dimension.getHeight() * 0.02);
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(0,scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
				.moveTo(PointOption.point(0,scrollEnd))
				.release().perform();
	}
	public static void appiumTest1() {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "android");
		caps.setCapability("platformVersion", "12");
		caps.setCapability("deviceName", "sdk_gphone64_arm64");
//		caps.setCapability("udid", "emulator-5554");
		caps.setCapability("automationName", "UiAutomator2");
//		caps.setCapability("appPackage", "com.nopstation.nopcommerce.nopstationcart");
//		caps.setCapability("appActivity", "com.bs.ecommerce.main.MainActivity");
//
		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		try {
			URL uri = new URL("http://127.0.0.1:4723/wd/hub/");
			driver = new AppiumDriver<MobileElement>(uri, caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			WebElement clickAndAgree = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.Button");
			clickAndAgree.click();
			driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);

			//When: Mike click "electronics" from our categories list from home page
			List<MobileElement> categoriesItem = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.nopstation.nopcommerce.nopstationcart:id/ivProductThumb\")"));
			MobileElement firstElement =  categoriesItem.get(0);
			MobileElement sixElement =  categoriesItem.get(6);
			int midOfYCoordinate = firstElement.getLocation().y + (firstElement.getSize().height/2);
			int firstElementXCoordinate = firstElement.getLocation().x;
			int sixElementXCoordinate =	sixElement.getLocation().x;

			TouchAction action = new TouchAction(driver);

			action.press(PointOption.point(sixElementXCoordinate, midOfYCoordinate))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
					.moveTo(PointOption.point(firstElementXCoordinate, midOfYCoordinate))
					.release()
					.perform();
			WebElement clickHere= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.RelativeLayout/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]");
			action.tap(ElementOption.element(clickHere)).perform();


			//Page Scrolling
			scrollDown();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


			//And: Mike click to "Nokia Lumia 1020" product details page
			MobileElement clickItem= driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.view.ViewGroup");
			action.tap(ElementOption.element(clickItem)).perform();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		/*	Then: Mike select size "Large" from product details page
			And: Mike click plus button to increase Qty by "2"
			Then: Mike click add to cart button to add the product in his cart*/


			MobileElement quantity = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/btnPlus");
			action.tap(ElementOption.element(quantity)).perform();
			MobileElement clickToSize = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.view.ViewGroup[1]");
			action.tap(ElementOption.element(clickToSize)).perform();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			List<MobileElement> getSize = driver.findElements(By.className("android.widget.RadioButton"));
			getSize.get(1).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			MobileElement clickDone = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/tvDone");
			action.tap(ElementOption.element(clickDone)).perform();

		/*	Given: Mike go to shopping cart by clicking top cart icon
			When: Mike click checkout button from shopping cart page
			And: Mike select checkout as guest from shopping cart page
			Then: Mike input all the details in checkout billing details page and click continue*/

			//Add to cart
			MobileElement addToCart = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/btnAddToCart");
			action.tap(ElementOption.element(addToCart)).perform();

			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

			//Get to Shopping cart
			MobileElement getToTheCart = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/menu_cart");
			action.tap(ElementOption.element(getToTheCart)).perform();


			//Checkout
			MobileElement checkOut = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.RelativeLayout/android.view.ViewGroup[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.TextView[2]");
			action.tap(ElementOption.element(checkOut)).perform();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			//As a guest
			MobileElement asAGuest = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/btnGuestCheckout");
			action.tap(ElementOption.element(asAGuest)).perform();
			driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);


			//shipping address add
			MobileElement firstName = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/etFirstName");
			action.tap(ElementOption.element(firstName)).perform();
			firstName.sendKeys("Mehedi");

			MobileElement lastName = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etLastName"));
			action.tap(ElementOption.element(lastName)).perform();
			lastName.sendKeys("Hasan");

			MobileElement email = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout[2]/android.widget.FrameLayout[2]/android.view.ViewGroup[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText"));
			action.tap(ElementOption.element(email)).perform();
			email.sendKeys("mehdimithu@gmail.com");

			List<MobileElement> dropdownItems = driver.findElements(By.xpath("com.nopstation.nopcommerce.nopstationcart:id/countrySpinner"));
			dropdownItems.get(1).click();
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS );

			List<MobileElement> stateList = driver.findElementsById("com.nopstation.nopcommerce.nopstationcart:id/stateSpinner");
			stateList.get(0).click();

			scrollDown2();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS );

			//com.nopstation.nopcommerce.nopstationcart:id/etCompanyName
			MobileElement company = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etCompanyName"));
			action.tap(ElementOption.element(company)).perform();
			email.sendKeys("Little Big Town");
			//com.nopstation.nopcommerce.nopstationcart:id/etCity
			MobileElement city = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etCity"));
			action.tap(ElementOption.element(city)).perform();
			email.sendKeys("Dhaka");
			//com.nopstation.nopcommerce.nopstationcart:id/etStreetAddress
			MobileElement streetAddress = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etStreetAddress"));
			action.tap(ElementOption.element(streetAddress)).perform();
			email.sendKeys("Dhaka");
			//com.nopstation.nopcommerce.nopstationcart:id/etZipCode
			MobileElement zipeCode = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etZipCode"));
			action.tap(ElementOption.element(zipeCode)).perform();
			email.sendKeys("1200");
			//com.nopstation.nopcommerce.nopstationcart:id/etPhone
			MobileElement phone = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etPhone"));
			action.tap(ElementOption.element(phone)).perform();
			email.sendKeys("01755525571");
			//com.nopstation.nopcommerce.nopstationcart:id/btnContinue
			MobileElement continueBtn = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnContinue"));
			action.tap(ElementOption.element(continueBtn)).perform();



		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
