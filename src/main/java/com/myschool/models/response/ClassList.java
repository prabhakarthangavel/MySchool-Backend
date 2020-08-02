package com.myschool.models.response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ClassList {
	
	public ClassList(List<String> section, List<String> subjects) {
		this.section = section;
		this.subjects = subjects;
	}
	private List<String> section;
	private List<String> subjects;
	public List<String> getSection() {
		return section;
	}
	public void setSection(List<String> section) {
		this.section = section;
	}
	public List<String> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}
}
