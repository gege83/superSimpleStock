package com.gergo.takacs.stock.calculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.UnmutableCommonStock;
import com.gergo.takacs.stock.calculator.dividendYield.DividendYieldCalculatorFactory;
import com.gergo.takacs.trade.Trade;
import com.gergo.takacs.trade.TradeDirection;
import com.gergo.takacs.trade.TradeService;
import com.gergo.takacs.trade.UnmutableTrade;

@RunWith(MockitoJUnitRunner.class)
public class SimpleStockInformationCalculatorTest {
	@Mock
	private DividendYieldCalculator dividendCalculator;
	@Mock
	private PERatioCalculator peRatioCalculator;
	@Mock
	private StockPriceCalculator stockPriceCalculator;
	@Mock
	private TradeService tradeService;
	@Mock
	private DividendYieldCalculatorFactory dividendCalculatorFactory;

	@Test
	public void testCalculate() throws Exception {
		// given
		int timeRange = 115;
		StockInformationCalculator underTest = new SimpleStockInformationCalculator(dividendCalculatorFactory,
				peRatioCalculator, tradeService, stockPriceCalculator, timeRange);
		String stockSymbol = "symbol";
		Stock stock = new UnmutableCommonStock(stockSymbol, 1., 54.);
		when(dividendCalculatorFactory.getCalculator(stock)).thenReturn(dividendCalculator);
		double dividendYield = 21.;
		when(dividendCalculator.calculate(stock)).thenReturn(dividendYield);
		double peRatio = 12.;
		when(peRatioCalculator.calculate(stock, dividendYield)).thenReturn(peRatio);
		List<Trade> trades = Arrays.<Trade> asList(new UnmutableTrade(stockSymbol, TradeDirection.BUY, 78, 54.));
		when(tradeService.findTradesBySymbolInRange(stockSymbol, timeRange)).thenReturn(trades);
		double stockPrice = 123.;
		when(stockPriceCalculator.calculate(trades)).thenReturn(stockPrice);

		StockInformation expected = new UnmodifiabelStockInformation(stockSymbol, dividendYield, peRatio, stockPrice);
		// when
		StockInformation actual = underTest.calculate(stock);
		// then
		assertEquals(expected, actual);
		verify(dividendCalculator).calculate(stock);
		verify(peRatioCalculator).calculate(stock, dividendYield);
		verify(stockPriceCalculator).calculate(trades);
		verify(tradeService).findTradesBySymbolInRange(stockSymbol, timeRange);
	}

}
