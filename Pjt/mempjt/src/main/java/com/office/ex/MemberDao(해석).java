/*
 * ===================================================================
 *  mempjt / MemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. IMemberDao 규격을 MySQL + JDBC
 *   로 구현한 파일입니다. 프로젝트 개요는 MemberConfig(해석).java 를,
 *   JDBC 6단계는 ex20260820 MemberDao(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   ex20260820 의 JDBC 코드에 "0단계 — 드라이버 로딩(Class.forName)"
 *   이 추가된 형태. 나머지는 같은 6단계 틀입니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● Class.forName(DRIVER)  ← 이번에 추가된 단계
 *    JDBC 드라이버 클래스("com.mysql.cj.jdbc.Driver")를 이름만 보고
 *    메모리에 불러옵니다. 이걸 해두면 DriverManager 가 MySQL 에
 *    붙는 법을 알게 됩니다. (요즘 버전에서는 생략해도 자동 인식되는
 *    경우가 많지만, 학습용으로 명시해 둔 것입니다.)
 *
 *  ● JDBC 표준 흐름 (여기 주석의 번호와 대응)
 *    1) 드라이버 로딩  : Class.forName(DRIVER)
 *    2) Connection     : DriverManager.getConnection(URL, USER, PASSWORD)
 *    3) SQL 문자열      : 값 자리는 ? 로 비워둠
 *    4) PreparedStatement : conn.prepareStatement(sql)
 *    5) 값 주입         : pstmt.setString(1, ...) / setInt(...)
 *    6) 실행           : executeUpdate()(INSERT/UPDATE, 행 수 반환) /
 *                        executeQuery()(SELECT, ResultSet 반환)
 *    finally           : rs → pstmt → conn 순서로 close()
 *
 *  ● catch (Exception e)  (SQLException 보다 넓게)
 *    Class.forName 은 ClassNotFoundException 을, JDBC 는
 *    SQLException 을 던질 수 있습니다. 둘 다 잡으려고 상위 타입
 *    Exception 으로 받았습니다.
 *
 *  ● List<MemberDto> = new ArrayList<>()
 *    변수 타입을 인터페이스 List 로 두고 실제 객체는 ArrayList 로
 *    만드는, 흔한 관례입니다.
 *
 *  ● ResultSet / rs.next() / RowMapper
 *    SELECT 결과표를 한 행씩 돌며(while rs.next()) 컬럼을 꺼내
 *    MemberDto 로 옮겨 담습니다.
 *
 *  ● 삼항 연산자 (조건 ? A : B)
 *    `memberDtos.size() > 0 ? memberDtos.get(0) : null` — 찾았으면
 *    첫 번째, 없으면 null.
 *
 *
 * ###################################################################
 *  1. 코드 해석 — insertNewMember (틀 전체를 보여줌)
 * ###################################################################
 *
 *
 * @Override
 * public int insertNewMember(MemberDto memberDto) {
 *     Connection conn = null; PreparedStatement pstmt = null; int result = -1;
 *     try {
 *         Class.forName(DRIVER);                                       // 1
 *         conn = DriverManager.getConnection(URL, USER, PASSWORD);     // 2
 *         String sql = "INSERT INTO tbl_member(memId, memPw, memMail, memPhone) "
 *                    + "VALUES(?, ?, ?, ?)";                           // 3
 *         pstmt = conn.prepareStatement(sql);                          // 4
 *         pstmt.setString(1, memberDto.getMemId());                    // 5
 *         pstmt.setString(2, memberDto.getMemPw());
 *         pstmt.setString(3, memberDto.getMemMail());
 *         pstmt.setString(4, memberDto.getMemPhone());
 *         result = pstmt.executeUpdate();                              // 6
 *     } catch (Exception e) {
 *         e.printStackTrace();
 *     } finally {
 *         try { if (pstmt != null) pstmt.close(); if (conn != null) conn.close(); }
 *         catch (Exception e2) { e2.printStackTrace(); }
 *     }
 *     return result;   // 성공 1, 실패 0, 예외 -1
 * }
 *
 *   [정상 — JDBC 표준 흐름]
 *   ex20260820 의 insertNewMember 와 같은데, 맨 앞에 Class.forName
 *   (DRIVER) 이 하나 더 있습니다. INSERT 라 executeUpdate() 를
 *   쓰고, 회원 1명이 추가되면 1 을 돌려줍니다. 값은 전부 ? +
 *   setString 방식(문자열 이어붙이기 아님)으로 안전하게 넣습니다.
 *
 *
 * ###################################################################
 *  2. 나머지 두 메서드 (SQL 만 다르고 틀은 동일)
 * ###################################################################
 *
 *
 * selectMemberByMemId(String id)
 *   SQL: SELECT * FROM TBL_MEMBER WHERE memId = ?
 *   - executeQuery() 로 ResultSet 을 받아, while (rs.next()) 로
 *     각 행의 컬럼(memNo ~ memModDate)을 꺼내 7개짜리 생성자로
 *     MemberDto 를 만들어 리스트에 담습니다.
 *   - memId 는 유일하므로 결과는 0행 또는 1행. 마지막에
 *     `size() > 0 ? get(0) : null` 로 첫 DTO 또는 null 을 반환.
 *   - [의도된 장치] 위쪽에 주석 처리된 if/else 버전은 같은 판정을
 *     삼항 연산자 대신 if 로 쓴 것 — 비교용.
 *   - finally 에서 rs 도 함께 close().
 *
 * updateMemberByMemNo(MemberDto memberDto)
 *   SQL: UPDATE tbl_member SET memPw=?, memMail=?, memPhone=? WHERE memNo=?
 *   - ? 순서에 맞춰 setString(1, pw), (2, mail), (3, phone),
 *     그리고 마지막은 숫자라 setInt(4, memNo).
 *   - WHERE 조건이 memId 가 아니라 memNo(회원 번호)입니다. 그래서
 *     MemberService 가 memNo 를 꼭 채워 넘깁니다.
 *   - executeUpdate() → 수정된 행 수. 보통 1.
 *
 *
 * ###################################################################
 *  3. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   MySQL 서버에 db_member DB 와 tbl_member 테이블이 있으면, 가입
 *   시 INSERT, 로그인 시 SELECT, 수정 시 UPDATE 가 실제로 실행되고
 *   각 메서드가 처리 행 수(또는 조회된 DTO)를 돌려줍니다.
 */
