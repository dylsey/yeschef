package com.yeschef.api.models;

public class RequestError {

	private Integer id;
	private String message;
	
	public RequestError(Integer id, String message) {
		this.id = id;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}