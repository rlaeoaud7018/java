/*
 * ===================================================================
 *  mempjt / MemberConfig.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 프로젝트(mempjt)는 지금까지의
 *   회원 서비스를 "서블릿 + JSP 화면 + JDBC + 세션" 으로 완성한
 *   웹 애플리케이션입니다. 이 파일은 그중 "설정값·상수를 한곳에
 *   모아두는" 담당입니다.
 *
 *   이 파일이 가르치려는 것:
 *   프로그램 곳곳에서 쓰는 문자열(요청 경로, 뷰 폴더 이름, 세션 키
 *   등)을 코드에 직접 박지 않고, 상수 클래스 하나에 모아 이름으로
 *   부른다. ex20260820 은 이걸 인터페이스(IConfig)에 뒀는데, 여기선
 *   그냥 "클래스에 static final 상수" 로 둡니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 상수 클래스
 *    필드가 전부 `final static public` 인 클래스. 값을 담기만 하고
 *    로직은 없습니다.
 *      final  : 값을 못 바꿈 (상수)
 *      static : 객체 없이 `MemberConfig.MEMBER_SIGNUP_FORM` 처럼
 *               클래스 이름으로 바로 접근
 *      public : 어느 클래스에서든 접근
 *
 *  ● 왜 상수로 빼는가
 *    "/member_signup_form.mem" 같은 문자열을 여러 파일에 직접 쓰면,
 *    나중에 하나 바뀔 때 전부 찾아 고쳐야 합니다. 상수로 빼두면
 *    한 곳만 고치면 되고, 오타로 문자열이 미묘하게 달라지는 실수도
 *    막습니다.
 *
 *  ● 세션 키(session key)
 *    SINGINED_MEMBERID = "signinedMemId" 는 "로그인한 회원 ID 를
 *    세션에 저장할 때 쓸 이름표" 입니다. 저장할 때도 꺼낼 때도 같은
 *    이름표를 써야 하므로 상수로 둡니다. (이름의 SINGINED 는
 *    SIGNED-IN 의 오타지만, 저장·조회에서 똑같이 쓰므로 동작에는
 *    문제 없음 — [단순 실수, 무해].)
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * final static public String MEMBER_SIGNUP_FORM    = "/member_signup_form.mem";
 * final static public String MEMBER_SIGNUP_CONFIRM = "/member_signup_confirm.mem";
 * final static public String MEMBER_SIGNIN_FORM    = "/member_signin_form.mem";
 * final static public String MEMBER_SIGNIN_CONFIRM = "/member_signin_confirm.mem";
 * final static public String MEMBER_MODIFY_FORM    = "/member_modify_form.mem";
 * final static public String MEMBER_MODIFY_CONFIRM = "/member_modify_confirm.mem";
 *
 *   [정상 — 요청 경로 상수]
 *   브라우저가 보낼 수 있는 요청 주소(끝이 .mem)들입니다. 보통
 *   "_form" 은 입력 화면을 보여주는 요청, "_confirm" 은 그 폼이
 *   제출된 뒤 실제 처리를 하는 요청입니다. MemberController 의
 *   switch 가 `case MemberConfig.MEMBER_SIGNUP_FORM:` 처럼 이
 *   상수로 분기합니다.
 *
 *
 * final static public String DEFAULT_VIEW_PATH   = "views";
 * final static public String DEFAULT_VIEW_SUFFIX = ".jsp";
 *
 *   [정상 — 뷰(화면) 경로 조립용 상수]
 *   결과 화면은 "views" 폴더 안의 ".jsp" 파일입니다.
 *   MemberController 의 generateViewName("/member_signup_ok") 가
 *   이 둘을 이어붙여 "views/member_signup_ok.jsp" 를 만듭니다.
 *
 *
 * final static public String SINGINED_MEMBERID = "signinedMemId";
 *
 *   [정상 — 세션 키 상수]
 *   로그인 성공 시 회원 ID 를 세션에 저장할 때 쓰는 이름표입니다.
 *   저장: session.setAttribute(MemberConfig.SINGINED_MEMBERID, id)
 *   조회: session.getAttribute(MemberConfig.SINGINED_MEMBERID)
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 게 없습니다. 여기 상수들이 어디서
 *   쓰이는지는 MemberController(해석).java 에서 이어집니다.
 */
