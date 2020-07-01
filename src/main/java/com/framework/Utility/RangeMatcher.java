package com.framework.Utility;

public class RangeMatcher {
	static double degree = 2; // variance allowed upto 2 degree
	static double fahrenheit = 35.6; // variance allowed upto 35.6 F // 2 degree C
	static double humidity = 10; // humidity variance allowed upto 10.

	public static boolean isTempWithinSpecifiedRange(double variance) {
		// the variance logic - for e.g. 2 degree celsius for temperature
		boolean flag = false;
		if ((variance <= fahrenheit)) {
			flag = true;
		}
		return flag;
	}

	
	// Humidity is 10%. for now it is compared with 10. 
	// Please share more details for compairing humidity. 10% of what?
	// changes need to be made in below method accordingly.
	public static boolean isHumidityWithinSpecifiedRange(double variance) {
		// the variance logic - for e.g. 10% humidity
		boolean flag = false;
		if ((variance <= humidity)) {
			flag = true;
		}
		return flag;

	}

}
