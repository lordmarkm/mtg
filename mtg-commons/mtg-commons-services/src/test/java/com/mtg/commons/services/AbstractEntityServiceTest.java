package com.mtg.commons.services;

import org.junit.Test;

public class AbstractEntityServiceTest {

	AbstractEntityService service = new AbstractEntityService() {
		
	};
	
	@Test
	public void testUrlFragment() {
		String name = "M14 midnight prerelease's deck";
		System.out.println(service.urlfragment(name));
	}
}
