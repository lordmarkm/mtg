package com.mtg.commons.services;

import java.io.File;

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
	
}
