package com.gergo.takacs.stock.dataprovider;

import java.util.List;

public interface StockEntityService {

	StockEntity findOneBySymbol(String string);

	public abstract long countAllStockEntitys();


	public abstract void deleteStockEntity(StockEntity stockEntity);


	public abstract StockEntity findStockEntity(Long id);


	public abstract List<StockEntity> findAllStockEntitys();


	public abstract List<StockEntity> findStockEntityEntries(int firstResult, int maxResults);


	public abstract void saveStockEntity(StockEntity stockEntity);


	public abstract StockEntity updateStockEntity(StockEntity stockEntity);

}
