package com.gergo.takacs.trade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceAdapter implements TradeService {

	private com.gergo.takacs.tradedataprovider.TradeService tradeService;
	private TradeConverter converter;

	@Autowired
	public TradeServiceAdapter(com.gergo.takacs.tradedataprovider.TradeService tradeService, TradeConverter converter) {
		this.tradeService = tradeService;
		this.converter = converter;
	}

	@Override
	public void saveTrade(Trade trade) {
		com.gergo.takacs.tradedataprovider.Trade tradeToSave = converter.convert(trade);
		tradeService.saveTrade(tradeToSave);
	}

	@Override
	public List<Trade> findTradesBySymbolInRange(String stockSymbol, int timeRangeInMinutes) {
		List<com.gergo.takacs.tradedataprovider.Trade> tradeEntities = tradeService
				.findTradesBySymbolAndRange(stockSymbol, timeRangeInMinutes);
		List<Trade> result = convertListToBusines(tradeEntities);
		return result;
	}

	private List<Trade> convertListToBusines(List<com.gergo.takacs.tradedataprovider.Trade> tradeEntities) {
		List<Trade> result = new ArrayList<>();
		for (com.gergo.takacs.tradedataprovider.Trade tradeEntity : tradeEntities) {
			Trade businessTrade = converter.convert(tradeEntity);
			result.add(businessTrade);
		}
		return result;
	}

}
