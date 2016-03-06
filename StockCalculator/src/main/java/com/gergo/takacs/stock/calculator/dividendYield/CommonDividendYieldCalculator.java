package com.gergo.takacs.stock.calculator.dividendYield;

import com.gergo.takacs.stock.CommonStock;
import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.calculator.DividendYieldCalculator;

public class CommonDividendYieldCalculator implements DividendYieldCalculator {

	@Override
	public double calculate(Stock stock) {
		if (stock instanceof CommonStock) {
			return calculate((CommonStock) stock);
		}
		throw new IllegalArgumentException(
				"Wrong input type. Required: " + CommonStock.class + " but got " + stock.getClass());
	}

	private double calculate(CommonStock stock) {
		return stock.getLastDividend() / stock.getTickerPrice();
	}

	@Override
	public Class<CommonStock> getStockType() {
		return CommonStock.class;
	}
}
