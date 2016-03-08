package com.gergo.takacs.stock;

import java.text.MessageFormat;

public class StockNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockNotFoundException(String stockSymbol, Throwable cause) {
		super(MessageFormat.format("stock not found {0}", stockSymbol), cause);
	}

	public StockNotFoundException(String stockSymbol) {
		this(stockSymbol, null);
	}
}
