package com.gergo.takacs.stock.calculator;

import java.util.List;

import org.apache.commons.math3.stat.StatUtils;

public class GBCEIndexCalculator implements IndexCalculator {

	@Override
	public double calculate(List<Double> stockPrices) {
		if (stockPrices.isEmpty()) {
			throw new IllegalArgumentException("No stock price presented.");
		}
		double[] prices = convertToArray(stockPrices);
		return StatUtils.geometricMean(prices);
	}

	private double[] convertToArray(List<Double> stockPrices) {
		double[] result = new double[stockPrices.size()];
		for (int index = 0; index < stockPrices.size(); index++) {
			result[index] = stockPrices.get(index);
		}
		return result;
	}

}
