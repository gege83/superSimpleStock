package com.gergo.takacs.stock;

import com.gergo.takacs.stock.converter.StockEntityConverter;
import com.gergo.takacs.stock.converter.StockEntityConverterFactory;
import com.gergo.takacs.stock.dataprovider.StockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntityService;

public class StockServiceAdapter implements StockService {

	private StockEntityService stockService;
	private StockEntityConverterFactory converterFactory;

	public StockServiceAdapter(StockEntityService stockService, StockEntityConverterFactory converterFactory) {
		this.stockService = stockService;
		this.converterFactory = converterFactory;
	}

	@Override
	public Stock findStockBySymbol(String symbol) {
		StockEntity stockEntity = stockService.findOneBySymbol(symbol);
		StockEntityConverter<? extends StockEntity> converter = converterFactory.getConverter(stockEntity.getClass());
		return converter.convert(stockEntity);
	}

}
