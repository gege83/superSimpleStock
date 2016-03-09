package com.gergo.takacs.stock;

public class CachedPreferredStock extends CachedStock implements PreferredStock {

	private final double fixedDividedend;
	private final double parValue;

	public CachedPreferredStock(String stockSymbol, double parValue, double fixedDividedend) {
		super(stockSymbol);
		this.parValue = parValue;
		this.fixedDividedend = fixedDividedend;
	}

	@Override
	public double getParValue() {
		return parValue;
	}

	@Override
	public double getFixedDividend() {
		return fixedDividedend;
	}
}
