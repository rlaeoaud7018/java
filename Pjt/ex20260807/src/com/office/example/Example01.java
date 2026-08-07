package com.office.example;

import java.util.Arrays;

public class Example01 {

	public static void main(String[] args) {
		
		Cat cat = new Cat();
		
		cat.breed = "페르시안 고양이";
		cat.color = "흰색";
		
		System.out.println("cat.breed: " + cat.breed);
		System.out.println("cat.color: " + cat.color);
		System.out.println("cat.age: " + cat.age);
		
		cat.eat();
		
//		cat.eatStatic();
		
		cat.eatStatic();
		
		int[] nums = {10, 20, 30};
		// Arrays arrays = new Arrays()
		// arrays.toString()
		System.out.println(Arrays.toString(nums));
		
		cat.breed = "^^";
		
	}
	
}
