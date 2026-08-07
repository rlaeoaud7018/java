package com.office.example;

import java.util.Arrays;

public class ArrayClass {

	public static void main(String[] args) {
		
		// 초기화 하지 않은 배열
		int[] nums = new int[5];
		System.out.println("nums[0]: " + nums[0]);  // 0
		System.out.println("nums[1]: " + nums[1]);  // 0
		System.out.println("nums[2]: " + nums[2]);  // 0
		
		for (int num : nums) {
			System.out.println("num: " + num);		// 0 0 0 0 0
		}
		
		float[] floats = new float[3];
		for (float f : floats) {
			System.out.println("f: " + f);		// 0.0
		}
		
		boolean[] flags = new boolean[3];
		for (boolean b : flags) {
			System.out.println("b: "+ b);		// false
		}
		
		// 배열 복사
		int number1 = 10;
		int number2 = 20;
		
		System.out.println("number1: " + number1);		// 10
		System.out.println("number2: " + number2);		// 20
		
		number2 = number1;
		System.out.println("number2: " + number2);		// 10
		
		int[] numbers1 = {10, 20, 30};		// [10, 20, 30]
		int[] numbers2 = {100, 200, 300};	// [100, 200, 300]
		
		System.out.println("number1: " + Arrays.toString(numbers1));		// [10, 20, 30]
		System.out.println("number2: " + Arrays.toString(numbers2));		// [100, 200, 300]
		
		numbers2 = numbers1;
		System.out.println("number2: " + Arrays.toString(numbers2));		// [10, 20, 30]
		System.out.println("numbers1 ADDR: " + numbers1);					// [I@61baa894
		System.out.println("numbers2 ADDR: " + numbers2);					// [I@61baa894
		
		numbers2[2] = 999;
		System.out.println(numbers1[2]);	// 999
		
		// 배열 삭제
		int[] scores = {80, 90, 100};
		System.out.println(Arrays.toString(scores));	// [80, 90, 100]
		System.out.println(scores);			// [I@b065c63
		
		scores = null;
		System.out.println(scores);			// null 연결을 끊어버린다 연결이 끊긴 배열은 자동으로 메모리에서 제거된다
		
		
		
		
	}
	
}
