package com.gergo.takacs.stock.dataprovider;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockEntityServiceImpl implements StockEntityService {

	@Autowired
    StockRepository stockRepository;

	public long countAllStockEntitys() {
        return stockRepository.count();
    }

	public void deleteStockEntity(StockEntity stockEntity) {
        stockRepository.delete(stockEntity);
    }

	public StockEntity findStockEntity(Long id) {
        return stockRepository.findOne(id);
    }

	public StockEntity findOneBySymbol(String symbol) {
    	return stockRepository.findOneBySymbol(symbol);
    }

	public List<StockEntity> findAllStockEntitys() {
        return stockRepository.findAll();
    }

	public List<StockEntity> findStockEntityEntries(int firstResult, int maxResults) {
        return stockRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveStockEntity(StockEntity stockEntity) {
        stockRepository.save(stockEntity);
    }

	public StockEntity updateStockEntity(StockEntity stockEntity) {
        return stockRepository.save(stockEntity);
    }
}
