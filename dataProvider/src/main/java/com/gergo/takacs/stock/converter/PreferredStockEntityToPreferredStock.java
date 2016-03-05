package com.gergo.takacs.stock.converter;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutablePreferredStock;
import com.gergo.takacs.stock.dataprovider.PreferredStockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntity;

public class PreferredStockEntityToPreferredStock implements StockEntityConverter<PreferredStockEntity> {

	public Stock convert(StockEntity source) {
		return convert((PreferredStockEntity) source);
	}

	@Override
	public Stock convert(PreferredStockEntity source) {
		return new UnmutablePreferredStock(source.getSymbol(), source.getTickerPrice(), source.getParValue(),
				source.getFixedDividend());
	}

	@Override
	public Class<PreferredStockEntity> getSourceClass() {
		return PreferredStockEntity.class;
	}
}
