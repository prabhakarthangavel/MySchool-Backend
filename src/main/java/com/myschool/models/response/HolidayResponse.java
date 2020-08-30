package com.myschool.models.response;

import java.util.Date;

public class HolidayResponse {
	private String event;
	private Date holiday;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getHoliday() {
		return holiday;
	}

	public void setHoliday(java.util.Date date) {
		this.holiday = date;
	}
}
