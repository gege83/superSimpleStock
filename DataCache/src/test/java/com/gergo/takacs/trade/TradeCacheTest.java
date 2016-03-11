package com.gergo.takacs.trade;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gergo.takacs.stock.CachedStock;
import com.gergo.takacs.stock.StockCache;

@RunWith(MockitoJUnitRunner.class)
public class TradeCacheTest {

	@Mock
	private StockCache stockService;

	private TradeCache underTest;

	@Before
	public void setUp() throws Exception {
		DateTimeUtils.setCurrentMillisFixed(13L);
		underTest = new TradeCache(stockService);
	}

	@After
	public void teardown() {
		DateTimeUtils.setCurrentMillisSystem();
	}

	@Test
	public void testSaveTrade() throws Exception {
		// given
		String symbol = "stock";
		CachedTrade trade = new CachedTrade(symbol, TradeDirection.BUY, 21, 5);
		CachedStock stock = mock(CachedStock.class);
		when(stockService.findStockBySymbol(symbol)).thenReturn(stock);
		// when
		underTest.saveTrade(trade);
		// then
		verify(stockService).findStockBySymbol(symbol);
		verify(stock).add(trade);
	}

	@Test
	public void testFindTradesBySymbolInRange() throws Exception {
		// given
		String symbol = "stock";
		CachedTrade trade = new CachedTrade(symbol, TradeDirection.BUY, 21, 5);
		DateTimeUtils.setCurrentMillisFixed(60000000);
		CachedTrade trade2 = new CachedTrade(symbol, TradeDirection.BUY, 21, 5);
		List<CachedTrade> trades = Arrays.asList(trade, trade2);
		List<CachedTrade> expected = Arrays.asList(trade2);
		CachedStock stock = mock(CachedStock.class);
		when(stock.getTrades()).thenReturn(trades);
		when(stockService.findStockBySymbol(symbol)).thenReturn(stock);
		// when
		List<Trade> actual = underTest.findTradesBySymbolInRange(symbol, 15);
		// then
		assertEquals(expected, actual);
	}

}
