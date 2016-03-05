package com.gergo.takacs.stock.dataprovider;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = StockEntity.class)
public interface StockRepository {
	StockEntity findOneBySymbol(String symbol);
}
