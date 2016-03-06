package com.gergo.takacs.stock.calculator;

import java.util.List;

import org.apache.commons.math3.stat.StatUtils;

public class GBCEIndexCalculator implements IndexCalculator {

	@Override
	public double calculate(List<StockInformation> stockInformation) {
		if (stockInformation.isEmpty()) {
			throw new IllegalArgumentException("No stock price presented.");
		}
		double[] prices = convertToArray(stockInformation);
		return StatUtils.geometricMean(prices);
	}

	private double[] convertToArray(List<StockInformation> stockInformation) {
		double[] result = new double[stockInformation.size()];
		for (int index = 0; index < stockInformation.size(); index++) {
			StockInformation currentStockInformation = stockInformation.get(index);
			result[index] = currentStockInformation.getStockPrice();
		}
		return result;
	}

}
