/*
 * ===================================================================
 *  MemberServicePjt(루트) / member/MemberService.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 프로젝트는 ex20260820
 *   MemberServicePjt 의 단순형입니다. 가입·로그인·수정 판정 로직
 *   담당입니다.
 *
 *   이 파일이 (이 버전에서) 가르치려는 것:
 *   Controller 가 넘긴 DTO 를 받아 DAO 를 시키고, 결과를 상수
 *   (IMember)로 판정한다. 단, DAO 를 아직 "직접 new" 로 만듭니다
 *   (의존성 주입 전 단계).
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● implements IMember
 *    결과 상수(MEMBER_SIGN_UP_SUCCESS 등)를 이름으로 쓰려고 붙였
 *    습니다.
 *
 *  ● 필드에서 DAO 를 직접 new (이 버전의 특징)
 *    `MemberDao memberDao = new MemberDao();` — 구체 클래스 이름
 *    (MemberDao)으로, 이 클래스 안에서 직접 만듭니다. 생성자도
 *    없습니다.
 *    → ex20260820 버전은 이걸 `IMemberDao memberDao;` (인터페이스
 *      타입) + 생성자 주입으로 바꿉니다. 그래야 MySQL/Oracle DAO 를
 *      갈아끼울 수 있습니다. 이 버전은 MySQL 용 MemberDao 에
 *      고정돼 있습니다.
 *
 *  ● switch 로 결과 분기
 *    가입 결과(0/1)를 switch 로 나눠 메시지를 출력.
 *    `case MEMBER_SIGN_UP_SUCCESS:` = case 1.
 *
 *  ● .equals() (비번 비교) / null 체크
 *    로그인 판정에서 아이디 존재 여부(null)와 비번 일치(.equals)를
 *    확인.
 *
 *  ● 세션에서 로그인 ID 가져오기
 *    수정 시 대상 회원 ID 를 세션에서 읽어 DTO 에 채웁니다.
 *
 *  ● ex20260820 버전과의 차이
 *    - DAO 를 인터페이스 타입으로 주입받지 않고 직접 new.
 *    - 삭제(doRemove) 메서드가 없습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public class MemberService implements IMember {
 *     MemberDao memberDao = new MemberDao();
 *
 *   [정상 — 다만 DI 전 단계]
 *   MemberDao 를 필드로 직접 만듭니다. 아래 세 메서드가 이 하나를
 *   공유합니다.
 *
 *
 * public int doSignUp(MemberDto memberDto) {
 *     int result = memberDao.insertNewMember(memberDto);
 *     switch (result) {
 *     case MEMBER_SIGN_UP_SUCCESS: ... "SUCCESS" ... break;   // 1
 *     case MEMBER_SIGN_UP_FAIL:    ... "FAIL" ...    break;   // 0
 *     default:                     ... "FAIL" ...    break;
 *     }
 *     return result;
 * }
 *
 *   [정상] DAO 에 INSERT 를 시키고, 돌아온 행 수를 switch 로 나눠
 *   메시지를 출력한 뒤 그대로 반환. 위쪽 주석 처리된 if/else 버전은
 *   [의도된 장치] — 같은 판정을 if 로도 쓸 수 있음을 보여주는 비교.
 *
 *
 * public boolean doSignIn(MemberDto memberDto) {
 *     MemberDto selectedMemberDto = memberDao.selectMemberByMemId(memberDto.getMemId());
 *     if (selectedMemberDto != null) {
 *         if (selectedMemberDto.getMemPw().equals(memberDto.getMemPw())) {
 *             return MEMBER_SIGN_IN_SUCCESS;   // true
 *         } else {
 *             ... "PW NG" ...
 *             return MEMBER_SIGN_IN_FAIL;      // false
 *         }
 *     } else {
 *         ... "ID NG" ...
 *         return MEMBER_SIGN_IN_FAIL;          // false
 *     }
 * }
 *
 *   [정상 — 2단계 판정]
 *   아이디 조회 → null 이면 "ID NG" 실패. 있으면 비번을 .equals()
 *   비교 → 맞으면 true, 틀리면 "PW NG" 실패. 위쪽에 주석 처리된
 *   짧은 버전(아이디 존재 여부만 보던 것)은 [의도된 장치] — 비번
 *   확인까지 하는 버전으로 발전시킨 흔적.
 *
 *
 * public int doModify(MemberDto memberDtoForModify) {
 *     memberDtoForModify.setMemId(SessionClass.getInstance().getSignInedMemberId());
 *     int result = memberDao.updateMember(memberDtoForModify);
 *     if (result > 0) { ... SUCCESS ... } else { ... FAIL ... }
 *     return result;
 * }
 *
 *   [정상]
 *   Controller 는 이 DTO 의 memId 를 null 로 넘겼습니다. 여기서
 *   `setMemId(세션의 로그인 ID)` 로 채웁니다 — "수정 대상은 지금
 *   로그인한 사람" 을 Service 가 강제. 그 다음 DAO 로 UPDATE.
 *   (이 버전에는 삭제 메서드가 없어서, Controller 의 DELETE 메뉴는
 *   비어 있습니다.)
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   각 메서드가 로그를 찍고 MySQL 용 MemberDao 를 부릅니다.
 *   MySQL + TBL_MEMBER 가 준비돼 있으면 실제로 INSERT/SELECT/UPDATE
 *   가 됩니다.
 */
