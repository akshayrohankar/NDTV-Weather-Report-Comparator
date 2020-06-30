package com.framework.Utility;

import java.math.BigDecimal;
import java.math.MathContext;

public class VarianceCalculator {

	// takes 2 sample data to calculate variance and return varianceS
	public static double getVariance(double n1, double n2) {
		double mean = (n1+n2)/2;
		//System.out.println("Mean: "+mean);
		double dev_n1 = n1-mean;
		double dev_n2 = n2-mean;
		//System.out.println("Daviation: " +dev_n1 +" " +dev_n2);
		double squaredDev_n1 = Math.pow(dev_n1, 2);
		double squaredDev_n2 = Math.pow(dev_n2, 2);
		//System.out.println("Squared Dev: " +squaredDev_n1 +" " +squaredDev_n1);
		//Round the result up to 3 decimal
		BigDecimal bd = new BigDecimal(squaredDev_n1+squaredDev_n2);
		bd = bd.round(new MathContext(3));
		double variance = bd.doubleValue();
		return variance;
	}
	
}
