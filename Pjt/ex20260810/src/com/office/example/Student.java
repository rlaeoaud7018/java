package com.office.example;

public class Student {

    // '디폴트 생성자' 라고 한다.
    public Student() {
    	
    }
	
	// 생성자(Constructor)		<-- 기본 생성자(Default Constructor)
	public Student() {
		System.out.println("Student() Constructor Called!");
		
		// 생성자를 이용한 초기화
		// ***** 이렇게 디폴트 생성자에서 멤버 필드를 초기화 하게 되면
		// 		 이제 디폴트 생성자는 더이상 디폴트 생성자가 아니게됨.
		//		 이제부터는 일반 생성자라고 함.
		//		 그러면 디폴트 생성자가 없으니, 컴파일러가 개발자를 대신해서 
		//		 디폴트 생성자를 넣어줄까요? 일반 생성자가 있다면 넣어 주지 않습니다.
		
		name = "홍길동";
		grade = 1;
		age = 20;
		major = "computer";
		
	}
	
	// 생성자 매개 변수  ==> 일반 생성자 == 생성자 오버로딩(overloading, 과적)
	// 매개변수 이름은 가독성이 뛰어난 이름으로 하자 == 속성명과 동일하게 하자
	public Student(String name, int grade, int age, String major) {
		System.out.println("Student() Constructor Called!");
		this.name = name;
		this.grade = grade;
		this.age = age;
		this.major = major;	
	}
	
	//속성  == 멤버 필드
    String name;			// null
    int grade;			// 0
    int age;				// 0
    String major;		// null
    

   
    //기능  == 멤버 메서드
    public void printInfo() {
       System.out.println("Name :" + name);
       System.out.println("Grade :" + grade);
       System.out.println("Age :" + age);
       System.out.println("Major :" + major);
    }
}
