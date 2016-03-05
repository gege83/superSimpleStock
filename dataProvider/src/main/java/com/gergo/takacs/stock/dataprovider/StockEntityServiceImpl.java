package com.gergo.takacs.stock.dataprovider;

import java.util.List;

public class StockEntityServiceImpl implements StockEntityService {
	@Override
	public List<StockEntity> findBySymbol(String symbol) {
		return stockRepository.findBySymbol(symbol);
	}
}
