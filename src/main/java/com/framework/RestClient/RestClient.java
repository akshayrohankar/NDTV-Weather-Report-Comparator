package com.framework.RestClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	public JSONObject getResponseJSON(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // Http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); // Status code for API
		System.out.println("Status code: " + statusCode);

		String responseRawString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"); // JSON Raw String

		JSONObject responseJSON = new JSONObject(responseRawString); // JSON formatter
		// System.out.println("Response JSON from API: " + responseJSON);
		// To validate JSON file visit https://jsonlint.com/
		return responseJSON;
	}

	public double getTempInfo(JSONObject responseJSON) {
		// Data extraction from responseJSON
		JSONObject object = (JSONObject) responseJSON.get("main");
		double kelvin = object.getDouble("temp");
		// System.out.println("Temerature from API in Kelvin: " + kelvin);

		double fahrenheit = ((kelvin - 273.15) * 9 / 5) + 32; // Formula to convert K to F ---> (297.13K − 273.15) × 9/5 + 32 = 75.164°F
		BigDecimal bd = new BigDecimal(fahrenheit);
		bd = bd.round(new MathContext(3));
		double roundedTempInFahrenheit = bd.doubleValue();
		// System.out.println("Temerature from API in Fahrenheit: " +
		// roundedTempInFahrenheit);
		return roundedTempInFahrenheit;
	}

	public double getHumidityInfo(JSONObject responseJSON) {
		// Data extraction from responseJSON
		JSONObject object = (JSONObject) responseJSON.get("main");
		double humidity = object.getDouble("humidity");
		// System.out.println("Humidity from API in Percentage: " + humidity);
		return humidity;
	}

}
