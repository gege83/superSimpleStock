// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gergo.takacs.stock.dataprovider;

import com.gergo.takacs.stock.dataprovider.CommonStockRepository;
import com.gergo.takacs.stock.dataprovider.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect CommonStockRepository_Roo_Jpa_Repository {
    
    declare parents: CommonStockRepository extends JpaRepository<StockEntity, Long>;
    
    declare parents: CommonStockRepository extends JpaSpecificationExecutor<StockEntity>;
    
    declare @type: CommonStockRepository: @Repository;
    
}