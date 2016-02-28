package com.gergo.takacs.dataprovider;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

public class TradeEntityServiceImpl implements TradeEntityService {

	@Override
	public List<TradeEntity> findTradeEntitysByStockSymbolAndCreationDateGreaterThan(String stockSymbol, int range) {
		return tradeRepository.findByStockSymbolAndCreationDateGreaterThan(stockSymbol, getCreationDateForRange(range));
	}

	private Date getCreationDateForRange(int rangeInMinutes) {
		DateTime dateTime = new DateTime();
		DateTime rangeStart = dateTime.minusMinutes(rangeInMinutes);
		return rangeStart.toDate();
	}
}
