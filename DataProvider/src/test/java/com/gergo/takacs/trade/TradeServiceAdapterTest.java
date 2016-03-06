package com.gergo.takacs.trade;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.gergo.takacs.trade.dataprovider.TradeEntity;
import com.gergo.takacs.trade.dataprovider.TradeEntityService;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceAdapterTest {
	@Mock
	private TradeConverter converter;

	@Mock
	private TradeEntityService tradeService;
	@InjectMocks
	private TradeServiceAdapter underTest;

	@Test
	public void testSaveTrade() throws Exception {
		// given
		TradeEntity savedTrade = new TradeEntity();
		Trade trade = new UnmutableTrade("someStock", TradeDirection.BUY, 2, 5.12);
		Mockito.when(converter.convert(trade)).thenReturn(savedTrade);
		// when
		underTest.saveTrade(trade);
		// then
		Mockito.verify(tradeService).saveTradeEntity(savedTrade);
		Mockito.verify(converter).convert(trade);
	}

	@Test
	public void testFindTradesBySymbolInRange() throws Exception {
		// given
		String symbol = "someSymbol";
		int timeRangeInMinutes = 15;
		TradeEntity trade1 = new TradeEntity();
		TradeEntity trade2 = new TradeEntity();
		Mockito.when(tradeService.findTradeEntitysByStockSymbolAndCreationDateGreaterThan(symbol, timeRangeInMinutes))
				.thenReturn(Arrays.asList(trade1, trade2));
		UnmutableTrade convertedTradeValue = new UnmutableTrade(null, null, 0, 0);
		Mockito.when(converter.convert(Mockito.any(TradeEntity.class))).thenReturn(convertedTradeValue);
		List<Trade> expected = Arrays.<Trade> asList(convertedTradeValue, convertedTradeValue);
		// when
		List<Trade> actual = underTest.findTradesBySymbolInRange(symbol, timeRangeInMinutes);
		// then
		assertEquals(expected, actual);
	}

}
