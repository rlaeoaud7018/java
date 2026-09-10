/*
 * ===================================================================
 *  MemberPjt / MemberController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 프로젝트(MemberPjt)는 "서블릿
 *   (Servlet)" 으로 웹 요청을 받아 회원가입을 처리하는 연습입니다.
 *   앞의 ex20260810/812 처럼 main() 으로 도는 게 아니라, 웹 서버
 *   (톰캣)가 요청이 올 때마다 이 클래스의 메서드를 대신 불러 줍니다.
 *
 *   이 파일이 가르치려는 것:
 *   (1) 서블릿은 HttpServlet 을 상속해서 만든다 (ex20260813 상속의
 *       실전), (2) @WebServlet 으로 "어떤 주소 요청을 이 서블릿이
 *       맡을지" 정한다, (3) 요청 주소(URI)를 잘라 "명령" 을 뽑아
 *       분기한다, (4) GET/POST 처리를 doGet/doPost 로 나눈다.
 *
 *   컴파일 상태: 서블릿 라이브러리(jakarta.servlet)가 있으면 문법
 *   오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 서블릿(Servlet)
 *    웹 서버(톰캣) 위에서 도는 자바 클래스. 브라우저가 특정 주소로
 *    요청을 보내면, 서버가 그 요청에 맞는 서블릿의 메서드를 대신
 *    불러 줍니다. 개발자가 main() 에서 직접 부르지 않습니다.
 *
 *  ● HttpServlet / extends
 *    서블릿을 만들려면 자바가 제공하는 HttpServlet 클래스를 상속
 *    (extends)해야 합니다. 그러면 doGet/doPost 같은 뼈대 메서드를
 *    물려받고, 그중 필요한 것을 내 코드로 채웁니다(재정의).
 *
 *  ● @WebServlet("*.dw")
 *    "확장자가 .dw 로 끝나는 모든 요청 주소를 이 서블릿이 처리한다"
 *    는 표시(애노테이션). 예: /MemberPjt/member_signup_confirm.dw
 *    요청이 오면 톰캣이 이 서블릿을 부릅니다.
 *
 *  ● doGet / doPost
 *    브라우저 요청 방식이 GET 이면 doGet, POST(폼 전송 등)이면
 *    doPost 가 호출됩니다. HttpServletRequest(요청 정보),
 *    HttpServletResponse(응답 도구)를 매개변수로 받습니다.
 *
 *  ● HttpServletRequest 의 주요 메서드
 *    getRequestURI()  : 요청 주소 전체 경로 (예: /MemberPjt/xxx.dw)
 *    getContextPath() : 이 웹 앱의 기준 경로 (예: /MemberPjt)
 *    getParameter("이름") : 폼/쿼리로 넘어온 값 하나 꺼내기
 *
 *  ● String.substring(시작번호)
 *    문자열을 시작번호 위치부터 끝까지 잘라 냅니다.
 *    requestURI 에서 contextPath 길이만큼 앞을 잘라내면, 순수한
 *    "명령 부분" (예: /member_signup_confirm.dw)만 남습니다.
 *
 *  ● .equals()
 *    문자열 내용 비교는 == 가 아니라 .equals(). command 가 어떤
 *    명령인지 이걸로 확인합니다.
 *
 *  ● setCharacterEncoding("UTF-8")
 *    폼으로 넘어온 한글이 깨지지 않도록, 요청 본문을 UTF-8 로
 *    읽으라고 지정하는 것. POST 요청 처리 전에 한 번 해 줍니다.
 *
 *  ● serialVersionUID
 *    HttpServlet 이 "직렬화 가능한(Serializable)" 클래스라, 상속한
 *    서블릿에도 관례상 붙이는 버전 번호입니다. 지금 단계에서는
 *    "이런 게 붙는구나" 정도만 알고 그대로 두면 됩니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * @WebServlet("*.dw")
 * public class MemberController extends HttpServlet {
 *     private static final long serialVersionUID = 1L;
 *     public MemberController() { }
 *
 *   [정상]
 *   HttpServlet 을 상속해 서블릿이 됐고, @WebServlet 으로 ".dw 요청은
 *   내가 맡는다" 고 등록했습니다. 생성자는 비어 있습니다(특별히
 *   초기화할 게 없음).
 *
 *
 * protected void doGet(HttpServletRequest request, HttpServletResponse response)
 *         throws ServletException, IOException {
 *     String requestURI = request.getRequestURI();
 *     System.out.println("requestURI: " + requestURI);
 *     String contextPath = request.getContextPath();
 *     System.out.println("contextPath: " + contextPath);
 *     String command = requestURI.substring(contextPath.length());
 *     System.out.println("command: " + command);
 *
 *   [정상]
 *   GET 요청이 오면 톰캣이 이 메서드를 부릅니다. 요청 주소 전체
 *   (requestURI)에서 앱 기준 경로(contextPath) 길이만큼 앞을 잘라
 *   내어, 순수한 명령 문자열(command)을 얻습니다.
 *   예: requestURI = "/MemberPjt/member_signup_confirm.dw",
 *       contextPath = "/MemberPjt"
 *       → command = "/member_signup_confirm.dw"
 *   중간중간 println 은 값을 눈으로 확인하는 로그입니다.
 *
 *
 *     MemberService memberService = null;
 *     if (command.equals("/member_signup_confirm.dw")) {
 *         System.out.println("SIGN UP!!");
 *         memberService = new MemberService();
 *         int result = memberService.memberRegistConfirm(request, response);
 *     } else if (command.equals("/member_signin_confirm.dw")) {
 *         System.out.println("SIGN IN!!");
 *     }
 * }
 *
 *   command 값에 따라 분기합니다.
 *   - 회원가입 명령이면: MemberService 를 만들어 memberRegistConfirm
 *     에 request/response 를 그대로 넘깁니다. 실제 파라미터 꺼내기와
 *     처리는 Service 가 합니다(MemberService(해석) 참고). 돌려받은
 *     result 는 지금은 쓰지 않습니다.
 *   - [미완성] 로그인 명령이면: "SIGN IN!!" 만 출력하고 아직 아무
 *     처리도 안 합니다. 원래대로면 여기서도 memberService 를 만들어
 *     로그인 확인 메서드를 불렀을 것입니다.
 *
 *
 * protected void doPost(HttpServletRequest request, HttpServletResponse response)
 *         throws ServletException, IOException {
 *     request.setCharacterEncoding("UTF-8");
 *     doGet(request, response);
 * }
 *
 *   [정상]
 *   POST 요청이면 먼저 한글 안 깨지게 UTF-8 로 읽으라고 지정한 뒤,
 *   doGet 을 그대로 불러 같은 처리를 시킵니다. "GET 이든 POST 든
 *   처리 로직은 하나로 통일" 하는 흔한 방식입니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   톰캣에 올린 뒤 브라우저에서 회원가입 폼을 제출하면(주소가
 *   ...signup_confirm.dw), 콘솔에 requestURI / contextPath /
 *   command 로그와 "SIGN UP!!" 이 찍히고, MemberService 의
 *   memberRegistConfirm 이 이어서 파라미터들을 출력합니다.
 */
