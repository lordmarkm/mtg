package com.mtg.parser.services.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.mtg.commons.models.Image;
import com.mtg.commons.models.locations.Country;
import com.mtg.commons.services.AbstractEntityService;
import com.mtg.commons.services.CountryService;

/**
 * Takes country names and icons from iconarchive.com icon set
 * @author Mark
 */
@Component
public class IconsArchiveCountryParser extends AbstractEntityService {

	private static Logger log = LoggerFactory.getLogger(IconsArchiveCountryParser.class);
	
	private final static String BASE_URL = "http://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/32/";
	private final static String AFFIX = "-icon.png";
	
	@Autowired
	private CountryService countries;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	public void refreshCountries() throws IOException {
		String parseString = env.getProperty("reparse.countries");
		Validate.notNull(parseString);
		Boolean parse = Boolean.parseBoolean(parseString);
		if(!parse) return;
		
		countries.exciseAll();
		
		Resource iconsTxtResource = appContext.getResource("classpath:icon-names.txt");
		BufferedReader reader = new BufferedReader(new FileReader(iconsTxtResource.getFile()));
		String iconString = reader.readLine();
		reader.close();
		
		String[] iconNames = iconString.split(",");
		
		for(String iconName : iconNames) {
			createCountry(iconName);
		}
	}
	
	protected void createCountry(String iconName) {
		Validate.notNull(iconName);
		Validate.isTrue(iconName.length() > 0);
		
		String name = extractCountryName(iconName);
		
		//Country country = countries.findByName(name); //always create now, reparse.countries property controls parse or not
		log.info("Creating country. source name={}", iconName);
		
		Image flag = new Image();
		flag.setOriginalPath(BASE_URL + iconName + AFFIX);
		//images.sideloadIfNeeded(flag); //sideload when image is requested instead, as there are over 200 countries
		
		Country country = new Country();
		country.setName(name);
		country.setFlag(flag);
		country.setUrlFragment(urlfragment(name));
		countries.save(country);
	}
	
	protected String extractCountryName(String iconName) {
		return iconName.replaceAll("-Flag", "").replaceAll("-", " ");
	}
}
