package com.framework.WeatherCompareTest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.PageObjectRepository.HomePage;
import com.framework.PageObjectRepository.WeatherPage;
import com.framework.Resources.base;
import com.framework.RestClient.RestClient;
import com.framework.Utility.RangeMatcher;
import com.framework.Utility.VarianceCalculator;

public class WeatherTest extends base {
	static double tempInFahrenheitFromNDTV = 0;
	static double tempInFahrenheitFromAPI = 0;
	static double humidityInPercentageFromNDTV = 0;
	static double humidityInPercentageFromAPI = 0;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}

	// PHASE-1
	@Test(priority = 1, enabled = true)
	public void searchCityNameTest() throws InterruptedException {
		HomePage homepage = new HomePage();
		WeatherPage weather = new WeatherPage();
		// Navigate to Weather Tab
		homepage.NavigateToWeatherTab();
		// Search and select city name in textbox
		weather.SearchCity(prop.getProperty("search_city"));
		// Validate city name entered against list of cities available
		weather.ValidateCityName();
		// Verify if the entered city is displayed in Map with temperature in Fahrenheit
		weather.IsCityAndTempDisplayedInMap();
		// Display temperature information in both D and F from view
		tempInFahrenheitFromNDTV = weather.DisplayTempInformation();
		System.out.println("Temperature from NDTV in Fahrenheit: " + tempInFahrenheitFromNDTV);
		// Display humidity information in percentage from view
		humidityInPercentageFromNDTV = weather.DisplayHumidityInformation();
		System.out.println("Humidity from NDTV in Percentage: " + humidityInPercentageFromNDTV);
	}

	// PHASE-2
	@Test(priority = 2, enabled = true)
	public void dataFromAPI() throws ClientProtocolException, IOException {
		String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + prop.getProperty("search_city")
				+ "&appid=" + prop.getProperty("API_key");
		RestClient restClient = new RestClient();
		JSONObject responseJSON = restClient.getResponseJSON(apiUrl); // Response JSON received from API

		tempInFahrenheitFromAPI = restClient.getTempInfo(responseJSON); // get temperature from API
		System.out.println("Temperature from API in Fahrenheit: " + tempInFahrenheitFromAPI);

		humidityInPercentageFromAPI = restClient.getHumidityInfo(responseJSON); // get humidity from API
		System.out.println("Humidity from API in Percentage: " + humidityInPercentageFromAPI);
	}

	// PHASE-3
	@Test(priority = 3, enabled = true)
	public void attributeComparator() {
		double variance_Temperature = VarianceCalculator.getVariance(tempInFahrenheitFromNDTV, tempInFahrenheitFromAPI);
		double variance_Humidity = VarianceCalculator.getVariance(humidityInPercentageFromNDTV,humidityInPercentageFromAPI);
		System.out.println("Temperature Variance is: " + variance_Temperature);
		System.out.println("Humidity Variance is: " + variance_Humidity);

		boolean temperatureRange = RangeMatcher.isTempWithinSpecifiedRange(variance_Temperature);
		Assert.assertTrue(temperatureRange);
		System.out.println("Temperature difference is within the range specified...");

		boolean humidityRange = RangeMatcher.isHumidityWithinSpecifiedRange(variance_Humidity);
		Assert.assertTrue(humidityRange);
		System.out.println("Humidity difference is within the range specified...");
	}

	@AfterTest
	public void teardown() throws IOException {
		driver.quit();
	}
}
