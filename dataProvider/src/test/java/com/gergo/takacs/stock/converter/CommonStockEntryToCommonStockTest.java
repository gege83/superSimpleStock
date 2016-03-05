package com.gergo.takacs.stock.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutableCommonStock;
import com.gergo.takacs.stock.dataprovider.CommonStockEntity;

public class CommonStockEntryToCommonStockTest {
	@Test
	public void testConvert() {
		// given
		CommonStockEntryToCommonStock underTest = new CommonStockEntryToCommonStock();
		UnmutableCommonStock expected = new UnmutableCommonStock("symbol", 250., 20.);
		CommonStockEntity source = new CommonStockEntity();
		source.setSymbol("symbol");
		source.setLastDividend(20.);
		source.setTickerPrice(250.);
		// when
		Stock actual = underTest.convert(source);
		// then
		assertEquals(expected, actual);
	}
}
