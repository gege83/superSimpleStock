package com.gergo.takacs.tradedataprovider;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

public class TradeServiceImpl implements TradeService {

	@Autowired
	TradeRepository tradeRepository;

	@Override
	public List<Trade> findTradesBySymbolAndRange(String symbol, int range) {
		DateTime currentTime = new DateTime();
		DateTime fromTime = currentTime.minusMinutes(range);
		return tradeRepository.findByStockSymbolAndCreationTimeGreaterThan(symbol, fromTime.toDate());
	}
}
