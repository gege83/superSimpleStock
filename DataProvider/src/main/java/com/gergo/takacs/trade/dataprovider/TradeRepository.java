package com.gergo.takacs.trade.dataprovider;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TradeEntity.class)
public interface TradeRepository {
	List<TradeEntity> findByStockSymbolAndCreationDateGreaterThan(String stockSymbol, Date creationDate);
}
