/*
 * ===================================================================
 *  MemberServicePjt / member/IMember.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 프로젝트 개요는 IConfig(해석).java
 *   의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   IConfig 와 같은 아이디어를 회원 기능 쪽에 적용한 것 — "메뉴
 *   번호" 와 "처리 결과 값" 을 숫자 대신 의미 있는 이름(상수)으로
 *   쓰기.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 인터페이스(interface) / 상수 모음
 *    "규격서" 역할을 하는 자바 요소. 여기서는 메서드 없이 상수만
 *    담았습니다. 인터페이스 필드는 자동으로 public static final
 *    (공개 상수)입니다. (자세한 정의는 IConfig(해석).java.)
 *
 *  ● implements 로 상수 가져다 쓰기
 *    `class MemberController implements IMember` 라고 하면, 이
 *    상수들을 `MEMBER_SIGN_UP` 처럼 이름만으로 쓸 수 있습니다.
 *    (참고: 인터페이스를 "상수 가져오기용" 으로 implements 하는 건
 *    요즘은 잘 안 쓰는 옛 스타일이지만, 배우는 단계라 그대로
 *    설명합니다.)
 *
 *  ● 매직 넘버 제거
 *    코드에 1, 2, 99 같은 숫자를 그냥 쓰면 나중에 무슨 뜻인지
 *    모릅니다. `MEMBER_SIGN_UP` 이라고 이름을 붙이면 읽는 사람이
 *    바로 이해합니다.
 *
 *  ● int 상수 vs boolean 상수
 *    가입 결과는 int(성공 1 / 실패 0)로, 로그인 결과는 boolean
 *    (성공 true / 실패 false)로 다르게 표현합니다. 메서드 반환
 *    타입에 맞춘 것입니다(doSignUp 은 int, doSignIn 은 boolean).
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * public interface IMember {
 *     // Menu
 *     final public int MEMBER_SIGN_UP   = 1;
 *     final public int MEMBER_SIGN_IN   = 2;
 *     final public int MEMBER_MODIFY    = 3;
 *     final public int MEMBER_DELETE    = 4;
 *     final public int MEMBER_SIGN_OUT  = 5;
 *
 *     // Result
 *     final public int MEMBER_SIGN_UP_SUCCESS = 1;
 *     final public int MEMBER_SIGN_UP_FAIL    = 0;
 *     final public boolean MEMBER_SIGN_IN_SUCCESS = true;
 *     final public boolean MEMBER_SIGN_IN_FAIL    = false;
 * }
 *
 *   [정상]
 *   위쪽 5개는 메뉴 번호입니다. MemberController 의 switch 에서
 *   `case MEMBER_SIGN_UP:` (= case 1) 처럼 씁니다.
 *   아래쪽 4개는 처리 결과 값입니다.
 *     - 가입: 성공하면 1, 실패하면 0 (DB 에 실제로 추가된 행 수와
 *       맞아떨어짐 — MemberDao(해석) 참고).
 *     - 로그인: 성공 true / 실패 false.
 *   MemberService 가 이 이름들로 결과를 돌려주고, MemberController
 *   가 이 이름들로 결과를 판단합니다.
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 게 없습니다. "DAO 가 갖춰야 할 메서드
 *   목록" 규격은 별도로 IMemberDao 가 정합니다(IMemberDao(해석).java).
 */
