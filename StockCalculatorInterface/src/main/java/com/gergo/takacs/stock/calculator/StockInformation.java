package com.gergo.takacs.stock.calculator;

public interface StockInformation {
	String getSymbol();

	double getDividendYield();

	double getPERatio();

	double getStockPrice();
}
