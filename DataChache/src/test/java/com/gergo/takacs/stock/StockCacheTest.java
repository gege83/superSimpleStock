package com.gergo.takacs.stock;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StockCacheTest {

	private static final String STOCK1 = "stock1";
	private static final String STOCK2 = "stock2";
	@Mock
	private CachedStock stock1;
	@Mock
	private CachedStock stock2;

	private StockCache underTest;

	@Before
	public void setUp() throws Exception {
		List<CachedStock> initialStocks = Arrays.asList(stock1, stock2);
		when(stock1.getStockSymbol()).thenReturn(STOCK1);
		when(stock2.getStockSymbol()).thenReturn(STOCK2);
		underTest = new StockCache(initialStocks);
	}

	@Test
	public void testFindStockBySymbolIfExsits() throws Exception {
		// given
		// when
		Stock actual = underTest.findStockBySymbol(STOCK1);
		// then
		assertEquals(stock1, actual);
	}

	@Test(expected = StockNotFoundException.class)
	public void testFindStockBySymbolIfNotExsits() throws Exception {
		// given
		// when
		underTest.findStockBySymbol("Not Exists");
		// then exception
	}

	@Test
	public void testFindAllStock() throws Exception {
		// given
		List<Stock> expecteds = Arrays.asList(stock1, stock2);
		// when
		List<Stock> actual = underTest.findAllStock();
		// then
		assertThat("list are not the same", actual, containsInAnyOrder(expecteds.toArray()));
	}

}
