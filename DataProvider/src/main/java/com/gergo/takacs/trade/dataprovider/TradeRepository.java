package com.gergo.takacs.trade.dataprovider;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaSpecificationExecutor<TradeEntity>, JpaRepository<TradeEntity, Long> {
	List<TradeEntity> findByStockSymbolAndCreationDateGreaterThan(String stockSymbol, Date creationDate);
}
