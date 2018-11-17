/*
 * SphericCoordinate
 * 
 * 1.0
 * 
 * 13.11.2018
 * 
 * Copyright (c) by Tim Wuttig
 */

package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate{
	private double phi;
	private double theta;
	private double radius;
	
	/**
	 * @param phi: azimuthal angle; theta: polar angle; radius: radial distance
	 * @methodtype Constructor
	 */
	public SphericCoordinate(double radius, double theta, double phi) {
		//Check parameters valid?
		if(radius < 0)throw new IllegalArgumentException("radius must be positive");
		isValidDouble(radius);
		isValidDouble(phi);
		isValidDouble(theta);		
		
		this.radius = radius;
		this.theta = theta;
		this.phi = phi;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getTheta() {
		return theta;
	}
	
	/**
	 * @methodtype getter
	 */
	public double getPhi() {
		return phi;
	}
	
	/**
	 * @methodtype conversion
	 * @mehtodproperties primitive
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = radius * Math.sin(theta) * Math.cos(phi);
		double y = radius * Math.sin(theta) * Math.sin(phi);
		double z = radius * Math.cos(theta);
		
		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public double getCartesianDistance(Coordinate other) {
		//check given parameter
		isValidCoordinate(other);
		
		//use doGetCartesianDistance to calculate distance
		return doGetCartesianDistance(other.asCartesianCoordinate());		
	}
	
	private double doGetCartesianDistance(CartesianCoordinate other) {
		//calculate cartesianCoordinate to use the getCartesianDistance() implementation form class CartesianCoordinate
		CartesianCoordinate cartesianSelf = this.asCartesianCoordinate();
		
		return cartesianSelf.getCartesianDistance(other);
	}

	/**
	 * @methodtype conversion
	 * @mehtodproperties primitive
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
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

	/**
	 * calculates the central angle
	 * @param other
	 * @return
	 */
	private double doGetCentralAngle(SphericCoordinate other) {
		double deltaTheta = this.theta - other.theta;
		double mulSins = Math.sin(this.phi) * Math.sin(other.phi);
		double mulCoss = Math.cos(this.phi) * Math.cos(other.phi) * Math.cos(deltaTheta);
		double sumAll = mulSins + mulCoss;
		
		return Math.acos(sumAll);
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
