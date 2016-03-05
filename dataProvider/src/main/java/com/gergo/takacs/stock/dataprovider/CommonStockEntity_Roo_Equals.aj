// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gergo.takacs.stock.dataprovider;

import com.gergo.takacs.stock.dataprovider.CommonStockEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect CommonStockEntity_Roo_Equals {
    
    public boolean CommonStockEntity.equals(Object obj) {
        if (!(obj instanceof CommonStockEntity)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        CommonStockEntity rhs = (CommonStockEntity) obj;
        return new EqualsBuilder().append(id, rhs.id).append(lastDividend, rhs.lastDividend).append(symbol, rhs.symbol).append(tickerPrice, rhs.tickerPrice).isEquals();
    }
    
    public int CommonStockEntity.hashCode() {
        return new HashCodeBuilder().append(id).append(lastDividend).append(symbol).append(tickerPrice).toHashCode();
    }
    
}
