package com.gergo.takacs.stock.converter;

import org.springframework.stereotype.Component;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutableCommonStock;
import com.gergo.takacs.stock.dataprovider.CommonStockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntity;

@Component
public class CommonStockEntryToCommonStock implements StockEntityConverter<CommonStockEntity> {

	@Override
	public Stock convert(StockEntity source) {
		return convert((CommonStockEntity) source);
	}

	@Override
	public Stock convert(CommonStockEntity source) {
		return new UnmutableCommonStock(source.getSymbol(), source.getTickerPrice(), source.getLastDividend());
	}

	@Override
	public Class<CommonStockEntity> getSourceClass() {
		return CommonStockEntity.class;
	}

}
