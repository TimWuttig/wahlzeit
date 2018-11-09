/**
 * BikePhotoManager
 * 
 * 1.0
 * 
 * 09.11.2018
 * 
 * Copyright (c) by Tim Wuttig
 */

package org.wahlzeit.model;

import java.util.logging.Logger;

public class BikePhotoManager extends PhotoManager {
	
	protected static final BikePhotoManager instance = new BikePhotoManager();
	
	private static final Logger log = Logger.getLogger(BikePhotoManager.class.getName());
	
	/**
	 * @constructor
	 */
	public BikePhotoManager() {
		photoTagCollector = BikePhotoFactory.getInstance().createPhotoTagCollector();
	}
	
	
}
