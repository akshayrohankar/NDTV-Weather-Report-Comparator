package com.framework.PageObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.Resources.base;

public class WeatherPage extends base {

	public WeatherPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='searchBox']")
	public WebElement searchBox_PinYourCity;

	@FindBy(xpath = "//div[@id='messages']")
	public WebElement list_PinYourCity;

	@FindBy(xpath = "//div[@class='temperatureContainer']")
	public WebElement tempContainers;

	@FindBy(xpath = "//div[@id='messages']//input")
	public WebElement cityCheckbox;

	@FindBy(xpath = "//b[contains(text(),'Temp in Degrees:')]")
	public WebElement tempInformationInD;

	@FindBy(xpath = "//b[contains(text(),'Temp in Fahrenheit:')]")
	public WebElement tempInformationInF;
	
	@FindBy(xpath = "//b[contains(text(),'Humidity:')]")
	public WebElement humidityInformationInPercentage;
	

	public void SearchCity(String cityName) throws InterruptedException {
		searchBox_PinYourCity.sendKeys(cityName);
		Thread.sleep(2000);
		try {
			String displayCheckbox = "//input[@id='" + prop.getProperty("search_city") + "']";
			WebElement checkbox = driver.findElement(By.xpath(displayCheckbox));
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		} catch (NoSuchElementException exception) {
			System.out.println("Invalid City name entered");
			driver.close();
			System.exit(0);
		}
	}

	public void ValidateCityName() throws InterruptedException {
		//WebElement cities = list_PinYourCity;
		//List<WebElement> cityList = cities.findElements(By.tagName("label"));
		//int cityCount = cityList.size();
		//System.out.println("Count of list of cities available: " + cityCount);

		List<WebElement> resultCityList = driver.findElements(By.xpath("//div[@id='messages']"));
		int flag = resultCityList.size();
		if (flag == 1) {
			System.out.println("City name available !");
		} else {
			System.out.println("City name not available !!! | Please enter valid city name.");
		}
		Thread.sleep(2000);
	}

	public void IsCityAndTempDisplayedInMap() throws InterruptedException {
		String displayCityNameInMap = "//div[@title='" + prop.getProperty("search_city") + "']";
		WebElement cityDisplayed = driver.findElement(By.xpath(displayCityNameInMap));
		if (cityDisplayed.isDisplayed()) {
			System.out.println("City displayed in Map !");
		} else {
			System.out.println("City not displayed in Map !");
		}

		// verify if temperature data displayed in white text
		String fahrenheitTempLocator = "//div[@title='" + prop.getProperty("search_city")
				+ "']//div[@class='temperatureContainer']//span[@class='tempWhiteText']";
		WebElement tempInFahrenheit = driver.findElement(By.xpath(fahrenheitTempLocator));
		tempInFahrenheit.click();
		Thread.sleep(3000);
	}

	public double DisplayTempInformation() {
		// temp information from view
		// String tempInformationOfCityInDegree = tempInformationInD.getText();
		String tempInformationOfCityInFahrnt = tempInformationInF.getText();
		// System.out.println(tempInformationOfCityInDegree);
		String[] tempArray = tempInformationOfCityInFahrnt.split(":");
		double tempInFahrnt = Double.parseDouble(tempArray[1].trim());
		return tempInFahrnt;
	}
	
	public double DisplayHumidityInformation() {
		// humidity information from view
		String humidityInfo = humidityInformationInPercentage.getText();
		//System.out.println(humidityInfo);
		String[] humidityArray = humidityInfo.split(":");
		String percentage = humidityArray[1].trim().replaceAll("[^a-zA-Z0-9]", "");
		double humidity = Double.parseDouble(percentage);
		return humidity;
	}

}
