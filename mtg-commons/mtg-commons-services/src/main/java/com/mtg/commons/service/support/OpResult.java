package com.mtg.commons.service.support;

import static com.mtg.commons.service.support.OpResult.OpStatus.*;

import org.springframework.core.style.ToStringCreator;

/**
 * Kind of like JSON, but only for internal ops
 * @author Mark
 */
public class OpResult {

	public enum OpStatus {
		OK, ERROR, FORBIDDEN
	}
	
	private OpStatus status;
	private String message;
	
	public static OpResult ok(String message) {
		return new OpResult(OK, message);
	}
	
	public static OpResult ok() {
		return ok(null);
	}

	public static OpResult error(String message) {
		return new OpResult(ERROR, message);
	}
	
	public static OpResult error() {
		return error(null);
	}
	
	public static OpResult forbidden(String message) {
		return new OpResult(FORBIDDEN, message);
	}
	
	public static OpResult forbidden() {
		return forbidden(null);
	}
	
	private OpResult(OpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("status", status)
			.append("message", message)
			.toString();
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OpStatus getStatus() {
		return status;
	}

	public void setStatus(OpStatus status) {
		this.status = status;
	}
	
}
