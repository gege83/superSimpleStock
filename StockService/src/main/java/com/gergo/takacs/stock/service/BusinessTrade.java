package com.gergo.takacs.stock.service;

import com.gergo.takacs.trade.Trade;
import com.gergo.takacs.trade.TradeDirection;

public class BusinessTrade implements Trade {

	private final String stockSymbol;
	private final TradeDirection tradeDirection;
	private final int quantity;
	private final double price;

	public BusinessTrade(String stockSymbol, TradeDirection tradeDirection, int quantity, double price) {
		this.stockSymbol = stockSymbol;
		this.tradeDirection = tradeDirection;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public String getStockSymbol() {
		return stockSymbol;
	}

	@Override
	public TradeDirection getTradeDirection() {
		return tradeDirection;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
		result = prime * result + ((tradeDirection == null) ? 0 : tradeDirection.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessTrade other = (BusinessTrade) obj;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (stockSymbol == null) {
			if (other.stockSymbol != null)
				return false;
		} else if (!stockSymbol.equals(other.stockSymbol))
			return false;
		if (tradeDirection != other.tradeDirection)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BusinessTrade [stockSymbol=" + stockSymbol + ", tradeDirection=" + tradeDirection + ", quantity="
				+ quantity + ", price=" + price + "]";
	}

}
