package com.gergo.takacs.trade.dataprovider;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { TradeEntity.class })
public interface TradeEntityService {
	List<TradeEntity> findTradeEntitysByStockSymbolAndCreationDateGreaterThan(String stockSymbol, int range);
}
