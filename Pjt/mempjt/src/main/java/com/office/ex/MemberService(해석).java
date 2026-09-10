/*
 * ===================================================================
 *  mempjt / MemberService.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. request 에서 값을 꺼내 DTO 로
 *   묶고, DAO 를 시켜 DB 작업을 하는 담당입니다. 프로젝트 개요는
 *   MemberConfig(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   (1) DAO 를 인터페이스 타입(IMemberDao)으로 주입받아 쓰기,
 *   (2) request.getParameter 로 폼 값 꺼내기,
 *   (3) 로그인 판정 결과로 "회원 ID(성공)" 또는 "null(실패)" 을
 *       돌려주기 — Controller 가 그걸로 세션 저장 여부를 정함.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● IMemberDao 타입 필드 + 생성자 주입
 *    `private IMemberDao memberDao;` + `MemberService(IMemberDao
 *    memberDao)`. 어떤 DAO 구현이 오든 규격(IMemberDao)만 알면
 *    되므로 갈아끼우기가 쉽습니다. (ex20260820 에서 배운 내용.)
 *
 *  ● request.getParameter("이름")
 *    브라우저 폼이 보낸 값 하나를 이름으로 꺼냅니다. 항상 String.
 *
 *  ● 반환값의 의미
 *      addNewMember        : int (INSERT 된 행 수. 0 이면 실패)
 *      searchMember        : String (성공 시 회원 ID, 실패 시 null)
 *      getCurrentSigninedMember : MemberDto (회원 정보, 없으면 null)
 *      modifyMemberByMemNo : int (UPDATE 된 행 수)
 *
 *  ● .equals() (비번 비교)
 *    == 가 아니라 .equals() 로 내용 비교.
 *
 *  ● Integer.parseInt / String.valueOf
 *    문자열 → int / 객체 → 문자열. (MemberController(해석) 의 "0.
 *    개념 정리" 참고.)
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * private IMemberDao memberDao;
 * public MemberService(IMemberDao memberDao) {
 *     this.memberDao = memberDao;
 * }
 *
 *   [정상] 생성자로 DAO 구현체를 받아 필드에 저장합니다.
 *   Controller 는 `new MemberService(new MemberDao())` 로 부릅니다.
 *
 *
 * public int addNewMember(HttpServletRequest request, HttpServletResponse response) {
 *     String memId = request.getParameter("memId");
 *     ... memPw, memMail, memPhone 도 getParameter ...
 *     MemberDto memberDto = new MemberDto(memId, memPw, memMail, memPhone);
 *
 *     memberDao = new MemberDao();          // ← 이 줄 주의
 *     int result = memberDao.insertNewMember(memberDto);
 *     if (result > 0) { ... SUCCESS ... } else { ... FAIL ... }
 *     return result;
 * }
 *
 *   [정상 + 한 줄 실수]
 *   폼 값 4개를 꺼내 DTO 로 묶고, DAO 에 INSERT 를 시켜 결과를
 *   돌려줍니다.
 *   [단순 실수 — 동작엔 지장 없음] `memberDao = new MemberDao();`
 *   는 생성자로 주입받은 DAO 를 버리고 새 MemberDao 로 덮어씁니다.
 *   지금은 Controller 가 어차피 new MemberDao() 를 주입하므로
 *   결과가 같지만, "밖에서 주입받은 것을 쓴다" 는 이 프로젝트의
 *   취지와 어긋나는 줄입니다. 다른 메서드들(searchMember 등)은
 *   주입받은 memberDao 를 그대로 씁니다 — 이 줄만 지우면 일관성이
 *   맞습니다.
 *
 *
 * public String searchMember(HttpServletRequest request, HttpServletResponse response) {
 *     String memId = request.getParameter("memId");
 *     String memPw = request.getParameter("memPw");
 *     MemberDto selectedMemberDtoByMemId = memberDao.selectMemberByMemId(memId);
 *
 *     if (selectedMemberDtoByMemId != null) {
 *         if (selectedMemberDtoByMemId.getMemPw().equals(memPw)) {
 *             return selectedMemberDtoByMemId.getMemId();   // 성공: 회원 ID
 *         } else {
 *             return null;                                  // 비번 불일치
 *         }
 *     } else {
 *         return null;                                      // 아이디 없음
 *     }
 * }
 *
 *   [정상 — 로그인 판정]
 *   아이디로 회원을 조회 → 없으면 null. 있으면 저장된 비번과 입력
 *   비번을 .equals() 비교 → 맞으면 회원 ID 를, 틀리면 null 을
 *   돌려줍니다. Controller 는 이 반환값이 null 이 아니면 "로그인
 *   성공" 으로 보고 세션에 저장합니다.
 *   (주석 처리된 `// MemberDto memberDto = new MemberDto(memId,
 *   memPw);` 는 2개짜리 생성자를 쓰려다 만 흔적 — [의도된 장치].)
 *
 *
 * public MemberDto getCurrentSigninedMember(String signinedMemId) {
 *     MemberDto signinedMemberDto = memberDao.selectMemberByMemId(signinedMemId);
 *     return signinedMemberDto;
 * }
 *
 *   [정상] 세션에 저장돼 있던 로그인 ID 로 회원 정보를 통째로
 *   읽어 돌려줍니다. Controller 가 수정 폼을 열기 전에 이걸 불러
 *   화면에 미리 채울 데이터를 준비합니다.
 *
 *
 * public int modifyMemberByMemNo(HttpServletRequest request, HttpServletResponse response) {
 *     int memNo = Integer.parseInt(request.getParameter("memNo"));
 *     HttpSession session = request.getSession();
 *     Object object = session.getAttribute(MemberConfig.SINGINED_MEMBERID);
 *     String memId = String.valueOf(object);
 *     String memPw = request.getParameter("memPw");
 *     ... memMail, memPhone ...
 *     MemberDto memberDto = new MemberDto(memNo, memId, memPw, memMail, memPhone, null, null);
 *     int result = memberDao.updateMemberByMemNo(memberDto);
 *     if (result > 0) { ... SUCCESS ... } else { ... FAIL ... }
 *     return result;
 * }
 *
 *   [정상 — 수정]
 *   - memNo(회원 번호)는 폼에서 문자열로 오므로 Integer.parseInt 로
 *     정수 변환.
 *   - memId 는 폼이 아니라 "세션에 저장된 로그인 ID" 에서 가져옵니다
 *     (아무 회원이나 못 고치게). getAttribute 가 Object 라
 *     String.valueOf 로 문자열화.
 *   - 나머지 값(pw/mail/phone)은 폼에서 꺼내, 7개짜리 생성자로
 *     DTO 를 만들어(가입일·수정일 자리는 null) DAO 에 UPDATE 를
 *     시킵니다. WHERE 조건이 memNo 라서 memNo 가 꼭 필요합니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   각 메서드는 로그를 찍고 DAO 를 부릅니다. MySQL + tbl_member
 *   테이블이 준비돼 있으면 실제로 INSERT/SELECT/UPDATE 가 되고,
 *   로그인 성공 시 Controller 가 회원 ID 를 세션에 저장합니다.
 */
