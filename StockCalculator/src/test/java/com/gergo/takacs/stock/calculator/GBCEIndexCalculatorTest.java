package com.gergo.takacs.stock.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class GBCEIndexCalculatorTest {

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateIfEmpty() {
		// given
		IndexCalculator underTest = new GBCEIndexCalculator();
		List<Double> stockPrices = Arrays.asList();
		// when
		underTest.calculate(stockPrices);
		// then exception
	}

	@Test
	public void testCalculate() {
		// given
		IndexCalculator underTest = new GBCEIndexCalculator();
		List<Double> stockPrices = Arrays.asList(9., 15., 25.);
		double expected = 15.;
		// when
		double actual = underTest.calculate(stockPrices);
		// then exception
		assertEquals(expected, actual, 0);
	}

}
