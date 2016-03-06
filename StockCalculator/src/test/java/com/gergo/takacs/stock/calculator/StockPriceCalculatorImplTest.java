package com.gergo.takacs.stock.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.gergo.takacs.trade.Trade;
import com.gergo.takacs.trade.TradeDirection;
import com.gergo.takacs.trade.UnmutableTrade;

public class StockPriceCalculatorImplTest {

	private StockPriceCalculator underTest;

	@Before
	public void setup() {
		underTest = new StockPriceCalculatorImpl();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateIfNoTrade() throws Exception {
		// given
		List<Trade> trades = Arrays.asList();
		// when
		underTest.calculate(trades);
		// then
	}

	@Test
	public void testCalculateIfTrades() throws Exception {
		// given
		Trade trade1 = new UnmutableTrade(new DateTime(), "symbol", TradeDirection.BUY, 2, 2.);
		Trade trade2 = new UnmutableTrade(new DateTime(), "symbol", TradeDirection.SELL, 20, 21.);
		Trade trade3 = new UnmutableTrade(new DateTime(), "symbol", TradeDirection.BUY, 10, 2.);
		List<Trade> trades = Arrays.asList(trade1, trade2, trade3);
		double expected = 13.875;
		// when
		double actual = underTest.calculate(trades);
		// then
		assertEquals(expected, actual, 0);
	}

}
