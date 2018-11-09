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

public class BikePhoto extends Photo{
	
	/*
	 * Default values for domain attributes	
	 */
	private static final String DEFAULT_MODEL = "Pretty cool Motorcycle";
	private static final String DEFAULT_BRANDE = "Bambusbikes Inc.";
	private static final int DEFAULT_HORSEPOWER = 199;
	
	/*
	 * Attributes for my domain
	 */	
	private String model;
	private String brande;
	private int horsepower;
	
	protected PhotoId id = null;
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto () {
		id = PhotoId.getNextId();
		incWriteCount();
		
		setAttr(DEFAULT_MODEL,DEFAULT_BRANDE,DEFAULT_HORSEPOWER);
	}
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto(PhotoId myId) {
		id = myId;

		incWriteCount();
		
		setAttr(DEFAULT_MODEL,DEFAULT_BRANDE,DEFAULT_HORSEPOWER);
	}
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto(String model, String brande, int horsepower) {
		BikePhoto createdBikePhoto = new BikePhoto();
		
		setAttr(model,brande,horsepower);
	}
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto(PhotoId myId, String model, String brande, int horsepower) {
		BikePhoto createdBikePhoto = new BikePhoto(myId);
		
		setAttr(model,brande,horsepower);
	}
	
	/**
	 * @methodtype initializer
	 * @methodproperties composed
	 */
	public void setAttr(String model, String brande, int horsepower) {
		setModel(model);
		setBrande(brande);
		setHorsepower(horsepower);
	}
	
	/**
	 * @methodtype getter
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * @methodtype getter
	 */
	public String getBrande() {
		return brande;
	}
	
	/**
	 * @methodtype getter
	 */
	public int getHorsepower() {
		return horsepower;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setBrande(String brande) {
		this.brande = brande;
	}
	
	/**
	 * @methodtype setter
	 * @exception throws IllegalArgumentexception if parameter is negative
	 */
	public void setHorsepower(int horsepower) {
		if(horsepower < 0)throw new IllegalArgumentException("horsepower must be positive to make sense");
		this.horsepower = horsepower;
	}
}
