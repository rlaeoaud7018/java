package com.office.example;

public class Constant {
	
	public static void main(String[] args) {
		
		int num1 = 10;		   	// 변수 선언 및 초기화
		final int NUM2 = 20;	  // 함수 선언 및 초기화
		
		// final int NUM2 = 20;	  // 함수 선언 및 초기화
				//	상수 	|  리터럴 상수 = 데이터 자체
		
		
		System.out.println("num1: " + num1);		// 10
		System.out.println("NUM2: " + NUM2);		// 20
		
		num1 = 100;
		NUM2 = 200;
		
		/*
		 * java에서는 딱 한번은 초기화를 할 수 있다.
		 */
		
		final int NUM3;
		
		NUM3 = 300;
		
		NUM3 = 3000;
		
		
		// 상수는 대문자로 표기할 것
		final int PENCIL_PRICE = 1000;
		final float STUDENT_HEIGHT = 175.5f;
		final char GRADE = 'A';
		final String COUNTRY = "Korea";
		
		System.out.println("PENCIL_PRICE: %d원" + PENCIL_PRICE);
		System.out.println("STUDENT_HEIGHT: %.1fcm" + STUDENT_HEIGHT);
		System.out.println("GRADE: %c등급" + GRADE);
		System.out.println("COUNTRY: %s \n" + COUNTRY);
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
