package com.gergo.takacs.trade;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.gergo.takacs.trade.dataprovider.TradeEntity;

public class TradeConverterTest {

	private TradeConverter underTest;

	@Before
	public void setup() {
		underTest = new TradeConverter();
	}

	@Test
	public void testConvertToBusinesReturnsEmptyTrade() {
		// given
		TradeEntity trade = new TradeEntity();
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
		TradeEntity expected = new TradeEntity();
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithCurrentTime() {
		// given
		TradeEntity trade = new TradeEntity();
		Date creationTime = new Date();
		DateTime expectedTime = new DateTime(creationTime);
		trade.setCreationDate(creationTime);
		UnmutableTrade expected = new UnmutableTrade(expectedTime, null, null, 0, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithCurrentTime() {
		// given
		TradeEntity expected = new TradeEntity();
		Date creationTime = new Date();
		DateTime expectedTime = new DateTime(creationTime);
		expected.setCreationDate(creationTime);
		UnmutableTrade trade = new UnmutableTrade(expectedTime, null, null, 0, 0);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithSymbol() {
		// given
		TradeEntity trade = new TradeEntity();
		String symbol = "Some stock";
		trade.setStockSymbol(symbol);
		UnmutableTrade expected = new UnmutableTrade(null, symbol, null, 0, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithSymbol() {
		// given
		TradeEntity expected = new TradeEntity();
		String symbol = "Some stock";
		expected.setStockSymbol(symbol);
		UnmutableTrade trade = new UnmutableTrade(null, symbol, null, 0, 0);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithSell() {
		// given
		TradeEntity trade = new TradeEntity();
		TradeDirection direction = TradeDirection.SELL;
		trade.setDirection(direction);
		UnmutableTrade expected = new UnmutableTrade(null, null, direction, 0, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithSell() {
		// given
		TradeEntity expected = new TradeEntity();
		TradeDirection direction = TradeDirection.SELL;
		expected.setDirection(direction);
		UnmutableTrade trade = new UnmutableTrade(null, null, direction, 0, 0);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinesWithQuantity10() {
		// given
		TradeEntity trade = new TradeEntity();
		int quantity = 10;
		trade.setQuantity(quantity);
		UnmutableTrade expected = new UnmutableTrade(null, null, null, quantity, 0);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithQuantity10() {
		// given
		TradeEntity expected = new TradeEntity();
		int quantity = 10;
		expected.setQuantity(quantity);
		UnmutableTrade trade = new UnmutableTrade(null, null, null, quantity, 0);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToBusinessWithPrice() {
		// given
		TradeEntity trade = new TradeEntity();
		double price = 20.2;
		trade.setPrice(price);
		UnmutableTrade expected = new UnmutableTrade(null, null, null, 0, price);
		// when
		Trade actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToEntityWithPrice() {
		// given
		TradeEntity expected = new TradeEntity();
		double price = 20.2;
		expected.setPrice(price);
		UnmutableTrade trade = new UnmutableTrade(null, null, null, 0, price);
		// when
		TradeEntity actual = underTest.convert(trade);
		// then
		assertEquals(expected, actual);
	}
}
