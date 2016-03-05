// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gergo.takacs.stock.dataprovider;

import com.gergo.takacs.stock.StockType;
import com.gergo.takacs.stock.dataprovider.CommonStockEntity;
import com.gergo.takacs.stock.dataprovider.CommonStockEntityDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect CommonStockEntityDataOnDemand_Roo_DataOnDemand {
    
    declare @type: CommonStockEntityDataOnDemand: @Component;
    
    private Random CommonStockEntityDataOnDemand.rnd = new SecureRandom();
    
    private List<CommonStockEntity> CommonStockEntityDataOnDemand.data;
    
    @Autowired
    StockRepository CommonStockEntityDataOnDemand.stockRepository;
    
    public CommonStockEntity CommonStockEntityDataOnDemand.getNewTransientCommonStockEntity(int index) {
        CommonStockEntity obj = new CommonStockEntity();
        setLastDividend(obj, index);
        setSymbol(obj, index);
        setTickerPrice(obj, index);
        setType(obj, index);
        return obj;
    }
    
    public void CommonStockEntityDataOnDemand.setLastDividend(CommonStockEntity obj, int index) {
        double lastDividend = new Integer(index).doubleValue();
        obj.setLastDividend(lastDividend);
    }
    
    public void CommonStockEntityDataOnDemand.setSymbol(CommonStockEntity obj, int index) {
        String symbol = "symbol_" + index;
        obj.setSymbol(symbol);
    }
    
    public void CommonStockEntityDataOnDemand.setTickerPrice(CommonStockEntity obj, int index) {
        double tickerPrice = new Integer(index).doubleValue();
        obj.setTickerPrice(tickerPrice);
    }
    
    public void CommonStockEntityDataOnDemand.setType(CommonStockEntity obj, int index) {
        StockType type = StockType.class.getEnumConstants()[0];
        obj.setType(type);
    }
    
    public CommonStockEntity CommonStockEntityDataOnDemand.getSpecificCommonStockEntity(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        CommonStockEntity obj = data.get(index);
        Long id = obj.getId();
        return (CommonStockEntity) stockRepository.findOne(id);
    }
    
    public CommonStockEntity CommonStockEntityDataOnDemand.getRandomCommonStockEntity() {
        init();
        CommonStockEntity obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return (CommonStockEntity) stockRepository.findOne(id);
    }
    
    public boolean CommonStockEntityDataOnDemand.modifyCommonStockEntity(CommonStockEntity obj) {
        return false;
    }
    
    public void CommonStockEntityDataOnDemand.init() {
        int from = 0;
        int to = 10;
        List<StockEntity> stockData = stockRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (stockData == null) {
            throw new IllegalStateException("Find entries implementation for 'CommonStockEntity' illegally returned null");
        }
        if (!stockData.isEmpty()) {
            return;
        }
        
        data = new ArrayList<CommonStockEntity>();
        for (int i = 0; i < 10; i++) {
            CommonStockEntity obj = getNewTransientCommonStockEntity(i);
            try {
                stockRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            stockRepository.flush();
            data.add(obj);
        }
    }
    
}
