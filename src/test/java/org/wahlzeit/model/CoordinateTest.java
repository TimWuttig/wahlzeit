/*
 * Coordinate
 * 
 * 1.0
 * 
 * 26.10.2018
 * 
 * Copyright (c) by Tim Wuttig 
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {
	private Coordinate positiveCoordinates;
	private Coordinate negativeCoordinates;
	private Coordinate mixedCoordinates;
	private Coordinate testPositiveCoordinates;
	private Coordinate testNegativeCoordinates;
	private Coordinate testMixedCoordinates;
	
	/**
	 * Initializes the different Coordinates
	 * positiveCoordinates: only positive values
	 * negativCoordinates: only negative values
	 * mixedCoordinates: mixed values, positive and negative
	 */
	@Before
	public void startup() {
		positiveCoordinates = new Coordinate(5.0, 3.5, 2.6);
		negativeCoordinates = new Coordinate(-6.3,-3.0,-2.5);
		mixedCoordinates = new Coordinate(3.2, -4.5, 9);
		testPositiveCoordinates = new Coordinate(5.0,3.5,2.6);
		testNegativeCoordinates = new Coordinate(-6.3,-3.0,-2.5);
		testMixedCoordinates = new Coordinate(3.2, -4.5, 9);
	}
	
	/**
	 * tests the getDistance() method	
	 */
	@Test
	public void testGetDistance() {
		double sameCoordinateErg = 0;
		double positiveAndNegativeErg = 13.998214172;
		double positiveAndMixedErg = 10.401922899;
		double negativeAndMixedErg = 14.991664351;
		assertEquals(sameCoordinateErg, positiveCoordinates.getDistance(positiveCoordinates),0);
		assertEquals(sameCoordinateErg, negativeCoordinates.getDistance(negativeCoordinates),0);
		assertEquals(sameCoordinateErg, mixedCoordinates.getDistance(mixedCoordinates),0);
		assertEquals(positiveAndNegativeErg, positiveCoordinates.getDistance(negativeCoordinates),0.000000001);
		assertEquals(positiveAndMixedErg, positiveCoordinates.getDistance(mixedCoordinates),0.000000001);
		assertEquals(negativeAndMixedErg, negativeCoordinates.getDistance(mixedCoordinates),0.000000001);
	}
	
	/**
	 * tests method isEqual
	 */
	@Test
	public void testIsEqual() {
		assertTrue(positiveCoordinates.isEqual(testPositiveCoordinates));
		assertTrue(negativeCoordinates.isEqual(testNegativeCoordinates));
		assertTrue(mixedCoordinates.isEqual(testMixedCoordinates));
		assertFalse(positiveCoordinates.isEqual(testNegativeCoordinates));
		assertFalse(negativeCoordinates.isEqual(mixedCoordinates));
	}
	
	/**
	 * tests the forwarding from equals to isEqual()
	 */
	@Test
	public void testEqualsForwarding() {
		assertTrue(positiveCoordinates.equals(testPositiveCoordinates));
		assertTrue(negativeCoordinates.equals(testNegativeCoordinates));
		assertTrue(mixedCoordinates.equals(testMixedCoordinates));
		assertFalse(positiveCoordinates.equals(testNegativeCoordinates));
		assertFalse(negativeCoordinates.equals(mixedCoordinates));
		assertFalse(positiveCoordinates.equals(new Object()));
	}
}
