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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
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



	private static void scrollDown(){
		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.50);
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
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebElement clickAndAgree = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.Button");
			clickAndAgree.click();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

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

			//Add to cart
			MobileElement addToCart = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/btnAddToCart");
			action.tap(ElementOption.element(addToCart)).perform();

			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			//Get to Shopping cart
			MobileElement getToTheCart = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/menu_cart");
			action.tap(ElementOption.element(getToTheCart)).perform();



			//Checkout
			MobileElement checkOut = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.RelativeLayout/android.view.ViewGroup[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.TextView[2]");
			action.tap(ElementOption.element(checkOut)).perform();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			//As a guest
			MobileElement asAGuest = driver.findElementById("com.nopstation.nopcommerce.nopstationcart:id/btnGuestCheckout");
			action.tap(ElementOption.element(asAGuest)).perform();



		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
