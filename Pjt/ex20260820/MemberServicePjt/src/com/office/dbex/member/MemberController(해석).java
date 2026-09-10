/*
 * ===================================================================
 *  MemberServicePjt / member/MemberController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 콘솔 메뉴로 회원 기능을 돌리는
 *   "사용자와 대화하는 담당" 입니다. 프로젝트 개요는 IConfig(해석).java
 *   를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   (1) 인터페이스 상수를 이름으로 쓰기(implements IMember, IConfig),
 *   (2) DAO 구현체를 여기서 골라 Service 에 주입하기,
 *   (3) 세션(로그인 상태)에 따라 메뉴를 다르게 보여주기.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● implements IMember, IConfig (인터페이스 여러 개)
 *    한 클래스가 인터페이스 여러 개를 동시에 implements 할 수
 *    있습니다. 여기서는 두 인터페이스의 상수(MEMBER_SIGN_UP,
 *    SYSTEM_OUT 등)를 이름만으로 쓰려고 붙였습니다.
 *
 *  ● 구현체 주입 (new MemberService(new MemberDaoForOracle()))
 *    MemberService 는 IMemberDao 규격만 알면 됩니다. 실제로 어떤
 *    DAO 를 쓸지는 "여기서" 골라 생성자에 넣어 줍니다. 지금은
 *    MemberDaoForOracle 을 넣고 있습니다.
 *
 *  ● 세션(session) — 로그인 상태 유지
 *    SessionClass 는 프로그램 전체에서 딱 하나만 존재하는 객체
 *    (싱글턴)이고, 그 안에 "지금 로그인한 회원 ID" 를 담아 둡니다.
 *    `SessionClass.getInstance().getSignInedMemberId()` 로 어디서든
 *    그 값을 읽습니다. null 이면 "로그인 안 함". (SessionClass(해석)
 *    참고.)
 *
 *  ● Scanner / nextInt() 다음 nextLine()
 *    키보드 입력 도구. 숫자를 읽은 뒤 남는 줄바꿈을 버리려고
 *    nextInt() 다음에 nextLine() 을 한 번 더 부릅니다.
 *    (ex20260810 MemberController(해석) 에 자세히.)
 *
 *  ● switch 의 case 에 상수 이름 쓰기
 *    `case MEMBER_SIGN_UP:` 는 `case 1:` 과 같습니다. 숫자 대신
 *    이름을 써서 "무슨 메뉴인지" 읽히게 한 것.
 *
 *  ● DTO 생성자 오버로딩 골라 쓰기
 *    가입은 4개짜리(id, pw, mail, phone), 로그인은 2개짜리(id, pw),
 *    수정은 4개짜리인데 id 자리에 null — MemberDto 의 여러 생성자
 *    중 상황에 맞는 걸 고릅니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public class MemberController implements IMember, IConfig {
 *
 *     public void execute() {
 *         Scanner scanner = new Scanner(System.in);
 *         boolean systemFlag = true;
 *         MemberService memberService = new MemberService(new MemberDaoForOracle());
 *
 *   [정상, 단 주의]
 *   Scanner 와 "계속 실행 중" 플래그를 준비하고, MemberService 를
 *   하나 만듭니다. 이때 생성자에 `new MemberDaoForOracle()` 을
 *   넣습니다 = "이 서비스는 Oracle 용 DAO 를 쓴다" 고 여기서 정한
 *   것입니다.
 *   [미완성] 다만 MemberDaoForOracle 은 아직 내용이 빈 껍데기라
 *   (전부 return 0 / return null), 실제로는 DB 에 아무 일도
 *   안 일어납니다. MySQL 로 진짜 동작을 보려면 이 줄을
 *   `new MemberService(new MemberDao())` 로 바꾸면 됩니다 — 나머지
 *   코드는 한 글자도 안 고쳐도 됩니다(둘 다 IMemberDao 규격을
 *   지키므로). 이게 인터페이스로 갈아끼우기의 핵심입니다.
 *
 *
 *         while (systemFlag) {
 *             if (SessionClass.getInstance().getSignInedMemberId() == null) {
 *                 System.out.println("1.SIGN-UP    2.SIGN-IN    99.SYSTEM-OUT");
 *             } else {
 *                 System.out.println("3.MODIFY    4.DELETE    5.SIGN-OUT    99.SYSTEM-OUT");
 *             }
 *             int userSelectedMenuNumber = scanner.nextInt();
 *             scanner.nextLine();
 *
 *   [정상 — 세션에 따른 메뉴 분기]
 *   세션의 로그인 ID 가 null 이면(= 아직 로그인 안 함) 가입/로그인
 *   메뉴를, null 이 아니면(= 로그인 중) 수정/삭제/로그아웃 메뉴를
 *   보여줍니다. 그리고 메뉴 번호를 하나 읽고, 남은 줄바꿈을 버립니다.
 *
 *
 *         switch (userSelectedMenuNumber) {
 *         case MEMBER_SIGN_UP:
 *             ... ID/PW/MAIL/PHONE 를 nextLine() 으로 입력받음 ...
 *             MemberDto memberDtoForSingUp = new MemberDto(memId, memPw, memMail, memPhone);
 *             int resultForSignUp = memberService.doSignUp(memberDtoForSingUp);  // 0 실패 / 1 성공
 *             if (resultForSignUp > MEMBER_SIGN_UP_FAIL) {  // > 0
 *                 System.out.println("[MemberController] NEW MEMBER SIGN-UP SUCCESS!!");
 *             } else {
 *                 System.out.println("[MemberController] NEW MEMBER SIGN-UP FAIL!!");
 *             }
 *             break;
 *
 *   [정상] 회원가입. 입력값 4개를 4개짜리 생성자로 묶어 Service 의
 *   doSignUp 에 넘깁니다. 돌아온 값이 0(MEMBER_SIGN_UP_FAIL)보다
 *   크면 성공. ([단순 실수 — 무해] 변수 이름 memberDtoForSingUp 은
 *   SignUp 의 오타지만 이름일 뿐이라 동작에 영향 없음.)
 *
 *
 *         case MEMBER_SIGN_IN:
 *             ... ID/PW 입력받음 ...
 *             MemberDto memberDtoForSingIn = new MemberDto(memId, memPw);
 *             boolean resultForSignIn = memberService.doSignIn(memberDtoForSingIn);
 *             if (resultForSignIn) {
 *                 System.out.println("... SIGN-IN SUCCESS!!");
 *                 SessionClass session = SessionClass.getInstance();
 *                 session.setSignInedMemberId(memId);
 *             } else {
 *                 System.out.println("... SIGN-IN FAIL!!");
 *                 SessionClass.getInstance().setSignInedMemberId(null);
 *             }
 *             break;
 *
 *   [정상 — 로그인 + 세션 저장]
 *   ID/PW 를 2개짜리 생성자로 묶어 doSignIn 에 넘깁니다. 성공하면
 *   세션에 로그인한 memId 를 저장합니다 → 다음 while 바퀴부터
 *   메뉴가 "수정/삭제/로그아웃" 으로 바뀝니다. 실패하면 세션을
 *   null 로 둡니다.
 *
 *
 *         case MEMBER_MODIFY:
 *             ... PW/MAIL/PHONE 입력받음 (ID 는 안 받음) ...
 *             MemberDto memberDtoForModify = new MemberDto(null, memPw, memMail, memPhone);
 *             int resultForModify = memberService.doModify(memberDtoForModify);
 *             ... result > 0 이면 성공 출력 ...
 *             break;
 *
 *   [정상] 정보 수정. ID 는 안 물어봅니다 — 이미 로그인 중이니까
 *   "누구를 수정할지" 는 세션이 압니다. 그래서 생성자 첫 인자에
 *   null 을 넣고, 실제 memId 는 Service 의 doModify 안에서 세션
 *   값으로 채웁니다(MemberService(해석) 참고).
 *
 *
 *         case MEMBER_DELETE:
 *             int reusltForRemove = memberService.doRemove();
 *             if (reusltForRemove > 0) {
 *                 System.out.println("... DELETE SUCCESS!!");
 *                 SessionClass.getInstance().setSignInedMemberId(null);
 *             } else { ... FAIL ... }
 *             break;
 *
 *   [정상] 탈퇴. 입력을 아예 안 받고 바로 doRemove() 를 부릅니다
 *   (지울 대상은 세션의 로그인 ID). 성공하면 세션을 null 로 비워
 *   로그아웃 상태로 되돌립니다. ([단순 실수 — 무해] 변수 이름
 *   reuslt 오타, 동작 영향 없음.)
 *
 *
 *         case MEMBER_SIGN_OUT:
 *             SessionClass.getInstance().setSignInedMemberId(null);
 *             break;
 *
 *   [정상] 로그아웃 = 세션의 로그인 ID 를 null 로. 다음 바퀴부터
 *   메뉴가 다시 가입/로그인으로 돌아갑니다.
 *
 *
 *         case SYSTEM_OUT:
 *             systemFlag = false;
 *             System.out.println("Say Gooooooo...d bye~!");
 *             break;
 *         }
 *     }
 *     scanner.close();
 *
 *   [정상] 99 를 누르면 플래그를 false 로 → while 종료 → Scanner
 *   닫음.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   메뉴가 뜨고, 로그인 전에는 1/2/99, 로그인 후에는 3/4/5/99 가
 *   보입니다. 다만 지금은 Oracle DAO(빈 껍데기)가 주입돼 있어서,
 *   가입/로그인/수정/삭제 모두 DAO 가 0 또는 null 을 돌려줍니다 →
 *   대부분 "FAIL" 로 출력됩니다. MySQL DAO(new MemberDao())로
 *   바꾸고 DB 가 준비돼 있으면 실제로 동작합니다.
 */
