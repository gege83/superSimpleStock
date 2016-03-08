package com.gergo.takacs.trade;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import com.gergo.takacs.stock.StockNotFoundException;
import com.gergo.takacs.stock.dataprovider.StockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntityService;
import com.gergo.takacs.trade.dataprovider.TradeEntity;
import com.gergo.takacs.trade.dataprovider.TradeEntityService;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceAdapterTest {
	private static final String STOCK_SYMBOL = "someStock";

	@Mock
	private TradeConverter converter;

	@Mock
	private TradeEntityService tradeService;

	@Mock
	private StockEntityService stockService;

	@InjectMocks
	private TradeServiceAdapter underTest;

	@Test
	public void testSaveTrade() throws Exception {
		// given
		TradeEntity tradeToSave = new TradeEntity();
		double tickerPrice = 5.12;
		Trade trade = new UnmutableTrade(STOCK_SYMBOL, TradeDirection.BUY, 2, tickerPrice);
		Mockito.when(converter.convert(trade)).thenReturn(tradeToSave);
		StockEntity stockEntity = mock(StockEntity.class);
		Mockito.when(stockService.findOneBySymbol(STOCK_SYMBOL)).thenReturn(stockEntity);
		// when
		underTest.saveTrade(trade);
		// then
		verify(tradeService).saveTradeEntity(tradeToSave);
		verify(converter).convert(trade);
		verify(stockEntity).setTickerPrice(tickerPrice);
		verify(stockService).saveStockEntity(stockEntity);
	}

	@Test(expected = StockNotFoundException.class)
	public void testSaveTradeIfStockNotFound() throws Exception {
		// given
		Trade trade = new UnmutableTrade(STOCK_SYMBOL, TradeDirection.BUY, 2, 51.);
		Mockito.when(stockService.findOneBySymbol(STOCK_SYMBOL))
				.thenThrow(new EmptyResultDataAccessException("asf", 1));
		// when
		underTest.saveTrade(trade);
		// then exception
	}

	@Test
	public void testFindTradesBySymbolInRange() throws Exception {
		// given
		int timeRangeInMinutes = 15;
		TradeEntity trade1 = new TradeEntity();
		TradeEntity trade2 = new TradeEntity();
		Mockito.when(
				tradeService.findTradeEntitysByStockSymbolAndCreationDateGreaterThan(STOCK_SYMBOL, timeRangeInMinutes))
				.thenReturn(Arrays.asList(trade1, trade2));
		UnmutableTrade convertedTradeValue = new UnmutableTrade(null, null, 0, 0);
		Mockito.when(converter.convert(Mockito.any(TradeEntity.class))).thenReturn(convertedTradeValue);
		List<Trade> expected = Arrays.<Trade> asList(convertedTradeValue, convertedTradeValue);
		// when
		List<Trade> actual = underTest.findTradesBySymbolInRange(STOCK_SYMBOL, timeRangeInMinutes);
		// then
		assertEquals(expected, actual);
	}

}
