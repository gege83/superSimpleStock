package com.gergo.takacs.stock.dataprovider;

import java.util.List;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = StockEntity.class)
public interface StockRepository {
	List<StockEntity> findBySymbol(String symbol);
}
