package com.mtg.commons.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexpTest {

	//@Test
	public void test() {
		String cost = "{r/g}{r/g}{r/g}";
		
		Pattern p = Pattern.compile("\\{(.*?)\\}");
		Matcher m = p.matcher(cost);
		
		while(m.find()) {
			System.out.println(m.group(1));
		}
	}
	
	@Test
	public void test2() {
		String cost = "{r/g}{r/g}{r/g}UW";
		System.out.println(Arrays.asList(costArray(cost)));
		
		cost = "1UW";
		System.out.println(Arrays.asList(costArray(cost)));
	}
	
	private String[] costArray(String costString) {
		if(costString.contains("{")) {
			return parseComplex(costString);
		} else {
			return costString.split("(?!^)");
		}
	}
	
	private String[] parseComplex(final String costString) {
		String complexCostPattern = "\\{(.*?)\\}";
		String simpleCost = costString.replaceAll(complexCostPattern, "");
		
		StringBuilder simpleCostString = new StringBuilder();
		char[] simpleCostArray = simpleCost.toCharArray();
		for(char c : simpleCostArray) {
			simpleCostString.append("{" + c + "}");
		}
		
		String splitcost = costString.replace(simpleCost, simpleCostString.toString());
		
		Pattern p = Pattern.compile(complexCostPattern);
		Matcher m = p.matcher(splitcost);
		
		List<String> costArray = new ArrayList<String>();
		while(m.find()) {
			costArray.add(m.group(1));
		}
		return costArray.toArray(new String[costArray.size()]);
	}
}
