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

import java.util.logging.Logger;;

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
		    PhotoFactory.setInstance(new BikePhotoFactory());
		}
		
		return instance;
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
}
