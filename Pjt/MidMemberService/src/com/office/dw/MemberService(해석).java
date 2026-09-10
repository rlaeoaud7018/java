/*
 * ===================================================================
 *  MidMemberService / MemberService.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 가입·로그인·수정·삭제의 실제
 *   판정 로직 담당입니다. 프로젝트 개요·개념은 MainClass(해석).java
 *   의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   (1) doSignIn 이 이번엔 boolean 을 돌려준다(성공/실패),
 *   (2) modify/delete 는 먼저 인증을 하고, 그 인증을
 *       verifyByMemIdAndMemPw() 로 묶어 재사용한다,
 *   (3) verifyByMemIdAndMemPw() 는 다시 doSignIn() 을 재사용한다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 인스턴스 필드로 만든 MemberDao (복습)
 *    `MemberDao memberDao = new MemberDao();` 를 메서드 밖에 둬서,
 *    모든 메서드가 같은 DAO(→ 같은 HashMap 명단)를 씁니다.
 *
 *  ● doSignIn 이 boolean 반환
 *    로그인 성공하면 true, 실패하면 false. 그래서 이 메서드를
 *    "인증 통과 여부" 판단에 그대로 쓸 수 있습니다.
 *
 *  ● 인증(Authentication)
 *    ID/PW 가 맞는지 확인하는 것. modify/delete 는 아무나 못 하게
 *    이걸 먼저 통과해야 합니다.
 *
 *  ● 메서드 재사용 사슬
 *    modify()  ─┐
 *    delete()  ─┼→ verifyByMemIdAndMemPw(id, pw) ─→ doSignIn(...)
 *    같은 판정을 여러 곳에서 다시 짜지 않고 하나를 호출합니다.
 *
 *  ● .equals() (비번 비교)
 *    == 가 아니라 .equals() 로 내용 비교.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * MemberDao memberDao = new MemberDao();
 *
 *   [정상] DAO 를 필드로 한 번만 만듭니다. 아래 메서드들이 공유.
 *
 *
 * public void doSignUp(MemberDto memberDto) {
 *     System.out.println("[MemberService] doSignUp()");
 * //     MemberDao memberDao = new MemberDao();
 *     memberDao.insertNewMember(memberDto);
 * }
 *
 *   [정상] 받은 DTO 를 DAO 의 insertNewMember 에 넘겨 명단에
 *   추가합니다. [의도된 장치] 주석 줄은 옛 방식(메서드 안에서
 *   매번 new).
 *
 *
 * public boolean doSignIn(MemberDto memberDto) {   // id, pw
 *     System.out.println("[MemberService] doSignIn()");
 *     MemberDto selectedMemberDto = memberDao.selectMemberDtoByMemId(memberDto.memId);
 *
 *     if (selectedMemberDto != null) {
 *         if (selectedMemberDto.memPw.equals(memberDto.memPw)) {
 *             System.out.println("[MemberService] SIGN-IN SUCCESS!!");
 *             return true;
 *         } else {
 *             System.out.println("[MemberService] SIGN-IN FAIL!!");
 *             return false;
 *         }
 *     } else {
 *         System.out.println("[MemberService] SIGN-IN FAIL!!");
 *         return false;
 *     }
 * }
 *
 *   [정상 — 2단계 판정, 결과를 boolean 으로]
 *   1) DAO 로 아이디 조회 → null 이면 실패(return false).
 *   2) 있으면 저장된 비번과 입력 비번을 .equals() 비교 → 같으면
 *      true, 다르면 false.
 *   이 true/false 를 modify/delete 가 "인증 통과 여부" 로 씁니다.
 *
 *
 * public void modify(MemberDto memberDto) {
 *     System.out.println("[MemberService] modify()");
 *     boolean resultForAutentication =
 *             verifyByMemIdAndMemPw(memberDto.memId, memberDto.memPw);
 *     if (resultForAutentication) {
 *         memberDao.updateMemberForMailAndPhone(memberDto);
 *     }
 * }
 *
 *   [정상]
 *   먼저 verifyByMemIdAndMemPw 로 ID/PW 인증. 통과(true)했을 때만
 *   DAO 에 MAIL/PHONE 업데이트를 시킵니다. 인증 실패면 아무 일도
 *   안 합니다. (변수명 Autentication 은 Authentication 오타지만
 *   이름일 뿐 — [단순 실수, 무해].)
 *
 *
 * public void delete(MemberDto memberDto) {
 *     System.out.println("[MemberService] delete()");
 *     boolean resultForAutentication =
 *             verifyByMemIdAndMemPw(memberDto.memId, memberDto.memPw);
 *     if (resultForAutentication) {
 *         memberDao.deleteMemberByMemId(memberDto.memId);
 *     }
 * }
 *
 *   [정상] modify 와 같은 패턴 — 인증 통과 시에만 DAO 에 삭제를
 *   시킵니다.
 *
 *
 * public boolean verifyByMemIdAndMemPw(String id, String pw) {
 *     System.out.println("[MemberService] verifyByMemIdAndMemPw()");
 *     return doSignIn(new MemberDto(id, pw, null, null));
 * }
 *
 *   [정상 — 재사용의 핵심]
 *   "ID/PW 로 본인 확인" 은 로그인과 똑같은 일입니다. 그래서 새로
 *   짜지 않고, id/pw 로 DTO 를 만들어 doSignIn 에 넘긴 결과
 *   (true/false)를 그대로 돌려줍니다. modify/delete 가 이걸 씁니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   수정: [MemberService] modify() → verifyByMemIdAndMemPw() →
 *         doSignIn() → (성공 시) [MemberDao] updateMemberForMailAndPhone()
 *   삭제: [MemberService] delete() → verifyByMemIdAndMemPw() →
 *         doSignIn() → (성공 시) [MemberDao] deleteMemberByMemId()
 *   인증 실패면 DAO 호출 없이 조용히 끝납니다.
 */
