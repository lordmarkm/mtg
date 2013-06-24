package com.mtg.web.dto;

import java.util.HashMap;

import com.mtg.commons.service.support.OpResult;

public class JSON extends HashMap<String, Object> {
	private static final long serialVersionUID = -8234082507884717784L;
	
	/**
	 * @return status 200 - Ok
	 */
	public static JSON ok(String message) {
		return new JSON().status("200").message(message);
	}
	
	public static JSON ok() {
		return new JSON().status("200").message("OK");
	}
	
	public static JSON error() {
		return new JSON().status("500");
	}
	
	public static JSON error(String message) {
	    return new JSON().status("500").message(message);
	}
	
	/**
	 * @return status 404 - Not found
	 */
	public static JSON status404() {
		return new JSON().status("404").message("Not found");
	}

	/**
	 * @return status 500 - Internal Error
	 */
	public static JSON status500(Exception e) {
		return new JSON().status("500").message(e.getClass() + ": " + e.getMessage());
	}
	
	/**
	 * @return status 418
	 */
	public static JSON teapot() {
		return new JSON().status("418");
	}
	
	/**
	 * @return status 409 - Conflict
	 */
	public static JSON status409(String... message) {
		JSON response = new JSON().status("409");
		if(message.length > 0) response.message(message[0]);
		return response;
	}
	
	public JSON status(String status) {
		super.put("status", status);
		return this;
	}
	
	public JSON message(String message) {
		super.put("message", message);
		return this;
	}
	
	public JSON put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public static JSON translate(OpResult result) {
		switch(result.getStatus()) {
		case OK:
			return JSON.ok(result.getMessage());
		case ERROR:
		case FORBIDDEN:
			return JSON.error(result.getMessage());
		default:
			throw new IllegalArgumentException("Unhandled operation result: " + result);
		}
	}
}
