/*
 * ===================================================================
 *  MemberServicePjt(루트) / member/IMember.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 파일은 ex20260820
 *   MemberServicePjt 의 IMember.java 와 내용이 완전히 같습니다.
 *   자세한 해설은 ex20260820 의 IMember(해석).java 에 있습니다.
 *
 *   이 파일이 가르치려는 것:
 *   메뉴 번호와 처리 결과 값을 숫자 대신 이름(상수)으로 쓰기.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리
 * ###################################################################
 *
 *  ● 인터페이스 = 상수 모음
 *    메서드 없이 상수만. 인터페이스 필드는 자동 public static final.
 *
 *  ● implements 로 가져다 쓰기
 *    `class MemberController implements IMember` → `MEMBER_SIGN_UP`
 *    을 이름만으로.
 *
 *  ● int 상수 vs boolean 상수
 *    가입 결과는 int(성공 1 / 실패 0), 로그인 결과는 boolean
 *    (true/false) — 각 메서드 반환타입에 맞춤.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * public interface IMember {
 *     // Menu
 *     final public int MEMBER_SIGN_UP = 1;  ...  MEMBER_SIGN_OUT = 5;
 *     // Result
 *     final public int MEMBER_SIGN_UP_SUCCESS = 1;
 *     final public int MEMBER_SIGN_UP_FAIL    = 0;
 *     final public boolean MEMBER_SIGN_IN_SUCCESS = true;
 *     final public boolean MEMBER_SIGN_IN_FAIL    = false;
 * }
 *
 *   [정상]
 *   위 5개는 메뉴 번호(`case MEMBER_SIGN_UP:` = case 1). 아래 4개는
 *   처리 결과 값. MemberService 가 이 이름으로 결과를 돌려주고
 *   MemberController 가 이 이름으로 판단합니다.
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   실행되는 게 없습니다. (참고: 이 버전의 IMemberDao 는 아직 메서드
 *   규격이 없고 DB 상수만 담고 있습니다 — IMemberDao(해석).java.)
 */
