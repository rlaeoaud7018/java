/*
 * ===================================================================
 *  MemberServicePjt / member/MemberService.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. "실제 처리 로직 담당" 입니다.
 *   프로젝트 개요는 IConfig(해석).java 를, 인터페이스/다형성 개념은
 *   IMemberDao(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   (1) DAO 를 인터페이스 타입(IMemberDao)으로 받아, 어떤 구현체가
 *       오든 똑같이 쓰기, (2) 로그인 판정(아이디 존재 + 비번 일치),
 *       (3) 수정·삭제 시 "대상 회원 ID" 를 세션에서 가져오기.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● implements IMember
 *    IMember 의 결과 상수(MEMBER_SIGN_UP_SUCCESS 등)를 이름으로
 *    쓰려고 붙였습니다.
 *
 *  ● IMemberDao 타입 필드 + 생성자 주입
 *    `IMemberDao memberDao;` — 필드 타입이 "구체 클래스" 가 아니라
 *    "인터페이스" 입니다. 생성자 `MemberService(IMemberDao iMemberDao)`
 *    로 밖에서 구현체를 받아 이 필드에 넣습니다. 그래서 이 클래스는
 *    MemberDao 인지 MemberDaoForOracle 인지 몰라도, memberDao 의
 *    메서드 4개만 부르면 됩니다(다형성). MemberController 가 무엇을
 *    주입했느냐에 따라 실제 동작이 달라집니다.
 *
 *  ● 반환값으로 성공/실패 알리기
 *    doSignUp/doModify/doRemove 는 int(DB 에서 처리된 행 수. 0 이면
 *    실패, 1 이상이면 성공), doSignIn 은 boolean(true/false)을
 *    돌려줍니다.
 *
 *  ● switch (result) 로 결과 분기
 *    가입 결과 숫자를 switch 로 나눠 메시지를 출력합니다.
 *    `case MEMBER_SIGN_UP_SUCCESS:` = case 1, `case MEMBER_SIGN_UP_FAIL:`
 *    = case 0. 그 외 값은 default 로.
 *
 *  ● .equals() (비번 비교)
 *    저장된 비번과 입력한 비번의 "내용" 이 같은지 == 가 아니라
 *    .equals() 로 확인합니다.
 *
 *  ● 세션에서 로그인 ID 가져오기
 *    `SessionClass.getInstance().getSignInedMemberId()` — 수정·삭제
 *    는 "지금 로그인한 사람" 을 대상으로 하므로, 그 ID 를 세션에서
 *    읽어 DTO 에 채우거나 DAO 에 넘깁니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * // IMemberDao memberDao = new MemberDao();
 * // IMemberDao memberDao = new MemberDaoForOracle();
 * IMemberDao memberDao;
 * public MemberService(IMemberDao iMemberDao) {
 *     this.memberDao = iMemberDao;
 * }
 *
 *   [의도된 장치 + 정상]
 *   주석 처리된 두 줄은 "DAO 를 이 클래스 안에서 직접 new 하던"
 *   방식입니다. 그렇게 하면 DB 를 바꿀 때 이 파일을 고쳐야 합니다.
 *   그래서 그걸 버리고, 아래처럼 필드는 타입만 선언하고(IMemberDao
 *   memberDao;) 실제 구현체는 생성자로 밖에서 받습니다. 이제 DB 를
 *   바꿔도 이 파일은 안 건드리고 MemberController 의 주입 한 줄만
 *   바꾸면 됩니다.
 *
 *
 * public int doSignUp(MemberDto memberDto) {
 *     System.out.println("[MemberService] doSignUp()");
 *     int result = memberDao.insertNewMember(memberDto);
 *     switch (result) {
 *     case MEMBER_SIGN_UP_SUCCESS: System.out.println("... SUCCESS!!"); break;  // 1
 *     case MEMBER_SIGN_UP_FAIL:    System.out.println("... FAIL!!");    break;  // 0
 *     default:                     System.out.println("... FAIL!!");    break;
 *     }
 *     return result;
 * }
 *
 *   [정상]
 *   DAO 에 회원 추가를 시키고(memberDao.insertNewMember), 돌아온
 *   행 수(result)를 switch 로 나눠 메시지를 출력한 뒤 그대로
 *   돌려줍니다. 위쪽에 주석 처리된 if/else 버전은 [의도된 장치] —
 *   "같은 판정을 if/else 로도, switch 로도 쓸 수 있다" 비교입니다.
 *
 *
 * public boolean doSignIn(MemberDto memberDto) {
 *     System.out.println("[MemberService] doSignIn()");
 *     MemberDto selectedMemberDto = memberDao.selectMemberByMemId(memberDto.getMemId());
 *
 *     if (selectedMemberDto != null) {
 *         if (selectedMemberDto.getMemPw().equals(memberDto.getMemPw())) {
 *             System.out.println("... SIGN-IN SUCCESS!!");
 *             return MEMBER_SIGN_IN_SUCCESS;   // true
 *         } else {
 *             System.out.println("... SIGN-IN FAIL!!");  System.out.println("... PW NG!!");
 *             return MEMBER_SIGN_IN_FAIL;      // false
 *         }
 *     } else {
 *         System.out.println("... SIGN-IN FAIL!!");  System.out.println("... ID NG!!");
 *         return MEMBER_SIGN_IN_FAIL;          // false
 *     }
 * }
 *
 *   [정상 — 2단계 판정]
 *   1) DAO 로 아이디를 조회. 결과가 null 이면 그런 아이디 없음
 *      → 실패("ID NG").
 *   2) 있으면, 저장된 비번과 입력한 비번을 .equals() 로 비교.
 *      같으면 성공, 다르면 실패("PW NG").
 *   위쪽에 주석 처리된 짧은 버전은 [의도된 장치] — "처음엔 아이디
 *   존재 여부만 봤다가, 비번 확인까지 하는 버전으로 발전" 시킨
 *   흔적입니다.
 *
 *
 * public int doModify(MemberDto memberDtoForModify) {
 *     System.out.println("[MemberService] doModify()");
 *     memberDtoForModify.setMemId(SessionClass.getInstance().getSignInedMemberId());
 *     int result = memberDao.updateMember(memberDtoForModify);
 *     if (result > 0) { ... SUCCESS ... } else { ... FAIL ... }
 *     return result;
 * }
 *
 *   [정상]
 *   MemberController 는 이 DTO 의 memId 를 null 로 넘겼습니다. 여기서
 *   `setMemId(세션의 로그인 ID)` 로 그 빈칸을 채웁니다 — "수정 대상은
 *   지금 로그인한 사람" 이라는 규칙을 Service 가 강제하는 셈입니다.
 *   그 다음 DAO 로 UPDATE 를 시킵니다.
 *
 *
 * public int doRemove() {
 *     System.out.println("[MemberService] doRemove()");
 *     int result = memberDao.deleteMemberByMemId(SessionClass.getInstance().getSignInedMemberId());
 *     if (result > 0) { ... SUCCESS ... } else { ... FAIL ... }
 *     return result;
 * }
 *
 *   [정상] 탈퇴. 지울 대상 아이디를 세션에서 바로 읽어 DAO 의
 *   DELETE 에 넘깁니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   각 메서드는 로그를 찍고 DAO 를 부릅니다. 실제 성공/실패는
 *   주입된 DAO 가 무엇이냐에 달렸습니다 — MemberDaoForOracle 이면
 *   전부 0/null 이라 "FAIL", MemberDao(MySQL) + DB 준비 시 실제
 *   결과가 나옵니다.
 */
