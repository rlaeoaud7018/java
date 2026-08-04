package com.office.example;

public class VariableCls {

	// 변수
	/*
	 * 컴퓨터 프로그램에서 숫자, 문자 등과 같은 데이터를 저장하고 관리하는 방법에 대해서 학습합니다.
	 * 모든 컴퓨터 프로그래밍 언어는 데이터를 효율적으로 관리하기 윈한 다양한 방법을 가지고 있습니다.
	 * 그중 변수를 이용한 데이터 관리는 데이터를 효율적으로 관리하기 위한 기초입니다.
	 * 
	 */
	public static void main(String[] args) {
		
		// python
		// num = 10
		
		// javascript
		// let num = 10
		
		// java
		// int num = 10; (어떤 변수든 앞에 데이터 타입 명시가 필수)
		//   int num       = 10;
		// 데이터 선언부  | 데이터 초기화
		
		/*
		 * '=' : 할당연산자 라고 합니다. 수학에서는 좌변과 우변이 같다라고 하지만, 
		 * 프로그램에선느 우변의 값을 좌변에 할당(대입)하는 연산자 입니다. 즉 '같다'라는 의미가 아닙니다.
		 */
		
		int num1; // 변수 선언
		del num1; // 
		num1 = 10; // 변수 초기화
		
		System.out.println("num1 is " + num1); // num1 is 10
		
		int addForNum1 = System.identityHashCode(num1);
		
		System.out.println(addForNum1);
		
		
		/* 
		 * java 응용 프로그램에서 메모리 주소를 꼭 알고 있어야 할까요?
		 * 절대 아니다. 신경쓸 필요 없다. 이유는 java에서는 메모리를 직접 관리하지 않기 때문에.
		 * 그러면 메모리를 누가 대신 관리하나요? JVM이 대신 관리합니다.
		 * 
		 */
		
		/* 
		 * 변수 이름 어덯게 작명 하나요?
		 * 1. 일반적으로 소문자로 한다.
		 * int money = 100;   --> O
		 * int Money = 100;   --> 적합하지 않다.
		 *  
		 * 2. 변수명은 일반적으로 영문으로 한다.
		 * int myMoney = 100;  --> O
		 * int 나의돈 = 100;  --> 적합하지 않다.
		 * 
		 * 3. 변수명은 데이터의 의미를 쉽게 파악할 수 있게 만들자.
		 * int score = 100;  --> O
		 * String plyaer = '홍길동'  --> O
		 * String state = '어려운스테이지';  --> O
		 * 
		 * int s = 100;  --> 적합하지 않다.
		 * String p1 = '홍길동'  --> 적합하지 않다.
		 * String st = '어려운스테이지'  --> 적합하지 않다.
		 * 
		 * 4. 2개 이상의 단어가 조합된 경우 낙차(camel)표기법을 사용하자.
		 * int myScore = 100  --> O
		 * int myTotalScore	= 100;  --> O
		 * 
		 * 
		 * int myscore = 100  --> 적합하지 않다.
		 * int mytotalscore	= 100;  --> 적합하지 않다.
		 * 
		 * 
		 * int my_score = 100  --> 적합하지 않다.
		 * int my_total_score = 100;  --> 적합하지 않다.
		 * 
		 * 정리하면
		 *   변수명은 영문으로 하고, 첫 글자는 소문자로 한다.
		 *   2개 이상의 단어가 조합된 경우 낙타표기법을 따른다.
		 *   특수문자, 예약어 그리고 공백굼낮는 사용하지 않는다.
		 *   숫자를 사용해야하는 경우 중간 또는 뒤에 사용한다. 
		 * 
		 */
		
		// ----------------------------------------------------------
		
		
		/* 
		 * 변수 데이터 변경
		 * 
		 * 변수는 '변하는 수'를 뜻한다. 따라서 필요할 때 언제든지 데이터를 변경할 수 있다.
		 */
		
		int score = 90;
		System.out.println("score : " + score);       // 90
		
		score = 100;
		System.out.println("score : " + score);       // 100		
	
		
		// -----------------------------------------------------------------
		
		/* 
		 * 데이터 복사
		 * 변수의 데이터를 복사할 수 있어요.
		 * int number1 = 10;
		 * int number2;
		 * System.out.println("number1 : " + score);	// 10
		 * System.out.println("number2 : " + score);	// 0
		 * 
		 * number2 = number1;
		 * 
		 * System.out.println("number1 : " + score);	// 10
		 * System.out.println("number2 : " + score);	// 10
		*/
		
		
		
		/*
		 * 변수를 ㄹ,프로그램에서 사용하는 이유?
		 * 데이터 재사용!
		 * 
		 * 함수를 
		 * 기능을 재사용하기 위해서
		 */
		
		int n = 20;
		System.out.println(n);
		System.out.println(n);
		System.out.println(n);
		System.out.println(n);
		System.out.println(n);
		System.out.println(n);
		System.out.println(n);
		System.out.println(n);
		System.out.println(n);
		
		
		
		
	}

}
