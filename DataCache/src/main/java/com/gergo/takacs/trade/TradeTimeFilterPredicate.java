package com.gergo.takacs.trade;

import org.apache.commons.collections.Predicate;
import org.joda.time.DateTime;

public class TradeTimeFilterPredicate implements Predicate {

	private final int timeInMinutes;

	public TradeTimeFilterPredicate(int timeInMinutes) {
		this.timeInMinutes = timeInMinutes;
	}

	private boolean test(CachedTrade trade) {
		DateTime timeBoundary = DateTime.now().minusMinutes(timeInMinutes);
		DateTime creationTime = trade.getCreationTime();
		return timeBoundary.isBefore(creationTime);
	}

	@Override
	public boolean evaluate(Object trade) {
		if (trade instanceof CachedTrade) {
			return test((CachedTrade) trade);
		}
		return false;
	}

}
