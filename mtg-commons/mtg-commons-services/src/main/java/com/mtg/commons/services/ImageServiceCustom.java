package com.mtg.commons.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.mtg.commons.models.Image;

/**
 * Save, retrieve from filesystem
 * folderpath MUST be an absolute path
 * @author Mark
 */

public interface ImageServiceCustom {

	Image update(Image image, byte[] bytes);
	
	File getFile(Long id);
	File getFile(Image image);
	
	/**
	 * Sideload the image from image.originalPath to local
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	void sideloadIfNeeded(Image image);
}
