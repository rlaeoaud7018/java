/*
 * ===================================================================
 *  PackagePjt / main/MainClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 프로젝트의 시작점(main)입니다.
 *   토끼·거북이 달리기를 예로 "패키지(package)로 클래스를 역할별로
 *   나누고 import 로 잇는" 구조를 연습합니다.
 *
 *   이 파일이 가르치려는 것:
 *   클래스 6개가 폴더(패키지) 4개로 나뉘어 있고, 다른 패키지의
 *   클래스를 쓰려면 import 를 해야 한다는 것. 코드 자체는 아주
 *   단순합니다 — 구조가 학습 대상입니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 프로젝트 전체에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 패키지(package)
 *    관련된 클래스들을 묶는 폴더입니다. 파일 맨 위의
 *    `package com.office.ex.main;` 선언은 그 파일이 놓인 폴더 경로
 *    (src/com/office/ex/main)와 정확히 일치해야 합니다.
 *
 *  ● 이 프로젝트의 패키지 4개
 *      com.office.ex.main       : 시작점 (MainClass)
 *      com.office.ex.controller : 입력 받고 분기 (GameController)
 *      com.office.ex.service    : 실제 로직 (GameService)
 *      com.office.ex.runner     : 달리는 주체 (Rabbit, Turtle)
 *
 *  ● import
 *    다른 패키지의 클래스를 이 파일에서 이름만으로 쓰려면 import
 *    합니다. 같은 패키지 안의 클래스는 import 없이 바로 씁니다.
 *    예: MainClass(main 패키지)가 GameController(controller 패키지)를
 *    쓰려면 `import com.office.ex.controller.GameController;` 필요.
 *
 *  ● 호출 사슬 (Controller → Service → 도메인 객체)
 *    MainClass 가 GameController 를, GameController 가 GameService 를,
 *    GameService 가 Rabbit·Turtle 을 부릅니다. ex20260810 member
 *    폴더에서 본 역할 분담과 같은 그림입니다.
 *
 *  ● "[클래스] 메서드()" 로그
 *    Rabbit/Turtle 의 메서드가 시작하자마자 찍는 줄. 기능이 아니라
 *    "호출이 어디까지 갔는지" 눈으로 따라가는 용도입니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * package com.office.ex.main;
 * import com.office.ex.controller.GameController;
 *
 * public class MainClass {
 *     public static void main(String[] args) {
 *         new GameController().execute();
 *     }
 * }
 *
 *   [정상]
 *   맨 위 package 선언으로 "나는 main 패키지 소속" 임을 밝히고,
 *   import 로 controller 패키지의 GameController 를 가져옵니다.
 *   main() 은 GameController 객체를 만들어 execute() 를 바로
 *   실행합니다. 이후 흐름은 controller/GameController(해석).java 로
 *   이어집니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   숫자를 입력받아 1이면 토끼·거북이가 "달리고", 2면 "멈춥니다".
 *   자세한 출력은 GameController(해석) / Rabbit(해석) / Turtle(해석)
 *   참고.
 */
