package com.office.example;

public class Operator02 {

	public static void main(String[] args) {
		
		/*
		 * 3-1. 비교 연산자
		 *  : > 크다, < 작다, >= 크거나 같다, <= 작거나 같다
		 *  : == 같다, != 같지 않다
		 *  
		 *  
		 * 
		 */
		
		int num1 = 100;
		int num2 = 200;
		
			
		System.out.println(num1 > num2);		// false
		System.out.println(num1 >= num2);		// false
		System.out.println(num1 < num2);		// true
		System.out.println(num1 <= num2);		// true
		
		System.out.println(num1 == num2);		// false
		System.out.println(num1 != num2);		// true
		
		
		/*
		 * [참고] 실수 비교
		 * 
		 * 
		 * 
		 */
		
		
		float num3 = 0.1f;
		double num4 = 0.1;
		
		System.out.println("num3 > num4 ===> " + (num3 > num4));		// false
		System.out.println("num3 >= num4 ===> " + (num3 >= num4));		// false
		System.out.println("num3 < num4 ===> " + (num3 < num4));		// false
		System.out.println("num3 <= num4 ===> " + (num3 <= num4));		// false
		System.out.println("num3 == num4 ===> " + (num3 == num4));		// false
		System.out.println("num3 != num4 ===> " + (num3 != num4));		// false
		
		System.out.println("num3: " + num3); // 0.1
		System.out.println("num4: " + num4); // 0.1
		System.out.println("num3 + num4: " + (num3 + num4)); // 0.2
		
		
		/*
		 * *****문자열 비교
		 * 
		 * 
		 * 
		 */
		
		String str1 = "abc";
		String str2 = "abc";
		System.out.println("str1 == str2: " + (str1 == str2));	 		// true
		System.out.println("str1 != str2: " + (str1 != str2));	 		// false
		
		String str3 = new String("abc");
		String str4 = new String("abc");
		
		System.out.println("str3 == str4: " + (str3 == str4));	 		// false
		System.out.println("str3 != str4: " + (str3 != str4));	 		// true
		
		System.out.println(str1.equals(str2));							// true
		System.out.println(str3.equals(str4));							// true
		
		System.out.println("ABC".compareTo("ABC"));						// 0
		System.out.println("ABC".compareTo("DEF"));						// -3
		System.out.println("DEF".compareTo("ABC"));						// 3
		
		String str5 = new String("Hello");
		String str6 = new String("Hello");
		
		System.out.println(str5.equals(str6));
		
		
		str5 = null;
		if (str5 != null) {
			System.out.println(str6.equals(str5));
		}
		
		
	}
}
