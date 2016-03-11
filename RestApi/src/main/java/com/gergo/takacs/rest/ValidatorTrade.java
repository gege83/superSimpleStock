package com.gergo.takacs.rest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gergo.takacs.trade.Trade;
import com.gergo.takacs.trade.TradeDirection;

public class ValidatorTrade implements Trade {

	@NotNull
	private String stockSymbol;
	@NotNull
	private TradeDirection tradeDirection;
	@NotNull
	@Min(1)
	private Integer quantity;
	@NotNull
	@Min(0)
	private Double price;

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String getStockSymbol() {
		return stockSymbol;
	}

	@Override
	public TradeDirection getTradeDirection() {
		return tradeDirection;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public void setTradeDirection(TradeDirection tradeDirection) {
		this.tradeDirection = tradeDirection;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ValidatorTrade [stockSymbol=" + stockSymbol + ", tradeDirection=" + tradeDirection + ", quantity="
				+ quantity + ", price=" + price + "]";
	}

}
