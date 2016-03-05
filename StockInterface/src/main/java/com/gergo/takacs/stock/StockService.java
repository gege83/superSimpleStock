package com.gergo.takacs.stock;

import java.util.List;

public interface StockService {
	Stock findStockBySymbol(String symbol);

	List<Stock> findAllStock();
}
