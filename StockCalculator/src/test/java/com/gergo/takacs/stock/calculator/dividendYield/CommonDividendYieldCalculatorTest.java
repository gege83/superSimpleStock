package com.gergo.takacs.stock.calculator.dividendYield;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutableCommonStock;
import com.gergo.takacs.stock.calculator.DividendYieldCalculator;

public class CommonDividendYieldCalculatorTest {

	@Test
	public void testCalculate() throws Exception {
		// given
		DividendYieldCalculator underTest = new CommonDividendYieldCalculator();
		Stock commonStock = new UnmutableCommonStock("stock", 1., 2.);
		double expected = 2.;
		// when
		double actual = underTest.calculate(commonStock);
		// then
		assertEquals(expected, actual, 0);
	}

}
