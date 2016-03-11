package com.gergo.takacs.stock;

public class CachedCommonStock extends CachedStock implements CommonStock {

	private final double lastDividend;

	public CachedCommonStock(String stockSymbol, double lastDividend) {
		super(stockSymbol);
		this.lastDividend = lastDividend;
	}

	@Override
	public double getLastDividend() {
		return lastDividend;
	}

}
