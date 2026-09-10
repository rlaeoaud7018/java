/*
 * ===================================================================
 *  Operator02.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 파일은 문법 오류 없이
 *   정상 컴파일돼요. 파일 맨 위부터 순서대로 짚어보겠습니다.
 *
 *
 * int num1 = 100;
 * int num2 = 200;
 * System.out.println(num1 > num2);   // false
 * System.out.println(num1 < num2);   // true
 * System.out.println(num1 == num2);  // false
 *
 *   >, <, >=, <=, ==, != 는 결과가 항상 true/false 둘 중 하나로
 *   나오는 비교 연산자예요. 특별할 거 없이 딱 보이는 대로예요.
 *
 *
 * float num3 = 0.1f;
 * double num4 = 0.1;
 * System.out.println(num3 == num4);  // false
 *
 *   숫자로는 둘 다 "0.1"인데 == 결과는 false예요. 컴퓨터는 0.1
 *   같은 소수를 2진수로 완벽하게 표현하지 못해요(무한소수가
 *   돼버려요). float(4바이트)와 double(8바이트)이 이 근사값을
 *   서로 다른 정밀도로 잘라 저장하다 보니, 겉보기엔 같아 보여도
 *   미세하게 다른 값이 돼요. 그래서 실무에서는 실수를 ==로 "정확히
 *   같다"고 비교하지 않고, 두 값의 차이가 아주 작은 범위 안에
 *   있는지로 비교해요.
 *
 *
 * String str1 = "abc";
 * String str2 = "abc";
 * System.out.println(str1 == str2);   // true
 *
 * String str3 = new String("abc");
 * String str4 = new String("abc");
 * System.out.println(str3 == str4);   // false
 * System.out.println(str1.equals(str2));  // true
 * System.out.println(str3.equals(str4));  // true
 *
 *   내용은 넷 다 "abc"로 똑같은데 == 결과만 달라요. "abc"처럼
 *   큰따옴표로 직접 쓴 문자열(리터럴)은 자바가 "문자열 풀"이라는
 *   창고에 하나만 만들어두고 재사용해요 — 그래서 str1과 str2는
 *   사실 같은 물건을 가리켜요. 반면 new String("abc")는 "창고에
 *   있는 거 말고 새 물건을 하나 더 만들어라"는 명령이라서, str3와
 *   str4는 내용은 같아도 서로 다른 물건이에요.
 *
 *   ==는 "두 이름표가 같은 물건을 가리키는가"를 보고, equals()는
 *   "물건 속 내용이 같은가"를 봐요. 그래서 문자열 비교에는
 *   equals()를 써야 해요.
 *
 *
 * System.out.println("ABC".compareTo("ABC"));   // 0
 * System.out.println("ABC".compareTo("DEF"));   // -3
 * System.out.println("DEF".compareTo("ABC"));   // 3
 *
 *   compareTo()는 사전 순서로 앞인지 뒤인지, 얼마나 차이나는지를
 *   숫자로 알려줘요. 같으면 0, 왼쪽이 사전 앞이면 음수, 뒤면
 *   양수예요.
 *
 *
 * str5 = null;
 * if (str5 != null) {
 *     System.out.println(str6.equals(str5));
 * }
 *
 *   str6.equals(str5)처럼 "null이 아닌 쪽을 앞에 두는" 순서도
 *   실무 습관이에요. 만약 반대로 str5.equals(str6)라고 썼다면,
 *   str5가 null이니까 그 순간 NullPointerException이 터졌을
 *   거예요. null이 아닌 물건을 앞에 두면 equals()는 인자로 null이
 *   들어와도 예외 없이 그냥 false를 돌려주도록 만들어져 있어서
 *   더 안전해요. (다만 여기선 if (str5 != null)로 이미 걸러버려서,
 *   str5가 null인 이 예제에서는 블록 안 코드가 실행되지 않아요.)
 *
 *
 * 실행하면 어떻게 될까
 *
 *   위에서부터 순서대로 정수·실수 비교 결과, 문자열 ==와 equals()
 *   비교 결과가 쭉 출력돼요. 마지막 if 블록은 조건이 거짓이라
 *   아무것도 출력되지 않고 그냥 프로그램이 끝나요.
 */
