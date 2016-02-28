// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gergo.takacs.stock.dataprovider;

import com.gergo.takacs.stock.dataprovider.StockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntityServiceImpl;
import com.gergo.takacs.stock.dataprovider.StockRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StockEntityServiceImpl_Roo_Service {
    
    declare @type: StockEntityServiceImpl: @Service;
    
    declare @type: StockEntityServiceImpl: @Transactional;
    
    @Autowired
    StockRepository StockEntityServiceImpl.stockRepository;
    
    public long StockEntityServiceImpl.countAllStockEntitys() {
        return stockRepository.count();
    }
    
    public void StockEntityServiceImpl.deleteStockEntity(StockEntity stockEntity) {
        stockRepository.delete(stockEntity);
    }
    
    public StockEntity StockEntityServiceImpl.findStockEntity(Long id) {
        return stockRepository.findOne(id);
    }
    
    public List<StockEntity> StockEntityServiceImpl.findAllStockEntitys() {
        return stockRepository.findAll();
    }
    
    public List<StockEntity> StockEntityServiceImpl.findStockEntityEntries(int firstResult, int maxResults) {
        return stockRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void StockEntityServiceImpl.saveStockEntity(StockEntity stockEntity) {
        stockRepository.save(stockEntity);
    }
    
    public StockEntity StockEntityServiceImpl.updateStockEntity(StockEntity stockEntity) {
        return stockRepository.save(stockEntity);
    }
    
}
