package com.gergo.takacs.stock;

public class UnmutablePreferredStock extends UnmutableStock implements PreferredStock {

	private double parValue;
	private double fixedDividend;

	public UnmutablePreferredStock(String symbol, double tickerPrice, double parValue, double fixedDividend) {
		super(symbol, tickerPrice);
		this.parValue = parValue;
		this.fixedDividend = fixedDividend;
	}

	@Override
	public double getParValue() {
		return parValue;
	}

	@Override
	public double getFixedDividend() {
		return fixedDividend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(fixedDividend);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(parValue);
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
		UnmutablePreferredStock other = (UnmutablePreferredStock) obj;
		if (Double.doubleToLongBits(fixedDividend) != Double.doubleToLongBits(other.fixedDividend))
			return false;
		if (Double.doubleToLongBits(parValue) != Double.doubleToLongBits(other.parValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnmutablePreferredStock [parValue=" + parValue + ", fixedDividend=" + fixedDividend + ", super_info="
				+ super.toString() + "]";
	}

}
