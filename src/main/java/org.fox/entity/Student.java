package org.fox.entity;

public class Student {
	private int sno;
	private String sname;
	private String sage;
	
	public Student() {
		
	}
	
	public Student(int sno, String sname, String sage) {
		this.sno = sno;
		this.sname = sname;
		this.sage = sage;
	}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSage() {
		return sage;
	}
	public void setSage(String sage) {
		this.sage = sage;
	}
	
	
}
