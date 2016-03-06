package com.gergo.takacs.stock.calculator.dividendYield;

import com.gergo.takacs.stock.PreferredStock;
import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.calculator.DividendYieldCalculator;

public class PreferredDividendYieldCalculator implements DividendYieldCalculator {

	@Override
	public double calculate(Stock stock) {
		if (stock instanceof PreferredStock) {
			return calculate((PreferredStock) stock);
		}
		throw new IllegalArgumentException(
				"Wrong input type. Required: " + PreferredStock.class + " but got " + stock.getClass());
	}

	private double calculate(PreferredStock stock) {
		return stock.getParValue() * stock.getFixedDividend() / stock.getTickerPrice();
	}

	@Override
	public Class<PreferredStock> getStockType() {
		return PreferredStock.class;
	}

}
