/*
 * ===================================================================
 *  vaccinepjt / VaccineDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. IVaccineDao 규격을 MySQL + JDBC
 *   로 구현한 파일입니다. 프로젝트 개요는 VaccineConfig(해석).java 를,
 *   인터페이스 상속은 IVaccineDao(해석).java 를, JDBC 6단계는
 *   mempjt 의 MemberDao(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것 (앞 프로젝트들 대비 새 내용):
 *   조회 SQL 에서 "JOIN" 으로 테이블 3개를 연결해 한 번에 가져오는 것.
 *   그리고 그 결과를 필드가 많은 VaccineDto 에 setter 로 채우는 것.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● implements IVaccineDao (상수 + 규격 한꺼번에)
 *    IVaccineDao 가 IVaccineConfig 를 extends 했으므로, 이 한 번의
 *    implements 로 DB 접속 상수(DRIVER/URL/USER/PASSWORD)와 메서드
 *    규격(insertVaccineInjection)을 둘 다 얻습니다.
 *
 *  ● @Override 는 insert 에만
 *    insertVaccineInjection 만 인터페이스 규격에 있으므로 @Override
 *    가 붙습니다. select/delete 는 이 DAO 만의 메서드라 @Override
 *    가 없습니다(규격에 안 넣었을 뿐, 실수 아님).
 *
 *  ● JDBC 6단계 (mempjt 와 동일)
 *    Class.forName(DRIVER) → getConnection → SQL(? 자리표시자) →
 *    prepareStatement → set → execute → finally 에서 close.
 *
 *  ● SQL JOIN (이 파일의 새 개념)
 *    여러 테이블에 흩어진 데이터를 "연결" 해서 한 번에 조회하는
 *    방법. 여기서는:
 *        tbl_flu_vaccin_person fp   (접종받은 사람)
 *        JOIN tbl_fv_location  fl   ON fp.fv_location_no = fl.fv_location_no
 *        JOIN tbl_fv_type      ft   ON fp.fv_type        = ft.fv_type
 *    "사람 테이블의 fv_location_no 와 장소 테이블의 fv_location_no
 *     가 같은 행끼리 이어라, 그리고 백신 종류도 같은 것끼리 이어라"
 *    라는 뜻입니다. 결과 한 행에는 사람 + 접종 장소 + 백신 종류
 *    정보가 모두 들어 있습니다. (fp, fl, ft 는 테이블 별명.)
 *
 *  ● 필드 많은 DTO 를 setter 로 채우기
 *    JOIN 결과에는 컬럼이 많아서, 생성자 인자로 다 넘기기보다
 *    빈 VaccineDto() 를 만든 뒤 setFvp_no(...), setFv_location_name(...)
 *    처럼 하나씩 채웁니다.
 *
 *  ● setInt / getInt
 *    숫자 컬럼은 setString/getString 이 아니라 setInt/getInt 를
 *    씁니다(fvp_no, fv_type_no).
 *
 *
 * ###################################################################
 *  1. 코드 해석 (세 메서드)
 * ###################################################################
 *
 *
 * @Override
 * public int insertVaccineInjection(VaccineDto vaccineDto) {
 *     ... Class.forName(DRIVER); conn = getConnection(URL, USER, PASSWORD); ...
 *     String sql = "INSERT INTO tbl_flu_vaccin_person("
 *                + "fvp_name, ss_number, fvp_mail, fvp_phone, fv_location_no, fv_type) "
 *                + "VALUES(?, ?, ?, ?, ?, ?)";
 *     pstmt = conn.prepareStatement(sql);
 *     pstmt.setString(1, vaccineDto.getFvp_name());
 *     ... 2~6번 ? 에 나머지 5개 값 ...
 *     result = pstmt.executeUpdate();
 *     ... finally 에서 pstmt, conn close ...
 *     return result;
 * }
 *
 *   [정상 — JDBC 6단계, INSERT]
 *   접종 사람 테이블에 한 줄을 넣습니다. 값은 전부 ? + setString
 *   방식. executeUpdate() → 추가된 행 수(보통 1). VaccineService 의
 *   registeVaccineInjection 이 만든 6개짜리 DTO 의 값들이 그대로
 *   1~6번 ? 에 들어갑니다.
 *
 *
 * public List<VaccineDto> selectVaccineInjections() {
 *     ... Class.forName / getConnection ...
 *     String sql = "SELECT * FROM tbl_flu_vaccin_person fp "
 *                + "JOIN tbl_fv_location fl ON fp.fv_location_no = fl.fv_location_no "
 *                + "JOIN tbl_fv_type ft ON fp.fv_type = ft.fv_type";
 *     rs = pstmt.executeQuery();
 *     while (rs.next()) {
 *         VaccineDto vaccineDto = new VaccineDto();
 *         vaccineDto.setFvp_no(rs.getInt("fvp_no"));
 *         vaccineDto.setFvp_name(rs.getString("fvp_name"));
 *         ... 사람 / 장소 / 백신종류 컬럼을 setter 로 하나씩 채움 ...
 *         vaccineDtos.add(vaccineDto);
 *     }
 *     ... finally 에서 rs, pstmt, conn close ...
 *     return vaccineDtos;
 * }
 *
 *   [정상 — JDBC 6단계, JOIN 조회]
 *   테이블 3개를 JOIN 해서, 접종 기록 한 건마다 "사람 + 접종 장소 +
 *   백신 종류" 정보가 다 들어 있는 결과를 받습니다. while (rs.next())
 *   로 한 행씩 돌며 빈 VaccineDto 를 만들어 setter 로 컬럼 값을
 *   채우고 리스트에 담습니다(RowMapper). 조건 없이 전체를 가져오므로
 *   결과가 여러 건 → List 로 돌려줍니다.
 *
 *
 * public int deleteVaccineInjectionByFvpNo(int fvp_no) {
 *     ... Class.forName / getConnection ...
 *     String sql = "DELETE FROM tbl_flu_vaccin_person WHERE fvp_no = ?";
 *     pstmt.setInt(1, fvp_no);
 *     result = pstmt.executeUpdate();
 *     ... finally 에서 close ...
 *     return result;
 * }
 *
 *   [정상 — JDBC 6단계, DELETE]
 *   기록 번호(fvp_no)로 한 줄을 지웁니다. fvp_no 는 숫자라
 *   setInt(1, fvp_no). executeUpdate() → 지워진 행 수(보통 1).
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   MySQL 서버에 db_flu DB 와 tbl_flu_vaccin_person / tbl_fv_location
 *   / tbl_fv_type 테이블이 있으면, 등록 시 INSERT, 목록 시 3-테이블
 *   JOIN SELECT, 삭제 시 DELETE 가 실제로 실행됩니다.
 */
