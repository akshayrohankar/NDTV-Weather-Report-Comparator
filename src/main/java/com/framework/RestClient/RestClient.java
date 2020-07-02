package com.framework.RestClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient {

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // Http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
		return closeableHttpResponse;
	}

	
	public static String getValueByJPath(JSONObject responsejson, String jpath){
		Object obj = responsejson;
		for(String s : jpath.split("/")) 
			if(!s.isEmpty()) 
				if(!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if(s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}
	
	public double getTempInfo(JSONObject responseJSON) {
		// Data extraction from responseJSON
		String temperatureKelvin = RestClient.getValueByJPath(responseJSON, "/main/temp");
		double kelvin = Double.parseDouble(temperatureKelvin);
		double fahrenheit = ((kelvin - 273.15) * 9 / 5) + 32; // Formula to convert K to F ---> (297.13K − 273.15) × 9/5 + 32 = 75.164°F
		BigDecimal bd = new BigDecimal(fahrenheit);
		bd = bd.round(new MathContext(3));
		double roundedTempInFahrenheit = bd.doubleValue();
		return roundedTempInFahrenheit;
	}

	public double getHumidityInfo(JSONObject responseJSON) {
		// Data extraction from responseJSON
		String humidityAPI = RestClient.getValueByJPath(responseJSON, "/main/humidity");
		double humidity = Double.parseDouble(humidityAPI);
		return humidity;
	}

}
