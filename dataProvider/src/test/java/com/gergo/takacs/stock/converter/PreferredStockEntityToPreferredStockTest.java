package com.gergo.takacs.stock.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutablePreferredStock;
import com.gergo.takacs.stock.dataprovider.PreferredStockEntity;

public class PreferredStockEntityToPreferredStockTest {

	@Test
	public void testConvert() {
		PreferredStockEntityToPreferredStock underTest = new PreferredStockEntityToPreferredStock();
		UnmutablePreferredStock expected = new UnmutablePreferredStock("symbol", 22., 23., 24.);
		PreferredStockEntity source = new PreferredStockEntity();
		source.setSymbol("symbol");
		source.setTickerPrice(22.);
		source.setParValue(23.);
		source.setFixedDividend(24.);
		// when
		Stock actual = underTest.convert(source);
		// then
		assertEquals(expected, actual);
	}
}
