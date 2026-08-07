package com.office.example;

public class Student {

	//기본 생성자
	public Student() {
		System.out.println("++++++++++++++++++++++++++++");
	}
	
	// 멤버 필드
	int id;
	String name;
	
	// 메세드
	void insertRecord(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	void printInfo() {
		System.out.println("아이디: " + id);
		System.out.println("이름: " + name);
	}
	
}
