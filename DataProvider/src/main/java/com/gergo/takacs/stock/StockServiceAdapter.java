package com.gergo.takacs.stock;

import java.util.ArrayList;
import java.util.List;

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
		return convertStockEntity(stockEntity);
	}

	private Stock convertStockEntity(StockEntity stockEntity) {
		StockEntityConverter<? extends StockEntity> converter = converterFactory.getConverter(stockEntity.getClass());
		return converter.convert(stockEntity);
	}

	@Override
	public List<Stock> findAllStock() {
		List<StockEntity> stockEntities = stockService.findAllStockEntitys();
		List<Stock> result = new ArrayList<>();
		for (StockEntity stockEntity : stockEntities) {
			result.add(convertStockEntity(stockEntity));
		}
		return result;
	}

}
