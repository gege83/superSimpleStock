package com.gergo.takacs.trade;

import org.joda.time.DateTime;

public interface Trade {
	DateTime getCreationTime();

	String getStockSymbol();

	TradeDirection getTradeDirection();

	int getQuantity();

	double getPrice();
}
