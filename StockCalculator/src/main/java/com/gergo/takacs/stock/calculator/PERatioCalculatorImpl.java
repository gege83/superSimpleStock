package com.gergo.takacs.stock.calculator;

import com.gergo.takacs.stock.Stock;

public class PERatioCalculatorImpl implements PERatioCalculator {

	@Override
	public double calculate(Stock stock, double dividend) {
		return stock.getTickerPrice() / dividend;
	}

}
