package com.gergo.takacs.stock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gergo.takacs.stock.converter.StockEntityConverter;
import com.gergo.takacs.stock.converter.StockEntityConverterFactory;
import com.gergo.takacs.stock.dataprovider.CommonStockEntity;
import com.gergo.takacs.stock.dataprovider.PreferredStockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntityService;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceAdapterTest {

	@Mock
	private StockEntityService stockService;

	@Mock
	private StockEntityConverterFactory converterFactory;

	@Mock
	private StockEntityConverter<CommonStockEntity> commonConverter;

	@Mock
	private StockEntityConverter<PreferredStockEntity> preferredConverter;

	@InjectMocks
	private StockServiceAdapter underTest;

	@Test
	public void testFindOneStockBySymbolCommon() {
		// given
		StockEntity stockEntity = new CommonStockEntity();
		when(stockService.findOneBySymbol("symbol")).thenReturn(stockEntity);
		Stock expected = new UnmutableCommonStock("symbol", 20., 10.);
		when(converterFactory.getConverter(CommonStockEntity.class)).thenReturn(commonConverter);
		when(commonConverter.convert(stockEntity)).thenReturn(expected);
		// when
		Stock actual = underTest.findStockBySymbol("symbol");
		// then
		assertEquals(expected, actual);
	}

	@Test
	public void testFindOneStockBySymbolPreferred() {
		// given
		StockEntity stockEntity = new PreferredStockEntity();
		when(stockService.findOneBySymbol("symbol")).thenReturn(stockEntity);
		Stock expected = new UnmutablePreferredStock("symbol", 20., 10., 5.);
		when(converterFactory.getConverter(PreferredStockEntity.class)).thenReturn(preferredConverter);
		when(preferredConverter.convert(stockEntity)).thenReturn(expected);
		// when
		Stock actual = underTest.findStockBySymbol("symbol");
		// then
		assertEquals(expected, actual);
	}

}
