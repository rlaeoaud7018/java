/*
 * ===================================================================
 *  vaccinepjt / VaccineController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 요청을 받아 흐름을 지휘하는
 *   서블릿입니다. 프로젝트 개요는 VaccineConfig(해석).java 를,
 *   서블릿 MVC 기본은 mempjt 의 MemberController(해석).java 를 먼저
 *   보세요.
 *
 *   이 파일이 가르치려는 것 (mempjt 대비 새 내용):
 *   "forward 와 sendRedirect 의 차이." 등록·목록은 forward(같은 요청
 *   안에서 JSP 로 넘김), 삭제는 sendRedirect(브라우저에게 목록
 *   주소로 다시 요청하라고 시킴).
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 서블릿 / @WebServlet("*.vc") / doGet / doPost
 *    .vc 로 끝나는 요청을 이 서블릿이 처리. (mempjt 참고.)
 *
 *  ● command 추출
 *    requestURI 에서 contextPath 길이만큼 앞을 잘라 순수 명령을
 *    얻고, switch (command) 로 분기.
 *
 *  ● CLASS_NAME 필드 + concat 으로 로그 접두어
 *    `private String CLASS_NAME = "[VaccineController] ";` 를 두고
 *    `CLASS_NAME.concat("...")` 로 로그 앞에 클래스 이름을 붙입니다.
 *    (concat = 문자열 이어붙이기 메서드, + 와 결과 같음.)
 *
 *  ● generateView(이름) : 뷰 경로 조립
 *    "views/" + 이름 + ".jsp" 를 만들어 줍니다.
 *
 *  ● forward vs sendRedirect (이 파일의 핵심)
 *    forward (RequestDispatcher.forward) :
 *      "같은 요청" 을 서버 안에서 다음 자원(JSP)에게 넘김. 브라우저는
 *      모름 → 주소창이 안 바뀜. request 에 담아둔 데이터가 JSP 로
 *      그대로 전달됨.
 *    sendRedirect (response.sendRedirect) :
 *      브라우저에게 "이 주소로 새로 요청해라" 라고 응답. 브라우저가
 *      새 요청을 보냄 → 주소창이 바뀜. 이전 request 데이터는 안
 *      넘어감.
 *    삭제·저장처럼 "한 번만 일어나야 하는 작업" 뒤에는 redirect 를
 *      씁니다. 그래야 사용자가 새로고침(F5)해도 그 작업이 다시
 *      실행되지 않습니다(중복 삭제/중복 등록 방지).
 *
 *  ● return; 으로 doGet 중간 종료
 *    redirect 를 한 뒤 `return;` 을 해서, 메서드 맨 아래의 forward
 *    코드가 실행되지 않게 막습니다(둘을 같이 하면 에러).
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * @WebServlet("*.vc")
 * public class VaccineController extends HttpServlet {
 *     private String CLASS_NAME = "[VaccineController] ";
 *
 *   [정상] .vc 요청 담당 서블릿. 로그 접두어용 필드.
 *
 *
 * protected void doGet(...) {
 *     String requestURI = request.getRequestURI();
 *     String contextPath = request.getContextPath();
 *     String command = requestURI.substring(contextPath.length());
 *     String nextPage = null;
 *     VaccineService vaccineService = null;
 *     switch (command) {
 *
 *   [정상] command 를 뽑아 분기 시작.
 *
 *
 *     case VaccineConfig.VACCINE_RESISTER_FORM:
 *         nextPage = generateView("vaccine_register_form");
 *         break;
 *
 *   [정상] 등록 폼 요청 → 처리 없이 입력 화면 JSP 경로만 정합니다.
 *   (요청 URL 상수는 "resister" 오타, 넘기는 JSP 이름은 "register" —
 *   철자가 다르지만 동작엔 지장 없음. VaccineConfig(해석) 참고.)
 *
 *
 *     case VaccineConfig.VACCINE_RESISTER_CONFIRM:
 *         vaccineService = new VaccineService();
 *         int resultForRegiste = vaccineService.registeVaccineInjection(request, response);
 *         if (resultForRegiste > 0) { nextPage = generateView("vaccine_register_ok"); }
 *         else                      { nextPage = generateView("vaccine_register_ng"); }
 *         break;
 *
 *   [정상] 등록 폼 제출 요청 → Service 가 파라미터를 꺼내 DB 에
 *   INSERT 하고 결과(행 수)를 돌려줍니다. 성공/실패 화면을 정하고
 *   맨 아래에서 forward. (변수명 resultForRegiste 는 오타지만 무해.)
 *
 *
 *     case VaccineConfig.VACCINE_LIST:
 *         vaccineService = new VaccineService();
 *         vaccineService.getVaccineInjections(request, response);
 *         nextPage = generateView("vaccine_records");
 *         break;
 *
 *   [정상] 목록 조회 → Service 가 DB 에서 전체 접종 기록을 읽어
 *   request 에 담아둡니다(setAttribute). 그리고 목록 JSP 로 forward
 *   → JSP 가 그 데이터를 꺼내 표로 그립니다.
 *
 *
 *     case VaccineConfig.VACCINE_DELETE:
 * //         String fvp_no = request.getParameter("fvp_no");
 *         vaccineService = new VaccineService();
 *         int rsultForDelete = vaccineService.removeVaccineInjectionByFvpNo(request, response);
 *         if (rsultForDelete > 0) { ... "DELETE SUCCESS" ... } else { ... "DELETE FAIL" ... }
 * //         nextPage = generateView("vaccine_records");
 *         response.sendRedirect(request.getContextPath().concat(VaccineConfig.VACCINE_LIST));
 *         return;
 *
 *   [정상 — forward 대신 sendRedirect]
 *   삭제 요청 → Service 가 fvp_no 로 DB 에서 DELETE. 그 다음이
 *   핵심입니다:
 *     [의도된 장치] 주석 처리된 `// nextPage = generateView("vaccine_records");`
 *     는 "삭제 후 목록 JSP 로 forward" 하던 방식입니다. 그 방식이면
 *     사용자가 새로고침할 때 삭제 요청이 다시 날아갈 수 있습니다.
 *     그래서 대신 sendRedirect 로 "목록 주소(.../vaccine_list.vc)로
 *     새로 요청해라" 라고 브라우저에게 시킵니다. 그러면 주소창이
 *     목록 주소가 되고, 새로고침해도 목록 조회만 다시 될 뿐 삭제는
 *     반복되지 않습니다.
 *   sendRedirect 뒤의 `return;` 은 메서드 맨 아래 forward 코드를
 *   실행하지 않고 여기서 doGet 을 끝내려는 것입니다.
 *
 *
 *     }
 *     RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
 *     dispatcher.forward(request, response);
 * }
 *
 *   [정상] switch 를 끝낸 뒤(삭제 case 는 이미 return 으로 빠졌음),
 *   정해진 nextPage(JSP)로 forward 합니다.
 *
 *
 * private String generateView(String viewName) {
 *     return VaccineConfig.VIEW_PREFIX.concat(viewName).concat(VaccineConfig.VIEW_SUFFIX);
 * }
 *
 *   [정상] "views/" + 이름 + ".jsp" 조립. private 라 이 서블릿
 *   안에서만.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   등록 폼 → 등록 처리(INSERT, 성공/실패 화면 forward) → 목록
 *   조회(SELECT, 목록 화면 forward) → 삭제(DELETE 후 목록 주소로
 *   redirect). 각 단계의 콘솔 로그는 "[VaccineController] ..." /
 *   "[VaccineService] ..." / "[VaccineDao] ..." 로 찍힙니다.
 */
