/**
 * BikePhotoFactory
 * 
 * 1.0
 * 
 * 09.11.2018
 * 
 * Copyright (c) by Tim Wuttig
 */

package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.PatternInstance;

import java.util.logging.Logger;;

@PatternInstance(
		patternName = "Factory Method",
		participants = {
				"Subclass"
		}
)
public class BikePhotoFactory extends PhotoFactory{
	
	private static final Logger log = Logger.getLogger(BikePhotoFactory.class.getName()); 
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static BikePhotoFactory instance = null;

	/**
	 * Public singelton access method from BikePhotoFactory.
	 * Overrides getInstance() from superclass
	 * @methodtype getter
	 */
	public static synchronized PhotoFactory getInstance() {
		if(instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic PhotoFactory").toString());
		    BikePhotoFactory.setInstance(new BikePhotoFactory());
		}
		
		return instance;
	}
		
	/**
	 * @methodtype setter
	 * @exception throws IllegalStateException if an instance already exists
	 */
	public static synchronized void setInstance(BikePhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		if (photoFactory == null)
			throw new IllegalArgumentException("photoFactory has not to be null");
		
		instance = photoFactory;
	}
	
	/**
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new BikePhoto();
	}
	
	/**
	 * Creates new bikephoto with specific id
	 * @methodtype factory
	 */
	public Photo createPhoto(PhotoId id) {
		return new BikePhoto(id);
	}
	
	/**
	 * @methodtype factory
	 */
	public Photo createPhoto(String model, String brande, int horsepower) {
		return new BikePhoto(model, brande, horsepower);
	}
	
	/**
	 * Creates new bikephoto with specific id
	 * @methodtype factory
	 */
	public Photo createPhoto(PhotoId id, String model, String brande, int horsepower) {
		return new BikePhoto(id, model, brande, horsepower);
	}
}
