package com.mtg.commons.models.dtos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.mtg.commons.models.collections.Bundle;

/**
 * A DTO for Bundles, primarily intended for simple card list
 * @author Mark
 */
public class BundleDto {

	private Bundle bundle;
	
	public BundleDto(Bundle bundle) {
		this.bundle = bundle;
	}

	public static List<BundleDto> toDtos(List<Bundle> bundles) {
	
		List<BundleDto> dtos = new ArrayList<BundleDto>();
		for (Bundle bundle : bundles) {
			dtos.add(new BundleDto(bundle));
		}
		
		return dtos;
	}
	
	public String getCardName() {
		return bundle.getCard().getName();
	}
	
	public long getCardId() {
		return bundle.getCard().getId();
	}
	
	/**
	 * Delegate methods
	 */
	public String getNote() {
		return bundle.getNote();
	}

	public int getCount() {
		return bundle.getCount();
	}

	public DateTime getLastModified() {
		return bundle.getLastModified();
	}

}
