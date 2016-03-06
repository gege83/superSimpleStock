package com.gergo.takacs.stock.service;

import com.gergo.takacs.stock.calculator.StockInformation;

public class BusinessStockInformation implements StockInformation {

	private final String symbol;
	private final double dividendYield;
	private final double peRatio;
	private final double stockPrice;

	public BusinessStockInformation(String symbol, double dividendYield, double peRatio, double stockPrice) {
		this.symbol = symbol;
		this.dividendYield = dividendYield;
		this.peRatio = peRatio;
		this.stockPrice = stockPrice;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public double getDividendYield() {
		return dividendYield;
	}

	@Override
	public double getPERatio() {
		return peRatio;
	}

	@Override
	public double getStockPrice() {
		return stockPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dividendYield);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(peRatio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(stockPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		BusinessStockInformation other = (BusinessStockInformation) obj;
		if (Double.doubleToLongBits(dividendYield) != Double.doubleToLongBits(other.dividendYield))
			return false;
		if (Double.doubleToLongBits(peRatio) != Double.doubleToLongBits(other.peRatio))
			return false;
		if (Double.doubleToLongBits(stockPrice) != Double.doubleToLongBits(other.stockPrice))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BusinessStockInformation [symbol=" + symbol + ", dividendYield=" + dividendYield + ", peRatio="
				+ peRatio + ", stockPrice=" + stockPrice + "]";
	}

}
