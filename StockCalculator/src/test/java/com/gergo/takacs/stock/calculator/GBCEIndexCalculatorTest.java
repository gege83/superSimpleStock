package com.gergo.takacs.stock.calculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GBCEIndexCalculatorTest {

	private IndexCalculator underTest;

	@Before
	public void setup() {
		underTest = new GBCEIndexCalculator();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateIfEmpty() {
		// given
		List<StockInformation> stockPrices = Arrays.asList();
		// when
		underTest.calculate(stockPrices);
		// then exception
	}

	@Test
	public void testCalculate() {
		// given
		StockInformation stockInfo1 = mockStockWithStockPrice(9.);
		StockInformation stockInfo2 = mockStockWithStockPrice(15.);
		StockInformation stockInfo3 = mockStockWithStockPrice(25.);
		List<StockInformation> stockPrices = Arrays.asList(stockInfo1, stockInfo2, stockInfo3);
		double expected = 15.;
		// when
		double actual = underTest.calculate(stockPrices);
		// then exception
		assertEquals(expected, actual, 0);
	}

	private StockInformation mockStockWithStockPrice(double stockPrice) {
		StockInformation stockInfo = mock(StockInformation.class);
		when(stockInfo.getStockPrice()).thenReturn(stockPrice);
		return stockInfo;
	}

}
