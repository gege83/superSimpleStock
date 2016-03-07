package com.gergo.takacs.trade.dataprovider;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TradeEntityServiceImpl implements TradeEntityService {

	@Override
	public List<TradeEntity> findTradeEntitysByStockSymbolAndCreationDateGreaterThan(String stockSymbol, int range) {
		return tradeRepository.findByStockSymbolAndCreationDateGreaterThan(stockSymbol, getCreationDateForRange(range));
	}

	private Date getCreationDateForRange(int rangeInMinutes) {
		DateTime dateTime = new DateTime();
		DateTime rangeStart = dateTime.minusMinutes(rangeInMinutes);
		return rangeStart.toDate();
	}

	@Autowired
    TradeRepository tradeRepository;

	public long countAllTradeEntitys() {
        return tradeRepository.count();
    }

	public void deleteTradeEntity(TradeEntity tradeEntity) {
        tradeRepository.delete(tradeEntity);
    }

	public TradeEntity findTradeEntity(Long id) {
        return tradeRepository.findOne(id);
    }

	public List<TradeEntity> findAllTradeEntitys() {
        return tradeRepository.findAll();
    }

	public List<TradeEntity> findTradeEntityEntries(int firstResult, int maxResults) {
        return tradeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveTradeEntity(TradeEntity tradeEntity) {
        tradeRepository.save(tradeEntity);
    }

	public TradeEntity updateTradeEntity(TradeEntity tradeEntity) {
        return tradeRepository.save(tradeEntity);
    }
}
