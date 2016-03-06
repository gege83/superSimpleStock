package com.gergo.takacs.stock.calculator.dividendYield;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gergo.takacs.stock.CommonStock;
import com.gergo.takacs.stock.PreferredStock;
import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutableCommonStock;
import com.gergo.takacs.stock.UnmutablePreferredStock;
import com.gergo.takacs.stock.calculator.DividendYieldCalculator;

@RunWith(MockitoJUnitRunner.class)
public class DividendYieldCalculatorFactoryTest {

	@Mock
	private PreferredDividendYieldCalculator preferredDividendYieldCalculator;
	@Mock
	private CommonDividendYieldCalculator commonDividendYieldCalculator;

	private DividendYieldCalculatorFactory underTest;

	@Before
	public void setup() {
		when(preferredDividendYieldCalculator.getStockType()).thenReturn(PreferredStock.class);
		when(commonDividendYieldCalculator.getStockType()).thenReturn(CommonStock.class);
		underTest = new DividendYieldCalculatorFactory(Arrays
				.<DividendYieldCalculator> asList(preferredDividendYieldCalculator, commonDividendYieldCalculator));
	}

	@Test
	public void testGetCalculatorIfStockIsPreferred() throws Exception {
		// given
		Stock stock = new UnmutablePreferredStock("stock", 2., 3., .1);
		// when
		DividendYieldCalculator actual = underTest.getCalculator(stock);
		// then
		assertEquals(preferredDividendYieldCalculator, actual);
	}

	@Test
	public void testGetCalculatorIfStockIsCommmon() throws Exception {
		// given
		Stock stock = new UnmutableCommonStock("stock", 2., 3.);
		// when
		DividendYieldCalculator actual = underTest.getCalculator(stock);
		// then
		assertEquals(commonDividendYieldCalculator, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCalculatorIfStockIsNotSupported() {
		// given
		Stock stock = new Stock() {
			@Override
			public double getTickerPrice() {
				return 2;
			}

			@Override
			public String getStockSymbol() {
				return "s";
			}
		};
		// when
		underTest.getCalculator(stock);
		// then exception
	}
}
