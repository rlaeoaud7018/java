/*
 * ===================================================================
 *  ArrayClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 어제(ex20260806) ArrayClass.java의
 *   뒷이야기 같은 파일이에요. 문법 오류 없이 컴파일됩니다. 파일 맨
 *   위부터 순서대로 짚어보겠습니다.
 *
 *
 * int[] nums = new int[5];
 * System.out.println("nums[0]: " + nums[0]);   // 0
 *
 *   {90,50,100}처럼 내용물을 안 넣고 "칸이 5개인 사물함"만
 *   만들면, 자바가 알아서 기본값을 채워둬요. int는 0, float는
 *   0.0, boolean은 false로 자동 채워져요 — 사람이 아무것도 안
 *   넣어도 칸이 완전히 비어있지는 않다는 뜻이에요.
 *
 *
 * int number1 = 10;
 * int number2 = 20;
 * number2 = number1;
 * System.out.println("number2: " + number2);   // 10
 *
 *   숫자 상자끼리는 대입하면 진짜로 값이 "복사"돼요. number2에
 *   10이 새로 채워질 뿐, number1과는 완전히 남남이에요.
 *
 *
 * int[] numbers1 = {10, 20, 30};
 * int[] numbers2 = {100, 200, 300};
 * numbers2 = numbers1;
 * System.out.println("numbers1 ADDR: " + numbers1);   // [I@61baa894
 * System.out.println("numbers2 ADDR: " + numbers2);   // [I@61baa894
 * numbers2[2] = 999;
 * System.out.println(numbers1[2]);   // 999
 *
 *   방금 본 숫자 상자와 다르게, 배열은 대입해도 내용물이 복사되지
 *   않아요. numbers2 = numbers1을 하는 순간, numbers2는 numbers1과
 *   "똑같은 사물함을 가리키는 이름표"가 돼요. 그 증거로 두 변수를
 *   찍어보면 뒤에 붙는 식별 번호(@61baa894)가 완전히 똑같아요 —
 *   같은 물건을 가리키고 있다는 뜻이에요. 그래서 numbers2로 3번째
 *   칸을 999로 바꾸면, numbers1로 봐도 999로 보여요. (어제 배운
 *   numberCopy와 똑같은 원리예요.)
 *
 *
 * int[] scores = {80, 90, 100};
 * System.out.println(scores);   // [I@b065c63
 * scores = null;
 * System.out.println(scores);   // null
 *
 *   scores = null;은 "이 이름표가 더 이상 아무 사물함도 안
 *   가리킨다"는 뜻이에요. 사물함 자체가 사라지는 게 아니라 이름표가
 *   떨어져 나가는 거예요. 근데 만약 그 사물함을 가리키는 이름표가
 *   하나도 안 남으면(아무도 안 찾는 물건이 되면), 자바가 알아서
 *   그 사물함을 메모리에서 치워버려요(가비지 컬렉션). 사람이 직접
 *   "치워라"라고 명령할 필요가 없어요.
 *
 *
 * 실행하면 어떻게 될까
 *
 *   기본값 0/0.0/false가 출력된 다음, 숫자 상자 복사 예시, 배열
 *   참조 공유 예시(numbers1도 999로 같이 바뀜), 마지막으로 null
 *   대입까지 순서대로 진행돼요.
 */
