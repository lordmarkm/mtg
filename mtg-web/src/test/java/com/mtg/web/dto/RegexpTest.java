package com.mtg.web.dto;

import org.junit.Test;

import com.mtg.web.controller.GenericController;

public class RegexpTest {

	private class SecretController extends GenericController {
		public String basic(String s) {
			return super.basic(s);
		}
	}
	
	@Test
	public void testNewlineReplace() {
		SecretController c = new SecretController();
		System.out.println(c.basic("a\n\n\na\n\na"));
	}
}
