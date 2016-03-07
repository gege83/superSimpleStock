package com.gergo.takacs.stock.dataprovider;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class CommonStockEntity extends StockEntity {

	/**
	 */
	private double lastDividend;

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CommonStockEntity)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		CommonStockEntity rhs = (CommonStockEntity) obj;
		return super.equals(rhs) && new EqualsBuilder().append(lastDividend, rhs.lastDividend).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(super.hashCode()).append(lastDividend).toHashCode();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
