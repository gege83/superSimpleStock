package com.gergo.takacs.stock.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.gergo.takacs.stock.CommonStock;
import com.gergo.takacs.stock.dataprovider.CommonStockEntity;

@Component
public class CommonStockToCommonStockEntry implements Converter<CommonStock, CommonStockEntity> {

	@Override
	public CommonStockEntity convert(CommonStock source) {
		CommonStockEntity commonStockEntity = new CommonStockEntity();
		commonStockEntity.setSymbol(source.getStockSymbol());
		commonStockEntity.setLastDividend(source.getLastDividend());
		commonStockEntity.setTickerPrice(source.getTickerPrice());
		return commonStockEntity;
	}

}
