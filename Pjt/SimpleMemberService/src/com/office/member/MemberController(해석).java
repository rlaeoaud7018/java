/*
 * ===================================================================
 *  SimpleMemberService / MemberController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 프로젝트 개요·"객체 재사용"
 *   개념은 MainClass(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   MemberService 를 while 반복 "밖" 에서 한 번만 만들어, 모든
 *   메뉴가 같은 Service(→ 같은 DAO → 같은 회원 명단)를 쓰게 하는 것.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● Scanner / nextInt() 다음 nextLine()
 *    숫자를 읽은 뒤 남는 줄바꿈을 버리려고 nextInt() 다음에
 *    nextLine() 을 한 번 더 부릅니다. (ex20260810 MemberController
 *    (해석) 에 자세히.)
 *
 *  ● boolean 플래그 / while / switch / case / break
 *    systemFlag 가 true 인 동안 while 반복. 메뉴 번호로 switch 분기.
 *
 *  ● 반복 "밖" 에서 만든 지역변수
 *    `MemberService memberService = new MemberService();` 가 while
 *    앞에 있습니다. 그래서 while 이 도는 내내 같은 memberService
 *    하나를 씁니다. 이게 이 프로젝트의 요점입니다.
 *
 *  ● MemberDto 생성자
 *    (memId, memPw, memMail) 3개짜리 하나뿐입니다. 로그인 때는
 *    메일이 없으므로 세 번째 자리에 null 을 넣습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public void execute() {
 *     System.out.println("[MemberController] execute()");
 *     boolean systemFlag = true;
 *     String memId; String memPw; String memMail;
 *     MemberDto memberDto = null;
 *     MemberService memberService = new MemberService();   // ★ while 밖, 한 번만
 *     Scanner scanner = new Scanner(System.in);
 *
 *   [정상 — 이 파일의 핵심 줄]
 *   memberService 를 while 시작 "전에" 딱 한 번 만듭니다. 이
 *   memberService 안에는 MemberDao 가 하나 들어 있고(MemberService
 *   (해석) 참고), 그 DAO 안에는 회원 명단 ArrayList 가 하나
 *   들어 있습니다. 이 셋이 프로그램 내내 그대로 유지됩니다.
 *
 *
 *     while (systemFlag) {
 *         ... 메뉴 출력, 번호 읽기, nextLine() 으로 줄바꿈 버리기 ...
 *
 *         case 1:            // sign-up
 *             ... ID/PW/MAIL 입력받기 ...
 *             memberDto = new MemberDto(memId, memPw, memMail);
 * //           memberService = new MemberService();
 *             memberService.doSignUp(memberDto);
 *             break;
 *
 *   [정상] 회원가입. 입력값 3개를 MemberDto 로 묶어 doSignUp 에
 *   넘깁니다.
 *   [의도된 장치] 주석 처리된 `// memberService = new MemberService();`
 *   가 "옛 방식" 입니다. 이걸 살리면 메뉴를 고를 때마다 Service 가
 *   새로 만들어져 회원 명단이 매번 초기화됩니다(= ex20260810
 *   member 의 버그). 그래서 주석으로 막고, 위에서 한 번 만든
 *   것을 그대로 씁니다.
 *
 *
 *         case 2:            // sign-in
 *             ... ID/PW 입력받기 ...
 *             memberDto = new MemberDto(memId, memPw, null);
 * //           memberService = new MemberService();
 *             memberService.doSignIn(memberDto);
 *             break;
 *
 *   [정상] 로그인. 메일 자리에 null 을 넣어 DTO 를 만들고 doSignIn
 *   에 넘깁니다. 여기도 `// memberService = new MemberService();` 는
 *   [의도된 장치] 옛 방식이라 주석 처리돼 있습니다. 같은
 *   memberService 를 쓰므로, case 1 에서 가입한 회원이 이 case 2
 *   에서 그대로 조회됩니다.
 *
 *
 *         case 3:  break;    // modify
 *         case 4:  break;    // delete
 *
 *   [미완성 — 오류 아님] 자리만 있고 내용은 없습니다.
 *
 *
 *         case 99:           // system-out
 *             systemFlag = false;
 *             System.out.println("Good bye~");
 *             break;
 *     }
 *     scanner.close();
 *
 *   [정상] 99 로 반복 종료 후 Scanner 닫음.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   1로 가입(→ [MemberService] doSignUp() → [MemberDao] insertMember())
 *   한 뒤 2로 로그인하면, 같은 회원 명단을 조회하므로 아이디·비번이
 *   맞으면 "SIGN-IN SUCCESS!!" 가 출력됩니다.
 */
