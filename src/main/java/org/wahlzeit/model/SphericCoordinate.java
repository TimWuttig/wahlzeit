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

import java.util.HashMap;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{
	private static HashMap<Integer, SphericCoordinate> instances = new HashMap<Integer, SphericCoordinate>();
	
	private double phi;
	private double theta;
	private double radius;
	
	/**
	 * @param phi: azimuthal angle; theta: polar angle; radius: radial distance 
	 * @methodtype Constructor
	 * if radius is 0 then phi and theta will be set on 0 too
	 */
	private SphericCoordinate(double radius, double theta, double phi) {
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
	
	public static SphericCoordinate get(double radius, double theta, double phi) throws IllegalArgumentException {
		//Check parameters valid?
		if(radius < 0)throw new IllegalArgumentException("radius must be positive");
		isValidDouble(radius);
		isValidDouble(phi);
		isValidDouble(theta);
		
		SphericCoordinate result = doGet(radius, theta, phi);
		
		isValidCoordinate(result);
		
		return result;
	}
	
	private static SphericCoordinate doGet(double radius, double theta, double phi) {
		int key = Objects.hash(radius, phi, theta);
		
		if(!instances.containsKey(key))
			instances.put(key, new SphericCoordinate(radius, theta, phi));
		
		return instances.get(key);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRadius(), getPhi(), getTheta());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#asCartesianCoordinate()
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = radius * Math.sin(theta) * Math.cos(phi);
		double y = radius * Math.sin(theta) * Math.sin(phi);
		double z = radius * Math.cos(theta);
		
		return CartesianCoordinate.get(x, y, z);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#doGetCartesianDistance(org.wahlzeit.model.CartesianCoordinate)
	 * uses the method of the CartesianCoordinate class
	 */
	@Override
	protected double doGetCartesianDistance(CartesianCoordinate other) {
		//calculate cartesianCoordinate to use the getCartesianDistance() implementation form class CartesianCoordinate
		CartesianCoordinate cartesianSelf = this.asCartesianCoordinate();
		
		return cartesianSelf.getCartesianDistance(other);
	}

	/*
	 * (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#asSphericCoordinate()
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.wahlzeit.model.AbstractCoordinate#doGetCentralAngle(org.wahlzeit.model.SphericCoordinate)
	 */
	@Override
	protected double doGetCentralAngle(SphericCoordinate other) {
		double deltaTheta = this.theta - other.theta;
		double mulSins = Math.sin(this.phi) * Math.sin(other.phi);
		double mulCoss = Math.cos(this.phi) * Math.cos(other.phi) * Math.cos(deltaTheta);
		double sumAll = mulSins + mulCoss;
		
		return Math.acos(sumAll);
	}
}
