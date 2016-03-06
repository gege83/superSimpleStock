package com.gergo.takacs.stock.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutableCommonStock;

public class PERatioCalculatorImplTest {

	@Test
	public void testCalculator() {
		// given
		Stock stock = new UnmutableCommonStock("common", 2., 3.);
		double dividend = 1.5;
		PERatioCalculator underTest = new PERatioCalculatorImpl();
		double expected = 2. / 1.5;
		// when
		double actual = underTest.calculate(stock, dividend);
		// then
		assertEquals(expected, actual, 0);
	}
}
