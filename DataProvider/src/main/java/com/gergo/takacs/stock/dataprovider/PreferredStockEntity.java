package com.gergo.takacs.stock.dataprovider;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class PreferredStockEntity extends StockEntity {

	/**
	 */
	private double fixedDividend;

	/**
	 */
	private double parValue;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PreferredStockEntity)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		PreferredStockEntity rhs = (PreferredStockEntity) obj;
		return super.equals(rhs) && new EqualsBuilder().append(fixedDividend, rhs.fixedDividend)
				.append(parValue, rhs.parValue).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fixedDividend).append(super.hashCode()).append(parValue).toHashCode();
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
}
