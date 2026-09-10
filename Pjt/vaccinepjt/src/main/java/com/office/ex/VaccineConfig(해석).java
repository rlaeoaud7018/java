/*
 * ===================================================================
 *  vaccinepjt / VaccineConfig.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 프로젝트(vaccinepjt)는
 *   mempjt 에서 배운 "서블릿 + JSP + JDBC + 설정 상수" 패턴을,
 *   회원가입이 아니라 "독감 백신 접종 등록" 이라는 새 주제에 그대로
 *   적용한 것입니다. 이 파일은 그중 "요청 경로·뷰 경로 상수" 담당
 *   입니다.
 *
 *   이 파일이 가르치려는 것:
 *   프로그램 곳곳에서 쓰는 문자열(요청 주소, 뷰 폴더/확장자)을
 *   상수 클래스 하나에 모아 이름으로 부른다. (mempjt 의 MemberConfig
 *   와 같은 역할.)
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 상수 클래스 (final static public 필드 모음)
 *    값만 담고 로직은 없는 클래스. `VaccineConfig.VACCINE_LIST` 처럼
 *    클래스 이름으로 접근합니다.
 *      final  : 값 못 바꿈
 *      static : 객체 없이 접근
 *      public : 어디서든 접근
 *
 *  ● "_form" vs "_confirm"
 *    보통 "_form" 은 입력 화면을 보여주는 요청, "_confirm" 은 그
 *    폼이 제출된 뒤 실제 처리를 하는 요청입니다.
 *
 *  ● 뷰 경로 조립 (VIEW_PREFIX + 이름 + VIEW_SUFFIX)
 *    결과 화면은 "views/" 폴더 안의 ".jsp" 파일. VaccineController 의
 *    generateView("vaccine_list") 가 이 셋을 이어 "views/vaccine_list.jsp"
 *    를 만듭니다.
 *
 *  ● 이름의 오타 (RESISTER)
 *    VACCINE_RESISTER_FORM 의 "RESISTER" 는 "REGISTER(등록)" 의
 *    오타입니다. 상수 이름과 그 값(경로 문자열)에 똑같이 쓰였기
 *    때문에 동작에는 문제가 없습니다 — [단순 실수, 무해]. 다만
 *    VaccineController 는 이 경로로 들어온 요청을
 *    generateView("vaccine_register_form")(철자 맞음) 으로 넘기므로,
 *    "요청 URL 철자(resister)와 JSP 파일 철자(register)가 다르다"
 *    는 점은 알아두세요.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * final static public String HOME = "/";
 *
 * final static public String VACCINE_RESISTER_FORM    = "/vaccine_resister_form.vc";
 * final static public String VACCINE_RESISTER_CONFIRM = "/vaccine_resister_confirm.vc";
 * final static public String VACCINE_LIST             = "/vaccine_list.vc";
 * final static public String VACCINE_DELETE           = "/vaccine_delete.vc";
 *
 *   [정상 — 요청 경로 상수]
 *   브라우저가 보낼 수 있는 .vc 요청 주소들입니다. 등록 폼 / 등록
 *   처리 / 목록 조회 / 삭제. VaccineController 의 switch 가
 *   `case VaccineConfig.VACCINE_LIST:` 처럼 이 상수로 분기합니다.
 *
 *
 * final static public String VIEW_PREFIX = "views/";
 * final static public String VIEW_SUFFIX = ".jsp";
 *
 *   [정상 — 뷰 경로 조립용]
 *   "views/" + "vaccine_records" + ".jsp" = "views/vaccine_records.jsp".
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   실행되는 게 없습니다. DB 접속 상수(DRIVER/URL/USER/PASSWORD)는
 *   이 파일이 아니라 IVaccineConfig 인터페이스가 담고 있습니다
 *   (IVaccineConfig(해석).java).
 */
