package com.gergo.takacs.trade.dataprovider;

import java.util.List;


public interface TradeEntityService {
	List<TradeEntity> findTradeEntitysByStockSymbolAndCreationDateGreaterThan(String stockSymbol, int range);

	public abstract long countAllTradeEntitys();


	public abstract void deleteTradeEntity(TradeEntity tradeEntity);


	public abstract TradeEntity findTradeEntity(Long id);


	public abstract List<TradeEntity> findAllTradeEntitys();


	public abstract List<TradeEntity> findTradeEntityEntries(int firstResult, int maxResults);


	public abstract void saveTradeEntity(TradeEntity tradeEntity);


	public abstract TradeEntity updateTradeEntity(TradeEntity tradeEntity);

}
