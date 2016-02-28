package com.gergo.takacs.dataprovider;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.gergo.takacs.dataprovider.TradeEntity.class })
public interface TradeEntityService {
	List<TradeEntity> findTradeEntitysByStockSymbolAndCreationDateGreaterThan(String stockSymbol, int range);
}
