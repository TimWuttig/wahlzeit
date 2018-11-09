package org.wahlzeit.model;

public class BikePhoto extends Photo{
	
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
	}
	
	/**
	 * @methodtype constructor
	 */
	public BikePhoto(PhotoId myId) {
		id = myId;

		incWriteCount();
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
