// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gergo.takacs.dataprovider;

import com.gergo.takacs.dataprovider.TradeEntity;
import com.gergo.takacs.trade.TradeDirection;
import java.util.Date;

privileged aspect TradeEntity_Roo_JavaBean {
    
    public Date TradeEntity.getCreationDate() {
        return this.creationDate;
    }
    
    public void TradeEntity.setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public TradeDirection TradeEntity.getDirection() {
        return this.direction;
    }
    
    public void TradeEntity.setDirection(TradeDirection direction) {
        this.direction = direction;
    }
    
    public int TradeEntity.getQuantity() {
        return this.quantity;
    }
    
    public void TradeEntity.setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double TradeEntity.getPrice() {
        return this.price;
    }
    
    public void TradeEntity.setPrice(double price) {
        this.price = price;
    }
    
    public String TradeEntity.getStockSymbol() {
        return this.stockSymbol;
    }
    
    public void TradeEntity.setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    
}
