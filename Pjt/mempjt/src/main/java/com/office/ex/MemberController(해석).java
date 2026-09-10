/*
 * ===================================================================
 *  mempjt / MemberController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 웹앱의 "요청을 받아 처리
 *   흐름을 지휘하는" 서블릿입니다. 프로젝트 개요는
 *   MemberConfig(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   서블릿 MVC 의 큰 그림 —
 *     브라우저 요청 → 이 서블릿의 doGet() → 요청 주소에서 command
 *     추출 → switch 로 분기 → 필요하면 MemberService 호출 → 결과에
 *     따라 보여줄 JSP 화면 경로(nextPage) 결정 → RequestDispatcher
 *     로 그 JSP 에 요청을 넘김(forward).
 *   그리고 로그인 상태를 HttpSession 에 저장·조회하는 법.
 *
 *   컴파일 상태: 지금 이대로는 컴파일이 안 됩니다. switch 안에서
 *   `HttpSession session` 을 두 번 선언했기 때문입니다(아래 [단순
 *   실수] 참고).
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 서블릿(Servlet) / @WebServlet("*.mem") / doGet / doPost
 *    웹 서버 위에서 도는 클래스. 확장자 .mem 로 끝나는 요청은 이
 *    서블릿이 처리합니다. GET 요청은 doGet, POST 는 doPost 가
 *    불립니다. (ex20260820 MemberPjt(해석) 에서 배운 내용.)
 *
 *  ● request 에서 요청 정보 꺼내기
 *      getRequestURL()  : 주소 전체 (http://... 포함), StringBuffer 로 옴
 *      getRequestURI()  : 서버 뒤 경로만 (예: /mempjt/member_signup_form.mem)
 *      getContextPath() : 이 웹앱의 기준 경로 (예: /mempjt)
 *      substring 으로 앞을 잘라내면 순수 명령(command)만 남음
 *
 *  ● switch (문자열)
 *    자바 7부터 switch 의 조건에 문자열을 쓸 수 있습니다. 여기서는
 *    command 문자열을 MemberConfig 의 경로 상수와 비교해 분기합니다.
 *
 *  ● 뷰(view) 경로와 forward
 *    처리 결과를 보여줄 화면은 JSP 파일입니다. nextPage 에 그 경로
 *    ("views/xxx.jsp")를 담고, 마지막에
 *    request.getRequestDispatcher(nextPage).forward(request, response)
 *    로 그 JSP 에게 요청을 넘깁니다. forward 는 "같은 요청 안에서"
 *    넘기는 것이라 브라우저 주소창은 안 바뀝니다.
 *
 *  ● HttpSession (여러 요청에 걸쳐 유지되는 저장소)
 *    request.getSession() 으로 얻습니다.
 *      setAttribute(키, 값) : 세션에 저장 (다음 요청에서도 살아 있음)
 *      getAttribute(키)     : 세션에서 꺼냄 (Object 로 나옴)
 *    로그인한 회원 ID 를 여기 넣어두면, 이후 요청에서 "지금 누가
 *    로그인 중인지" 를 알 수 있습니다. (ex20260820 의 SessionClass
 *    싱글턴이 하던 일을, 웹에서는 서블릿 내장 세션이 대신함.)
 *
 *  ● request.setAttribute / getAttribute (한 요청 안에서만)
 *    Controller 가 조회한 데이터를 JSP 로 넘길 때 씁니다. 세션과
 *    달리 이 요청이 끝나면 사라집니다.
 *
 *  ● String.concat(다른문자열)
 *    `"a".concat("b")` = "ab". + 연산자로 잇는 것과 결과가 같습니다.
 *
 *  ● Integer.parseInt(문자열)
 *    "12" 같은 문자열을 정수 12 로 바꿉니다. request 파라미터는
 *    항상 문자열로 오므로, 숫자로 쓰려면 이걸 거칩니다.
 *
 *  ● String.valueOf(객체)
 *    아무 객체나 문자열로 바꿉니다. 객체가 null 이면 "null" 이라는
 *    문자열이 됩니다.
 *
 *  ● MemberService 를 인터페이스로 주입
 *    `new MemberService(new MemberDao())` — Service 는 IMemberDao
 *    규격만 알고, 실제 구현(MemberDao)은 여기서 넣어 줍니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * protected void doGet(HttpServletRequest request, HttpServletResponse response) ... {
 *     StringBuffer stringBuffer = request.getRequestURL();
 *     String requestURI = request.getRequestURI();
 *     String contextPath = request.getContextPath();
 *     String command = requestURI.substring(contextPath.length());
 *
 *   [정상]
 *   요청 주소를 여러 형태로 꺼내 출력해 보고(학습용 로그), 그중
 *   requestURI 에서 contextPath 길이만큼 앞을 잘라 순수 명령
 *   (command)을 얻습니다. 예: "/mempjt/member_signup_form.mem"
 *   에서 "/mempjt" 를 떼면 "/member_signup_form.mem".
 *
 *
 *     String nextPage = null;
 *     MemberService memberService = null;
 *
 *     switch (command) {
 *
 *   [정상] 보여줄 화면 경로와 Service 자리를 준비하고, command
 *   문자열로 분기를 시작합니다.
 *
 *
 *     case MemberConfig.MEMBER_SIGNUP_FORM:
 *         nextPage = generateViewName("/member_signup_form");
 *         break;
 *
 *   [정상] 가입 폼 요청 → 처리할 것 없이 가입 입력 화면 JSP 경로만
 *   정합니다. (주석 처리된 `case "/member_signup_form.mem":` 은
 *   상수 대신 문자열을 직접 쓰던 버전 — [의도된 장치] 비교용.)
 *
 *
 *     case MemberConfig.MEMBER_SIGNUP_CONFIRM:
 *         memberService = new MemberService(new MemberDao());
 *         int result = memberService.addNewMember(request, response);
 *         if (result > 0) { ... nextPage = "..._ok" ... }
 *         else            { ... nextPage = "..._ng" ... }
 *         break;
 *
 *   [정상] 가입 폼이 제출된 요청 → Service 를 만들어 addNewMember
 *   에 request 를 넘깁니다. Service 가 파라미터를 꺼내 DB 에 INSERT
 *   하고 결과(행 수)를 돌려줍니다. 0보다 크면 성공 화면, 아니면
 *   실패 화면으로 nextPage 를 정합니다.
 *
 *
 *     case MemberConfig.MEMBER_SIGNIN_FORM:
 *         nextPage = generateViewName("/member_signin_form");
 *         break;
 *
 *   [정상] 로그인 입력 화면 경로만 정합니다.
 *
 *
 *     case MemberConfig.MEMBER_SIGNIN_CONFIRM:
 *         memberService = new MemberService(new MemberDao());
 *         String signinMemId = memberService.searchMember(request, response);
 *         if (signinMemId != null) {
 *             nextPage = generateViewName("/member_signin_ok");
 *             HttpSession session = request.getSession();               // (A) 여기서 session 선언
 *             session.setAttribute(MemberConfig.SINGINED_MEMBERID, signinMemId);
 *         } else {
 *             nextPage = generateViewName("/member_signin_ng");
 *         }
 *         break;
 *
 *   [정상 — 로그인 + 세션 저장]
 *   Service 가 ID/PW 를 확인해 맞으면 그 회원 ID 를, 틀리면 null 을
 *   돌려줍니다. 성공이면 세션을 얻어(request.getSession())
 *   "signinedMemId" 이름표로 회원 ID 를 저장합니다 → 이후 요청에서
 *   "로그인 중인 회원" 을 알 수 있게 됩니다.
 *
 *
 *     case MemberConfig.MEMBER_MODIFY_FORM:
 *         memberService = new MemberService(new MemberDao());
 *         HttpSession session = request.getSession();                   // (B) 여기서 session 또 선언
 *         String signinedMemId = String.valueOf(session.getAttribute(MemberConfig.SINGINED_MEMBERID));
 *         MemberDto currentSigninedMember = memberService.getCurrentSigninedMember(signinedMemId);
 *         request.setAttribute("currentSigninedMember", currentSigninedMember);
 *         nextPage = generateViewName("/member_modify_form");
 *         break;
 *
 *   [단순 실수 — 컴파일 에러] (A)와 (B):
 *   자바 switch 는 case 마다 중괄호가 없으면 switch 전체가 "하나의
 *   블록" 입니다. 그 안에서 같은 이름의 지역변수를 두 번 선언할 수
 *   없습니다. (A)에서 `HttpSession session` 을 선언했는데 (B)에서
 *   또 `HttpSession session` 을 선언해서 "Duplicate local variable
 *   session" 에러가 납니다.
 *
 *   수정 (case 본문을 중괄호로 감싸 각자 블록으로):
 *
 *     case MemberConfig.MEMBER_SIGNIN_CONFIRM: {
 *         ...
 *         HttpSession session = request.getSession();
 *         ...
 *         break;
 *     }
 *     case MemberConfig.MEMBER_MODIFY_FORM: {
 *         ...
 *         HttpSession session = request.getSession();
 *         ...
 *         break;
 *     }
 *
 *   이렇게 각 case 를 { } 로 묶으면 session 이 서로 다른 블록의
 *   변수가 되어 충돌하지 않습니다. (또는 switch 앞에서 session 을
 *   한 번만 선언하고 각 case 에서는 재대입만 해도 됩니다.)
 *
 *   기능 설명: 이 case 는 수정 폼을 열기 전에, 세션에 저장된 로그인
 *   ID 로 현재 회원 정보를 DB 에서 읽어 request 에 담습니다. 그러면
 *   수정 화면 JSP 가 그 정보를 미리 채워 보여줄 수 있습니다.
 *   String.valueOf(...) 로 감싼 건 getAttribute 가 Object 를
 *   돌려주기 때문에 문자열로 바꾸려는 것입니다.
 *
 *
 *     case MemberConfig.MEMBER_MODIFY_CONFIRM:
 *         memberService = new MemberService(new MemberDao());
 *         int resultForModify = memberService.modifyMemberByMemNo(request, response);
 *         if (resultForModify > 0) { ... "_ok" ... } else { ... "_ng" ... }
 *         break;
 *     }
 *
 *   [정상] 수정 폼이 제출된 요청 → Service 가 DB 를 UPDATE 하고
 *   결과에 따라 성공/실패 화면을 정합니다.
 *
 *
 *     RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
 *     dispatcher.forward(request, response);
 * }
 *
 *   [정상] 모든 case 가 끝나면, 정해진 nextPage(JSP 경로)로 요청을
 *   넘깁니다. 화면은 그 JSP 가 그립니다.
 *
 *
 * protected void doPost(...) {
 *     request.setCharacterEncoding("UTF-8");
 *     doGet(request, response);
 * }
 *
 *   [정상] POST 요청이면 한글 안 깨지게 UTF-8 설정 후 doGet 으로
 *   같은 처리를 시킵니다.
 *
 *
 * private String generateViewName(String viewName) {
 *     return MemberConfig.DEFAULT_VIEW_PATH
 *             .concat(viewName)
 *             .concat(MemberConfig.DEFAULT_VIEW_SUFFIX);
 * }
 *
 *   [정상] 뷰 이름 조립 도우미. "views" + "/member_signup_ok" +
 *   ".jsp" = "views/member_signup_ok.jsp". private 라 이 서블릿
 *   안에서만 씁니다. (주석 처리된 `+` 연산자 버전은 [의도된 장치]
 *   같은 결과의 다른 표기.)
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까 (session 중복 선언을 고쳤다고 가정)
 * ###################################################################
 *
 *   .mem 요청이 오면 콘솔에 URL/URI/contextPath/command 로그가
 *   찍히고, command 에 맞는 case 가 실행되어 (필요 시 DB 작업 후)
 *   해당 JSP 화면으로 forward 됩니다. 로그인 성공 시 세션에 회원
 *   ID 가 저장되고, 수정 폼 요청 때 그 ID 로 현재 정보를 불러와
 *   화면에 넘깁니다.
 */
