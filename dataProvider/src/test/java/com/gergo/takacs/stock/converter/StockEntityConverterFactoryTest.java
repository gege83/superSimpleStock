package com.gergo.takacs.stock.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gergo.takacs.stock.dataprovider.CommonStockEntity;
import com.gergo.takacs.stock.dataprovider.PreferredStockEntity;
import com.gergo.takacs.stock.dataprovider.StockEntity;

@RunWith(MockitoJUnitRunner.class)
public class StockEntityConverterFactoryTest {

	@Mock
	private CommonStockEntryToCommonStock commonStockConverter;

	@Mock
	private PreferredStockEntityToPreferredStock preferredStockConverter;

	private StockEntityConverterFactory underTest;

	@Before
	public void setup() {
		when(commonStockConverter.getSourceClass()).thenReturn(CommonStockEntity.class);
		when(preferredStockConverter.getSourceClass()).thenReturn(PreferredStockEntity.class);
		List<StockEntityConverter<? extends StockEntity>> converterList = Arrays.asList(commonStockConverter,
				preferredStockConverter);
		underTest = new StockEntityConverterFactory(converterList);
	}

	@Test
	public void testGetConverterIfSourceIsPreferred() throws Exception {
		// given
		Class<PreferredStockEntity> clazz = PreferredStockEntity.class;
		// when
		StockEntityConverter<PreferredStockEntity> actual = underTest.getConverter(clazz);
		// then
		assertEquals(preferredStockConverter, actual);
	}

	@Test
	public void testGetConverterIfSourceIsCommon() throws Exception {
		// given
		Class<CommonStockEntity> clazz = CommonStockEntity.class;
		// when
		StockEntityConverter<CommonStockEntity> actual = underTest.getConverter(clazz);
		// then
		assertEquals(commonStockConverter, actual);
	}

}
