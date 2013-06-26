package com.mtg.web.controller.impl;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.mtg.commons.models.Image;
import com.mtg.commons.services.ImageService;
import com.mtg.web.controller.ImageController;
import com.mtg.web.dto.JSON;

@Component
public class ImageControllerImpl implements ImageController {

	private static Logger log = LoggerFactory.getLogger(ImageControllerImpl.class);
	
	@Resource
	private ImageService images;
	
	@Override
	public ResponseEntity<byte[]> image(@PathVariable Long id) throws IOException {
		log.debug("Service image. id={}", id);
		File image = images.getFile(id);
		
		if(image.exists()) {
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(image), HttpStatus.OK);
		}
		return null;
	}

    @Override
    public JSON refresh(@PathVariable Long id) {
        log.debug("Image refresh requested. image id={}", id);
        
        Image image = images.refresh(id);
        return JSON.ok().put("image", image);
    }
	
}
