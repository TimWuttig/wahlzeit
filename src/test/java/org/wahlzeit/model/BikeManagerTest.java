package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BikeManagerTest {
	BikeManager manager;
	
	@Before
	public void init() {
		manager = BikeManager.getInstance();
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(manager);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHasTypeOnException() {
		manager.hasType(null);
	}
	
	@Test
	public void testHasAndAddType() {
		BikeType type = BikeType.getInstance("sport");
		assertFalse(manager.hasType(type));
		manager.addBikeType(type);
		assertTrue(manager.hasType(type));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHasBikeOnException() {
		manager.hasBike(null);
	}
	
	@Test
	public void testHasAndAddBike() {
		Bike bike = new Bike(BikeType.getInstance("sport"), "CBR 500 R", "Honda", 48);
		assertFalse(manager.hasBike(bike));
		manager.addBike(bike);
		assertTrue(manager.hasBike(bike));
	}
}
