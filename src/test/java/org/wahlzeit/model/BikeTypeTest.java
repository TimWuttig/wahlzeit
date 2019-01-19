package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class BikeTypeTest {
	@Test
	public void testGetInstances() {
		BikeType firstCall = BikeType.getInstance("sport");
		BikeType secondCall = BikeType.getInstance("sport");
		BikeType otherType = BikeType.getInstance("cross");
		assertTrue(firstCall == secondCall);
		assertNotEquals(firstCall, otherType);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetInstanceOnExceptionWithNull() {
		BikeType.getInstance(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetInstanceOnExceptionWithEmptyString() {
		BikeType.getInstance(new String());
	}
	
	@Test
	public void testGetTypeName() {
		BikeType sportType = BikeType.getInstance("sport");
		BikeType crossType = BikeType.getInstance("cross");
		
		assertEquals("sport", sportType.getTypeName());
		assertEquals("cross", crossType.getTypeName());
	}
	
	@Test
	public void testSetAndGetSuperType() {
		BikeType testType = BikeType.getInstance("supersport");
		
		testType.setSupertype(BikeType.getInstance("sport"));
		assertFalse(testType == BikeType.getInstance("supersport"));
		assertTrue(BikeType.getInstance("sport") == BikeType.getInstance("supersport").getSupertype());
	}
	
	@Test
	public void testIsSubType() {
		BikeType test = BikeType.getInstance("supersport");
		
		assertFalse(test.isSubtype());
		test.setSupertype(BikeType.getInstance("sport"));
		
		assertTrue(BikeType.getInstance("supersport").isSubtype());
	}
}