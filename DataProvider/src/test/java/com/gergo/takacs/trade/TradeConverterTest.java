package com.gergo.takacs.trade;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gergo.takacs.trade.dataprovider.TradeEntity;

public class TradeConverterTest {

	private TradeConverter underTest;

	@Before
	public void setup() {
		underTest = new TradeConverter();
		DateTimeUtils.setCurrentMillisFixed(123L);
	}

	@After
	public void tearDown() {
		DateTimeUtils.setCurrentMillisSystem();
	}

	@Test
	public void testConvertToBusinesReturnsEmptyTrade() {
		// given
		TradeEntity trade = createTradeEntity();
		UnmutableTrade expected = new UnmutableTrade(null, null, 0, 0);
		// when
		com.gergo.takacs.trade.Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityReturnsEmptyTrade() {
		// given
		UnmutableTrade trade = new UnmutableTrade(null, null, 0, 0);
		TradeEntity expected = createTradeEntity();
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	private TradeEntity createTradeEntity() {
		TradeEntity tradeEntity = new TradeEntity();
		tradeEntity.setCreationDate(new DateTime().toDate());
		return tradeEntity;
	}

	@Test
	public void testConvertToBusinesWithSymbol() {
		// given
		TradeEntity trade = createTradeEntity();
		String symbol = "Some stock";
		trade.setStockSymbol(symbol);
		UnmutableTrade expected = new UnmutableTrade(symbol, null, 0, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithSymbol() {
		// given
		TradeEntity expected = createTradeEntity();
		String symbol = "Some stock";
		expected.setStockSymbol(symbol);
		UnmutableTrade trade = new UnmutableTrade(symbol, null, 0, 0);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithSell() {
		// given
		TradeEntity trade = createTradeEntity();
		TradeDirection direction = TradeDirection.SELL;
		trade.setDirection(direction);
		UnmutableTrade expected = new UnmutableTrade(null, direction, 0, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithSell() {
		// given
		TradeEntity expected = createTradeEntity();
		TradeDirection direction = TradeDirection.SELL;
		expected.setDirection(direction);
		UnmutableTrade trade = new UnmutableTrade(null, direction, 0, 0);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithQuantity10() {
		// given
		TradeEntity trade = createTradeEntity();
		int quantity = 10;
		trade.setQuantity(quantity);
		UnmutableTrade expected = new UnmutableTrade(null, null, quantity, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithQuantity10() {
		// given
		TradeEntity expected = createTradeEntity();
		int quantity = 10;
		expected.setQuantity(quantity);
		UnmutableTrade trade = new UnmutableTrade(null, null, quantity, 0);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinessWithPrice() {
		// given
		TradeEntity trade = createTradeEntity();
		double price = 20.2;
		trade.setPrice(price);
		UnmutableTrade expected = new UnmutableTrade(null, null, 0, price);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithPrice() {
		// given
		TradeEntity expected = createTradeEntity();
		double price = 20.2;
		expected.setPrice(price);
		UnmutableTrade trade = new UnmutableTrade(null, null, 0, price);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}
}
