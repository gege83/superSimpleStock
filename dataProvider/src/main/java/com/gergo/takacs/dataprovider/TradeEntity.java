package com.gergo.takacs.dataprovider;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import com.gergo.takacs.trade.TradeDirection;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals
public class TradeEntity {

	/**
	 */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date creationDate;

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
	@Min(0L)
	private double price;

	/**
	 */
	@NotNull
	@Size(min = 2)
	private String stockSymbol;
}
