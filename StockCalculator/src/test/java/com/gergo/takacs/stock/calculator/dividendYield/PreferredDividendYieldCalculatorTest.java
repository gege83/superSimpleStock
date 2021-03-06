package com.gergo.takacs.stock.calculator.dividendYield;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutableCommonStock;
import com.gergo.takacs.stock.UnmutablePreferredStock;
import com.gergo.takacs.stock.calculator.DividendYieldCalculator;

public class PreferredDividendYieldCalculatorTest {

	@Test
	public void testCalculate() {
		// given
		DividendYieldCalculator underTest = new PreferredDividendYieldCalculator();
		Stock stock = new UnmutablePreferredStock("stock", 2., 3., 0.03);
		double expected = 0.045;
		// when
		double actual = underTest.calculate(stock);
		// then
		assertEquals(expected, actual, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateIfNotPreferredStock() throws Exception {
		// given
		DividendYieldCalculator underTest = new PreferredDividendYieldCalculator();
		Stock commonStock = new UnmutableCommonStock("stock", 1., 2.);
		// when
		underTest.calculate(commonStock);
		// then exception
	}
}
