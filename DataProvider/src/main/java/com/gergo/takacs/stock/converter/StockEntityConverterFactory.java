package com.gergo.takacs.stock.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gergo.takacs.stock.dataprovider.StockEntity;

@Component
public class StockEntityConverterFactory {

	private final Map<Class<? extends StockEntity>, StockEntityConverter<? extends StockEntity>> converterMap;

	@Autowired
	public StockEntityConverterFactory(List<StockEntityConverter<? extends StockEntity>> converterList) {
		converterMap = new HashMap<>();
		for (StockEntityConverter<? extends StockEntity> stockEntityConverter : converterList) {
			converterMap.put(stockEntityConverter.getSourceClass(), stockEntityConverter);
		}
	}

	@SuppressWarnings("unchecked")
	public <S extends StockEntity> StockEntityConverter<S> getConverter(Class<S> sourceType) {
		return (StockEntityConverter<S>) converterMap.get(sourceType);
	}
}
