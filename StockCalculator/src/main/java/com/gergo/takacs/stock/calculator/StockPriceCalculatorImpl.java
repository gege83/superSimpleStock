package com.gergo.takacs.stock.calculator;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

import com.gergo.takacs.trade.Trade;

public class StockPriceCalculatorImpl implements StockPriceCalculator {

	private Mean mean;

	private class WeightedMeanData {
		private final double[] values;
		private final double[] weights;

		protected WeightedMeanData(double[] values, double[] weights) {
			this.values = values;
			this.weights = weights;
		}

		public double[] getValues() {
			return values;
		}

		public double[] getWeights() {
			return weights;
		}
	}

	public StockPriceCalculatorImpl() {
		mean = new Mean();
	}

	@Override
	public double calculate(List<Trade> trades) {
		if (trades.isEmpty()) {
			throw new IllegalArgumentException("no trade suplied");
		}
		WeightedMeanData weightedMeanData = calculateArrays(trades);
		return mean.evaluate(weightedMeanData.getValues(), weightedMeanData.getWeights());
	}

	private WeightedMeanData calculateArrays(List<Trade> trades) {
		int size = trades.size();
		double[] values = new double[size];
		double[] weights = new double[size];
		for (int i = 0; i < size; i++) {
			Trade trade = trades.get(i);
			values[i] = trade.getPrice();
			weights[i] = trade.getQuantity();
		}
		return new WeightedMeanData(values, weights);
	}

}
