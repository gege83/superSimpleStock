package com.gergo.takacs.stock.calculator;

import com.gergo.takacs.stock.Stock;

public interface PERatioCalculator {
	double calculate(Stock stock, double dividend);
}
