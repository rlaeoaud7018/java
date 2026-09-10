/*
 * ===================================================================
 *  SimpleMemberService / MemberService.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 프로젝트 개요·"객체 재사용"
 *   개념은 MainClass(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   MemberDao 를 "메서드 안" 이 아니라 "클래스 필드" 로 한 번만
 *   만들어, doSignUp 과 doSignIn 이 같은 DAO(→ 같은 회원 명단)를
 *   쓰게 하는 것.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 인스턴스 필드로 만든 MemberDao
 *    `MemberDao memberDao = new MemberDao();` 가 메서드 밖(클래스
 *    안)에 있습니다. 그래서 이 MemberService 객체가 사는 동안
 *    memberDao 하나가 계속 유지되고, doSignUp/doSignIn 이 둘 다
 *    그 하나를 씁니다.
 *
 *  ● null 체크
 *    DAO 가 회원을 못 찾으면 null 을 돌려줍니다. `if (selectedMemberDto
 *    != null)` 로 "그 아이디가 존재하는가" 를 판단합니다.
 *
 *  ● .equals() (비번 비교)
 *    저장된 비번과 입력한 비번의 내용이 같은지 == 가 아니라
 *    .equals() 로 확인합니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * MemberDao memberDao = new MemberDao();
 *
 *   [정상 — 이 파일의 핵심 줄]
 *   MemberDao 를 필드로 한 번만 만듭니다. 아래 두 메서드는 이
 *   memberDao 하나를 공유합니다.
 *
 *
 * public void doSignUp(MemberDto memberDto) {
 *     System.out.println("[MemberService] doSignUp()");
 * //     MemberDao memberDao = new MemberDao();
 *     memberDao.insertMember(memberDto);
 * }
 *
 *   [정상] 받은 DTO 를 DAO 의 insertMember 에 넘겨 회원 명단에
 *   추가합니다.
 *   [의도된 장치] 주석 처리된 `// MemberDao memberDao = new MemberDao();`
 *   가 "옛 방식" 입니다. 메서드 안에서 매번 새로 만들면 그 명단은
 *   이 메서드가 끝나면 사라져서, doSignIn 이 조회할 명단이 비어
 *   있게 됩니다(= ex20260810 member 의 버그). 그래서 주석으로
 *   막고 필드의 memberDao 를 씁니다.
 *
 *
 * public void doSignIn(MemberDto memberDto) {
 *     System.out.println("[MemberService] doSignIn()");
 * //     MemberDao memberDao = new MemberDao();
 *     MemberDto selectedMemberDto = memberDao.selectMemberByMemId(memberDto.memId);
 *
 *     if (selectedMemberDto != null) {
 *         if (memberDto.memPw.equals(selectedMemberDto.memPw)) {
 *             System.out.println("SIGN-IN SUCCESS!!");
 *         } else {
 *             System.out.println("Sorry. SIGN-IN FAIL!!");
 *         }
 *     } else {
 *         System.out.println("Sorry. SIGN-IN FAIL!!");
 *     }
 * }
 *
 *   [정상 — 2단계 판정]
 *   1) DAO 로 아이디 조회 → null 이면 그런 아이디 없음 → 실패.
 *   2) 있으면, 저장된 비번과 입력 비번을 .equals() 로 비교 → 같으면
 *      성공, 다르면 실패.
 *   여기도 `// MemberDao memberDao = new MemberDao();` 는 [의도된
 *   장치] 옛 방식이라 주석 처리. 필드의 memberDao 를 쓰므로,
 *   doSignUp 에서 같은 memberDao 에 넣어둔 회원을 여기서 찾을 수
 *   있습니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   doSignUp → [MemberDao] insertMember() 로 명단에 추가.
 *   doSignIn → [MemberDao] selectMemberByMemId() 로 같은 명단에서
 *   조회 → 아이디·비번 맞으면 "SIGN-IN SUCCESS!!".
 */
