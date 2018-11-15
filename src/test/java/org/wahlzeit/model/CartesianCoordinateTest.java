/*
 * CartesianCoordinateTest
 *  
 * 2.0 
 * Refactored CoordinateTest class
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

public class CartesianCoordinateTest {
	//test instances
	private CartesianCoordinate maxValueCartesianCoordinate;
	private CartesianCoordinate minValueCartesianCoordinate;
	private CartesianCoordinate positiveCartesianCoordinate;
	private CartesianCoordinate negativeCartesianCoordinate;
	private CartesianCoordinate mixedCartesianCoordinate;
	private CartesianCoordinate equalPositiveCartesianCoordinate;
	private CartesianCoordinate equalMixedCartesianCoordinate;
	private SphericCoordinate equalPositiveSphericCoordinate;
	private SphericCoordinate equalMixedSphericCoordinate;

	/**
	 * Initializes the different Coordinates
	 * positiveCoordinates: only positive values
	 * negativCoordinates: only negative values
	 * mixedCoordinates: mixed values, positive and negative
	 */
	@Before
	public void startup() {
		maxValueCartesianCoordinate = new CartesianCoordinate(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		minValueCartesianCoordinate = new CartesianCoordinate(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
		positiveCartesianCoordinate = new CartesianCoordinate(5.0, 3.5, 2.6);
		negativeCartesianCoordinate = new CartesianCoordinate(-6.3,-3.0,-2.5);
		mixedCartesianCoordinate = new CartesianCoordinate(3.2, -4.5, 9);
		equalPositiveCartesianCoordinate = new CartesianCoordinate(5.0, 3.5, 2.6);
		equalMixedCartesianCoordinate = new CartesianCoordinate(3.2, -4.5, 9);
		equalPositiveSphericCoordinate = new SphericCoordinate(6.634003316	, 1.168078459 , 0.6107259644);
		equalMixedSphericCoordinate = new SphericCoordinate(10.55888252, 0.5503090856, -0.9526521009);
	}
	
	/**
	 * tests the getDistanceCartesian() method	
	 */
	@Test
	public void testGetCartesianDistance() {
		double sameCoordinateErg = 0;
		double positiveAndNegativeErg = 13.998214172;
		double positiveAndMixedErg = 10.401922899;
		double negativeAndMixedErg = 14.991664351;
		assertEquals(sameCoordinateErg, positiveCartesianCoordinate.getCartesianDistance(positiveCartesianCoordinate),0);
		assertEquals(sameCoordinateErg, negativeCartesianCoordinate.getCartesianDistance(negativeCartesianCoordinate),0);
		assertEquals(sameCoordinateErg, mixedCartesianCoordinate.getCartesianDistance(mixedCartesianCoordinate),0);
		assertEquals(positiveAndNegativeErg, positiveCartesianCoordinate.getCartesianDistance(negativeCartesianCoordinate),0.000000001);
		assertEquals(positiveAndMixedErg, positiveCartesianCoordinate.getCartesianDistance(mixedCartesianCoordinate),0.000000001);
		assertEquals(negativeAndMixedErg, negativeCartesianCoordinate.getCartesianDistance(mixedCartesianCoordinate),0.000000001);
		assertEquals(sameCoordinateErg, maxValueCartesianCoordinate.getCartesianDistance(maxValueCartesianCoordinate), 0);
		assertEquals(sameCoordinateErg, minValueCartesianCoordinate.getCartesianDistance(minValueCartesianCoordinate), 0);
	}
	
	@Test(expected = ArithmeticException.class)
	public void testGetCartesianDistanceOnOverflow() {
		minValueCartesianCoordinate.getCartesianDistance(maxValueCartesianCoordinate);
	}
	
	
	
	/**
	 * tests method isEqual
	 */
	@Test
	public void testIsEqual() {
		//Test with CartesianCoordinates as other
		assertTrue(positiveCartesianCoordinate.isEqual(equalPositiveCartesianCoordinate));
		assertTrue(mixedCartesianCoordinate.isEqual(equalMixedCartesianCoordinate));
		
		assertFalse(positiveCartesianCoordinate.isEqual(equalMixedCartesianCoordinate));
		
		//Test with SphericCoordinate as other
		assertTrue(positiveCartesianCoordinate.isEqual(equalPositiveSphericCoordinate));
		assertTrue(mixedCartesianCoordinate.isEqual(equalMixedSphericCoordinate));		
		
		assertFalse(positiveCartesianCoordinate.isEqual(equalMixedSphericCoordinate));
		assertFalse(mixedCartesianCoordinate.isEqual(equalPositiveSphericCoordinate));
		
		//test special case with null
		assertFalse(positiveCartesianCoordinate.isEqual(null));
	}
		
	/**
	 * Test method asSphericCoordinate
	 */
	@Test
	public void testAsSphericCoordinate() {
		double delta = 0.000000001;
		SphericCoordinate testPositiveCoordinate = positiveCartesianCoordinate.asSphericCoordinate();
		SphericCoordinate testMixedCoordinate = mixedCartesianCoordinate.asSphericCoordinate();
		
		//Test with positiveCartesianCoordinate
		assertEquals(equalPositiveSphericCoordinate.getRadius(), testPositiveCoordinate.getRadius(), delta);
		assertEquals(equalPositiveSphericCoordinate.getTheta(), testPositiveCoordinate.getTheta(), delta);
		assertEquals(equalPositiveSphericCoordinate.getPhi(), testPositiveCoordinate.getPhi(), delta);
		//Test with positiveCartesianCoordinate
		assertEquals(equalMixedSphericCoordinate.getRadius(), testMixedCoordinate.getRadius(), delta);
		assertEquals(equalMixedSphericCoordinate.getTheta(), testMixedCoordinate.getTheta(), delta);
		assertEquals(equalMixedSphericCoordinate.getPhi(), testMixedCoordinate.getPhi(), delta);	
	}
	
	/**
	 * tests the forwarding from equals to isEqual()
	 */
	@Test
	public void testEqualsForwarding() {
		//Test with CartesianCoordinates as other
			assertTrue(positiveCartesianCoordinate.equals(equalPositiveCartesianCoordinate));
			assertTrue(mixedCartesianCoordinate.equals(equalMixedCartesianCoordinate));
			
			assertFalse(positiveCartesianCoordinate.equals(equalMixedCartesianCoordinate));
			
			//Test with SphericCoordinate as other
			assertTrue(positiveCartesianCoordinate.equals(equalPositiveSphericCoordinate));
			assertTrue(mixedCartesianCoordinate.equals(equalMixedSphericCoordinate));		
			
			assertFalse(positiveCartesianCoordinate.equals(equalMixedSphericCoordinate));
			assertFalse(mixedCartesianCoordinate.equals(equalPositiveSphericCoordinate));
			
			//Test special objects
			assertFalse(positiveCartesianCoordinate.equals(new Object()));
			assertFalse(positiveCartesianCoordinate.equals(null));
	}
}
