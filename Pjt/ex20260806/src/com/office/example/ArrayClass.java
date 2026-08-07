package com.office.example;

import java.util.Arrays;

public class ArrayClass {

	public static void main(String[] args) {
		
		// 배열 선언 - I *****
		int[] myScores = {90, 50, 100};
		
		System.out.println("myScores: " + myScores);			// [I@5aaa6d82
		System.out.println("myScores[0]: " + myScores[0]);		// 90
		
		for (int i = 0; i < myScores.length; i++) {
			System.out.println(myScores[i]);
		}
		
		for (int score : myScores) {
			System.out.println("score: " + score);
		}
		
		System.out.println(Arrays.toString(myScores));			// [90, 50, 100]
		
		myScores[2] = 1000;
		System.out.println(myScores[2]); 	// 1000
		
		// 배열 선언 - II
		String[] myTools = new String[3];	// [ , , ]
		myTools[0] = "지우개";
		myTools[1] = "공책";
		myTools[2] = "만년필";
		
		// 배열 선언 - III
		float[] myFloats =new float[] {3.14f, 3.141592f, 3.141592876f};
		// 인덱스 시작 번호는 0부터 0, 1, 2  
		
		/*
		 * 학급 학생의 시험 성적과 평균을 출력하는 프로그램을 만들자!
		 */
		
		int[] studentScores = {80, 95, 100, 70, 68, 74, 88, 98, 100, 76};	// 학생 점수
		int total = 0;
		float average = 0.0f;
		
		for (int i = 0; i < studentScores.length; i++) {
			System.out.printf("studentScores[%d] score: %d \n", i, studentScores[i]);
			
			total += studentScores[i];
		}
		
		System.out.println("총합: " + total);		// 849
		
		average = total / (float)studentScores.length;
		System.out.println("평균: " + average);		// 84.9
		
		int[] numbers = {10, 20, 30};
		System.out.println("numbers: " + Arrays.toString(numbers));
		
		int[] numberCopy = numbers;
		System.out.println("numberCopy: " + Arrays.toString(numberCopy));
		
		numbers[2] = 999;
		System.out.println("numbers: " + Arrays.toString(numbers));
		System.out.println("numberCopy: " + Arrays.toString(numberCopy));
		
		
	}
	
}
