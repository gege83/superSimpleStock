package com.gergo.takacs.trade.dataprovider;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.gergo.takacs.trade.TradeDirection;

@Entity
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

	public boolean equals(Object obj) {
        if (!(obj instanceof TradeEntity)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        TradeEntity rhs = (TradeEntity) obj;
        return new EqualsBuilder().append(creationDate, rhs.creationDate).append(direction, rhs.direction).append(id, rhs.id).append(price, rhs.price).append(quantity, rhs.quantity).append(stockSymbol, rhs.stockSymbol).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(creationDate).append(direction).append(id).append(price).append(quantity).append(stockSymbol).toHashCode();
    }

	public Date getCreationDate() {
        return this.creationDate;
    }

	public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

	public TradeDirection getDirection() {
        return this.direction;
    }

	public void setDirection(TradeDirection direction) {
        this.direction = direction;
    }

	public int getQuantity() {
        return this.quantity;
    }

	public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	public double getPrice() {
        return this.price;
    }

	public void setPrice(double price) {
        this.price = price;
    }

	public String getStockSymbol() {
        return this.stockSymbol;
    }

	public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }
}
