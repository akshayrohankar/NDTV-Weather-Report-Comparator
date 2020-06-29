package com.framework.WeatherCompareTest;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.PageObjectRepository.HomePage;
import com.framework.PageObjectRepository.WeatherPage;
import com.framework.Resources.base;

public class WeatherTest extends base {

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}

	@Test
	public void searchCityTemperatureTest() throws InterruptedException {
        HomePage homepage = new HomePage();
		WeatherPage weather = new WeatherPage();
		
		//PHASE-1
		// Navigate to Weather Tab
		homepage.NavigateToWeatherTab();
		//Search and select city name in textbox
		weather.SearchCity(prop.getProperty("search_city"));
		//Validate city name entered against list of cities available
		weather.ValidateCityName();
		//Verify if the entered city is displayed in Map with temperature in Fahrenheit
		weather.IsCityAndTempDisplayedInMap();
		//Display temperature information in both D and F from view
		weather.DisplayTempInformation();	
	}

	@AfterTest
	public void teardown() throws IOException {
		driver.quit();
	}
}
