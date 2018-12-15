/*
 * SphericCoordinateTest
 *  
 * 1.0
 * 
 * 15.11.2018
 * 
 * Copyright (c) by Tim Wuttig 
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SphericCoordinateTest {
	//test instances
	private SphericCoordinate maxValueSphericCoordinate;
	private SphericCoordinate minValueSphericCoordinate;
	private SphericCoordinate positiveSphericCoordinate;
	private SphericCoordinate negativeSphericCoordinate;
	private SphericCoordinate mixedSphericCoordinate;
	private SphericCoordinate equalPositiveSphericCoordinate;
	private SphericCoordinate equalMixedSphericCoordinate;
	private CartesianCoordinate equalPositiveCartesianCoordinate;
	private CartesianCoordinate equalMixedCartesianCoordinate;
	private double delta = 0.000001;

	/**
	 * Initializes the different Coordinates
	 * positiveCoordinates: only positive values
	 * negativCoordinates: only negative values
	 * mixedCoordinates: mixed values, positive and negative
	 */
	@Before
	public void startup() {
		maxValueSphericCoordinate = SphericCoordinate.get(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		minValueSphericCoordinate = SphericCoordinate.get(0, Double.MIN_VALUE, Double.MIN_VALUE);
		positiveSphericCoordinate = SphericCoordinate.get(6.634003316	, 1.168078459 , 0.6107259644);
		negativeSphericCoordinate = SphericCoordinate.get(7.412152184, 1.914826624, -2.69717345);
		mixedSphericCoordinate = SphericCoordinate.get(10.55888252, 0.5503090856, -0.9526521009);
		equalPositiveSphericCoordinate = SphericCoordinate.get(6.634003316	, 1.168078459 , 0.6107259644);
		equalMixedSphericCoordinate = SphericCoordinate.get(10.55888252, 0.5503090856, -0.9526521009);
		equalPositiveCartesianCoordinate = CartesianCoordinate.get(5.0, 3.5, 2.6);
		equalMixedCartesianCoordinate = CartesianCoordinate.get(3.2, -4.5, 9);
	}
	
	@Test
	public void testClone() {
		Object clonePositive = positiveSphericCoordinate.clone();
		Object cloneNegative = negativeSphericCoordinate.clone();
		Object cloneMixed = mixedSphericCoordinate.clone();
		
		assertTrue(clonePositive == positiveSphericCoordinate);
		assertTrue(cloneNegative == negativeSphericCoordinate);
		assertTrue(cloneMixed == mixedSphericCoordinate);
	}
	
	@Test
	public void testGet() {
		SphericCoordinate testMaxValueSphericCoordinate = SphericCoordinate.get(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		SphericCoordinate testMinValueSphericCoordinate = SphericCoordinate.get(0, Double.MIN_VALUE, Double.MIN_VALUE);
		SphericCoordinate testPositiveSphericCoordinate = SphericCoordinate.get(6.634003316	, 1.168078459 , 0.6107259644);
		SphericCoordinate testNegativeSphericCoordinate = SphericCoordinate.get(7.412152184, 1.914826624, -2.69717345);
		SphericCoordinate testMixedSphericCoordinate = SphericCoordinate.get(10.55888252, 0.5503090856, -0.9526521009);

		assertTrue(maxValueSphericCoordinate == testMaxValueSphericCoordinate);
		assertTrue(minValueSphericCoordinate == testMinValueSphericCoordinate);
		assertTrue(positiveSphericCoordinate == testPositiveSphericCoordinate);
		assertTrue(negativeSphericCoordinate == testNegativeSphericCoordinate);
		assertTrue(mixedSphericCoordinate == testMixedSphericCoordinate);
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
		assertEquals(sameCoordinateErg, positiveSphericCoordinate.getCartesianDistance(positiveSphericCoordinate),0);
		assertEquals(sameCoordinateErg, negativeSphericCoordinate.getCartesianDistance(negativeSphericCoordinate),0);
		assertEquals(sameCoordinateErg, mixedSphericCoordinate.getCartesianDistance(mixedSphericCoordinate),0);
		assertEquals(positiveAndNegativeErg, positiveSphericCoordinate.getCartesianDistance(negativeSphericCoordinate), delta);
		assertEquals(positiveAndMixedErg, positiveSphericCoordinate.getCartesianDistance(mixedSphericCoordinate), delta);
		assertEquals(negativeAndMixedErg, negativeSphericCoordinate.getCartesianDistance(mixedSphericCoordinate), delta);
		assertEquals(sameCoordinateErg, maxValueSphericCoordinate.getCartesianDistance(maxValueSphericCoordinate), 0);
		assertEquals(sameCoordinateErg, minValueSphericCoordinate.getCartesianDistance(minValueSphericCoordinate), 0);
	}
	
	/**
	 * Tests method asCartesianCoordinate
	 */
	@Test
	public void testAsCartesianCoordinate() {
		CartesianCoordinate testPositiveCoordinate = positiveSphericCoordinate.asCartesianCoordinate();
		CartesianCoordinate testMixedCoordinate = mixedSphericCoordinate.asCartesianCoordinate();
		
		//Test with positiveCartesianCoordinate
		assertEquals(equalPositiveCartesianCoordinate.getX(), testPositiveCoordinate.getX(), delta);
		assertEquals(equalPositiveCartesianCoordinate.getY(), testPositiveCoordinate.getY(), delta);
		assertEquals(equalPositiveCartesianCoordinate.getZ(), testPositiveCoordinate.getZ(), delta);
		//Test with positiveCartesianCoordinate
		assertEquals(equalMixedCartesianCoordinate.getX(), testMixedCoordinate.getX(), delta);
		assertEquals(equalMixedCartesianCoordinate.getY(), testMixedCoordinate.getY(), delta);
		assertEquals(equalMixedCartesianCoordinate.getZ(), testMixedCoordinate.getZ(), delta);	
	}
	
	/**
	 * tests method getCentralAngle
	 */
	@Test
	public void testGetCentralAngle() {
		double sameCoordinates = 0;
		double positiveAndNegativeErg = 2.4805989747;
		double positiveAndMixedErg = 1.6512142959;
		double negativeAndMixed = 1.3251231052;
		
		assertEquals(sameCoordinates,positiveSphericCoordinate.getCentralAngle(positiveSphericCoordinate),delta);
		assertEquals(sameCoordinates,negativeSphericCoordinate.getCentralAngle(negativeSphericCoordinate),delta);
		assertEquals(sameCoordinates,mixedSphericCoordinate.getCentralAngle(mixedSphericCoordinate),delta);
		assertEquals(positiveAndNegativeErg, positiveSphericCoordinate.getCentralAngle(negativeSphericCoordinate),delta);
		assertEquals(positiveAndMixedErg, positiveSphericCoordinate.getCentralAngle(mixedSphericCoordinate),delta);
		assertEquals(negativeAndMixed, negativeSphericCoordinate.getCentralAngle(mixedSphericCoordinate),delta);
	}
		
	/**
	 * tests method isEqual
	 */
	@Test
	public void testIsEqual() {
		//Test with SphericCoordinates as other
		assertTrue(positiveSphericCoordinate.isEqual(equalPositiveSphericCoordinate));
		assertTrue(mixedSphericCoordinate.isEqual(equalMixedSphericCoordinate));
		
		assertFalse(positiveSphericCoordinate.isEqual(equalMixedSphericCoordinate));
		
		//Test with SphericCoordinate as other
		assertFalse(positiveSphericCoordinate.isEqual(equalMixedCartesianCoordinate));
		assertFalse(mixedSphericCoordinate.isEqual(equalPositiveCartesianCoordinate));
		
		//test special case with null
		assertFalse(positiveSphericCoordinate.isEqual(null));
	}
		
	/**
	 * tests the forwarding from equals to isEqual()
	 */
	@Test
	public void testEqualsForwarding() {
		//Test with SphericCoordinates as other
		assertTrue(positiveSphericCoordinate.equals(equalPositiveSphericCoordinate));
		assertTrue(mixedSphericCoordinate.equals(equalMixedSphericCoordinate));
		
		assertFalse(positiveSphericCoordinate.equals(equalMixedSphericCoordinate));
			
		//Test with SphericCoordinate as other			
		assertFalse(positiveSphericCoordinate.equals(equalMixedCartesianCoordinate));
		assertFalse(mixedSphericCoordinate.equals(equalPositiveCartesianCoordinate));
			
		//test special case with null
		assertFalse(positiveSphericCoordinate.equals(null));
		assertFalse(positiveSphericCoordinate.equals(new Object()));
	}
}
