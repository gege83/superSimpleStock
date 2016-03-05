package com.gergo.takacs.stock.dataprovider;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { StockEntity.class })
public interface StockEntityService {
	List<StockEntity> findBySymbol(String symbol);
}
