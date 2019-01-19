package org.wahlzeit.model;

public class Bike {
	
	private BikeType type;
	private String model;
	private String brande;
	private int horsepower;
	
	
	/**
	 * Creates new Bike Instance with the specific type
	 * @param type: not null
	 * @param model: not null or empty string
	 * @param brande: not null or empty string
	 * @param horsepower: between 1 and 20.000
	 * @throws IllegalArgumentException
	 */
	public Bike(BikeType type, String model, String brande, int horsepower) throws IllegalArgumentException{
		checkType(type);
		this.type = type;
		
		checkString(model);
		this.model = model;
		
		checkString(brande);
		this.brande = brande;
		
		checkHorsepower(horsepower);
		this.horsepower = horsepower;
	}
	
	/**
	 * @methodtype assert
	 * @param horsepower
	 */
	private void checkHorsepower(int horsepower) {
		if(horsepower <= 0 || horsepower > 20000)
			throw new IllegalArgumentException("horsepower must be between 1 and 20.000");
	}

	/**
	 * @methodtype assert
	 * @param string
	 */
	private void checkString(String string) {
		if(string == null)
			throw new IllegalArgumentException("typeName cannot be null");
		if(string.length() == 0)
			throw new IllegalArgumentException("typeName cannot be a empty string");
	}

	/**
	 * @methodtype assert
	 */
	private void checkType(BikeType type) {
		if(type == null)
			throw new IllegalArgumentException("type cannot be null");
	}
	
	/**
	 * @methodtype getter
	 */
	public BikeType getType() {
		return type;
	}

	/**
	 * @methodtype getter
	 */
	public String getModel() {
		return model;
	}

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
	 * @param newType
	 * @throws IllegalArgumentException
	 */
	public void setType(BikeType newType) throws IllegalArgumentException{
		checkType(newType);
		
		this.type = newType;
	}

	/**
	 * @methodtype setter
	 * @param newHorsepower
	 * @throws IllegalArgumentException
	 */
	public void setHorsepower(int newHorsepower) throws IllegalArgumentException{
		checkHorsepower(newHorsepower);
		
		this.horsepower = newHorsepower;
	}
}
