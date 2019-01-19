/**
 * BikePhoto
 * 
 * 1.0
 * 
 * 09.11.2018
 * 
 * Copyright (c) by Tim Wuttig
 */

package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class BikePhoto extends Photo{
	
	/*
	 * Default values for domain attributes	
	 */
	private static final BikeType DEFAULT_TYPE = BikeType.getInstance("default");
	private static final String DEFAULT_MODEL = "Pretty cool Motorcycle";
	private static final String DEFAULT_BRANDE = "Bambusbikes Inc.";
	private static final int DEFAULT_HORSEPOWER = 199;
	
	/*
	 * Attributes for my domain
	 */	
	private Bike bike;
	protected PhotoId id = null;
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto () {
		id = PhotoId.getNextId();
		incWriteCount();
		
		init(DEFAULT_TYPE, DEFAULT_MODEL,DEFAULT_BRANDE,DEFAULT_HORSEPOWER);
	}
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto(PhotoId myId) {
		id = myId;

		incWriteCount();
		
		init(DEFAULT_TYPE, DEFAULT_MODEL,DEFAULT_BRANDE,DEFAULT_HORSEPOWER);
	}
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto(String typename, String model, String brande, int horsepower) {
		BikePhoto createdBikePhoto = new BikePhoto();
		
		init(BikeType.getInstance(typename), model,brande,horsepower);
	}
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto(PhotoId myId, String typename, String model, String brande, int horsepower) {
		BikePhoto createdBikePhoto = new BikePhoto(myId);
		
		createdBikePhoto.init(BikeType.getInstance(typename), model,brande,horsepower);
	}
	
	/**
	 * @methodtype initializer
	 * @methodproperties composed
	 * @exception IllegalArgumentException
	 */
	public void init(BikeType type, String model, String brande, int horsepower) throws IllegalArgumentException{
		bike = new Bike(type, model, brande, horsepower);
		
		BikeManager manager = BikeManager.getInstance();
		manager.addBike(bike);
		manager.addBikeType(type);
	}
	
	/**
	 * @methodtype getter
	 */
	public Bike getBike() {
		return this.bike;
	}
}
