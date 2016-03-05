package com.gergo.takacs.stock.converter;

import org.springframework.core.convert.converter.Converter;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.dataprovider.StockEntity;

public interface StockEntityConverter<S extends StockEntity> extends Converter<S, Stock> {

	@Override
	Stock convert(StockEntity source);

	Class<S> getSourceClass();

}
