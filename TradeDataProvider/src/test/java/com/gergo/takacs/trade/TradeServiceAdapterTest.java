package com.gergo.takacs.trade;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceAdapterTest {
	@Mock
	private TradeConverter converter;

	@Mock
	private com.gergo.takacs.tradedataprovider.TradeService tradeService;
	@InjectMocks
	private TradeServiceAdapter underTest;

	@Test
	public void testSaveTrade() throws Exception {
		// given
		Date creationTime = new Date();
		com.gergo.takacs.tradedataprovider.Trade savedTrade = new com.gergo.takacs.tradedataprovider.Trade();
		Trade trade = new UnmutableTrade(new DateTime(creationTime), "someStock", TradeDirection.BUY, 2, 5.12);
		Mockito.when(converter.convert(trade)).thenReturn(savedTrade);
		// when
		underTest.saveTrade(trade);
		// then
		Mockito.verify(tradeService).saveTrade(savedTrade);
		Mockito.verify(converter).convert(trade);
	}

	@Test
	public void testFindTradesBySymbolInRange() throws Exception {
		// given
		String symbol = "someSymbol";
		int timeRangeInMinutes = 15;
		com.gergo.takacs.tradedataprovider.Trade trade1 = new com.gergo.takacs.tradedataprovider.Trade();
		com.gergo.takacs.tradedataprovider.Trade trade2 = new com.gergo.takacs.tradedataprovider.Trade();
		Mockito.when(tradeService.findTradesBySymbolAndRange(symbol, timeRangeInMinutes))
				.thenReturn(Arrays.asList(trade1, trade2));
		UnmutableTrade convertedTradeValue = new UnmutableTrade(null, null, null, 0, 0);
		Mockito.when(converter.convert(Mockito.any(com.gergo.takacs.tradedataprovider.Trade.class)))
				.thenReturn(convertedTradeValue);
		List<Trade> expected = Arrays.<Trade> asList(convertedTradeValue, convertedTradeValue);
		// when
		List<Trade> actual = underTest.findTradesBySymbolInRange(symbol, timeRangeInMinutes);
		// then
		assertEquals(expected, actual);
	}

}
