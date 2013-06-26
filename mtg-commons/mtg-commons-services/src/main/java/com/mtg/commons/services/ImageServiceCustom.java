package com.mtg.commons.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.mtg.commons.models.Image;

/**
 * Save, retrieve from filesystem
 * folderpath MUST be an absolute path
 * @author Mark
 */

public interface ImageServiceCustom {

	String DEFAULT_FORMAT = "png";
	
	Image update(Image image, byte[] bytes, String extension);
	
	File getFile(Long id);
	File getFile(Image image);
	
	/**
	 * Sideload the image from image.originalPath to local
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	void sideloadIfNeeded(Image image);
	
	String getMimeType(URL url) throws IOException;
	
	/**
	 * Delete the image file, then the image
	 * @param image
	 */
	void excise(Image image);
	
	/**
	 * Reload from original source
	 */
    Image refresh(Long id);

}
