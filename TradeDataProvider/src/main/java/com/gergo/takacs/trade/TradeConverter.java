package com.gergo.takacs.trade;

import java.util.Date;

import org.joda.time.DateTime;

public class TradeConverter {
	public Trade convert(com.gergo.takacs.tradedataprovider.Trade source) {
		DateTime creationTime = null;
		if (source.getCreationTime() != null) {
			creationTime = new DateTime(source.getCreationTime());
		}
		return new UnmutableTrade(creationTime, source.getStockSymbol(), source.getDirection(), source.getQuantity(),
				source.getPrice());
	}

	public com.gergo.takacs.tradedataprovider.Trade convert(Trade source) {
		com.gergo.takacs.tradedataprovider.Trade trade = new com.gergo.takacs.tradedataprovider.Trade();
		trade.setCreationTime(getCreationTime(source));
		trade.setStockSymbol(source.getStockSymbol());
		trade.setDirection(source.getTradeDirection());
		trade.setQuantity(source.getQuantity());
		trade.setPrice(source.getPrice());
		return trade;
	}

	private Date getCreationTime(Trade source) {
		DateTime creationTime = source.getCreationTime();
		Date currentTime = null;
		if (creationTime != null) {
			currentTime = creationTime.toDate();
		}
		return currentTime;
	}

}
