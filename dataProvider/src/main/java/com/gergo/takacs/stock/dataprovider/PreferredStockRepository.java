package com.gergo.takacs.stock.dataprovider;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = CommonStockEntity.class)
public interface PreferredStockRepository {
}
