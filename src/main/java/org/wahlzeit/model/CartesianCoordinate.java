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

import java.util.HashMap;
import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate{
	/**
	 * variables for the Cartesian coordinates
	 */
	private double x;
	private double y;
	private double z;
	private static HashMap<Integer, CartesianCoordinate> instances = new HashMap<Integer, CartesianCoordinate>();
	
	/**
	 * Constructor sets the attributes x,y,z on the specific values
	 * @param x stands for the x-Coordinate
	 * @param y stands for the y-Coordinate
	 * @param z stands for the z-Coordinate
	 */
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * When CartesianCoordinate already exists, this will be returned. 
	 * If it not exists a new CartesianCoordinate will be inserted into instances and will be returned as result 
	 * @param x stands for the x-Coordinate
	 * @param y stands for the y-Coordinate
	 * @param z stands for the z-Coordinate
	 */
	public static CartesianCoordinate get(double x, double y, double z) throws IllegalArgumentException{
		isValidDouble(x);
		isValidDouble(y);
		isValidDouble(z);
		
		
		CartesianCoordinate result = doGet(x, y, z);
		
		isValidCoordinate(result);
		
		return result;
	}
	
	/**
	 * @methodtype helper
	 * @methodproperties primitive
	 * @param key: a array specifying the x,y,z-value of the CartesianCoordinate
	 * @return the CartesianCoordinate with the specific key values
	 */
	private static CartesianCoordinate doGet(double x, double y, double z) {
		int key = Objects.hash(x, y, z);
		
		if(!instances.containsKey(key)) 
			instances.put(key, new CartesianCoordinate(x, y, z));
		return instances.get(key);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY(), getZ());
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
	 * @param other: must not to be null
	 * @return CartesianDistance between two CartesianCoordinate objects
	 */
	@Override
	protected double doGetCartesianDistance(CartesianCoordinate other) {
		//Build square deltas between the Coordinates parameters
		double squareDeltaX = Math.pow(this.x - other.getX(),2);		
		double squareDeltaY = Math.pow(this.y - other.getY(),2);
		double squareDeltaZ = Math.pow(this.z - other.getZ(),2);
		
		double squareDeltasSum = squareDeltaX + squareDeltaY + squareDeltaZ;
		
		return Math.sqrt(squareDeltasSum);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#asCartesianCoordinate()
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#asSphericCoordinate()
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
		
		//Calculates theta but if x and y are 0 then theta is set to default value zero
		double theta = 0;
		if(x != 0 || y != 0) {
			theta = (Math.PI / 2) - Math.atan(z / (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))));
		}
		
		double phi = Math.atan2(y, x);
		return SphericCoordinate.get(radius, theta, phi);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#doGetCentralAngle(org.wahlzeit.model.SphericCoordinate)
	 */
	@Override
	protected double doGetCentralAngle(SphericCoordinate other) {
		SphericCoordinate selfAsSpheric = asSphericCoordinate();
		
		return selfAsSpheric.getCentralAngle(other);
	}
}
