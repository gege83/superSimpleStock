package com.gergo.takacs.stock.dataprovider;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { StockEntity.class })
public interface StockEntityService {

	StockEntity findOneBySymbol(String string);
}
