// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gergo.takacs.dataprovider;

import com.gergo.takacs.dataprovider.TradeEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect TradeEntity_Roo_Equals {
    
    public boolean TradeEntity.equals(Object obj) {
        if (!(obj instanceof TradeEntity)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        TradeEntity rhs = (TradeEntity) obj;
        return new EqualsBuilder().append(creationDate, rhs.creationDate).append(direction, rhs.direction).append(id, rhs.id).append(price, rhs.price).append(quantity, rhs.quantity).append(stockSymbol, rhs.stockSymbol).isEquals();
    }
    
    public int TradeEntity.hashCode() {
        return new HashCodeBuilder().append(creationDate).append(direction).append(id).append(price).append(quantity).append(stockSymbol).toHashCode();
    }
    
}
