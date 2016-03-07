package com.gergo.takacs.stock.dataprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long>, JpaSpecificationExecutor<StockEntity> {
	StockEntity findOneBySymbol(String symbol);
}
