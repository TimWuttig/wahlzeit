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

public class SphericCoordinate extends AbstractCoordinate{
	private double phi;
	private double theta;
	private double radius;
	
	/**
	 * @param phi: azimuthal angle; theta: polar angle; radius: radial distance 
	 * @methodtype Constructor
	 * if radius is 0 then phi and theta will be set on 0 too
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
	
	protected double doGetCartesianDistance(CartesianCoordinate other) {
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
	 * calculates the central angle
	 * @param other
	 * @return the central angle if its possible to calculate else NaN is returned
	 */
	protected double doGetCentralAngle(SphericCoordinate other) {
		//test if radius is null then no angle exist
		if(other.getRadius() == 0 || this.getRadius() == 0) return Double.NaN;
		
		double deltaTheta = this.theta - other.theta;
		double mulSins = Math.sin(this.phi) * Math.sin(other.phi);
		double mulCoss = Math.cos(this.phi) * Math.cos(other.phi) * Math.cos(deltaTheta);
		double sumAll = mulSins + mulCoss;
		
		return Math.acos(sumAll);
	}
}
