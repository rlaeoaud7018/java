package com.office.example;

public class Example001 {

	public static void main(String[] args) {
		
		/*
		 * 객체 초기화
		 * 클래스에서 생성된 객체의 초기화를 담당하는 메서드를 생성자라고 함.
		 */
		
		
		Student student01 = new Student("홍길동", 1, 20, "computer");
		student01.printInfo();
		
		Student student02 = new Student("박찬호", 1, 20, "computer");
		student02.printInfo();
		
		Student student03 = new Student("박세리", 1, 20, "computer");
		student03.printInfo();
		
		new Student(null, 0, 0, null)
	
	}

}
