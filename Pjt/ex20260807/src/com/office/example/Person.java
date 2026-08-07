package com.office.example;

public class Person {

	// 속성
	String name;		// 멤버 변수 or 멤버 필드
	int regNum;			// 멤버 변수 or 멤버 필드
	int setAtrribute = setData();
	int setAtrribute1 = setData1();
			
	int setData() {
		String name = "홍길동";
		this.name = name;
		regNum = 1000;
		
		return 0;
	}
	
	int setData1() {
		name = "박찬호";
		regNum = 10000000;
		
		return 1;
	}
	
	
	// 기능
	void walk() {
		System.out.println("걷다.");
		
		int regNum;		// 변수
	}
	
	void walkForEng() {
		System.out.println("walk");
	}
	
	void run() {
		
	}
	
	
}
