/*
 * ===================================================================
 *  vaccinepjt / VaccineService.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. request 에서 값을 꺼내 DTO 로
 *   묶고, DAO 를 시켜 DB 작업을 하는 담당입니다. 프로젝트 개요는
 *   VaccineConfig(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   등록(파라미터 → DTO → INSERT), 목록(SELECT → request 에 담기),
 *   삭제(fvp_no → DELETE) 세 가지를 Controller 대신 처리한다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● VaccineDao 를 직접 new (필드)
 *    `private VaccineDao vaccineDao = new VaccineDao();` — 인터페이스
 *    타입 주입이 아니라 구체 클래스로 직접 만듭니다(루트
 *    MemberServicePjt 와 같은 단계).
 *
 *  ● request.getParameter("이름")
 *    폼이 보낸 값 하나를 이름으로 꺼냅니다. 항상 String.
 *
 *  ● request.setAttribute("이름", 값)
 *    Controller 가 조회한 데이터를 JSP 로 넘길 때 씁니다. 이 요청이
 *    끝나면 사라집니다(세션과 다름).
 *
 *  ● List<VaccineDto> 반환
 *    목록 조회는 여러 건이 나오므로 List 로 돌려받습니다.
 *
 *  ● Integer.parseInt(문자열)
 *    "12" → 12. 삭제 시 fvp_no 가 폼에서 문자열로 오므로 정수로
 *    바꿉니다.
 *
 *  ● CLASS_NAME.concat(...) 로그
 *    "[VaccineService] " 접두어를 붙여 로그를 찍습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * final private String CLASS_NAME = "[VaccineService] ";
 * private VaccineDao vaccineDao = new VaccineDao();
 *
 *   [정상] 로그 접두어 필드, 그리고 DAO 를 필드로 하나 만듭니다.
 *   세 메서드가 이 하나를 공유합니다.
 *
 *
 * public int registeVaccineInjection(HttpServletRequest request, HttpServletResponse response) {
 *     String fvp_name       = request.getParameter("fvp_name");
 *     String ss_number      = request.getParameter("ss_number");
 *     String fvp_mail       = request.getParameter("fvp_mail");
 *     String fvp_phone      = request.getParameter("fvp_phone");
 *     String fv_location_no = request.getParameter("fv_location_no");
 *     String fv_type        = request.getParameter("fv_type");
 *
 *     VaccineDto vaccineDto =
 *         new VaccineDto(fvp_name, ss_number, fvp_mail, fvp_phone, fv_location_no, fv_type);
 *
 *     int result = vaccineDao.insertVaccineInjection(vaccineDto);
 *     if (result > 0) { ... SUCCESS ... } else { ... "FAIl" ... }
 *     return result;
 * }
 *
 *   [정상]
 *   접종 등록 폼의 값 6개를 꺼내 VaccineDto 의 6개짜리 생성자로
 *   묶고, DAO 에 INSERT 를 시켜 결과(행 수)를 돌려줍니다.
 *   ([단순 실수, 무해] 실패 메시지 문자열 "FAIl" 오타.)
 *
 *
 * public void getVaccineInjections(HttpServletRequest request, HttpServletResponse response) {
 *     List<VaccineDto> vaccineDtos = vaccineDao.selectVaccineInjections();
 *     request.setAttribute("vaccineDtos", vaccineDtos);
 * }
 *
 *   [정상]
 *   DAO 에서 전체 접종 기록 목록(List)을 받아, request 에
 *   "vaccineDtos" 라는 이름으로 담아둡니다. 이 메서드는 값을 안
 *   돌려줍니다(void) — 대신 request 에 실어두고, 뒤에 Controller 가
 *   목록 JSP 로 forward 하면 JSP 가 그걸 꺼내 씁니다.
 *   (response 매개변수는 받지만 안 씁니다 — 형식만 맞춰둔 것.)
 *
 *
 * public int removeVaccineInjectionByFvpNo(HttpServletRequest request, HttpServletResponse response) {
 *     int fvp_no = Integer.parseInt(request.getParameter("fvp_no"));
 *     int result = vaccineDao.deleteVaccineInjectionByFvpNo(fvp_no);
 *     if (result > 0) { ... SUCCESS ... } else { ... FAIL ... }
 *     return result;
 * }
 *
 *   [정상]
 *   삭제할 기록 번호(fvp_no)를 문자열로 받아 정수로 바꾸고, DAO 에
 *   DELETE 를 시켜 지워진 행 수를 돌려줍니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   각 메서드가 로그를 찍고 VaccineDao 를 부릅니다. MySQL + db_flu
 *   + 관련 테이블이 준비돼 있으면 실제로 INSERT/SELECT/DELETE 가
 *   됩니다.
 */
