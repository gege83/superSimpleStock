package com.gergo.takacs.trade;

public interface Trade {
	String getStockSymbol();

	TradeDirection getTradeDirection();

	int getQuantity();

	double getPrice();
}
