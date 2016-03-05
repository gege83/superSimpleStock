package com.gergo.takacs.stock;

public class UnmutableCommonStock extends UnmutableStock implements CommonStock {

	private double lastDididend;

	public UnmutableCommonStock(String symbol, double tickerPrice, double lastDididend) {
		super(symbol, tickerPrice);
		this.lastDididend = lastDididend;
	}

	@Override
	public double getLastDividend() {
		return lastDididend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(lastDididend);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnmutableCommonStock other = (UnmutableCommonStock) obj;
		if (Double.doubleToLongBits(lastDididend) != Double.doubleToLongBits(other.lastDididend))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnmutableCommonStock [lastDididend=" + lastDididend + ", super_info=" + super.toString() + "]";
	}

}
