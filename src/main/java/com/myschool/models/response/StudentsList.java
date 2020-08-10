package com.myschool.models.response;

public class StudentsList {
	private int clas;
	private String first_name;
	private String last_name;
	private String section;
	private int student_id;
	public int getClas() {
		return clas;
	}
	public void setClas(int clas) {
		this.clas = clas;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
}
