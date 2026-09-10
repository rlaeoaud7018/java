/*
 * ===================================================================
 *  AccessModifier / controller/PayController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 월을 입력받아 PayService 에
 *   넘기는 담당입니다. 프로젝트 개요·접근 제어자 표는
 *   main/MainClass(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   Controller 의 역할 — 사용자 입력(월)을 받아 Service 에 전달만
 *   한다. 급여 계산·지급은 안 한다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● import (다른 패키지 클래스 가져오기)
 *    PayService 는 com.office.ex.service 패키지, 이 파일은
 *    com.office.ex.controller 패키지 — 서로 다르므로 import 합니다.
 *    Scanner 도 java.util 패키지라 import.
 *
 *  ● Scanner / nextInt()
 *    키보드 입력 도구. nextInt() 는 정수 한 개를 읽습니다.
 *
 *  ● public void execute()
 *    이 메서드가 public 이라 다른 패키지의 MainClass 에서 부를 수
 *    있습니다. (접근 제어자가 실제로 쓰인 첫 예시 — public 이 아니면
 *    MainClass 에서 `new PayController().execute()` 가 안 됩니다.)
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public void execute() {
 *     Scanner scanner = new Scanner(System.in);
 *     System.out.println("Please enter the month: ");
 *     int thisMonth = scanner.nextInt();
 *
 *     PayService payService = new PayService();
 *     payService.pay(thisMonth);
 *
 *     scanner.close();
 * }
 *
 *   [정상]
 *   키보드 도구를 만들고, "월을 입력하세요" 를 띄운 뒤 정수 하나를
 *   읽어 thisMonth 에 담습니다. 그리고 PayService 객체를 만들어
 *   pay() 에 그 월을 넘깁니다. 급여를 실제로 지급하는 일은
 *   PayService 가 합니다(PayService(해석).java 로 이어짐). 끝나면
 *   Scanner 를 닫습니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   "Please enter the month:" 출력 → 사용자가 숫자 입력 →
 *   PayService.pay(그 숫자) 로 넘어감.
 */
