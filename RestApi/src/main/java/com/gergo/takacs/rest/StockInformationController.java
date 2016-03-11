package com.gergo.takacs.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gergo.takacs.stock.StockNotFoundException;
import com.gergo.takacs.stock.calculator.StockIndexInformation;
import com.gergo.takacs.stock.calculator.StockInformation;
import com.gergo.takacs.stock.service.SimpleStockServiceFacade;

@RestController
public class StockInformationController {

	private final SimpleStockServiceFacade simpleStockServiceFacade;

	@Autowired
	public StockInformationController(SimpleStockServiceFacade simpleStockServiceFacade) {
		this.simpleStockServiceFacade = simpleStockServiceFacade;
	}

	@RequestMapping("/calculateStockIndexInformation")
	public ResponseEntity<StockIndexInformation> calculateStockIndexInformation() {
		try {
			StockIndexInformation stockIndexInformation = simpleStockServiceFacade.calculateStockIndexInformation();
			return new ResponseEntity<StockIndexInformation>(stockIndexInformation, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<StockIndexInformation>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping("/calculateStockInformation")
	public ResponseEntity<StockInformation> calculateStockInformation(String stockSymbol) {
		try {
			StockInformation stockIndexInformation = simpleStockServiceFacade.calculateStockInformation(stockSymbol);
			return new ResponseEntity<StockInformation>(stockIndexInformation, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<StockInformation>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/addTrade")
	public ResponseEntity<?> addTrade(@Valid ValidatorTrade trade, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<BindingResult>(bindingResult, HttpStatus.BAD_REQUEST);
		}
		try {
			simpleStockServiceFacade.saveTrade(trade.getStockSymbol(), trade.getTradeDirection(), trade.getPrice(),
					trade.getQuantity());
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (StockNotFoundException ex) {
			bindingResult.reject("StockNotFound", "stock not found");
			return new ResponseEntity<BindingResult>(bindingResult, HttpStatus.BAD_REQUEST);
		}
	}

}
