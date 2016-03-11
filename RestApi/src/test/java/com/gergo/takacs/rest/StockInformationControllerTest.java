package com.gergo.takacs.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.gergo.takacs.stock.calculator.StockIndexInformation;
import com.gergo.takacs.stock.calculator.StockInformation;
import com.gergo.takacs.stock.service.SimpleStockServiceFacade;

@RunWith(MockitoJUnitRunner.class)
public class StockInformationControllerTest {
	@Mock
	private StockIndexInformation stockIndexInformation;
	@Mock
	private StockInformation stockInformation;
	@Mock
	private SimpleStockServiceFacade simpleStockServiceFacade;
	@InjectMocks
	private StockInformationController underTest;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculateStockIndexInformation() throws Exception {
		// given
		when(simpleStockServiceFacade.calculateStockIndexInformation()).thenReturn(stockIndexInformation);
		ResponseEntity<StockIndexInformation> expected = new ResponseEntity<StockIndexInformation>(
				stockIndexInformation, HttpStatus.OK);
		// when
		ResponseEntity<StockIndexInformation> actual = underTest.calculateStockIndexInformation();
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateStockInformation() throws Exception {
		// given
		when(simpleStockServiceFacade.calculateStockInformation("symbol")).thenReturn(stockInformation);
		ResponseEntity<StockInformation> expected = new ResponseEntity<StockInformation>(stockInformation,
				HttpStatus.OK);
		// when
		ResponseEntity<StockInformation> actual = underTest.calculateStockInformation("symbol");
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testAddTrade() throws Exception {
		// given
		BindingResult bindingResult = mock(BindingResult.class);
		ValidatorTrade trade = mock(ValidatorTrade.class);
		ResponseEntity<?> expected = new ResponseEntity<Object>(HttpStatus.OK);
		// when
		ResponseEntity<?> actual = underTest.addTrade(trade, bindingResult);
		// then
		assertEquals(expected, actual);
	}

}
