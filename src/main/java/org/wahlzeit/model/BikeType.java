package org.wahlzeit.model;

import java.util.HashMap;

import org.wahlzeit.utils.PatternInstance;

@PatternInstance(
		patternName = "Value Object",
		participants = {
				"ValueObject"
		}
)
public class BikeType {

	private String typeName;
	private BikeType supertype;
	private static HashMap<String, BikeType> instances = new HashMap<>();
	
	private BikeType(String typeName, BikeType supertype) {
		this.typeName = typeName;
		this.supertype = supertype;
	}

	/**
	 * If type exists it will be returned else a new BikeType Object  will be created
	 * @param typeName: not empty string or null
	 * @param supertype: can be null
	 * @return BikeType object with the specific TypeName
	 * @throws IllegalArgumentException
	 */
	public static BikeType getInstance(String typeName) throws IllegalArgumentException{
		checkTypeName(typeName);
		
		if(!instances.containsKey(typeName))
			instances.put(typeName, new BikeType(typeName, null));
			
		return instances.get(typeName);
	}
	
	/**
	 * @methodtype assert
	 * @param typeName 
	 */
	private static void checkTypeName(String typeName) {
		if(typeName == null)
			throw new IllegalArgumentException("typeName cannot be null");
		if(typeName.length() == 0)
			throw new IllegalArgumentException("typeName cannot be a empty string");
	}
	
	/**
	 * replace old object with a new biketype "extends" from supertype
	 * @methodtype setter
	 * @param supertype: can be null
	 */
	public BikeType setSupertype(BikeType supertype) {
		instances.replace(typeName, new BikeType(typeName, supertype));
		
		return instances.get(typeName);
	}

	/**
	 * @methodtype getter
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @methodtype getter
	 */
	public BikeType getSupertype() {
		return supertype;
	}
	
	/**
	 * @methodtype query
	 * @return true if object is a subType of another BikeType object
	 */
	public boolean isSubtype() {
		return supertype != null;
	}
}
