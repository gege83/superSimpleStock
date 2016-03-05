package com.gergo.takacs.stock;

public abstract class UnmutableStock implements Stock {

	private String symbol;
	private double tickerPrice;

	protected UnmutableStock(String symbol, double tickerPrice) {
		this.symbol = symbol;
		this.tickerPrice = tickerPrice;
	}

	@Override
	public String getStockSymbol() {
		return symbol;
	}

	@Override
	public double getTickerPrice() {
		return tickerPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tickerPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		UnmutableStock other = (UnmutableStock) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (Double.doubleToLongBits(tickerPrice) != Double.doubleToLongBits(other.tickerPrice))
			return false;
		return true;
	}

	@Override

	public String toString() {
		return "UnmutableStock [symbol=" + symbol + ", tickerPrice=" + tickerPrice + "]";
	}

}
