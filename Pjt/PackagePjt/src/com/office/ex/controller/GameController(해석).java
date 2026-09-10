/*
 * ===================================================================
 *  PackagePjt / controller/GameController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 숫자를 입력받아 run/stop 으로
 *   분기하는 담당입니다. 프로젝트 개요·패키지 구조는
 *   main/MainClass(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   Controller 는 입력을 받아 Service 의 알맞은 메서드를 부르기만
 *   한다. 그리고 service 패키지의 GameService 를 쓰려면 import 가
 *   필요하다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● import (다른 패키지 클래스)
 *    이 파일은 controller 패키지. GameService 는 service 패키지라
 *    `import com.office.ex.service.GameService;` 가 필요합니다.
 *    Scanner 도 java.util 패키지라 import.
 *
 *  ● Scanner / nextInt()
 *    키보드 입력 도구. nextInt() 로 정수 하나를 읽습니다. 이후
 *    문자열을 읽지 않으므로 nextLine() 으로 줄바꿈을 버리는 코드는
 *    없어도 됩니다.
 *
 *  ● switch / case / break
 *    입력한 숫자로 분기. case 1 이면 run, case 2 면 stop. 여기엔
 *    default 가 없어서, 1·2 가 아닌 값을 넣으면 아무 case 도 안
 *    타고 그냥 지나갑니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public void execute() {
 *     GameService gameService = new GameService();
 *     Scanner scanner = new Scanner(System.in);
 *     int selectedNumber = scanner.nextInt();
 *
 *     switch (selectedNumber) {
 *     case 1:            // run
 *         gameService.run();
 *         break;
 *     case 2:            // stop
 *         gameService.stop();
 *         break;
 *     }
 *     scanner.close();
 * }
 *
 *   [정상]
 *   GameService 객체와 Scanner 를 준비하고, 정수 하나를 읽습니다.
 *   - 1 이면 gameService.run() → 토끼·거북이가 달림
 *   - 2 이면 gameService.stop() → 토끼·거북이가 멈춤
 *   실제 동작은 GameService 가 하고, GameService 는 다시 Rabbit·
 *   Turtle 을 부릅니다(GameService(해석).java 로 이어짐). 끝나면
 *   Scanner 를 닫습니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   1 입력 → "[Turtle] run()" / "거북이 엉금 엉금~" / "[Rabbit] run()"
 *            / "깡총 깡총~"
 *   2 입력 → "[Turtle] stop()" / "거북이 멈춤!" / "[Rabbit] stop()"
 *            / "토끼 멈춤!"
 *   그 외 숫자 → 아무것도 출력 안 됨(해당 case 없음).
 */
