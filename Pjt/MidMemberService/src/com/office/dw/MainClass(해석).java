/*
 * ===================================================================
 *  MidMemberService / MainClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. SimpleMemberService 의 다음
 *   단계입니다. 거기서 "객체를 한 번만 만들어 재사용" 을 배웠고,
 *   여기서는 세 가지가 더해집니다.
 *
 *   이 프로젝트가 가르치려는 것:
 *   (1) 회원 명단을 ArrayList 대신 HashMap 으로 — 회원 ID 를 key 로
 *       쓰면 for 문 없이 바로 찾고·고치고·지운다.
 *   (2) 수정(modify)·삭제(delete)를 실제로 구현 — 먼저 ID/PW 인증
 *       (authentication)을 하고, 통과해야 진행.
 *   (3) 그 인증을 verifyByMemIdAndMemPw() 라는 메서드로 묶어
 *       modify/delete 가 재사용.
 *
 *   컴파일 상태: 회원 서비스 5개 파일은 문법 오류 없이 컴파일됩니다.
 *   단, 같은 폴더의 TempClass.java 에 진짜 문법 오류가 하나 있어서
 *   (아래 TempClass(해석) 참고) 프로젝트 전체 빌드는 그 줄을 고치기
 *   전까지 실패합니다.
 *
 *
 * ###################################################################
 *  0. 이 프로젝트 전체에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 역할별 파일 나누기 (복습)
 *    MainClass / MemberController / MemberService / MemberDao /
 *    MemberDto — SimpleMemberService 와 같은 구조.
 *
 *  ● HashMap<K, V> (핵심 추가)
 *    "이름표(key)" 와 "값(value)" 을 짝지어 저장하는 그릇. 파이썬
 *    딕셔너리와 같습니다.
 *      put(key, value) : 저장 (같은 key 로 또 put 하면 값이 덮어써짐)
 *      get(key)        : 그 key 의 값 꺼내기 (없으면 null)
 *      remove(key)     : 그 key 짝 삭제
 *      size()          : 짝의 개수
 *      clear()         : 전부 비우기
 *
 *  ● HashMap vs ArrayList (왜 바꿨나)
 *    ArrayList 는 "몇 번째" (인덱스)로 접근합니다. 특정 회원을
 *    찾으려면 for 로 처음부터 훑으며 ID 를 비교해야 합니다.
 *    HashMap 은 key 로 바로 접근합니다. 회원 ID 를 key 로 쓰면
 *    `memberDB.get("hong")` 한 줄로 찾습니다 — for 문이 사라집니다.
 *    (MemberDao(해석) 에서 옛 ArrayList 버전과 나란히 비교합니다.)
 *
 *  ● 인증(Authentication) vs 인가(Authorization)
 *    인증 = "너 진짜 이 사람 맞아?" (ID/PW 확인).
 *    인가 = "이 사람이 이 작업을 해도 돼?" (권한 확인).
 *    이 프로젝트는 인증만 다룹니다 — 수정·삭제 전에 ID/PW 를
 *    확인합니다.
 *
 *  ● 메서드 재사용
 *    modify() 와 delete() 는 둘 다 "먼저 인증" 이 필요합니다. 그
 *    인증 로직을 verifyByMemIdAndMemPw() 하나로 빼두고 둘 다 그걸
 *    부릅니다. (그리고 그 메서드는 다시 doSignIn() 을 재사용합니다.)
 *
 *  ● 메뉴 상수 (final int 필드)
 *    MemberController 안에 MEMBER_SIGN_UP = 1 같은 상수를 두고
 *    `case MEMBER_SIGN_UP:` 으로 씁니다. (ex20260820 은 이걸
 *    인터페이스에 뒀는데, 여기선 클래스 안에 직접 둠.)
 *
 *  ● 객체 한 번만 만들어 재사용 (복습)
 *    MemberController 가 MemberService 를 while 밖에서 한 번만
 *    만들고, MemberService 가 MemberDao 를 필드로 한 번만 만듭니다.
 *    그래서 HashMap 회원 명단이 프로그램 내내 유지됩니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * public static void main(String[] args) {
 *     new MemberController().execute();
 * }
 *
 *   [정상]
 *   MemberController 를 만들어 execute() 를 실행합니다. 이후 흐름은
 *   MemberController(해석).java.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   메뉴가 반복해서 뜹니다. 가입 → 로그인 은 물론, 이번엔 수정·삭제
 *   도 됩니다(단 ID/PW 인증을 통과해야 함). 자세한 흐름은 각
 *   해석본 참고.
 */
