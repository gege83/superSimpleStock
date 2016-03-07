package com.gergo.takacs.stock.dataprovider;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class PreferredStockEntityDataOnDemand {


	private Random rnd = new SecureRandom();

	private List<PreferredStockEntity> data;

	@Autowired
    StockRepository stockRepository;

	public PreferredStockEntity getNewTransientPreferredStockEntity(int index) {
        PreferredStockEntity obj = new PreferredStockEntity();
        setFixedDividend(obj, index);
        setParValue(obj, index);
        setSymbol(obj, index);
        setTickerPrice(obj, index);
        return obj;
    }

	public void setFixedDividend(PreferredStockEntity obj, int index) {
        float fixedDividend = new Integer(index).floatValue();
        obj.setFixedDividend(fixedDividend);
    }

	public void setParValue(PreferredStockEntity obj, int index) {
    	double parValue = new Integer(index).doubleValue();
    	obj.setParValue(parValue);
    }

	public void setSymbol(PreferredStockEntity obj, int index) {
        String symbol = "symbol_" + index;
        obj.setSymbol(symbol);
    }

	public void setTickerPrice(PreferredStockEntity obj, int index) {
        double tickerPrice = new Integer(index).doubleValue();
        obj.setTickerPrice(tickerPrice);
    }

	public PreferredStockEntity getSpecificPreferredStockEntity(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        PreferredStockEntity obj = data.get(index);
        Long id = obj.getId();
        return (PreferredStockEntity) stockRepository.findOne(id);
    }

	public PreferredStockEntity getRandomPreferredStockEntity() {
        init();
        PreferredStockEntity obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return (PreferredStockEntity) stockRepository.findOne(id);
    }

	public boolean modifyPreferredStockEntity(PreferredStockEntity obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        List<StockEntity> stockData = stockRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (stockData == null) {
            throw new IllegalStateException("Find entries implementation for 'PreferredStockEntity' illegally returned null");
        }
        if (!stockData.isEmpty()) {
            return;
        }
        
        data = new ArrayList<PreferredStockEntity>();
        for (int i = 0; i < 10; i++) {
            PreferredStockEntity obj = getNewTransientPreferredStockEntity(i);
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
