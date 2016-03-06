package com.gergo.takacs.stock.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.StockService;
import com.gergo.takacs.stock.UnmutableCommonStock;
import com.gergo.takacs.stock.calculator.IndexCalculator;
import com.gergo.takacs.stock.calculator.StockIndexInformation;
import com.gergo.takacs.stock.calculator.StockInformation;
import com.gergo.takacs.stock.calculator.StockInformationCalculator;
import com.gergo.takacs.trade.Trade;
import com.gergo.takacs.trade.TradeDirection;
import com.gergo.takacs.trade.TradeService;

@RunWith(MockitoJUnitRunner.class)
public class SimpleStockServiceFacadeTest {
	@Mock
	private TradeService tradeService;
	@Mock
	private StockService stockService;
	@Mock
	private StockInformationCalculator stockInformationCalculator;
	@Mock
	private IndexCalculator indexCalculator;
	@InjectMocks
	private SimpleStockServiceFacade simpleStockServiceFacade;

	@Test
	public void testSaveTrade() throws Exception {
		// given
		String stockSymbol = "symbol";
		TradeDirection tradeDirection = TradeDirection.BUY;
		int quantity = 100;
		double price = 2.;
		Trade trade = new BusinessTrade(stockSymbol, tradeDirection, quantity, price);
		// when
		simpleStockServiceFacade.saveTrade(stockSymbol, tradeDirection, price, quantity);
		// then
		verify(tradeService).saveTrade(trade);
	}

	@Test
	public void testCaclulateStockInformation() throws Exception {
		// given
		String stockSymbol = "symbol";
		Stock stock = new UnmutableCommonStock(stockSymbol, 12., 45.);
		when(stockService.findStockBySymbol(stockSymbol)).thenReturn(stock);
		StockInformation expected = new BusinessStockInformation(stockSymbol, 12., 2., 123.);
		when(stockInformationCalculator.calculate(stock)).thenReturn(expected);
		// when
		StockInformation actual = simpleStockServiceFacade.calculateStockInformation(stockSymbol);
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testCaclulateStockIndexInformation() throws Exception {
		// given
		String stockSymbol = "symbol";
		Stock stock = new UnmutableCommonStock(stockSymbol, 12., 45.);
		List<Stock> stocks = Arrays.asList(stock, stock);
		when(stockService.findAllStock()).thenReturn(stocks);
		StockInformation stockInformation = new BusinessStockInformation(stockSymbol, 12., 2., 123.);
		when(stockInformationCalculator.calculate(stock)).thenReturn(stockInformation);
		List<StockInformation> stockInformationList = Arrays.asList(stockInformation, stockInformation);
		double stockIndex = 54.;
		when(indexCalculator.calculate(stockInformationList)).thenReturn(stockIndex);
		StockIndexInformation expected = new BusinessStockIndexInformation(stockInformationList, stockIndex);
		// when
		StockIndexInformation actual = simpleStockServiceFacade.calculateStockIndexInformation();
		// then
		assertEquals(expected, actual);
	}
}
