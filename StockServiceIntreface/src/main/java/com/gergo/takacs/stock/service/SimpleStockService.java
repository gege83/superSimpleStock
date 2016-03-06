package com.gergo.takacs.stock.service;

import com.gergo.takacs.stock.calculator.StockIndexInformation;
import com.gergo.takacs.stock.calculator.StockInformation;
import com.gergo.takacs.trade.TradeDirection;

public interface SimpleStockService {
	void saveTrade(String symbol, TradeDirection direction, double price, int quantity);

	StockInformation calculateStockInformation(String symbol);

	StockIndexInformation calculateStockIndexInformation();
}
