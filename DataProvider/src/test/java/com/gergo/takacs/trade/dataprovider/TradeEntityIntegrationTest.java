package com.gergo.takacs.trade.dataprovider;

import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class TradeEntityIntegrationTest {

	@Autowired
	TradeEntityDataOnDemand dod;

	@Autowired
	TradeEntityService tradeEntityService;

	@Autowired
	TradeRepository tradeRepository;

	@Test
	public void testCountAllTradeEntitys() {
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly",
				dod.getRandomTradeEntity());
		long count = tradeEntityService.countAllTradeEntitys();
		Assert.assertTrue("Counter for 'TradeEntity' incorrectly reported there were no entries", count > 0);
	}

	@Test
	public void testFindTradeEntity() {
		TradeEntity obj = dod.getRandomTradeEntity();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to provide an identifier", id);
		obj = tradeEntityService.findTradeEntity(id);
		Assert.assertNotNull("Find method for 'TradeEntity' illegally returned null for id '" + id + "'", obj);
		Assert.assertEquals("Find method for 'TradeEntity' returned the incorrect identifier", id, obj.getId());
	}

	@Test
	public void testFindAllTradeEntitys() {
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly",
				dod.getRandomTradeEntity());
		long count = tradeEntityService.countAllTradeEntitys();
		Assert.assertTrue(
				"Too expensive to perform a find all test for 'TradeEntity', as there are " + count
						+ " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test",
				count < 250);
		List<TradeEntity> result = tradeEntityService.findAllTradeEntitys();
		Assert.assertNotNull("Find all method for 'TradeEntity' illegally returned null", result);
		Assert.assertTrue("Find all method for 'TradeEntity' failed to return any data", result.size() > 0);
	}

	@Test
	public void testFindTradeEntityEntries() {
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly",
				dod.getRandomTradeEntity());
		long count = tradeEntityService.countAllTradeEntitys();
		if (count > 20) {
			count = 20;
		}
		int firstResult = 0;
		int maxResults = (int) count;
		List<TradeEntity> result = tradeEntityService.findTradeEntityEntries(firstResult, maxResults);
		Assert.assertNotNull("Find entries method for 'TradeEntity' illegally returned null", result);
		Assert.assertEquals("Find entries method for 'TradeEntity' returned an incorrect number of entries", count,
				result.size());
	}

	@Test
	public void testFlush() {
		TradeEntity obj = dod.getRandomTradeEntity();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to provide an identifier", id);
		obj = tradeEntityService.findTradeEntity(id);
		Assert.assertNotNull("Find method for 'TradeEntity' illegally returned null for id '" + id + "'", obj);
		boolean modified = dod.modifyTradeEntity(obj);
		Integer currentVersion = obj.getVersion();
		tradeRepository.flush();
		Assert.assertTrue("Version for 'TradeEntity' failed to increment on flush directive",
				(currentVersion != null && obj.getVersion() > currentVersion) || !modified);
	}

	@Test
	public void testUpdateTradeEntityUpdate() {
		TradeEntity obj = dod.getRandomTradeEntity();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to provide an identifier", id);
		obj = tradeEntityService.findTradeEntity(id);
		boolean modified = dod.modifyTradeEntity(obj);
		Integer currentVersion = obj.getVersion();
		TradeEntity merged = tradeEntityService.updateTradeEntity(obj);
		tradeRepository.flush();
		Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(),
				id);
		Assert.assertTrue("Version for 'TradeEntity' failed to increment on merge and flush directive",
				(currentVersion != null && obj.getVersion() > currentVersion) || !modified);
	}

	@Test
	public void testSaveTradeEntity() {
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly",
				dod.getRandomTradeEntity());
		TradeEntity obj = dod.getNewTransientTradeEntity(Integer.MAX_VALUE);
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to provide a new transient entity", obj);
		Assert.assertNull("Expected 'TradeEntity' identifier to be null", obj.getId());
		try {
			tradeEntityService.saveTradeEntity(obj);
		} catch (final ConstraintViolationException e) {
			final StringBuilder msg = new StringBuilder();
			for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
				final ConstraintViolation<?> cv = iter.next();
				msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath())
						.append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue())
						.append(")").append("]");
			}
			throw new IllegalStateException(msg.toString(), e);
		}
		tradeRepository.flush();
		Assert.assertNotNull("Expected 'TradeEntity' identifier to no longer be null", obj.getId());
	}

	@Test
	public void testDeleteTradeEntity() {
		TradeEntity obj = dod.getRandomTradeEntity();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to provide an identifier", id);
		obj = tradeEntityService.findTradeEntity(id);
		tradeEntityService.deleteTradeEntity(obj);
		tradeRepository.flush();
		Assert.assertNull("Failed to remove 'TradeEntity' with identifier '" + id + "'",
				tradeEntityService.findTradeEntity(id));
	}

	@Test
	public void testFindTradeEntitysByStockSymbolAndCreationDateGreaterThan() {
		TradeEntity randomTradeEntity = dod.getRandomTradeEntity();
		Assert.assertNotNull("Data on demand for 'TradeEntity' failed to initialize correctly", randomTradeEntity);
		long count = tradeEntityService.countAllTradeEntitys();
		Assert.assertTrue(
				"Too expensive to perform a find all test for 'TradeEntity', as there are " + count
						+ " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test",
				count < 250);
		List<TradeEntity> result = tradeEntityService
				.findTradeEntitysByStockSymbolAndCreationDateGreaterThan(randomTradeEntity.getStockSymbol(), 15);
		Assert.assertNotNull("Find all method for 'TradeEntity' illegally returned null", result);
		Assert.assertTrue("Find all method for 'TradeEntity' failed to return any data", result.size() > 0);
	}
}
