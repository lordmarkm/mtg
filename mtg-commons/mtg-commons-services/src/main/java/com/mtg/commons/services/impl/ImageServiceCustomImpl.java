package com.mtg.commons.services.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Image;
import com.mtg.commons.services.ImageService;
import com.mtg.commons.services.ImageServiceCustom;

@Transactional
public class ImageServiceCustomImpl implements ImageServiceCustom {

	private static Logger log = LoggerFactory.getLogger(ImageServiceCustomImpl.class);
	
	@Resource
	private Environment env;
	
	@Resource
	private ImageService images;
	
	private String folderpath;
	
	@PostConstruct
	public void ensurePath() {
		folderpath = env.getProperty("images.path");
		Validate.notEmpty(folderpath, "Images folder path not set!");
	}
	
	@Override
	public Image update(Image image, byte[] bytes, String extension) {
	    log.debug("Refreshing image. image={}", image);
	    
		Validate.notNull(image);
		
		if(null != image.getPath()) {
			deleteFile(image.getPath());
		}
		
		String filepath = null;
		while(null == filepath || new File(filepath).exists()) {
			filepath = folderpath + RandomStringUtils.randomAlphanumeric(10) + "." + extension;
		}
		
		try {
			saveFile(filepath, bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		image.setPath(filepath);
		
		return images.save(image);
	}

	@Override
	public File getFile(Long id) {
		Image img = images.findOne(id);
		return getFile(img);
	}
	
	@Override
	public File getFile(Image image) {
		Validate.notNull(image);
		sideloadIfNeeded(image);
		return new File(image.getPath());
	}
	
	private void deleteFile(String path) {
	    log.debug("Deleting image file. path={}", path);
	    if (null == path) {
	        log.warn("Image path not defined!");
	        return;
	    }
		File file = new File(path);
		if(file.exists()) {
			file.delete();
		}
	}
	
	private void saveFile(String path, byte[] data) throws IOException {
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(path));
			out.write(data);
			out.flush();
			
			log.debug("Written data to file. path={}", path);
		} finally {
			if(null != out) out.close();
		}
	}
	
	@Override
	public void sideloadIfNeeded(Image image) {
		
		if(null == image || image.getOriginalPath() == null) {
			log.debug("Can't get image, it has no source");
			return;
		}
		
		if(image.getPath() != null) {
			//no need to ensure image if it already exists
			//TODO check file exists here?
			return;
		}
		
		try {
		    log.debug("Loading image from original path. path={}", image.getOriginalPath());
			URL url = new URL(image.getOriginalPath());
			String format = getFormat(url);
			if(null == format) format = DEFAULT_FORMAT;

			BufferedImage newimage = ImageIO.read(url);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(newimage, format, baos);
			baos.flush();
			byte[] bytes = baos.toByteArray();
			baos.close();
			
			update(image, bytes, format);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getMimeType(URL url) throws IOException {
		URLConnection c = url.openConnection();
		return c.getContentType();
	}
	
	protected String getFormat(URL url) throws IOException {
		String type = getMimeType(url);
		if("image/jpeg".equals(type)) {
			return "jpeg";
		} else if("image/png".equals(type)) {
			return "png";
		} else {
			return null;
		}
	}

	@Override
	public void excise(Image image) {
		if(null == image || null == image.getPath()) return;
		
		File f = new File(image.getPath());
		if(f.exists()) {
			f.delete();
		}
		images.delete(image);
	}

    @Override
    public Image refresh(Long id) {
        Image image = images.findOne(id);
        Validate.notNull(image);
        
        deleteFile(image.getPath());
        image.setPath(null);
        
        sideloadIfNeeded(image);
        
        return image;
    }

}
