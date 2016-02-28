package com.gergo.takacs.trade;

import java.util.Date;

import org.joda.time.DateTime;

import com.gergo.takacs.dataprovider.TradeEntity;

public class TradeConverter {
	public Trade convert(TradeEntity source) {
		DateTime creationTime = null;
		if (source.getCreationDate() != null) {
			creationTime = new DateTime(source.getCreationDate());
		}
		return new UnmutableTrade(creationTime, source.getStockSymbol(), source.getDirection(), source.getQuantity(),
				source.getPrice());
	}

	public TradeEntity convert(Trade source) {
		TradeEntity trade = new TradeEntity();
		trade.setCreationDate(getCreationDate(source));
		trade.setStockSymbol(source.getStockSymbol());
		trade.setDirection(source.getTradeDirection());
		trade.setQuantity(source.getQuantity());
		trade.setPrice(source.getPrice());
		return trade;
	}

	private Date getCreationDate(Trade source) {
		DateTime creationTime = source.getCreationTime();
		Date currentTime = null;
		if (creationTime != null) {
			currentTime = creationTime.toDate();
		}
		return currentTime;
	}

}
