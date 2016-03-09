package com.gergo.takacs.trade;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TradeTimeFilterPredicateTest {
	@Mock
	private CachedTrade trade;

	private final int timeInMinutes = 5;
	private TradeTimeFilterPredicate underTest;

	@Before
	public void setUp() throws Exception {
		underTest = new TradeTimeFilterPredicate(timeInMinutes);
	}

	@Test
	public void testTestIfNotChachedTrade() throws Exception {
		// given
		Object test = new Object();
		// when
		boolean actual = underTest.evaluate(test);
		// then
		assertFalse(actual);
	}

	@Test
	public void testTestIfIn() throws Exception {
		// given
		when(trade.getCreationTime()).thenReturn(DateTime.now().minusMinutes(3));
		// when
		boolean actual = underTest.evaluate(trade);
		// then
		assertTrue(actual);
	}

	@Test
	public void testTestIfOut() throws Exception {
		// given
		when(trade.getCreationTime()).thenReturn(DateTime.now().minusMinutes(13));
		// when
		boolean actual = underTest.evaluate(trade);
		// then
		assertFalse(actual);
	}

}
