package com.gergo.takacs.stock;

public interface PreferredStock extends Stock {
	double getParValue();

	double getFixedDividend();
}
