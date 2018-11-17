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


public class CartesianCoordinate implements Coordinate{
	/**
	 * variables for the Cartesian coordinates
	 */
	private double x;
	private double y;
	private double z;
	
	/**
	 * Constructor sets the attributes x,y,z on the specific values
	 * @param x stands for the x-Coordinate
	 * @param y stands for the y-Coordinate
	 * @param z stands for the z-Coordinate
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Calculates the direct distance between this coordinate object and target
	 * @exception IllegalArgumentException: When target is null; ArithmeticException: when there is a overflow when we calculate the distance
	 * @param target: another coordinate object
	 * @return the direct distance
	 */
	public double getCartesianDistance(Coordinate other) {
		//check parameter
		isValidCoordinate(other);
		
		return doGetCartesianDistance(other.asCartesianCoordinate());
	}
	
	/**
	 * @methodetype query
	 * @methodproperties
	 * @return CartesianDistance between two CartesianCoordinate objects
	 */
	private double doGetCartesianDistance(CartesianCoordinate other) throws ArithmeticException{
		//Build square deltas between the Coordinates parameters
		double squareDeltaX = Math.pow(this.x - other.getX(),2);		
		double squareDeltaY = Math.pow(this.y - other.getY(),2);
		double squareDeltaZ = Math.pow(this.z - other.getZ(),2);
	
		//Check of under and overflow
		if(squareDeltaX == Double.POSITIVE_INFINITY || squareDeltaY == Double.POSITIVE_INFINITY || squareDeltaZ == Double.POSITIVE_INFINITY)
			throw new ArithmeticException("overflow error occured by calulating the distance");
		
		if(squareDeltaX == Double.NEGATIVE_INFINITY || squareDeltaY == Double.NEGATIVE_INFINITY || squareDeltaZ == Double.NEGATIVE_INFINITY)
			throw new ArithmeticException("underflow error occured by calculating the distance");
		
		double squareDeltasSum = squareDeltaX + squareDeltaY + squareDeltaZ;
		return Math.sqrt(squareDeltasSum);
	}
	
	/**
	 * Checks if this coordinate object is equal to other using method getDistance()
	 * @param other: another Coordinate object
	 * @return if they are equal true else false 
	 */
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

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
		double theta = (Math.PI / 2) - Math.atan(z / (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))));
		double phi = Math.atan2(y, x);
		return new SphericCoordinate(radius, theta, phi);
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
	
	private double doGetCentralAngle(SphericCoordinate other) {
		SphericCoordinate selfAsSpheric = asSphericCoordinate();
		
		return selfAsSpheric.getCentralAngle(other);
	}

	/**
	 * @methodtype assert
	 * @param other: Coordinate which has to be checked from this method.
	 * @exception throws IllegalArgumentException when other isn't valid.
	 */
	public void isValidCoordinate(Coordinate other) throws IllegalArgumentException {
		if(other == null) throw new IllegalArgumentException("other have not to be null");
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
}
