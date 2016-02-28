package com.gergo.takacs.tradedataprovider;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import com.gergo.takacs.trade.TradeDirection;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals
public class Trade {

	/**
	 */
	@NotNull
	private Date creationTime;

	/**
	 */
	@NotNull
	@Size(min = 2)
	private String stockSymbol;

	/**
	 */
	@Enumerated
	private TradeDirection direction;

	/**
	 */
	@Min(1L)
	private int quantity;

	/**
	 */
	@NotNull
	@Min(0L)
	private double price;
}
