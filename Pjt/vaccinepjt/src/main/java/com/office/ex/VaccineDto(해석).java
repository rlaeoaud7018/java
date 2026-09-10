/*
 * ===================================================================
 *  vaccinepjt / VaccineDto.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 접종 기록 한 건을 담는 그릇
 *   (DTO)입니다. 프로젝트 개요는 VaccineConfig(해석).java 를 먼저
 *   보세요.
 *
 *   이 파일이 가르치려는 것:
 *   DB 테이블 3개(사람 / 접종 장소 / 백신 종류)에 흩어진 컬럼을
 *   한 DTO 에 모아 담는다. JOIN 조회 결과 한 행이 이 DTO 하나에
 *   대응합니다. 필드가 16개로 많아서, 생성자는 상황별 3개만 두고
 *   나머지는 setter 로 채웁니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● DTO / private 필드 / getter·setter (캡슐화)
 *    값 묶음 그릇. 필드를 private 로 감추고 get/set 으로만 접근.
 *
 *  ● "한 DTO 에 여러 테이블 컬럼"
 *    필드가 세 묶음입니다:
 *      - tbl_flu_vaccin_person : fvp_no, fvp_name, ss_number(주민번호),
 *        fvp_mail, fvp_phone, fvp_reg_date, fvp_mod_date
 *      - tbl_fv_location : fv_location_no/name/addr/phone/manager
 *      - fv_type : fv_type_no, fv_type, fv_type_manuf_country/date
 *    JOIN 결과 한 행에 이 세 묶음이 다 있으므로, DTO 하나에 다
 *    받습니다.
 *
 *  ● 생성자 오버로딩 3개
 *      0개  : 빈 DTO (JOIN 조회에서 setter 로 채울 때 씀)
 *      6개  : 등록 폼 값 (이름·주민번호·메일·전화·장소번호·백신종류)
 *      9개  : 번호·등록일·수정일까지 포함한 버전
 *
 *  ● this / 섀도잉
 *    매개변수 이름 = 필드 이름이라 `this.fvp_name = fvp_name;` 으로
 *    필드를 콕 집습니다.
 *
 *  ● 필드 이름에 밑줄(snake_case)
 *    보통 자바 필드는 fvpName 처럼 쓰는데, 여기선 DB 컬럼 이름
 *    (fvp_name)을 그대로 따서 밑줄을 씁니다. 그래서 getter 이름도
 *    getFvp_name() 처럼 조금 어색하게 나옵니다 — 문법 문제는 없습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * // tbl_flu_vaccin_person
 * private int fvp_no;
 * private String fvp_name, ss_number, fvp_mail, fvp_phone, fvp_reg_date, fvp_mod_date;
 * // tbl_fv_location
 * private String fv_location_no, fv_location_name, fv_location_addr, fv_location_phone, fv_location_manager;
 * // fv_type
 * private int fv_type_no;
 * private String fv_type, fv_type_manuf_country, fv_type_manuf_date;
 *
 *   [정상] 세 테이블에서 온 컬럼 16개. 주석으로 어느 테이블 소속
 *   인지 표시해 뒀습니다.
 *
 *
 * public VaccineDto() { }                                             // 0개
 * public VaccineDto(String fvp_name, String ss_number, String fvp_mail,
 *                   String fvp_phone, String fv_location_no, String fv_type) { ... }  // 6개
 * public VaccineDto(int fvp_no, ... , String fvp_reg_date, String fvp_mod_date) { ... }  // 9개
 *
 *   [정상 — 생성자 오버로딩 3종]
 *     - 0개 : VaccineDao.selectVaccineInjections() 에서 빈 DTO 를
 *             만든 뒤 setter 로 컬럼을 하나씩 채울 때.
 *     - 6개 : VaccineService.registeVaccineInjection() 에서 등록 폼
 *             값을 묶을 때(딱 이 6개만 입력받으므로).
 *     - 9개 : 번호·등록일·수정일까지 있는 완전한 형태를 한 번에
 *             넘길 때.
 *   안 채운 필드는 기본값(int 0, String null).
 *
 *
 * // + getFvp_no/setFvp_no ~ getFv_type_manuf_date/setFv_type_manuf_date 전체 쌍
 *
 *   [정상] 필드 16개 각각의 getter/setter. 패턴이 반복되므로 하나만
 *   보면 나머지도 같습니다. VaccineDao 가 JOIN 결과를 이 setter 들로
 *   채우고, JSP 가 getter 들로 꺼내 화면에 표시합니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   실행되는 게 없습니다(설계도, main 없음). Service 가 폼 값이나
 *   JOIN 조회 결과를 이 그릇에 담아 주고받습니다.
 */
