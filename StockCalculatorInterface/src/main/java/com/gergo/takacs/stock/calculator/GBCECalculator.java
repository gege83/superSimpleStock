package com.gergo.takacs.stock.calculator;

import java.util.List;

import com.gergo.takacs.stock.Stock;

public interface GBCECalculator {
	double calculate(List<Stock> stocks);
}
