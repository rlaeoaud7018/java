package com.office.example;

public class Operator01 {
	
	public static void main(String[] args) {
		
		/*
		 * 1. 연산자 란?
		 * 각종 연산을 수행할 때 사용하는 '기호'이다.
		 *  - 연사자 종류
		 * 	  : 산술 연산자		: +, -, *, /, %, ++, --
		 * 	  : 할당 연산자	    : =, +=, -=, *=, /=, %=  자신에게 다시 적용하는 것      
		 * 	  : 비교 연산자	    : ==, !=, >, <, >=, <= 
		 * 	  : 논리 연산자	    : &&, ||, !
		 * 	  표에 명시한 연산자 말고도 조건 연산자,
		 * 	  그리고 연산자와 관련한 몇 가지 메서드가 있는데, 웹 개발에는 몰라도 상관 없다.
		 *  
		 * 2. 산술 연산자
		 * 2-1. 숫자 덧셈(+)
		 *  : 숫자를 연결할때 사용한다.
		 * 2-2. 문자열 덧셈(+)
		 *  : 문자열을 연결할 때 사용한다.
		 * 2-3. 숫자와 문자열 덧셈(+)
		 *  : 이런 경우 숫자가 문자열화 됩니다. number to String
		 * 2-4. 문자 덧셈
		 *  : 문자 덧셈은 문자가 유니코드로 변환되어 사용
		 *  
		 *  
		 */

		int num = 10;
		let num = 10;	10 == '10' --> true
		System.out.println("num1 + num2: " + (num1 + num2));
		
		float num3 = 0.1f;
		float num4 = 0.2f;
		System.out.println("num3 + num4: " + (num3 + num4));
		
		String str1 = "Hello";
		String str2 = "JAVA";
		System.out.println("str1 + str2: " + (str1 + str2));
		System.out.println("str1 + str2: " + str1 + str2);
		
		System.out.println(10 + "Hello");
		System.out.println(3.14 + "Hello");
		
		
		/*
		 * 2-5. 숫자 뺄셈
		 *  : 수학에서의 숫자 뺄셈과 동일함.
		 * 
		 * 2-6. 문자열 뺄셈
		 *  : 문자열 뺄셈은 불가하다.
		 * 
		 * 2-7. 숫자와 문자열 뺄셈
		 *  : 숫자와 문자열 뺄셈은 불가하다.
		 * 
		 * 2-8. 문자 뺄셈
		 *  : 문자 뺄셈은 문자가 유니코드로 변경되어 뺄셈 연산한다.
		 */
		
		System.out.println("10 - 20 = " + (10 - 20));				// -10
		System.out.println("0.1f - 0.2f = " + (0.1f - 0.2f));		// -0.1
		
		System.out.println("Hello" - "JAVA");  						// Exception(Error)
		
		System.out.println(10 - "JAVA"); 							// Exception(Error)
		
		System.out.println('A' - 'B');  							// -1
		System.out.println('가' - '나');  							// -1176
		
		
		/*
		 * 2-9. 숫자 곱셈
		 *  : 수학에서의 곱셈과 동일하다.
		 * 
		 * 2.10 숫자와 문자열 곱셈
		 *  : 불가하다
		 * 
		 * 
		 */
		
		System.out.println(10 * 3);  								// 30
		System.out.println(0.2f * 3);  								// 0.6
		
		System.out.println(3 * "Hello"); 							// Exception(Error)
		System.out.println("Hello" * 3); 							// Exception(Error)
		
		// at python : "Hello" * 3 --> HelloHelloHello
		
		
		/*
		 * 2-11. 숫자 나눗셈
		 *  : 수학과 동일하다.
		 *  
		 * 2-12. 문자 나눗셈
		 *  : 유니코드를 이용한 연산 가능
		 *  
		 * 2-13. 0으로 나눗셈
		 *  : 세상에 어떤 수도 0으로 나눌 수는 없다.
		 *  
		 * 2-14. 0을 어떤 수를 나눗셈
		 *  : 결과값은 무조건 0이다.  
		 *
		 */
		
		System.out.println(10 / 20); 								// 0.5
		System.out.println(10.0f / 20); 							// 0.5
		System.out.println((float)10 / 20); 						// 0.5
		
		System.out.println((float)'A' / 'B'); 					
		
		System.out.println(10 / 0);
		
		System.out.println(0 / 100);
		
		
		/*
		 * 2-15. 나머지 연산자(%)
		 *  : 나눈셈한 결과에서 나머지만 구한다.
		 * 
		 */
		
		System.out.println(10 % 3);									// 1
		System.out.println('A' % 'B');  							// 65
		
		
		/*
		 * 2-16. 산술 변환
		 *  : 피연산자들을 이용해서 연산이 이루어 질때 데이터 타입이 자동으로 변환되는 것을 산술 변환이라고 한다.
		 * 
		 */
		
		System.out.println(10 + 3.14f); 							// 정수 + 실수
		
		
		
		/*
		 * 2-17. 자동 증감 연산자(++, --)
		 *  : 증감 연산자는 1을 증가 시키거나 1을 감소 시키는 연산자이다.
		 * 
		 * 
		 * 
		 */
		
		int no = 10;
		System.out.println("no: " + no);  							// 10
		
		no = no + 1; 					
		System.out.println("no: " + no);							// 11
		
		no++; 	// no = no + 1; no += 1;													
		System.out.println("no: " + no);							// 12
		
		int number = 10;
		int result = 0;
		
		result = (number++);
		System.out.println("result: " + result);					// 10
		System.out.println("number: " + number);					// 11
		
		result = ++number;
		System.out.println("result: " + result);					// 11
		System.out.println("number: " + number);					// 11
		
		
		
		
		
		
		
		
	}
	
}
