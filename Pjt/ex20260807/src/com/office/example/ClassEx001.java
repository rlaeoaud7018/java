package com.office.example;

public class ClassEx001 {

	public static void main(String[] args) {
		
		Cat cat1 = new Cat();		// 객체 생성 at 메모리, 가장 기본적인 방법
		// Cat 은 말이 클래스이지, 데이터타입으로 인식도 된다.
		
		System.out.println("cat1: " + cat1);		// // cat1: com.office.example.Cat@73a28541
		System.out.println(cat1.breed);					// . =  도트 접근 연산자
		
		cat1.eat();
		
		Person person1 = new Person();
//		person1.walkForEng();
		
		System.out.println(person1.name);			// 홍길동
		System.out.println(person1.regNum);			// 1000
		System.out.println(person1.setAtrribute);	// 0
		System.out.println(person1.setAtrribute1);	// 1
		
	}
	
}
