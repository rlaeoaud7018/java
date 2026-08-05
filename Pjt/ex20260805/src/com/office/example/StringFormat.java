package com.office.example;

import java.util.Scanner;

public class StringFormat {
	
	public static void main(String[] args) {
		
		/*
		 * 형식 문자열 출력
		 * 1. System.out.printf();
		 * 
		 */
		
		String name = "Mr. Hong";
		System.out.println("name: " + name);  //name: Mr. Hong
		
		System.out.printf("name: %s\n", name);
		System.out.printf("name: %s", name);
		System.out.println();
		System.out.printf("name: %s", name);
		
		
		// %s ---> 지정자
		/*
		 * 지정자 종류
		 * 1. %d	: 정수형 출력		*****
		 * 2. %f	: 실수형 출력		*****
		 * 3. %c	: 문자형 출력		*****
		 * 4. %b	: 논리형 출력		*****
		 * 5. %o	: 8진수형 출력
		 * 6. %x	: 16진수형 출력
		 * 7. %n	: 개행 == \n		***
		 */
		
		System.out.printf("이름 : %s%n", "Mr. Hong");
		System.out.printf("성구분 : %c%n", 'w');
		System.out.printf("나이 : %d%n", 20);
		System.out.printf("신장 : %f%n", 187.7);
		System.out.printf("체중 : %f%n", 85.5);
		System.out.printf("결혼유무 : %b%n", false);
		
		System.out.printf("10진수 %d > 8진수  %o\n", 123, 123);
		System.out.printf("10진수 %d > 8진수  %o\n", 123, 123);
		
		
		// 키와 몸무게는 188cm, 86.5kg이고, 결혼은 false 이다.
		System.out.printf("키와 몸무게는 %fcm, %fkg이고, 결혼은 %b 이다.", 188.0, 85.5, false);
		
		/*
		 * 지정자 옵션 사용하기
		 * 1. 소수점 아래 자리수를 지정한다.
		 */
		
		float rate = 3.131592f;
		System.out.printf("rate: " + rate);
		
		System.out.printf("rate: %.2f", + rate);
		System.out.printf("rate: %.4f", + rate);
		
		/*
		 * 2. 전체 출력 자리수를 지정하고, 오른쪽 정렬한다.
		 */
		
		int num1 = 1;
		int num2 = 12;
		int num  = 123;
		
		System.out.println();
		
		System.out.printf("num1: %d\n", num1);		//	: 1
		System.out.printf("num2: %d\n", num1);		//	: 1
		System.out.printf("num3: %d\n", num1);		//	: 1
		
		System.out.printf("num1: %3d\n", num1);		//	:    1
		System.out.printf("num2: %3d\n", num1);		//	:    1
		System.out.printf("num3: %3d\n", num1);		//	:    1
		
		System.out.printf("num1: %5d\n", num1);		//	:     1 
		System.out.printf("num2: %5d\n", num1);		//	:     1
		System.out.printf("num3: %5d\n", num1);		//	:     1
		
		System.out.printf("num1: %-5d\n", num1);	//	: 1
		System.out.printf("num2: %-5d\n", num1);	//	: 1
		System.out.printf("num3: %-5d\n", num1);	//	: 1
		
		System.out.printf("num1: %05d\n", num1);	//	: 00001
		System.out.printf("num2: %05d\n", num1);	//	: 00001
		System.out.printf("num3: %05d\n", num1);	//	: 00001
		
		double dou = 123.45678;
		System.out.printf("dou: %10.3f\n", dou);	//	:    123.457
		System.out.printf("dou: %010.3f\n", dou);	//	: 000123.457
		System.out.printf("dou: %-10.3f\n", dou);	//	: 123.457
		
			
		/*
		 * 2. 형식 문자열 반환하기
		 * 2-1. String.format() == javascirpt의 템플릿 문자열('')
		 */
		
		String res;
		res = "abcdefg";
		System.out.printf("res: %s \n", res);
		
		String result = String.format("res: %s", res);
		System.out.println(result);
		
		
		Scanner scanner = new Scanner(System.in);
			
		String inputData = scanner.next();
		System.out.println("inputData: " + inputData);
		
//		String inputData = scanner.next();
//		System.out.println("inputData: " + inputData);
		
		System.out.print("나이: ");
		int age = scanner.nextInt();
		System.out.println("age: " + age);
		
		System.out.print("이름: ");
		scanner.nextLine();
		String userName = scanner.nextLine();
		System.out.println("userName: " + userName);
		
		
		
		scanner.close();
		
		
		
	}

}
