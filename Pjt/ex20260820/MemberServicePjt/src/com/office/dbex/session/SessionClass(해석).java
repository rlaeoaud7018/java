/*
 * ===================================================================
 *  MemberServicePjt / session/SessionClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. "로그인 상태를 프로그램 전체에서
 *   하나로 유지" 하는 담당입니다. 프로젝트 개요는 IConfig(해석).java
 *   참고.
 *
 *   이 파일이 가르치려는 것:
 *   "싱글턴(singleton) 패턴" — 어떤 클래스의 객체를 프로그램 전체에
 *   딱 하나만 만들고, 어디서든 그 하나에 접근하게 하는 방법. 그리고
 *   그 하나에 "지금 로그인한 회원 ID" 를 담아, Controller 든
 *   Service 든 같은 값을 보게 하는 것.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 싱글턴(singleton) 패턴
 *    "이 클래스의 객체는 프로그램 전체에서 오직 하나" 로 만드는
 *    설계. 세 가지 장치로 만듭니다:
 *      1) private 생성자 : 바깥에서 new 못 하게 막는다.
 *      2) private static 필드 : 그 하나뿐인 객체를 클래스가 자기
 *         안에 보관한다.
 *      3) public static getInstance() : 그 하나를 꺼내 주는 유일한
 *         통로. 없으면 그때 만들고, 있으면 있는 걸 그대로 준다.
 *
 *  ● static 필드 / static 메서드
 *    객체가 아니라 "클래스 자체" 에 속함. `SessionClass.getInstance()`
 *    처럼 객체 없이 클래스 이름으로 부릅니다. static 필드는 프로그램
 *    내내 한 자리에 유지됩니다.
 *
 *  ● private 생성자
 *    생성자에 private 를 붙이면 그 클래스 바깥에서는 new 를 못
 *    합니다. 객체를 만드는 방법을 클래스가 독점하게 됩니다.
 *
 *  ● 왜 세션을 싱글턴으로?
 *    로그인 상태는 프로그램에 "하나만" 있어야 합니다. 여기저기서
 *    new SessionClass() 로 새로 만들면 각자 다른 로그인 상태를
 *    갖게 되어 뒤죽박죽이 됩니다(ex20260810 member 폴더에서 DAO 를
 *    매번 new 해서 데이터가 사라졌던 것과 같은 문제). getInstance()
 *    로 항상 같은 하나를 쓰면 그 문제가 사라집니다.
 *
 *  ● signInedMemberId
 *    "지금 로그인한 회원의 ID". null 이면 로그인 안 한 상태.
 *    로그인 성공 시 setSignInedMemberId(memId), 로그아웃·탈퇴 시
 *    setSignInedMemberId(null).
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public class SessionClass {
 *
 *     private static SessionClass session;
 *     private String signInedMemberId = null;
 *
 *   - session : 하나뿐인 SessionClass 객체를 담아 둘 자리. static
 *     이라 클래스에 딱 하나. 처음엔 아무것도 없어서 null.
 *   - signInedMemberId : 로그인한 회원 ID. 시작값 null(= 로그인 안 함).
 *
 *
 *     private SessionClass() {}
 *
 *   [정상 — 싱글턴 장치 1]
 *   생성자가 private 입니다. 그래서 다른 파일에서 `new SessionClass()`
 *   를 할 수 없습니다. 객체를 만드는 길을 이 클래스가 독점합니다.
 *
 *
 *     public static SessionClass getInstance() {
 *         if (session == null) {
 *             session = new SessionClass();
 *         }
 *         return session;
 *     }
 *
 *   [정상 — 싱글턴 장치 2·3]
 *   바깥에서 세션을 얻는 유일한 통로입니다.
 *     - 처음 불렸을 때: session 이 null 이므로 여기서 한 번
 *       new SessionClass() (private 생성자는 같은 클래스 안이라
 *       호출 가능) → session 에 저장 → 반환.
 *     - 그 다음부터: session 에 이미 객체가 있으므로 if 를 건너뛰고
 *       "그때 만든 그 객체" 를 그대로 반환.
 *   결과적으로 프로그램 어디서 몇 번을 불러도 항상 같은 객체 하나가
 *   나옵니다.
 *
 *
 *     public String getSignInedMemberId() { return signInedMemberId; }
 *     public void setSignInedMemberId(String signInedMemberId) {
 *         this.signInedMemberId = signInedMemberId;
 *     }
 * }
 *
 *   [정상]
 *   로그인 ID 의 getter/setter. setter 매개변수 이름이 필드와 같아
 *   `this.` 로 필드를 콕 집습니다. MemberController 는 로그인 성공
 *   시 set(memId), 로그아웃·탈퇴 시 set(null) 하고, 메뉴를 그릴 때
 *   get() 이 null 인지로 로그인 여부를 판단합니다. MemberService 는
 *   수정·삭제 시 get() 으로 "대상 회원 ID" 를 가져옵니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 게 없습니다(설계도, main 없음).
 *   처음 getInstance() 가 불리는 순간 세션 객체 하나가 생기고,
 *   그 뒤로는 계속 같은 객체가 로그인 ID 를 들고 있습니다.
 *   로그인하면 메뉴가 3/4/5/99 로, 로그아웃하면 다시 1/2/99 로
 *   바뀌는 것이 이 세션 하나 때문입니다.
 */
