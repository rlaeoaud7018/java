/*
 * ===================================================================
 *  controller/CoolingController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. main 메서드가 없는 "제어 담당"
 *   파일이고, main/MainClass.java가 만들어서 씁니다. 다른 패키지의
 *   CoolingSystem.java를 받아서 조작합니다(CoolingSystem(해석).java
 *   참고).
 *
 *   이 파일이 가르치려는 것:
 *   "의존성 주입(dependency injection)" — 필요한 객체를 이 클래스가
 *   직접 new 로 만들지 않고, 밖에서 만들어진 것을 생성자로 받아
 *   계속 쓴다. (ex20260810 의 MemberController 가 매번 new 해서
 *   생긴 문제와 정확히 대비되는 부분입니다.) 그리고 메뉴 반복
 *   (while + switch)과 getter/setter 로만 대상 객체를 만지는 것.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 필드로 다른 객체를 들고 있기
 *    `private CoolingSystem coolingSystem;` — 이 컨트롤러 객체는
 *    자기 안에 CoolingSystem 객체 하나를 필드로 붙들고 있습니다.
 *    제어 대상(에어컨)을 계속 손에 쥐고 있는 셈입니다.
 *
 *  ● 의존성 주입 (dependency injection)
 *    필요한 객체를 이 클래스가 직접 `new` 로 만들지 않고, 밖에서
 *    이미 만들어진 것을 생성자 매개변수로 받아 쓰는 방식.
 *    여기선 CoolingController(CoolingSystem coolingSystem) 생성자가
 *    CoolingSystem 을 받아 필드에 저장합니다. 실제로 만드는 쪽은
 *    main/MainClass 입니다.
 *    → ex20260810 MemberController 는 메뉴마다 new MemberService()
 *      → new MemberDao() 로 매번 새로 만들어서, 저장한 데이터가
 *      사라졌습니다. 이쪽은 밖에서 하나 받아 계속 같은 것을 쓰므로
 *      상태가 유지됩니다. 대비해서 보세요.
 *
 *  ● this / 섀도잉
 *    생성자 매개변수 coolingSystem 이 필드 coolingSystem 과 이름이
 *    같아 필드가 가려지므로, `this.coolingSystem = coolingSystem;`
 *    으로 필드를 콕 집습니다.
 *
 *  ● Scanner / nextInt()
 *    키보드 입력 도구. nextInt() 는 정수 한 개를 읽습니다.
 *    참고: 여기서는 nextInt() 뒤에 nextLine() 으로 줄바꿈을 버리는
 *    코드가 없습니다. 그 처리는 "숫자를 읽은 뒤 곧바로 문자열을
 *    읽을 때" 필요한데, 이 파일은 이후에도 계속 nextInt() 만 쓰므로
 *    없어도 문제가 없습니다. (ex20260810 MemberController 는 숫자
 *    다음에 문자열을 읽어서 그 코드가 필요했습니다.)
 *
 *  ● boolean 플래그 / while / switch / case / break
 *    systemFlag 가 true 인 동안 while 이 반복. 사용자가 고른 번호를
 *    switch 로 분기. break 는 그 case 를 끝내고 switch 를 빠져나옴.
 *
 *  ● ! (논리 부정)
 *    boolean 을 뒤집음. `!coolingSystem.isPower()` = 현재 전원
 *    상태의 반대값.
 *
 *  ● 조작은 전부 getter/setter 를 통해서
 *    CoolingSystem 의 필드는 private 이라, 이 컨트롤러는 isPower() /
 *    setPower(...) / setTargetTemperature(...) /
 *    printSystemInfomation() 같은 공개 메서드로만 에어컨을 만집니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * private CoolingSystem coolingSystem;
 *
 * public CoolingController(CoolingSystem coolingSystem) {
 *     this.coolingSystem = coolingSystem;
 * }
 *
 *   [정상 — 의존성 주입]
 *   이 컨트롤러가 제어할 에어컨(CoolingSystem)을 필드로 하나 들고
 *   있고, 그 값은 생성자로 받습니다. 즉 컨트롤러는 에어컨을 직접
 *   만들지 않고 "누가 만들어 준 것" 을 받아서 씁니다. 매개변수와
 *   필드 이름이 같아 this. 로 필드를 지정합니다.
 *
 *
 * public void control() {
 *     Scanner scanner = new Scanner(System.in);
 *     boolean systemFlag = true;
 *     while (systemFlag) {
 *         System.out.println("Choose the function you want.");
 *         System.out.println("1. Power on/off    2. Temperature setting    3. The end");
 *         int selectedFunction = scanner.nextInt();
 *
 *   [정상]
 *   키보드 입력 도구와 "계속 실행 중" 플래그를 준비하고 반복을
 *   시작합니다. 매 바퀴 메뉴를 출력하고 번호를 하나 읽습니다.
 *
 *
 *         case 1:
 * //             boolean currentStatus = coolingSystem.isPower();
 * //             if (currentStatus) { coolingSystem.setPower(false); }
 * //             else               { coolingSystem.setPower(true); }
 *             coolingSystem.setPower(!coolingSystem.isPower());
 *             break;
 *
 *   [의도된 장치 + 정상] 1번: 전원 토글(켜져 있으면 끄고, 꺼져
 *   있으면 켜기). 주석 처리된 부분은 "현재 상태를 읽어서 → if/else
 *   로 반대로 세팅" 하는 풀어 쓴 버전입니다. 실제로 도는 한 줄은
 *   그걸 압축한 것 — isPower() 로 현재 값을 읽고, ! 로 뒤집어서,
 *   setPower(...) 로 그 반대값을 넣습니다. 결과는 같고 더 짧습니다.
 *
 *
 *         case 2:
 *             System.out.println("Enter the desired temperature.");
 *             int desiredTemperature = scanner.nextInt();
 *             coolingSystem.setTargetTemperature(desiredTemperature);
 *             break;
 *
 *   [정상] 2번: 원하는 온도를 하나 더 입력받아 setTargetTemperature
 *   에 넘깁니다. 18~30 범위로 자르거나 전원을 켜는 처리는 이
 *   컨트롤러가 안 하고 CoolingSystem 의 setter 안에서 알아서 합니다
 *   — 컨트롤러는 "값 전달" 만, "값 검사" 책임은 CoolingSystem 에
 *   있습니다(캡슐화).
 *
 *
 *         case 3:
 *             systemFlag = false;
 *             break;
 *
 *   [정상] 3번: systemFlag 를 false 로 → 다음 while 조건 검사에서
 *   반복 종료.
 *
 *
 *         coolingSystem.printSystemInfomation();
 *     }
 *     scanner.close();
 *
 *   switch 를 빠져나온 뒤, 매 바퀴 끝에 에어컨의 현재 상태 전체를
 *   출력합니다. 그래서 어떤 메뉴를 골랐든 그 직후 바뀐 상태를
 *   눈으로 확인할 수 있습니다. 반복이 끝나면 Scanner 를 닫습니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   메뉴가 뜨고, 1을 누르면 전원이 반대로 바뀌고 상태표가 출력,
 *   2를 누르면 온도를 입력받아(18~30로 잘림, 전원 자동 on) 상태표
 *   출력, 3을 누르면 상태표를 한 번 더 출력하고 반복이 끝납니다.
 *   전체 시작은 main/MainClass.java 에서 이어집니다.
 */
