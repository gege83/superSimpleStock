package com.gergo.takacs.stock.service;

import java.util.ArrayList;
import java.util.List;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.StockService;
import com.gergo.takacs.stock.calculator.IndexCalculator;
import com.gergo.takacs.stock.calculator.StockIndexInformation;
import com.gergo.takacs.stock.calculator.StockInformation;
import com.gergo.takacs.stock.calculator.StockInformationCalculator;
import com.gergo.takacs.trade.Trade;
import com.gergo.takacs.trade.TradeDirection;
import com.gergo.takacs.trade.TradeService;

public class SimpleStockServiceFacade implements SimpleStockService {

	private final TradeService tradeService;
	private final StockService stockService;
	private final StockInformationCalculator stockInformationCalculator;
	private final IndexCalculator indexCalculator;

	public SimpleStockServiceFacade(TradeService tradeService, StockService stockService,
			StockInformationCalculator stockInformationCalculator, IndexCalculator indexCalculator) {
		this.tradeService = tradeService;
		this.stockService = stockService;
		this.stockInformationCalculator = stockInformationCalculator;
		this.indexCalculator = indexCalculator;
	}

	@Override
	public void saveTrade(String stockSymbol, TradeDirection direction, double price, int quantity) {
		// TODO verify stock symbol
		Trade trade = new BusinessTrade(stockSymbol, direction, quantity, price);
		tradeService.saveTrade(trade);
	}

	@Override
	public StockInformation calculateStockInformation(String symbol) {
		Stock stock = stockService.findStockBySymbol(symbol);
		return stockInformationCalculator.calculate(stock);
	}

	@Override
	public StockIndexInformation calculateStockIndexInformation() {
		List<Stock> stocks = stockService.findAllStock();
		List<StockInformation> stockInformation = new ArrayList<>();
		for (Stock stock : stocks) {
			StockInformation stockInfo = stockInformationCalculator.calculate(stock);
			stockInformation.add(stockInfo);
		}
		double stockIndex = indexCalculator.calculate(stockInformation);
		return new BusinessStockIndexInformation(stockInformation, stockIndex);
	}

}
