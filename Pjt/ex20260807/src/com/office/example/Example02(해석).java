/*
 * ===================================================================
 *  Example02.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 문법 오류 없이 컴파일됩니다.
 *   가장 단순한 형태로 "필드 읽기"만 보여주는 파일이에요.
 *
 *
 * public class Example02 {
 *     String name = "홍길동";
 *
 *     public static void main(String[] args) {
 *         Example02 example02 = new Example02();
 *         System.out.println("name: " + example02.name);
 *     }
 * }
 *
 *   특이한 점 하나 — main()이 Example02 자기 자신의 안에 들어있고,
 *   그 안에서 "new Example02()"로 자기 자신의 빵을 구워요. 클래스
 *   는 자기 자신을 재료로 써도 전혀 문제없어요. 빵(example02)이
 *   구워지는 순간 name 필드가 "홍길동"으로 채워지고,
 *   example02.name으로 그 값을 꺼내 읽어요.
 *
 *
 * 실행하면 어떻게 될까
 *
 *   "name: 홍길동" 한 줄만 출력돼요.
 */
