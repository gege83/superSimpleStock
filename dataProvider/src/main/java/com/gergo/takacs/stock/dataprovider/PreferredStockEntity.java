package com.gergo.takacs.stock.dataprovider;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals
public class PreferredStockEntity extends StockEntity {

    /**
     */
    private float fixedDividend;

    /**
     */
    private double parValue;
}
