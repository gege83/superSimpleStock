package com.gergo.takacs.stock.calculator;

import java.util.List;

import com.gergo.takacs.stock.Stock;
import com.gergo.takacs.stock.calculator.dividendYield.DividendYieldCalculatorFactory;
import com.gergo.takacs.trade.Trade;
import com.gergo.takacs.trade.TradeService;

public class SimpleStockInformationCalculator implements StockInformationCalculator {

	private DividendYieldCalculatorFactory dividendCalculatorFactory;
	private PERatioCalculator peRatioCalculator;
	private int timeRange;
	private TradeService tradeService;
	private StockPriceCalculator stockPriceCalculator;

	public SimpleStockInformationCalculator(DividendYieldCalculatorFactory dividendCalculatorFactory,
			PERatioCalculator peRatioCalculator, TradeService tradeService, StockPriceCalculator stockPriceCalculator,
			int timeRange) {
		this.dividendCalculatorFactory = dividendCalculatorFactory;
		this.peRatioCalculator = peRatioCalculator;
		this.tradeService = tradeService;
		this.stockPriceCalculator = stockPriceCalculator;
		this.timeRange = timeRange;
	}

	@Override
	public StockInformation calculate(Stock stock) {
		double dividend = calculateDividend(stock);
		double peRatio = peRatioCalculator.calculate(stock, dividend);
		double stockPrice = calculateStockPrice(stock);
		return new UnmodifiabelStockInformation(stock.getStockSymbol(), dividend, peRatio, stockPrice);
	}

	private double calculateStockPrice(Stock stock) {
		List<Trade> trades = tradeService.findTradesBySymbolInRange(stock.getStockSymbol(), timeRange);
		return stockPriceCalculator.calculate(trades);
	}

	private double calculateDividend(Stock stock) {
		DividendYieldCalculator dividendCalculator = dividendCalculatorFactory.getCalculator(stock);
		return dividendCalculator.calculate(stock);
	}

}
