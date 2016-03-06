package com.gergo.takacs.stock.service;

import java.util.List;

import com.gergo.takacs.stock.calculator.StockIndexInformation;
import com.gergo.takacs.stock.calculator.StockInformation;

public class BusinessStockIndexInformation implements StockIndexInformation {

	private final double stockIndex;
	private final List<StockInformation> stockInfromation;

	public BusinessStockIndexInformation(List<StockInformation> stockInfromation, double stockIndex) {
		this.stockInfromation = stockInfromation;
		this.stockIndex = stockIndex;
	}

	@Override
	public double getStockIndex() {
		return stockIndex;
	}

	@Override
	public List<StockInformation> getStockInformation() {
		return stockInfromation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(stockIndex);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stockInfromation == null) ? 0 : stockInfromation.hashCode());
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
		BusinessStockIndexInformation other = (BusinessStockIndexInformation) obj;
		if (Double.doubleToLongBits(stockIndex) != Double.doubleToLongBits(other.stockIndex)) {
			return false;
		}
		if (stockInfromation == null) {
			if (other.stockInfromation != null) {
				return false;
			}
		} else if (!stockInfromation.equals(other.stockInfromation)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BusinessStockIndexInformation [stockIndex=" + stockIndex + ", stockInfromation=" + stockInfromation
				+ "]";
	}
}
