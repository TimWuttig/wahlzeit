package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {
	private Location first;
	private Location second;
	
	/**
	 * create two test locations
	 */
	@Before
	public void setUp() {
		first = new Location(3.5, 4.8, -5.3);
		second = new Location(2.8, -1.4, -7.0);
	}
	
	/**
	 * tests the equals forwarding
	 */
	@Test
	public void testEquals() {
		assertTrue(first.equals(first));
		assertTrue(second.equals(second));
		assertFalse(first.equals(second));
		assertFalse(second.equals(first));
		assertFalse(first.equals(new Object()));
	}
}
