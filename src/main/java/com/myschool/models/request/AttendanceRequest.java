package com.myschool.models.request;

public class AttendanceRequest {
	private int year;
	private String student_id;
	private String month;
	private int working_days;
	private int present;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getWorking_days() {
		return working_days;
	}
	public void setWorking_days(int working_days) {
		this.working_days = working_days;
	}
	public int getPresent() {
		return present;
	}
	public void setPresent(int present) {
		this.present = present;
	}
}
