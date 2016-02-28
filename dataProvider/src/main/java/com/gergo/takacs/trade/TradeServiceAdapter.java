package com.gergo.takacs.trade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gergo.takacs.dataprovider.TradeEntity;
import com.gergo.takacs.dataprovider.TradeEntityService;

@Service
public class TradeServiceAdapter implements TradeService {

	private TradeEntityService tradeService;
	private TradeConverter converter;

	@Autowired
	public TradeServiceAdapter(TradeEntityService tradeService, TradeConverter converter) {
		this.tradeService = tradeService;
		this.converter = converter;
	}

	@Override
	public void saveTrade(Trade trade) {
		TradeEntity tradeToSave = converter.convert(trade);
		tradeService.saveTradeEntity(tradeToSave);
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
