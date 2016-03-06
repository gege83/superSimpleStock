package com.gergo.takacs.stock.calculator;

import java.util.List;

import com.gergo.takacs.trade.Trade;

public interface StockPriceCalculator {
	double calculate(List<Trade> trades);
}
