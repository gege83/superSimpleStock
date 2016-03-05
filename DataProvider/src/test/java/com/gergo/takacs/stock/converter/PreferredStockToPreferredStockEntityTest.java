package com.gergo.takacs.stock.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gergo.takacs.stock.UnmutablePreferredStock;
import com.gergo.takacs.stock.dataprovider.PreferredStockEntity;

public class PreferredStockToPreferredStockEntityTest {

	@Test
	public void testConvert() {
		// given
		PreferredStockToPreferredStockEntity underTest = new PreferredStockToPreferredStockEntity();
		UnmutablePreferredStock source = new UnmutablePreferredStock("symbol", 22., 23., 24.);
		PreferredStockEntity expected = new PreferredStockEntity();
		expected.setSymbol("symbol");
		expected.setTickerPrice(22.);
		expected.setParValue(23.);
		expected.setFixedDividend(24.);
		// when
		PreferredStockEntity actual = underTest.convert(source);
		// then
		assertEquals(expected, actual);
	}
}
