package org.wahlzeit.model;

import java.util.LinkedList;
import java.util.logging.Logger;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.utils.PatternInstance;

@PatternInstance(
		patternName = "Singelton",
		participants = {
				"Singelton"
		}
)
public class BikeManager extends ObjectManager{
	private static final BikeManager instance = new BikeManager();
	private static final Logger log = Logger.getLogger(BikeManager.class.getName());
	
	private LinkedList<BikeType> typeInstances;
	private LinkedList<Bike> bikeInstances;
	
	/**
	 * @methodtype constructor
	 */
	public BikeManager() {
		typeInstances = new LinkedList<BikeType>();
		bikeInstances = new LinkedList<Bike>();
	}
	
	/**
	 * @methodtype getter
	 */
	public static final BikeManager getInstance() {
		return instance;
	}
	
	/**
	 * @methodtype query
	 * @param type:not null
	 * @return if type is managed
	 * @throws IllegalArgumentException
	 */
	public boolean hasType(BikeType type) throws IllegalArgumentException{
		checkNotNull(type);
		
		return typeInstances.contains(type);
	}
	
	/**
	 * @methodtype assert
	 * @param object
	 */
	private void checkNotNull(Object object) {
		if(object == null)
			throw new IllegalArgumentException("Parameter cannot be null");
	}

	/**
	 * Adds BikeType type to management if its not already managed
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public void addBikeType(BikeType type) throws IllegalArgumentException{
		checkNotNull(type);
		if(!hasType(type))		
			typeInstances.add(type);
	}
	
	public boolean hasBike(Bike bike) {
		checkNotNull(bike);
		return bikeInstances.contains(bike);
	}

	/**
	 * Adds Bike bike to management if its not already managed
	 * @param bike
	 * @throws IllegalArgumentException
	 */
	public void addBike(Bike bike) throws IllegalArgumentException{
		checkNotNull(bike);
		
		if(!hasBike(bike))
			bikeInstances.add(bike);
	}
}
