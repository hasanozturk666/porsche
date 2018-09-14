import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.CharMatcher;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PorschePurchase {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		String URL = "https://www.porsche.com/usa/modelstart/";
		driver.get(URL);

		// Clicking on 718
		driver.findElement(By.className("b-teaser-preview-wrapper")).click();

		// Storing  price
		String carPrice = driver.findElement(By.className("m-14-model-price")).getText();

		// Removing non-digit characters from String
		String theDigits = CharMatcher.DIGIT.retainFrom(carPrice);

		// Getting rid of last two zeros, which were cents
		String carprice = theDigits.substring(0, theDigits.length()-2);

		// Converting the  price from String to int
		int carpriceInt = Integer.parseInt(carprice);

		// Clicking on Build & Price - opens new page
		driver.findElement(By.className("m-14-quick-link")).click();

		// switch the driver to the new window
		String winHandleBefore = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        // Storing base price
		String basePrice = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[1]")).getText();
		String basePricedigit = CharMatcher.DIGIT.retainFrom(basePrice);
		int basepriceInt = Integer.parseInt(basePricedigit);

		// Storing equipment price
		String equipment = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
		String equipdigit = CharMatcher.DIGIT.retainFrom(equipment);
		int equipmentInt = Integer.parseInt(equipdigit);

		// Storing delivery, processing and handling fee
		String deliver = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[3]")).getText();
		String deliverdigit = CharMatcher.DIGIT.retainFrom(deliver);
		int deliveryInt = Integer.parseInt(deliverdigit);

		// Storing total price
		String total = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]")).getText();
		String totaldigit = CharMatcher.DIGIT.retainFrom(total);
		int totalInt = Integer.parseInt(totaldigit);

		// Verifying if base price is the same as the price in the first page
		if(basepriceInt == carpriceInt) {
			System.out.println("Base price and car price are equal");
		}else {
			System.out.println("prices don't match");
		}

		// Verifying that no equipment is selected at the beginning
		if(equipmentInt == 0) {
			System.out.println("No equipment is selected");
		}else {
			System.out.println("You will be charged for selected equipment");
		}

		// Verifying that total price is initially equal to the sum of base price an delivery
		if(totalInt == (basepriceInt + deliveryInt)) {
			System.out.println("Total price is correct");
		}else {
			System.out.println("There is a mismatch in total price");
		}

		// Clicking on the Miami blue color
		driver.findElement(By.xpath("(//section[@id='s_exterieur']//span[@style='background-color: rgb(0, 120, 138);'])")).click();

		// Storing Miami blue color price and converting it into int
		String colornum = driver.findElement(By.id("s_exterieur_x_FJ5")).getAttribute("data-price");
		String miamidigit = CharMatcher.DIGIT.retainFrom(colornum);
		int miamicolorprice = Integer.parseInt(miamidigit);

		// Updating equipment price after selecting Miami Blue
		String equipmentnew = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
		String equipdigitmiami = CharMatcher.DIGIT.retainFrom(equipmentnew);
		int miamiequipment = Integer.parseInt(equipdigitmiami);

		// Verifying if the equipment price is correct after selecting Miami Blue
		if(miamicolorprice == miamiequipment) {
			System.out.println("Color price added successfully");
		}else {
			System.out.println("There is a problem with the color price ");
		}

		// Updating total price after selecting Miami Blue
		String newtotal = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]")).getText();
		String newtotaldigit = CharMatcher.DIGIT.retainFrom(newtotal);
		int newtotalInt = Integer.parseInt(newtotaldigit);

		// Verifying if the total price is correct after selecting Miami Blue
		if(newtotalInt == (basepriceInt + miamiequipment + deliveryInt)) {
			System.out.println("Color price added to the total price");
		}else {
			System.out.println("there is a problem with pricing after selecting color");
		}


		//scroll down
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,250)");

		//click on the wheel
		driver.findElement(By.xpath("//section[@id='s_conf']//div[@id='s_exterieur_x_IRA']//li[@id='s_exterieur_x_MXRD']")).click();

		// Storing wheel price and converting it into int
		String wheelnum = driver.findElement(By.id("s_exterieur_x_MXRD")).getAttribute("data-price");
		String wheeldigit = CharMatcher.DIGIT.retainFrom(wheelnum);
		int wheelprice = Integer.parseInt(wheeldigit);

		// Updating equipment price after selecting wheels
		String equipmentwheel = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
		String equipdigitwheel = CharMatcher.DIGIT.retainFrom(equipmentwheel);
		int wheelequipment = Integer.parseInt(equipdigitwheel);

		// Verifying if the equipment price is correct after selecting wheels
		if(wheelequipment == (wheelprice + miamicolorprice)) {
			System.out.println("Wheel price added successfully");
		}else {
			System.out.println("There is a problem with pricing of wheel selection");
		}

		// Updating total price after selecting wheels
		String totalwheel = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]")).getText();
		String totalwheeldigit = CharMatcher.DIGIT.retainFrom(totalwheel);
		int totalwheelInt = Integer.parseInt(totalwheeldigit);

		// Verifying if the total price is correct after selecting wheels
		if(totalwheelInt == (basepriceInt + wheelequipment + deliveryInt )) {
			System.out.println("Wheel price added to the total price ");
		}else {
			System.out.println("There is a problem with pricing after selecting wheel");
		}

		// scroll down
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");

		 // click on the seat
		 driver.findElement(By.id("s_interieur_x_PP06")).click();

		 // get the price of the seat and convert to int
		 String seatcost = driver.findElement(By.xpath("(//div[@id='s_interieur_x_submenu_seats']//div[@class='content']//div[@id='73']//div[@id='seatGroup_73']//div[@id='seats_73']//div[@class='seat'])[2]//div//div//div")).getText();
		 String seatcostdigit = CharMatcher.DIGIT.retainFrom(seatcost);
		 int seatprice = Integer.parseInt(seatcostdigit);

		// Updating equipment price
		 String equipmentseat = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
		 String equipdigitseat = CharMatcher.DIGIT.retainFrom(equipmentseat);
		 int seatequipment = Integer.parseInt(equipdigitseat);

		// Verifying if the equipment price is correct after selecting seat
		 if(seatequipment == (wheelprice + miamicolorprice + seatprice )) {
			 System.out.println("Seat pricing added successfully");
		 }else {
			System.out.println("There is a problem with pricing of seat selection");
		 }

		// Updating total price after selecting seat
		 String totalseat = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]")).getText();
		 String totalseatdigit = CharMatcher.DIGIT.retainFrom(totalseat);
		 int totalseatInt = Integer.parseInt(totalseatdigit);

		// Verifying if the total price is correct after selecting seat
		 if(totalseatInt == (basepriceInt + seatequipment + deliveryInt )) {
			System.out.println("Seat price added to the total price");
		 }else {
			System.out.println("There is a problem with pricing after selecting wheel");
		 }

		// scroll down
      	js.executeScript("window.scrollBy(0,1000)");

      	// click on interior carbon fiber
      	driver.findElement(By.id("s_individual_x_IIC")).click();
      	driver.findElement(By.id("vs_table_IIC_x_PEKH_x_c01_PEKH")).click();

      	// Getting the price of interior
      	String intprice = driver.findElement(By.xpath("//section[@id='vs_table_IIC']//div//div[2]//div")).getText();
      	String intpricedigit = CharMatcher.DIGIT.retainFrom(intprice);
		int interiorInt = Integer.parseInt(intpricedigit);

		// Updating equipment price
		String equipmentinterior = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
		 String equipdigitinterior = CharMatcher.DIGIT.retainFrom(equipmentinterior);
		 int interiorequipment = Integer.parseInt(equipdigitinterior);

		// Verifying if the equipment price is correct after selecting interior
		 if(interiorequipment == (wheelprice + miamicolorprice + seatprice + interiorInt )) {
			 System.out.println("Interior Carbon Fiber price added successfully");
		 }else {
			System.out.println("There is a problem with pricing of seat selection");
		 }

		 // Updating total price after selecting interior
		 String totalinterior = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]")).getText();
		 String totalinteriordigit = CharMatcher.DIGIT.retainFrom(totalinterior);
		 int totalinteriorInt = Integer.parseInt(totalinteriordigit);

		// Verifying if the total price is correct after selecting interior
		 if(totalinteriorInt == (basepriceInt + interiorequipment + deliveryInt )) {
			System.out.println("Total price is correct after selecting interior");
		 }else {
			System.out.println("There is a problem with pricing after selecting interior");
		 }

		// scroll up
	      	js.executeScript("window.scrollBy(0,-300)");

		//click on performance
	    driver.findElement(By.id("IMG_subHdl")).click();

	    // click on 7-speed
	    driver.findElement(By.id("vs_table_IMG_x_M250_x_c11_M250")).click();

	    // Getting the price of 7-speed and storing
		String speedcost = driver.findElement(By.xpath("//div[@id='vs_table_IMG_x_M250']//div[2]//div")).getText();
		String speedcostdigit = CharMatcher.DIGIT.retainFrom(speedcost);
		int speedprice = Integer.parseInt(speedcostdigit);

		// scroll down
      	js.executeScript("window.scrollBy(0,700)");

		// clicking on composite brakes
		driver.findElement(By.id("vs_table_IMG_x_M450_x_c91_M450")).click();

		// Getting the price of composite brake and storing
		String brakecost = driver.findElement(By.xpath("//div[@id='vs_table_IMG_x_M450']//div[2]//div")).getText();
		String brakecostdigit = CharMatcher.DIGIT.retainFrom(brakecost);
		int brakeprice = Integer.parseInt(brakecostdigit);

		// Updating equipment price after selecting 7-speed and brakes
		String equipmentbrake = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
		 String equipdigitbrake = CharMatcher.DIGIT.retainFrom(equipmentbrake);
		 int brakespeedequipment = Integer.parseInt(equipdigitbrake);

		// Verifying if the equipment price is correct after selecting brakes and 7-speed
		 if(brakespeedequipment == (wheelprice + miamicolorprice + seatprice + interiorInt + brakeprice + speedprice )) {
			 System.out.println("7-speed and brake price added successfully");
		 }else {
			System.out.println("There is a problem with pricing of brakes and 7-speed selection");
		 }

		// Updating total price after selecting brakes and 7-speed
		 String totalbrake = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]")).getText();
		 String totalbrakedigit = CharMatcher.DIGIT.retainFrom(totalbrake);
		 int totalbrakespeed = Integer.parseInt(totalbrakedigit);

		// Verifying if the total price is correct after selecting brakes and 7-speed
		 if(totalbrakespeed == (basepriceInt + brakespeedequipment + deliveryInt )) {
			System.out.println("Total price is correct after selecting brakes and 7-speed");
		 }else {
			System.out.println("There is a problem with pricing after selecting brakes and 7-speed");
		 }

		 System.out.println("================");
		 System.out.println("CONGRAT! YOU HAVE BUILT YOUR DREAM PORSCHE!");


	}
}
