package com.gergo.takacs.tradedataprovider;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.gergo.takacs.tradedataprovider.Trade.class })
public interface TradeService {
	public List<Trade> findTradesBySymbolAndRange(String symbol, int timeRangeInMinutes);
}
