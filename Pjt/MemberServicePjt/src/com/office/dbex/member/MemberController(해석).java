/*
 * ===================================================================
 *  MemberServicePjt(루트) / member/MemberController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 프로젝트는 ex20260820
 *   MemberServicePjt 의 단순형입니다. 콘솔 메뉴로 회원 기능을 돌리는
 *   담당입니다.
 *
 *   이 파일이 (이 버전에서) 가르치려는 것:
 *   인터페이스 상수를 case 로 쓰고(implements IMember, IConfig),
 *   세션의 로그인 여부로 메뉴를 바꾸고, 각 메뉴에서 입력받아
 *   Service 를 부른다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● implements IMember, IConfig
 *    두 인터페이스의 상수(MEMBER_SIGN_UP, SYSTEM_OUT 등)를 이름
 *    으로 쓰려고 붙였습니다.
 *
 *  ● 세션(로그인 상태) — SessionClass 싱글턴
 *    `SessionClass.getInstance().getSignInedMemberId()` 가 null 이면
 *    로그인 안 함, 값이 있으면 로그인 중. 이걸로 메뉴를 갈라
 *    보여줍니다. (SessionClass(해석) 참고.)
 *
 *  ● Scanner / nextInt() 다음 nextLine()
 *    숫자 읽은 뒤 남는 줄바꿈 버리기.
 *
 *  ● DTO 생성자 골라 쓰기
 *    가입 = 4개(id,pw,mail,phone), 로그인 = 2개(id,pw), 수정 =
 *    4개인데 id 자리에 null(세션에서 채움).
 *
 *  ● ex20260820 버전과의 차이
 *    - `new MemberService()` — 인자 없이 만듭니다(이 버전 Service
 *      는 생성자가 없음). ex20260820 은 `new MemberService(new
 *      MemberDaoForOracle())` 로 DAO 를 주입합니다.
 *    - case MEMBER_DELETE 가 비어 있습니다(삭제 미구현). ex20260820
 *      은 여기에 doRemove() 호출이 들어갑니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public class MemberController implements IMember, IConfig {
 *     public void execute() {
 *         Scanner scanner = new Scanner(System.in);
 *         boolean systemFlag = true;
 *         MemberService memberService = new MemberService();   // 인자 없이
 *
 *   [정상] Scanner, 플래그, 그리고 Service 를 while 밖에서 한 번만
 *   만듭니다(SimpleMemberService 에서 배운 "한 번만 만들어 재사용").
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
 *   로그인 전이면 가입/로그인, 로그인 후면 수정/삭제/로그아웃 메뉴.
 *
 *
 *         case MEMBER_SIGN_UP:
 *             ... ID/PW/MAIL/PHONE 입력 ...
 *             MemberDto memberDtoForSingUp = new MemberDto(memId, memPw, memMail, memPhone);
 *             int resultForSignUp = memberService.doSignUp(memberDtoForSingUp);
 *             if (resultForSignUp > MEMBER_SIGN_UP_FAIL) { ... SUCCESS ... } else { ... FAIL ... }
 *             break;
 *
 *   [정상] 가입. 4개 입력 → DTO → doSignUp. 결과 > 0 이면 성공.
 *   ([단순 실수, 무해] 변수명 SingUp 오타.)
 *
 *
 *         case MEMBER_SIGN_IN:
 *             ... ID/PW 입력 ...
 *             MemberDto memberDtoForSingIn = new MemberDto(memId, memPw);
 *             boolean resultForSignIn = memberService.doSignIn(memberDtoForSingIn);
 *             if (resultForSignIn) {
 *                 SessionClass.getInstance().setSignInedMemberId(memId);   // 세션 저장
 *             } else {
 *                 SessionClass.getInstance().setSignInedMemberId(null);
 *             }
 *             break;
 *
 *   [정상 — 로그인 + 세션 저장]
 *   2개짜리 생성자로 DTO 를 만들어 doSignIn. 성공하면 세션에 로그인
 *   ID 를 저장 → 다음 바퀴부터 메뉴가 수정/삭제/로그아웃으로 바뀜.
 *
 *
 *         case MEMBER_MODIFY:
 *             ... PW/MAIL/PHONE 입력 (ID 는 안 받음) ...
 *             MemberDto memberDtoForModify = new MemberDto(null, memPw, memMail, memPhone);
 *             int resultForModify = memberService.doModify(memberDtoForModify);
 *             ... result > 0 이면 성공 출력 ...
 *             break;
 *
 *   [정상] 수정. ID 는 세션이 아니까 null 로 넘기고, Service 의
 *   doModify 가 세션 값으로 채웁니다.
 *
 *
 *         case MEMBER_DELETE:
 *             break;
 *
 *   [미완성 — 오류 아님] 이 버전에는 삭제 기능이 없습니다. case 만
 *   있고 내용은 없습니다. ex20260820 은 여기에 memberService.doRemove()
 *   호출과 세션 비우기가 들어갑니다.
 *
 *
 *         case MEMBER_SIGN_OUT:
 *             SessionClass.getInstance().setSignInedMemberId(null);
 *             break;
 *         case SYSTEM_OUT:
 *             systemFlag = false;
 *             System.out.println("Say Gooooo...d bye~!");
 *             break;
 *     }
 *     scanner.close();
 *
 *   [정상] 로그아웃 = 세션 비우기. 99 = 반복 종료 후 Scanner 닫음.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   가입 → 로그인(성공 시 세션 저장, 메뉴 전환) → 수정 → 로그아웃
 *   → 종료. 삭제 메뉴는 눌러도 아무 일이 안 일어납니다(미구현).
 *   실제 DB 성공/실패는 MySQL + TBL_MEMBER 준비 여부에 달렸습니다.
 */
