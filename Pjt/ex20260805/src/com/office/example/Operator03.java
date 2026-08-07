package com.office.example;

public class Operator03 {

		public static void main(String[] args) {
			
			/*
			 * 논리 연산자
			 * && AND, || OR, ! NOT
			 * A && B: A와 B가 모두 true인 경우 결과값은 true이다.
			 * A || B: A 또는 B 중 하나라도 true이면 결과값은 true이다.
			 * !A: A의 현재 상태를 부정한다.
			 * 
			 * 
			 */
			
			int num1 = 10;
			int num2 = 20;
			int num3 = 30;
			
			boolean result = false;
			
			result = (num1 < num3) && (num2 < num3);		// true	&& true		=> true
			System.out.println("result: " + result);
			
			result = (num1 > num3) && (num2 < num3);		// false && true		=> false
			System.out.println("result: " + result);
			
			result = (num1 > num3) && (num2 > num3);		// false && false		=> false
			System.out.println("result: " + result);
			
			result = (num1 < num2) && (num2 < num3) && (num3 > num1);		// true	&& true		=> true
					//	true && true	=> true
									//	true && true	=> true
			System.out.println("result: " + result);
			
			
			int num1 = 10;
			int num2 = 20;
			int num3 = 30;
			
			
			result = (num1 < num3) || (num2 < num3);		// true	&& true		=> true
			System.out.println("result: " + result);
			
			result = (num1 > num3) || (num2 < num3);		// false && true		=> true
			System.out.println("result: " + result);
			
			result = (num1 > num3) || (num2 > num3);		// false && false		=> false
			System.out.println("result: " + result);
			

			result = !!(num1 < num2);		// true	=> false => true
			System.out.println("result: " + result);
			
			
			// 논리 연산자 우선순위 && > ||
			int num1 = 10;
			int num2 = 20;
			int num3 = 30;
			
			result = (num1 < num3) || (num2 < num3) && (num1 > num3);
								   //     true      ||     false
					//   true	   &&     false
					//   true
			System.out.println("result: " + result);
			
			
			/*
			 * 조건식 연산자 == 삼항연산자
			 * 조건식 ?  x   :   y
			 * 		  true   false  로 나옴
			 * 
			 */
			
			int passScore = 80;
			int myScore = 90;
			
			// 3항문으로 코드를 짰을 때 2줄 소요
			String passResult = (myScore >= passScore) ? "PASS" : "TRY AGAIN";
			System.out.println("passResult: " + passResult);
			
			// if 문으로 코드를 짰을 때 7줄 소요
			String passResult = "";
			if (myScore >= passScore) {
				passResult = "PASS";
			} else {
				passResult = "TRY AGAIN";
			}
			System.out.println("passResult: " + passResult);
			
			// 이런것들은 3항문으로 작성하는게 실무에 적합
			
			
			
		}
}
