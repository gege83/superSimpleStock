package com.gergo.takacs.stock.calculator.dividendYield;

import java.util.List;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.calculator.DividendYieldCalculator;

public class DividendYieldCalculatorFactory {

	private List<DividendYieldCalculator> calculators;

	public DividendYieldCalculatorFactory(List<DividendYieldCalculator> calculators) {
		this.calculators = calculators;
	}

	public DividendYieldCalculator getCalculator(Stock stock) {
		for (DividendYieldCalculator calculator : calculators) {
			if (calculator.getStockType().isInstance(stock)) {
				return calculator;
			}
		}
		throw new IllegalArgumentException("Stock type is not supported");
	}

}
