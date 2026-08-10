package com.office.example;

public class OurStudent {

	// 속성 ?
	int grade;				// 학년
	int classNo;			// 반번호
	int studentNo;			// 개인번호
	String studentName;		// 학생이름
	boolean absence;		// 휴학구분
	
	// 생성자 ?
	public OurStudent() {
	
	}
	
	// 생성자 오버로딩 ==> 절대로 필수인 속성 초기화를 놓치는 오류를 피할 수 있다.
	public OurStudent(					
			int classNo,						
			int studentNo,			
			String studentName) {
		
		this.classNo = classNo;
		this.studentNo = studentNo;
		this.studentName = studentName;
				
}
	
	// 매서드
	public void printInfo() {
		System.out.println("Grade: " + grade);
		System.out.println("ClassNo: " + classNo);
		System.out.println("StudentNo: " + studentNo);
		System.out.println("StudentName: " + studentName);
		System.out.println("Absence: " + absence);
		
	}
	
}
