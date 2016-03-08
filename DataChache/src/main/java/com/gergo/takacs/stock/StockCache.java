package com.gergo.takacs.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.StockNotFoundException;
import com.gergo.takacs.stock.StockService;

public class StockCache implements StockService {

	private final Map<String, Stock> cachedStocks;

	public StockCache(List<Stock> initialStocks) {
		cachedStocks = new HashMap<>();
		for (Stock stock : initialStocks) {
			cachedStocks.put(stock.getStockSymbol(), stock);
		}
	}

	@Override
	public Stock findStockBySymbol(String symbol) {
		Stock stock = cachedStocks.get(symbol);
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
