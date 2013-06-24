package com.mtg.parser.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Resource;

import com.mtg.commons.services.CountryService;
import com.mtg.parser.services.impl.IconsArchiveCountryParser;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {CommonsServicesConfig.class, ParserConfig.class})
public class IconsArchiveCountryParserTest {

	String icodir = "C:/Users/Mark/Desktop/dev/flags";
	String target = "C:/Users/Mark/Desktop/dev/icon-names.txt";
	
	@Resource
	private CountryService countries;
	
	@Resource
	private IconsArchiveCountryParser parser;
	
//	@Test
	public void testInit() {
		assertNotNull(parser);
		assertTrue(countries.count() > 0);
	}
	
//	@Test
	public void makefile() throws IOException {
		File dir = new File(icodir);
		assertTrue(dir.exists());
		assertTrue(dir.isDirectory());
		
		StringBuilder names = new StringBuilder();
		for(File ico : dir.listFiles()) {
			if(names.length() != 0) {
				names.append(",");
			}
			names.append(ico.getName());
		}
		System.out.println(names);
		
		FileWriter writer = new FileWriter(new File(target));
		writer.write(names.toString());
		writer.flush();
		writer.close();
	}
	
//	@Test
	public void testRegexpReplace() {
		File dir = new File(icodir);
		assertTrue(dir.exists());
		assertTrue(dir.isDirectory());
		
		StringBuilder names = new StringBuilder();
		for(File ico : dir.listFiles()) {
			String name = ico.getName();
			if(names.length() != 0) {
				names.append(",");
			}
			names.append(name.replaceAll("(-Flag.ico|.ico)", "").replaceAll("-", " "));
		}
		System.out.println(names);
	}
	
}
