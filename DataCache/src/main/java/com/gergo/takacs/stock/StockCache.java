package com.gergo.takacs.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockCache implements StockService {

	private final Map<String, CachedStock> cachedStocks;

	public StockCache(List<CachedStock> initialStocks) {
		cachedStocks = new HashMap<>();
		for (CachedStock stock : initialStocks) {
			cachedStocks.put(stock.getStockSymbol(), stock);
		}
	}

	@Override
	public CachedStock findStockBySymbol(String symbol) {
		CachedStock stock = cachedStocks.get(symbol);
		if (stock != null) {
			return stock;
		}
		throw new StockNotFoundException("stock not found " + stock);
	}

	@Override
	public List<Stock> findAllStock() {
		return new ArrayList<>(cachedStocks.values());
	}

}
