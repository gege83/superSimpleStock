package com.gergo.takacs.trade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.gergo.takacs.stock.StockNotFoundException;
import com.gergo.takacs.stock.dataprovider.StockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntityService;
import com.gergo.takacs.trade.dataprovider.TradeEntity;
import com.gergo.takacs.trade.dataprovider.TradeEntityService;

@Service
public class TradeServiceAdapter implements TradeService {

	private final TradeEntityService tradeService;
	private final TradeConverter converter;
	private final StockEntityService stockService;

	@Autowired
	public TradeServiceAdapter(TradeEntityService tradeService, TradeConverter converter,
			StockEntityService stockService) {
		this.tradeService = tradeService;
		this.converter = converter;
		this.stockService = stockService;
	}

	@Override
	public void saveTrade(Trade trade) {
		updateTickerPrice(trade);
		doSaveTrade(trade);
	}

	private void doSaveTrade(Trade trade) {
		TradeEntity tradeToSave = converter.convert(trade);
		tradeService.saveTradeEntity(tradeToSave);
	}

	private void updateTickerPrice(Trade trade) {
		StockEntity stock = findStock(trade);
		stock.setTickerPrice(trade.getPrice());
		stockService.saveStockEntity(stock);
	}

	private StockEntity findStock(Trade trade) {
		try {
			return stockService.findOneBySymbol(trade.getStockSymbol());
		} catch (DataAccessException ex) {
			throw new StockNotFoundException("stock: " + trade.getStockSymbol(), ex);
		}
	}

	@Override
	public List<Trade> findTradesBySymbolInRange(String stockSymbol, int timeRangeInMinutes) {
		List<TradeEntity> tradeEntities = tradeService
				.findTradeEntitysByStockSymbolAndCreationDateGreaterThan(stockSymbol, timeRangeInMinutes);
		List<Trade> result = convertListToBusines(tradeEntities);
		return result;
	}

	private List<Trade> convertListToBusines(List<TradeEntity> tradeEntities) {
		List<Trade> result = new ArrayList<>();
		for (TradeEntity tradeEntity : tradeEntities) {
			Trade businessTrade = converter.convert(tradeEntity);
			result.add(businessTrade);
		}
		return result;
	}

}
