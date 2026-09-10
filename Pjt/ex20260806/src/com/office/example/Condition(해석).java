/*
 * ===================================================================
 *  Condition.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 파일은 컴파일·실행이 정상적으로
 *   되는 파일이에요 — 문제는 컴파일 오류가 아니라, 마지막 switch문이
 *   의도한 대로 동작하지 않는다는 점이에요. 파일 맨 위부터 순서대로
 *   짚어보겠습니다.
 *
 *
 * import java.lang.invoke.SwitchPoint;
 *
 *   이 파일 어디에도 안 쓰이는 import예요. SwitchPoint는 실제로
 *   존재하는 자바 클래스이긴 한데(동적 메서드 연결에 쓰는 고급
 *   기능), 아래에서 배우는 switch문이랑은 이름만 비슷할 뿐 전혀
 *   상관없어요. "switch"를 치다가 이클립스 자동완성이 엉뚱한 걸
 *   추천해서 잘못 가져온 것으로 보여요. 없어도 프로그램엔 영향
 *   없어요.
 *
 *
 * // int num = scanner.nextInt();
 * // if (num > 10) { System.out.printf("%d은 10보다 크다.\n", num); }
 *
 *   가장 단순한 if문이에요 — 조건이 참일 때만 안쪽 코드가 실행돼요.
 *   지금은 주석 처리돼서 실제로 돌아가진 않아요.
 *
 *
 * // if (userScore >= 70) { 합격 } / if (userScore < 70) { 불합격 }
 * // if (userScore >= 70) { 합격 } else { 불합격 }
 * // String result = userScore >= 70 ? "합격!" : "불합격!";
 *
 *   똑같은 문제("70점 이상이면 합격")를 세 가지 방식으로 풀어본
 *   거예요 — if 두 개를 따로 쓰기, if-else로 합치기, 삼항연산자로
 *   한 줄로 줄이기. 뒤로 갈수록 코드가 짧아지는 흐름을 보여줘요.
 *
 *
 * // if (studentScore >= 90) 'A' else if (>=80) 'B' ... else 'F'
 *
 *   조건이 여러 개(90, 80, 70, 60 이상)일 때는 else if로 줄줄이
 *   엮어서 위에서부터 순서대로 검사해요.
 *
 *
 * // final int CASE1~5 = 1~5;
 * // switch (selectedNumber) { case CASE1: ... break; ... }
 *
 *   if-else if랑 비슷하게 여러 값을 비교하는 문법인데, 원본 주석에도
 *   "동일하지는 않다"고 적혀 있듯 완전히 같은 건 아니에요. 숫자
 *   대신 CASE1~CASE5라는 이름 붙은 상수를 써서 뭘 고른 건지 더
 *   알아보기 쉽게 만들었어요.
 *
 *
 * System.out.println("고객등급 입력: ");
 * String userGrade = scanner.nextLine();
 * switch (userGrade) {
 * case "NORMAL":
 *     System.out.println("문자 50개 무료/월");
 *     break;
 * case "VIP":
 *     System.out.println("영화권1매 증정/월");
 *     // break 없음
 * case "VVIP":
 *     System.out.println("해외여행/년");
 *     // break 없음
 * default:
 *     break;
 * }
 *
 *   여기부터가 실제로 실행되는 코드예요. 문자열(String)로도
 *   switch를 쓸 수 있다는 걸 보여주고 있어요.
 *
 *   바로 위 요구사항 주석(NORMAL / VIP=NORMAL+영화권 /
 *   VVIP=VIP+해외여행, 즉 등급이 올라갈수록 아래 등급 혜택을 전부
 *   포함)을 보면, break를 일부러 뺀 건 "흘러내리게 해서 혜택을
 *   누적시키려는" 의도로 보여요. switch는 break가 없으면 다음
 *   case로 그대로 흘러내려가거든요.
 *
 *   근데 실제로 돌려보면 의도한 대로 안 돼요. "VIP"를 입력하면
 *   코드는 위에서부터 순서대로 다 검사하는 게 아니라 값이 일치하는
 *   "case VIP:"로 곧장 점프해요 — "case NORMAL:"은 아예 건너뛰어요.
 *   그래서 "영화권1매 증정/월"을 찍고, break가 없으니 그대로
 *   흘러내려가서 "해외여행/년"까지 찍혀버려요. 즉 VIP를 골랐는데
 *   NORMAL 혜택("문자 50개 무료")은 못 받고, 오히려 VVIP 혜택을
 *   덤으로 받는 이상한 결과가 나와요.
 *
 *   이유는 case 순서예요. 흘러내림은 항상 "아래(나중에 적힌
 *   case)"로만 가지 "위(먼저 적힌 case)"로는 못 가요. 그래서 지금
 *   처럼 "낮은 등급이 먼저 나오는" 순서로는 흘러내림으로 누적
 *   효과를 만들 수가 없어요.
 *
 *   고치려면 case 순서를 반대(VVIP → VIP → NORMAL)로 적으면 돼요.
 *
 *     case "VVIP":
 *         System.out.println("해외여행/년");
 *         // break 없음 — 아래로 계속 흘러내림
 *     case "VIP":
 *         System.out.println("영화권1매 증정/월");
 *         // break 없음
 *     case "NORMAL":
 *         System.out.println("문자 50개 무료/월");
 *         break;
 *
 *   이렇게 순서를 바꾸면 VVIP는 세 줄 다, VIP는 두 줄, NORMAL은
 *   한 줄만 찍혀서 원래 의도대로 동작해요.
 *
 *
 * scanner.close();
 *
 *   다 쓴 입력 도구를 정리해요.
 *
 *
 * 실행하면 어떻게 될까
 *
 *   "고객등급 입력: "이 뜬 다음 NORMAL/VIP/VVIP 중 하나를 입력하면
 *   되는데, 원본 그대로는 VIP를 입력했을 때 "영화권1매 증정/월"과
 *   "해외여행/년" 두 줄이 함께 찍혀요.
 */
