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


public class CartesianCoordinate extends AbstractCoordinate{
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
	 * @methodetype query
	 * @methodproperties
	 * @return CartesianDistance between two CartesianCoordinate objects
	 */
	@Override
	protected double doGetCartesianDistance(CartesianCoordinate other) throws ArithmeticException{
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
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
		
		//Calculates theta but if x and y are 0 then theta is set to default value zero
		double theta = 0;
		if(x != 0 || y != 0) {
			theta = (Math.PI / 2) - Math.atan(z / (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))));
		}
		
		double phi = Math.atan2(y, x);
		return new SphericCoordinate(radius, theta, phi);
	}
	
	protected double doGetCentralAngle(SphericCoordinate other) {
		SphericCoordinate selfAsSpheric = asSphericCoordinate();
		
		return selfAsSpheric.getCentralAngle(other);
	}
}
