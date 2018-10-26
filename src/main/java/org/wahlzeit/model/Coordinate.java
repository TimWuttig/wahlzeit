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

public class Coordinate {
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
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Calculates the direct distance between this coordinate object and target
	 * @param target: another coordinate object
	 * @return the direct distance
	 */
	protected double getDistance(Coordinate target) {
		//get vector between this and target
		double deltaX = this.x - target.x;
		double deltaY = this.y - target.y;
		double deltaZ = this.z - target.z;
		
		//calculate length of distance vector		
		double absSquareDeltasSum = Math.abs(deltaX * deltaX) + Math.abs(deltaY * deltaY) + Math.abs(deltaZ * deltaZ);
		double distance = Math.sqrt(absSquareDeltasSum);
		
		return distance;
	}
	
	/**
	 * Checks if this coordinate object is equal to other using method getDistance()
	 * @param other: another Coordinate object
	 * @return if they are equal true else false 
	 */
	protected boolean isEqual(Coordinate other) {
		double distanceToOther = getDistance(other);
		
		return distanceToOther == 0? true : false;
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
}
