/*
 * Location
 * 
 * 1.0
 * 
 * 26.10.2018
 * 
 * Copyright (c) by Tim Wuttig
 */

package org.wahlzeit.model;

public class Location {
	
	/**
	 * Every Instance of Location must have a pointer of a Coordinate object
	 */
	public Coordinate coordinate;
	
	/**
	 * Gets the coordinates of the location Instance and create these as a new Coordinate object 
	 * because variable coordinate must be initialized
	 */
	public Location(double x, double y, double z) {
		coordinate = CartesianCoordinate.get(x,y,z);
	}
	
	/**
	 * Overrides and forwards equals() to isEqual() from class Coordinate
	 * @param other Object which must be tested
	 * @return if they are equal true else false
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Location) {
			Location otherLocation = (Location) other;
			return coordinate.equals(otherLocation.coordinate);
		}
		
		return false;
	}

}
