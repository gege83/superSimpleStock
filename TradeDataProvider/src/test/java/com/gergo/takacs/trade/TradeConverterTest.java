package com.gergo.takacs.trade;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.gergo.takacs.tradedataprovider.Trade;

public class TradeConverterTest {

	private TradeConverter underTest;

	@Before
	public void setup() {
		underTest = new TradeConverter();
	}

	@Test
	public void testConvertToBusinesReturnsEmptyTrade() {
		// given
		Trade trade = new Trade();
		UnmutableTrade expected = new UnmutableTrade(null, null, null, 0, 0);
		// when
		com.gergo.takacs.trade.Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityReturnsEmptyTrade() {
		// given
		UnmutableTrade trade = new UnmutableTrade(null, null, null, 0, 0);
		Trade expected = new Trade();
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithCurrentTime() {
		// given
		Trade trade = new Trade();
		Date creationTime = new Date();
		DateTime expectedTime = new DateTime(creationTime);
		trade.setCreationTime(creationTime);
		UnmutableTrade expected = new UnmutableTrade(expectedTime, null, null, 0, 0);
		// when
		com.gergo.takacs.trade.Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithCurrentTime() {
		// given
		Trade expected = new Trade();
		Date creationTime = new Date();
		DateTime expectedTime = new DateTime(creationTime);
		expected.setCreationTime(creationTime);
		UnmutableTrade trade = new UnmutableTrade(expectedTime, null, null, 0, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithSymbol() {
		// given
		Trade trade = new Trade();
		String symbol = "Some stock";
		trade.setStockSymbol(symbol);
		UnmutableTrade expected = new UnmutableTrade(null, symbol, null, 0, 0);
		// when
		com.gergo.takacs.trade.Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithSymbol() {
		// given
		Trade expected = new Trade();
		String symbol = "Some stock";
		expected.setStockSymbol(symbol);
		UnmutableTrade trade = new UnmutableTrade(null, symbol, null, 0, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithSell() {
		// given
		Trade trade = new Trade();
		TradeDirection direction = TradeDirection.SELL;
		trade.setDirection(direction);
		UnmutableTrade expected = new UnmutableTrade(null, null, direction, 0, 0);
		// when
		com.gergo.takacs.trade.Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithSell() {
		// given
		Trade expected = new Trade();
		TradeDirection direction = TradeDirection.SELL;
		expected.setDirection(direction);
		UnmutableTrade trade = new UnmutableTrade(null, null, direction, 0, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithQuantity10() {
		// given
		Trade trade = new Trade();
		int quantity = 10;
		trade.setQuantity(quantity);
		UnmutableTrade expected = new UnmutableTrade(null, null, null, quantity, 0);
		// when
		com.gergo.takacs.trade.Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithQuantity10() {
		// given
		Trade expected = new Trade();
		int quantity = 10;
		expected.setQuantity(quantity);
		UnmutableTrade trade = new UnmutableTrade(null, null, null, quantity, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinessWithPrice() {
		// given
		Trade trade = new Trade();
		double price = 20.2;
		trade.setPrice(price);
		UnmutableTrade expected = new UnmutableTrade(null, null, null, 0, price);
		// when
		com.gergo.takacs.trade.Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithPrice() {
		// given
		Trade expected = new Trade();
		double price = 20.2;
		expected.setPrice(price);
		UnmutableTrade trade = new UnmutableTrade(null, null, null, 0, price);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}
}
