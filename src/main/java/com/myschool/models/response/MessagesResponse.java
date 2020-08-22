package com.myschool.models.response;

import java.util.Date;

public class MessagesResponse {
	private String message;
	private Date created_on;

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
