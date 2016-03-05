package com.gergo.takacs.stock.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gergo.takacs.stock.UnmutableCommonStock;
import com.gergo.takacs.stock.converter.CommonStockToCommonStockEntry;
import com.gergo.takacs.stock.dataprovider.CommonStockEntity;

public class CommonStockToCommonStockEntryTest {
	@Test
	public void testConvert() {
		// given
		CommonStockToCommonStockEntry underTest = new CommonStockToCommonStockEntry();
		UnmutableCommonStock source = new UnmutableCommonStock("symbol", 250., 20.);
		CommonStockEntity expected = new CommonStockEntity();
		expected.setSymbol("symbol");
		expected.setLastDividend(20.);
		expected.setTickerPrice(250.);
		// when
		CommonStockEntity actual = underTest.convert(source);
		// then
		assertEquals(expected, actual);
	}
}
