/*
 * AbstractCoordinate
 * 
 * 1.0
 * 
 * 23.11.2018
 * 
 * Copyright (c) by Tim Wuttig
 */

package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{
	
	public abstract CartesianCoordinate asCartesianCoordinate();
	public abstract SphericCoordinate asSphericCoordinate();
	protected abstract double doGetCartesianDistance(CartesianCoordinate other);
	protected abstract double doGetCentralAngle(SphericCoordinate other);
	
	@Override
	public double getCartesianDistance(Coordinate other) {
		//check given parameter
		isValidCoordinate(other);
		
		//use doGetCartesianDistance to calculate distance
		return doGetCartesianDistance(other.asCartesianCoordinate());		
	}
	
	/**
	 * @methodtype query
	 * @methodproperties composed
	 */
	@Override
	public double getCentralAngle(Coordinate other) {
		isValidCoordinate(other);
		
		return doGetCentralAngle(other.asSphericCoordinate());
	}
	
	@Override
	public boolean isEqual(Coordinate other) {
		if(other == null) return false;
		
		double distanceToOther = getCartesianDistance(other);
		
		return distanceToOther <= 0.000001? true : false;
	}
	
	/**
	 * Overrides and forwards equals() to isEqual
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Coordinate) {
			Coordinate otherCoordinate = (Coordinate) other;
			return isEqual(otherCoordinate);		
		}
		
		return false;
	}
	
	/**
	 * @methodtype assert
	 * @param value will be checked if its a valid parameter for the Coordinate domain
	 * @exception throws IllegalArgumentException when the value isn't valid 
	 */
	public void isValidDouble(double value) {	
		if(value == Double.NaN)
			throw new IllegalArgumentException("parameters can't be set of NaN");
		
		if(value == Double.POSITIVE_INFINITY)
			throw new IllegalArgumentException("parameters can't be set on +inf");
		
		if(value == Double.NEGATIVE_INFINITY)
			throw new IllegalArgumentException("parameters can't be set on -inf");
	}

	/**
	 * @methodtype assert
	 * @param other: Coordinate which has to be checked from this method.
	 * @exception throws IllegalArgumentException when other isn't valid.
	 */
	public void isValidCoordinate(Coordinate other) throws IllegalArgumentException {
		if(other == null) throw new IllegalArgumentException("other have not to be null");
	}
}
