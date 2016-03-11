package com.gergo.takacs.trade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.gergo.takacs.stock.CachedStock;
import com.gergo.takacs.stock.StockCache;

public class TradeCache implements TradeService {

	private final StockCache stockService;

	public TradeCache(StockCache stockService) {
		this.stockService = stockService;
	}

	@Override
	public void saveTrade(Trade trade) {
		CachedStock stock = stockService.findStockBySymbol(trade.getStockSymbol());
		stock.add(new CachedTrade(trade));
	}

	@Override
	public List<Trade> findTradesBySymbolInRange(String stockSymbol, int timeRangeInMinutes) {
		CachedStock stock = stockService.findStockBySymbol(stockSymbol);
		List<CachedTrade> trades = stock.getTrades();
		return findMatchedTrades(timeRangeInMinutes, trades);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Trade> findMatchedTrades(int timeRangeInMinutes, List<CachedTrade> trades) {
		Collection select = CollectionUtils.select(trades, new TradeTimeFilterPredicate(timeRangeInMinutes));
		return new ArrayList<>(select);
	}
}
