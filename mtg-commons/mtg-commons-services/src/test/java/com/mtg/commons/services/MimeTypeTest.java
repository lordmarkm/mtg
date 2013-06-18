package com.mtg.commons.services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.mtg.commons.services.impl.ImageServiceCustomImpl;

public class MimeTypeTest {

	ImageServiceCustom service = new ImageServiceCustomImpl();
	
	//@Test
	public void testMimeTypes() throws MalformedURLException, IOException {
		String url = "http://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/32/Vietnam-Flag-icon.png";
		String mtype = service.getMimeType(new URL(url));
		assertEquals("image/png", mtype);
	
		url = "http://8e8460c4912582c4e519-11fcbfd88ed5b90cfb46edba899033c9.r65.cf1.rackcdn.com/sales/cardscans/MAGTIM/akroma,_angel_of_wrath.jpg";
		mtype = service.getMimeType(new URL(url));
		assertEquals("image/jpeg", mtype);
	}
	
}
