package com.mtg.commons.service.support;

/**
 * Thrown for #21
 * The last moderator of a location may not leave that location until he grants mod status to at least one other location member
 * @author Mark
 */
public class LastModeratorCantLeaveException extends Throwable {
	
	public LastModeratorCantLeaveException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
