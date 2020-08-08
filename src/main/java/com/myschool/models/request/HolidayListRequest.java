package com.myschool.models.request;

import java.util.Date;
import java.util.List;

public class HolidayListRequest {
	private List<String> event;
	private List<Date> holiday;
	public List<String> getEvent() {
		return event;
	}
	public void setEvent(List<String> event) {
		this.event = event;
	}
	public List<Date> getHoliday() {
		return holiday;
	}
	public void setHoliday(List<Date> holiday) {
		this.holiday = holiday;
	}
}
