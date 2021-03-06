package com.gergo.takacs.stock.dataprovider;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class PreferredStockEntityIntegrationTest {

	@Autowired
	PreferredStockEntityDataOnDemand dod;

	@Autowired
	StockRepository stockRepository;

	@Test
	public void testCount() {
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to initialize correctly",
				dod.getRandomPreferredStockEntity());
		long count = stockRepository.count();
		Assert.assertTrue("Counter for 'PreferredStockEntity' incorrectly reported there were no entries", count > 0);
	}

	@Test
	public void testFind() {
		PreferredStockEntity obj = dod.getRandomPreferredStockEntity();
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to provide an identifier", id);
		obj = (PreferredStockEntity) stockRepository.findOne(id);
		Assert.assertNotNull("Find method for 'PreferredStockEntity' illegally returned null for id '" + id + "'", obj);
		Assert.assertEquals("Find method for 'PreferredStockEntity' returned the incorrect identifier", id,
				obj.getId());
	}

	@Test
	public void testFindAll() {
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to initialize correctly",
				dod.getRandomPreferredStockEntity());
		long count = stockRepository.count();
		Assert.assertTrue(
				"Too expensive to perform a find all test for 'PreferredStockEntity', as there are " + count
						+ " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test",
				count < 250);
		List<StockEntity> result = stockRepository.findAll();
		Assert.assertNotNull("Find all method for 'PreferredStockEntity' illegally returned null", result);
		Assert.assertTrue("Find all method for 'PreferredStockEntity' failed to return any data", result.size() > 0);
	}

	@Test
	public void testFindEntries() {
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to initialize correctly",
				dod.getRandomPreferredStockEntity());
		long count = stockRepository.count();
		if (count > 20) {
			count = 20;
		}
		int firstResult = 0;
		int maxResults = (int) count;
		List<StockEntity> result = stockRepository
				.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults))
				.getContent();
		Assert.assertNotNull("Find entries method for 'PreferredStockEntity' illegally returned null", result);
		Assert.assertEquals("Find entries method for 'PreferredStockEntity' returned an incorrect number of entries",
				count, result.size());
	}

	@Test
	public void testFlush() {
		PreferredStockEntity obj = dod.getRandomPreferredStockEntity();
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to provide an identifier", id);
		obj = (PreferredStockEntity) stockRepository.findOne(id);
		Assert.assertNotNull("Find method for 'PreferredStockEntity' illegally returned null for id '" + id + "'", obj);
		boolean modified = dod.modifyPreferredStockEntity(obj);
		Integer currentVersion = obj.getVersion();
		stockRepository.flush();
		Assert.assertTrue("Version for 'PreferredStockEntity' failed to increment on flush directive",
				(currentVersion != null && obj.getVersion() > currentVersion) || !modified);
	}

	@Test
	public void testSaveUpdate() {
		PreferredStockEntity obj = dod.getRandomPreferredStockEntity();
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to provide an identifier", id);
		obj = (PreferredStockEntity) stockRepository.findOne(id);
		boolean modified = dod.modifyPreferredStockEntity(obj);
		Integer currentVersion = obj.getVersion();
		PreferredStockEntity merged = stockRepository.save(obj);
		stockRepository.flush();
		Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(),
				id);
		Assert.assertTrue("Version for 'PreferredStockEntity' failed to increment on merge and flush directive",
				(currentVersion != null && obj.getVersion() > currentVersion) || !modified);
	}

	@Test
	public void testSave() {
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to initialize correctly",
				dod.getRandomPreferredStockEntity());
		PreferredStockEntity obj = dod.getNewTransientPreferredStockEntity(Integer.MAX_VALUE);
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to provide a new transient entity", obj);
		Assert.assertNull("Expected 'PreferredStockEntity' identifier to be null", obj.getId());
		try {
			stockRepository.save(obj);
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
		stockRepository.flush();
		Assert.assertNotNull("Expected 'PreferredStockEntity' identifier to no longer be null", obj.getId());
	}

	@Test
	public void testDelete() {
		PreferredStockEntity obj = dod.getRandomPreferredStockEntity();
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'PreferredStockEntity' failed to provide an identifier", id);
		obj = (PreferredStockEntity) stockRepository.findOne(id);
		stockRepository.delete(obj);
		stockRepository.flush();
		Assert.assertNull("Failed to remove 'PreferredStockEntity' with identifier '" + id + "'",
				stockRepository.findOne(id));
	}
}
