package com.gergo.takacs.stock.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.gergo.takacs.stock.PreferredStock;
import com.gergo.takacs.stock.dataprovider.PreferredStockEntity;

@Component
public class PreferredStockToPreferredStockEntity implements Converter<PreferredStock, PreferredStockEntity> {

	@Override
	public PreferredStockEntity convert(PreferredStock source) {
		PreferredStockEntity result = new PreferredStockEntity();
		result.setSymbol(source.getStockSymbol());
		result.setTickerPrice(source.getTickerPrice());
		result.setFixedDividend(source.getFixedDividend());
		result.setParValue(source.getParValue());
		return result;
	}

}
