package com.gergo.takacs.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gergo.takacs.trade.CachedTrade;
import com.gergo.takacs.trade.Trade;

public abstract class CachedStock implements Stock {

	private final String stockSymbol;
	private final List<CachedTrade> trades;

	public CachedStock(String stockSymbol) {
		this.stockSymbol = stockSymbol;
		trades = new ArrayList<>();
	}

	@Override
	public String getStockSymbol() {
		return stockSymbol;
	}

	@Override
	public double getTickerPrice() {
		Trade lastTrade = trades.get(trades.size() - 1);
		return lastTrade.getPrice();
	}

	public void add(CachedTrade trade) {
		trades.add(trade);
	}

	public List<CachedTrade> getTrades() {
		return Collections.unmodifiableList(trades);
	}

}
