package com.gergo.takacs.stock.calculator;

import java.util.List;

public interface IndexCalculator {
	double calculate(List<StockInformation> stockPrices);
}
