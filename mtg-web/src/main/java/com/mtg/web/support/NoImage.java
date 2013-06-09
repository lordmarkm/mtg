package com.mtg.web.support;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class NoImage {

	@Autowired
	private ApplicationContext ctx;
	
	private static byte[] NO_IMAGE = null;
	
	public byte[] get() throws IOException {
		if(null == NO_IMAGE) {
			loadImage();
		}
		return NO_IMAGE;
	}
	
	private void loadImage() throws IOException {
		Resource img = ctx.getResource("classpath:no-img.jpg");
		BufferedImage bimg = ImageIO.read(img.getFile());
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(bimg, "jpg", baos );
		byte[] imageInByte=baos.toByteArray();
		NO_IMAGE = imageInByte;
	}
	
}
