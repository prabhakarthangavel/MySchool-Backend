package com.myschool.models.response;

public class PrimaryPerformance {
	private String exam;
	private int tamil;
	private int english;
	private int maths;
	private int social;
	private int science;
	private int total;
	
	
	
//	"[{\"account number\":234349,\"share account\":435345},{\"account number\":234349,\"share account\":64564}]";
	
	public int getTotal() {
		total = tamil+english+maths+social+science;
		return total;
	}
	public String getExam() {
		return exam.toUpperCase();
	}
	public void setExam(String exam) {
		if(exam.equalsIgnoreCase("term 1")) {
			this.exam = "T1";
		}else if(exam.equalsIgnoreCase("quearterly")) {
			this.exam = "QY";
		}else if(exam.equalsIgnoreCase("term 2")) {
			this.exam = "T2";
		}else if(exam.equalsIgnoreCase("halfyearly")) {
			this.exam = "HY";
		}else if(exam.equalsIgnoreCase("term 3")) {
			this.exam = "T3";
		}else if(exam.equalsIgnoreCase("final exam")) {
			this.exam = "FE";
		}
	}
	public int getTamil() {
		return tamil;
	}
	public void setTamil(int tamil) {
		this.tamil = tamil;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMaths() {
		return maths;
	}
	public void setMaths(int maths) {
		this.maths = maths;
	}
	public int getSocial() {
		return social;
	}
	public void setSocial(int social) {
		this.social = social;
	}
	public int getScience() {
		return science;
	}
	public void setScience(int science) {
		this.science = science;
	}
	
}
