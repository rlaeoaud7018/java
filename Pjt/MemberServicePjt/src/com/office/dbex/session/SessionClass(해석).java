/*
 * ===================================================================
 *  MemberServicePjt(루트) / session/SessionClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 파일은 ex20260820
 *   MemberServicePjt 의 SessionClass.java 와 내용이 완전히 같습니다.
 *   자세한 해설은 ex20260820 의 SessionClass(해석).java 에 있습니다.
 *
 *   이 파일이 가르치려는 것:
 *   "싱글턴(singleton) 패턴" — 클래스의 객체를 프로그램 전체에 딱
 *   하나만 만들고, 어디서든 그 하나에 접근. 그 하나에 로그인한
 *   회원 ID 를 담아 Controller/Service 가 같은 값을 보게 함.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리
 * ###################################################################
 *
 *  ● 싱글턴 패턴의 3장치
 *    1) private 생성자    : 바깥에서 new 못 하게 막음
 *    2) private static 필드 : 하나뿐인 객체를 클래스가 보관
 *    3) public static getInstance() : 없으면 만들고, 있으면 있는
 *       걸 그대로 주는 유일한 통로
 *
 *  ● static 필드/메서드
 *    객체가 아니라 "클래스 자체" 에 속함. `SessionClass.getInstance()`
 *    처럼 객체 없이 클래스 이름으로 부름.
 *
 *  ● 왜 싱글턴인가
 *    로그인 상태는 프로그램에 하나만 있어야 함. 여기저기서 new
 *    하면 각자 다른 상태를 갖게 되어 뒤죽박죽. getInstance() 로
 *    항상 같은 하나를 쓰면 해결.
 *
 *  ● signInedMemberId
 *    로그인한 회원 ID. null 이면 로그인 안 함. 로그인 성공 시
 *    set(memId), 로그아웃·탈퇴 시 set(null).
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * private static SessionClass session;
 * private String signInedMemberId = null;
 *
 *   session = 하나뿐인 객체를 담을 자리(처음엔 null).
 *   signInedMemberId = 로그인 ID(시작값 null).
 *
 *
 * private SessionClass() {}
 *
 *   [정상 — 싱글턴 장치 1] 생성자가 private → 다른 파일에서
 *   new SessionClass() 불가.
 *
 *
 * public static SessionClass getInstance() {
 *     if (session == null) {
 *         session = new SessionClass();
 *     }
 *     return session;
 * }
 *
 *   [정상 — 싱글턴 장치 2·3]
 *   처음 불리면 여기서 한 번 new(같은 클래스 안이라 private 생성자
 *   호출 가능) → session 에 저장 → 반환. 그 다음부터는 이미 있는
 *   그 객체를 그대로 반환. 어디서 몇 번을 불러도 같은 객체 하나.
 *
 *
 * public String getSignInedMemberId()  { return signInedMemberId; }
 * public void setSignInedMemberId(String signInedMemberId) {
 *     this.signInedMemberId = signInedMemberId;
 * }
 *
 *   [정상] 로그인 ID 의 getter/setter. Controller 는 로그인 성공 시
 *   set(memId), 로그아웃·탈퇴 시 set(null) 하고, 메뉴 그릴 때 get()
 *   이 null 인지로 로그인 여부를 판단. Service 는 수정 시 get() 으로
 *   대상 회원 ID 를 가져옴.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   실행되는 게 없습니다(설계도). 처음 getInstance() 가 불리는
 *   순간 세션 객체 하나가 생기고, 이후 계속 같은 객체가 로그인
 *   ID 를 들고 있습니다.
 */
