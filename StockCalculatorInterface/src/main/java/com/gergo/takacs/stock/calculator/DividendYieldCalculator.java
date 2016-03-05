package com.gergo.takacs.stock.calculator;

import com.gergo.takacs.stock.Stock;

public interface DividendYieldCalculator {
	double calculate(Stock stock);
}
