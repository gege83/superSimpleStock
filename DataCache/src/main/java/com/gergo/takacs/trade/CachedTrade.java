package com.gergo.takacs.trade;

import org.joda.time.DateTime;

public class CachedTrade implements Trade {

	private final double price;
	private final int quantity;
	private final TradeDirection direction;
	private final String symbol;
	private final DateTime time;

	public CachedTrade(Trade trade) {
		this(trade.getStockSymbol(), trade.getTradeDirection(), trade.getQuantity(), trade.getPrice());
	}

	public CachedTrade(String symbol, TradeDirection direction, int quantity, double price) {
		this.symbol = symbol;
		this.direction = direction;
		this.quantity = quantity;
		this.price = price;
		time = new DateTime();
	}

	@Override
	public String getStockSymbol() {
		return symbol;
	}

	@Override
	public TradeDirection getTradeDirection() {
		return direction;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public double getPrice() {
		return price;
	}

	public DateTime getCreationTime() {
		return time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CachedTrade other = (CachedTrade) obj;
		if (direction != other.direction) {
			return false;
		}
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
			return false;
		}
		if (quantity != other.quantity) {
			return false;
		}
		if (symbol == null) {
			if (other.symbol != null) {
				return false;
			}
		} else if (!symbol.equals(other.symbol)) {
			return false;
		}
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CachedTrade [price=" + price + ", quantity=" + quantity + ", direction=" + direction + ", symbol="
				+ symbol + ", time=" + time + "]";
	}
}
