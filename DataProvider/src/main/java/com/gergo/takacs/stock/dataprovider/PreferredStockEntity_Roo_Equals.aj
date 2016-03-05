// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gergo.takacs.stock.dataprovider;

import com.gergo.takacs.stock.dataprovider.PreferredStockEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect PreferredStockEntity_Roo_Equals {
    
    public boolean PreferredStockEntity.equals(Object obj) {
        if (!(obj instanceof PreferredStockEntity)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PreferredStockEntity rhs = (PreferredStockEntity) obj;
        return new EqualsBuilder().append(fixedDividend, rhs.fixedDividend).append(id, rhs.id).append(parValue, rhs.parValue).append(symbol, rhs.symbol).append(tickerPrice, rhs.tickerPrice).isEquals();
    }
    
    public int PreferredStockEntity.hashCode() {
        return new HashCodeBuilder().append(fixedDividend).append(id).append(parValue).append(symbol).append(tickerPrice).toHashCode();
    }
    
}