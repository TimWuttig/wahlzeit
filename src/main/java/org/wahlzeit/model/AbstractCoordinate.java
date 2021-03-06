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
	//the threshold when two coordinates are "equal".
	protected final double acceptThreshold = 0.000001;
	
	/**
	 * @methodtype conversion
	 * @mehtodproperties primitive
	 */
	public abstract CartesianCoordinate asCartesianCoordinate();
	
	/**
	 * @methodtype conversion
	 * @mehtodproperties primitive
	 */
	public abstract SphericCoordinate asSphericCoordinate();
	
	/**
	 * @methodetype query
	 * @methodproperties
	 * @param other: must not to be null
	 * @return CartesianDistance between two CartesianCoordinate objects
	 */
	protected abstract double doGetCartesianDistance(CartesianCoordinate other);
	
	/**
	 * calculates the central angle
	 * @param other: must be not null and not the original coordinate
	 * @return the central angle if its possible to calculate else NaN is returned
	 */
	protected abstract double doGetCentralAngle(SphericCoordinate other);
	
	@Override
	public double getCartesianDistance(Coordinate other) {
		//Test preconditions
		isValidCoordinate(other);
		
		//use doGetCartesianDistance to calculate distance
		double erg = doGetCartesianDistance(other.asCartesianCoordinate());		
		
		//Test postconditions
		if(!Double.isFinite(erg)) throw new ArithmeticException("ERROR: calculated result is not finite or NaN.\n Maybe there was an overflow.");
		
		return erg;
	}
	
	/**
	 * @methodtype query
	 * @methodproperties composed
	 */
	@Override
	public double getCentralAngle(Coordinate other) {
		SphericCoordinate sphericOther = other.asSphericCoordinate();
		SphericCoordinate sphericThis = this.asSphericCoordinate();
		
		//Test preconditions
		isValidCoordinate(other); //test if other is null
		if(sphericOther.getRadius() == 0 || sphericThis.getRadius() == 0) //test if radius is null then no angle exist
					throw new IllegalArgumentException("ERROR: Can't calculate the central angle with original coordinates");
		
		//use doGetCentralAngle to calculate the central angle
		double erg = doGetCentralAngle(other.asSphericCoordinate());
		
		//Test postconditions
		if(!Double.isFinite(erg)) throw new ArithmeticException("ERROR: calculated result is not finite or NaN");
		
		return erg;
	}
	
	@Override
	public boolean isEqual(Coordinate other) {
		if(other == null) return false;
		
		double distanceToOther = getCartesianDistance(other);
		
		return distanceToOther <= acceptThreshold? true : false;
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
