package com.framework.Utility;

public class RangeMatcher {
	static double degree = 2; // variance allowed upto 2 degree
	static double fahrenheit = 35.6; // variance allowed upto 35.6 F

	public static boolean isTempWithinSpecifiedRange(double variance) {
		//the variance logic - for e.g. 2 degree celsius for temperature & 10% humidity
		boolean flag = false;
		if ((variance <= fahrenheit)) {
			flag = true;
		}
		return flag;
	}
	
}
