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
