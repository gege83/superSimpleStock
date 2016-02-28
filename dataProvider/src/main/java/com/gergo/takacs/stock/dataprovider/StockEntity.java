package com.gergo.takacs.stock.dataprovider;

import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import com.gergo.takacs.stock.StockType;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals
public abstract class StockEntity {

	/**
	 */
	@Size(min = 2)
	private String symbol;

	/**
	 */
	@Min(0L)
	private double tickerPrice;

	/**
	 */
	@Enumerated
	private StockType type;
}
