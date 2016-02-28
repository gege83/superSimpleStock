package com.gergo.takacs.stock;

public interface Stock {
	String getStockSymbol();

	StockType getType();

	double getTickerPrice();
}
