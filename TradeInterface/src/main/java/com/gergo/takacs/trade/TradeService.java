package com.gergo.takacs.trade;

import java.util.List;

public interface TradeService {
	void saveTrade(Trade trade);

	List<Trade> findTradesBySymbolInRange(String stockSymbol, int timeRangeInMinutes);
}
