package com.gergo.takacs.trade.dataprovider;

import com.gergo.takacs.trade.TradeDirection;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class TradeEntityDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<TradeEntity> data;

	@Autowired
    TradeEntityService tradeEntityService;

	@Autowired
    TradeRepository tradeRepository;

	public TradeEntity getNewTransientTradeEntity(int index) {
        TradeEntity obj = new TradeEntity();
        setCreationDate(obj, index);
        setDirection(obj, index);
        setPrice(obj, index);
        setQuantity(obj, index);
        setStockSymbol(obj, index);
        return obj;
    }

	public void setCreationDate(TradeEntity obj, int index) {
        Date creationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationDate(creationDate);
    }

	public void setDirection(TradeEntity obj, int index) {
        TradeDirection direction = TradeDirection.class.getEnumConstants()[0];
        obj.setDirection(direction);
    }

	public void setPrice(TradeEntity obj, int index) {
        double price = new Integer(index).doubleValue();
        obj.setPrice(price);
    }

	public void setQuantity(TradeEntity obj, int index) {
        int quantity = index;
        if (quantity < 1) {
            quantity = 1;
        }
        obj.setQuantity(quantity);
    }

	public void setStockSymbol(TradeEntity obj, int index) {
        String stockSymbol = "stockSymbol_" + index;
        obj.setStockSymbol(stockSymbol);
    }

	public TradeEntity getSpecificTradeEntity(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        TradeEntity obj = data.get(index);
        Long id = obj.getId();
        return tradeEntityService.findTradeEntity(id);
    }

	public TradeEntity getRandomTradeEntity() {
        init();
        TradeEntity obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return tradeEntityService.findTradeEntity(id);
    }

	public boolean modifyTradeEntity(TradeEntity obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = tradeEntityService.findTradeEntityEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'TradeEntity' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<TradeEntity>();
        for (int i = 0; i < 10; i++) {
            TradeEntity obj = getNewTransientTradeEntity(i);
            try {
                tradeEntityService.saveTradeEntity(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            tradeRepository.flush();
            data.add(obj);
        }
    }
}
