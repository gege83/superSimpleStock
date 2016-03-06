package com.gergo.takacs.trade;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.gergo.takacs.trade.dataprovider.TradeEntity;

@Component
public class TradeConverter {
	public Trade convert(TradeEntity source) {
		return new UnmutableTrade(source.getStockSymbol(), source.getDirection(), source.getQuantity(),
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
		DateTime creationTime = new DateTime();
		Date currentTime = null;
		if (creationTime != null) {
			currentTime = creationTime.toDate();
		}
		return currentTime;
	}

}
