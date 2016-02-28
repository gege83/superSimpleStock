package com.gergo.takacs.tradedataprovider;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Trade.class)
public interface TradeRepository {
	List<Trade> findByStockSymbolAndCreationTimeGreaterThan(String stockSymbol, Date creationTime);
}
