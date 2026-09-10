/*
 * ===================================================================
 *  member/MemberController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. "사용자와 대화하는 담당" 파일
 *   입니다. 폴더 전체 구조와 공통 용어는 MainClass(해석).java 의
 *   "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   Controller 의 역할 — 메뉴를 보여주고, 입력을 받고, 그 값을
 *   DTO 에 담아 Service 에 "넘기기" 만 한다. 실제 가입/로그인 판정은
 *   여기서 안 한다. 그리고 콘솔 입력(Scanner)과 메뉴 반복(while +
 *   switch)의 기본 골격.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● import
 *    다른 패키지 클래스를 이 파일에서 쓰겠다는 선언.
 *    `import java.util.Scanner;` 는 자바 기본 제공 Scanner 를
 *    가져옵니다. (같은 패키지의 MemberDto/MemberService 는 import
 *    없이 바로 씀.)
 *
 *  ● Scanner
 *    키보드 입력을 읽는 도구. `new Scanner(System.in)` 으로 만듭니다.
 *      nextInt()  : 정수 한 개를 읽음
 *      nextLine() : 한 줄(문자열)을 통째로 읽음
 *
 *  ● nextInt() 다음에 nextLine() 을 한 번 더 부르는 이유
 *    사용자가 숫자를 치고 Enter 를 누르면, nextInt() 는 숫자만
 *    읽고 그 Enter(줄바꿈 문자)는 안 읽고 남깁니다. 바로 다음에
 *    nextLine() 을 부르면 그 남은 줄바꿈이 먼저 잡혀서 빈 문자열이
 *    들어갑니다. 그래서 nextInt() 뒤에 nextLine() 을 한 번 더 불러
 *    남은 줄바꿈을 "버리는" 것입니다.
 *
 *  ● boolean 플래그(flag)
 *    true / false 만 담는 변수로 "계속할까 / 멈출까" 를 표시.
 *    여기선 systemFlag. `while (systemFlag)` 는 그게 true 인 동안
 *    반복.
 *
 *  ● while 반복문
 *    괄호 안 조건이 true 인 동안 블록을 계속 되풀이.
 *
 *  ● switch / case / break
 *    값 하나를 여러 경우로 나눠 분기. `case 1:` 은 값이 1 일 때.
 *    `break` 는 그 분기를 끝내고 switch 를 빠져나옴(없으면 아래
 *    case 로 줄줄이 흘러 들어감).
 *
 *  ● 지역변수(local variable) / null 로 초기화
 *    메서드 안에서만 사는 변수(memId, memberDto, scanner 등).
 *    `MemberDto memberDto = null;` 은 "변수는 만들되 아직 아무
 *    객체도 안 가리킨다" 는 뜻 — 나중에 실제 객체를 대입.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * import java.util.Scanner;
 *
 *   키보드 입력을 읽는 Scanner 를 가져옵니다.
 *
 *
 * public void execute() {
 *     System.out.println("[MemberController] execute()");
 *
 *   MainClass 가 부른 그 메서드. 첫 줄은 흐름 추적용 로그.
 *
 *
 *     boolean systemFlag = true;
 *     String memId;
 *     String memPw;
 *     String memMail;
 *     MemberDto memberDto = null;
 *     MemberService memberService = null;
 *     Scanner scanner = new Scanner(System.in);
 *
 *   "프로그램 계속 실행 중" 플래그, 사용자가 입력할 값을 잠깐 담을
 *   지역변수 3개, DTO·Service 를 담을 자리(아직 객체 없어서 null),
 *   그리고 키보드 입력 도구 scanner 를 준비합니다.
 *
 *
 *     while (systemFlag) {
 *         System.out.println("1.SIGN-UP  2.SIGN-IN  3.MODIFY  4.DELETE  99.SYSTEM-OUT");
 *         System.out.println("Please MENU NUMBER!!");
 *         int selectedMenuNumber = scanner.nextInt();
 *         scanner.nextLine();
 *
 *   메뉴를 출력하고 번호를 하나 읽습니다. scanner.nextLine() 한 줄은
 *   "0. 개념 정리" 에 적은 그 이유 — nextInt() 가 안 읽고 남긴
 *   줄바꿈을 버리는 역할입니다. 이게 없으면 아래 case 안의 첫
 *   nextLine() 이 빈 문자열을 받아버립니다.
 *
 *
 *         case 1:            // sign-up (회원가입)
 *             System.out.println("Please new member ID!!");
 *             memId = scanner.nextLine();
 *             ... PW, MAIL 도 같은 방식 ...
 *             memberDto = new MemberDto(memId, memPw, memMail);
 *             memberService = new MemberService();
 *             memberService.doSignUp(memberDto);
 *             break;
 *
 *   [정상] 회원가입 흐름. 아이디·비번·메일을 nextLine() 으로 읽어
 *   지역변수에 담고, 그 3개를 인자로 넘겨 MemberDto(값 봉투)를
 *   만듭니다. 그리고 MemberService 객체를 새로 만들어 그 봉투를
 *   doSignUp() 에 넘깁니다. 여기까지가 Controller 의 일 — 실제
 *   저장은 Service 와 Dao 가 합니다.
 *
 *
 *         case 2:            // sign-in (로그인)
 *             ... ID, PW 만 읽음 ...
 *             memberDto = new MemberDto(memId, memPw, null);
 *             memberService = new MemberService();
 *             memberService.doSignIn(memberDto);
 *             break;
 *
 *   [정상] 로그인 흐름. 메일은 안 물어보므로 봉투의 메일칸에 null.
 *   MemberService 를 새로 만들어 doSignIn() 에 봉투를 넘김.
 *
 *
 *         case 3:  break;    // modify (수정)
 *         case 4:  break;    // delete (삭제)
 *
 *   [미완성 — 오류 아님] case 자리만 잡아두고 내용은 아직 안 채웠
 *   습니다. 원래대로면 여기서도 봉투를 만들어
 *   memberService.doModify(...) / doDelete(...) 같은 걸 불렀을
 *   것입니다.
 *
 *
 *         case 99:          // system-out (종료)
 *             systemFlag = false;
 *             System.out.println("Good bye~");
 *             break;
 *
 *   [정상] systemFlag 를 false 로 → 다음 while 조건 검사에서 반복
 *   종료.
 *
 *
 *     scanner.close();
 *
 *   반복이 끝나면 키보드 입력 도구를 닫습니다.
 *
 *
 * ###################################################################
 *  2. 이 단계에서 알아둘 점 (오류는 아님, MemberDao(해석)에서 이어짐)
 * ###################################################################
 *
 *   메뉴를 고를 때마다 `memberService = new MemberService();` 로
 *   Service 를 매번 새로 만듭니다. 그리고 Service 는 또 매번
 *   MemberDao 를 새로 만들고, MemberDao 의 "회원 명단" 은 그 객체가
 *   생길 때 빈 상태로 시작합니다. 그래서 지금 구조로는 "가입 →
 *   로그인" 을 이어서 해도 로그인이 명단을 못 찾습니다.
 *   이건 실수가 아니라, 이 수업의 목표가 호출 사슬
 *   (Controller → Service → Dao → Dto)을 익히는 것이라 데이터 유지
 *   (영속성)를 다음 단계로 미뤄둔 것입니다. 그 "다음 단계" 의
 *   해법(의존성 주입)은 ex20260812 의 CoolingController 에서
 *   실제로 나옵니다. 자세한 이유는 MemberDao(해석).java.
 */
