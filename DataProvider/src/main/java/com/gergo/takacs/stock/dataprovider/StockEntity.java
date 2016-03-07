package com.gergo.takacs.stock.dataprovider;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "symbol" }))
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CLASS_TYPE")
public abstract class StockEntity {

	/**
	 */
	@Size(min = 2)
	private String symbol;

	/**
	 */
	@Min(0L)
	private double tickerPrice;

	public String getSymbol() {
        return this.symbol;
    }

	public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

	public double getTickerPrice() {
        return this.tickerPrice;
    }

	public void setTickerPrice(double tickerPrice) {
        this.tickerPrice = tickerPrice;
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof StockEntity)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        StockEntity rhs = (StockEntity) obj;
        return new EqualsBuilder().append(id, rhs.id).append(symbol, rhs.symbol).append(tickerPrice, rhs.tickerPrice).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(symbol).append(tickerPrice).toHashCode();
    }
}
