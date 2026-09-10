/*
 * ===================================================================
 *  MemberServicePjt(루트) / member/MemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. IMemberDao(상수만 담긴 인터페
 *   이스)를 implements 하고, MySQL + JDBC 로 회원 저장·조회·수정을
 *   하는 파일입니다. 프로젝트 개요는 IMemberDao(해석).java 를,
 *   JDBC 6단계는 ex20260820 MemberDao(해석).java 를 먼저 보세요.
 *
 *   이 파일이 (이 버전에서) 가르치려는 것:
 *   JDBC 표준 6단계 틀. insertNewMember 하나를 보면 나머지는 SQL 만
 *   다릅니다. 그리고 값을 넣을 때 문자열 이어붙이기 대신 ? 자리
 *   표시자를 쓰는 이유.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● implements IMemberDao — 이 버전에서는 "상수 가져오기용"
 *    IMemberDao 에 메서드 규격이 없으므로, 이 implements 는 URL/
 *    USER/PASSWORD 상수를 `URL` 처럼 이름만으로 쓰기 위한 것입니다.
 *    그래서 이 파일의 메서드들에는 @Override 가 없습니다(오버라이드
 *    할 규격이 없으니까). ex20260820 버전은 IMemberDao 에 메서드
 *    규격이 생겨서 @Override 가 붙습니다.
 *
 *  ● JDBC 표준 6단계 (주석 번호와 대응)
 *    1) Connection : DriverManager.getConnection(URL, USER, PASSWORD)
 *    2) SQL 문자열 : 값 자리는 ? 로 비워둠
 *    3) PreparedStatement : conn.prepareStatement(sql)
 *    4) 값 주입 : pstmt.setString(1, ...)
 *    5) 실행 : executeUpdate()(INSERT/UPDATE, 행 수) /
 *              executeQuery()(SELECT, ResultSet)
 *    6) finally 에서 rs → pstmt → conn 순 close()
 *
 *  ● ? 자리표시자 vs 문자열 이어붙이기
 *    값을 SQL 문자열에 + 로 직접 붙이면 따옴표 문제·SQL 인젝션
 *    위험이 있습니다. ? + setString 이 안전합니다.
 *
 *  ● ResultSet / rs.next() / RowMapper
 *    SELECT 결과를 한 행씩 돌며 컬럼을 꺼내 MemberDto 로 옮겨 담기.
 *
 *  ● 삼항 연산자
 *    `dtos.size() > 0 ? dtos.get(0) : null` — 찾았으면 첫 번째,
 *    없으면 null.
 *
 *  ● ex20260820 버전과의 차이
 *    - @Override 없음(위 설명대로).
 *    - deleteMemberByMemId 메서드가 없습니다(삭제 미구현).
 *
 *
 * ###################################################################
 *  1. 코드 해석 — insertNewMember (6단계를 그대로 보여줌)
 * ###################################################################
 *
 *
 * public int insertNewMember(MemberDto memberDto) {
 *     Connection conn = null; PreparedStatement pstmt = null; int result = -1;
 *     try {
 *         conn = DriverManager.getConnection(URL, USER, PASSWORD);        // 1
 *
 *         // (원본에서 아래는 /* *(별) 로 주석 처리 — 문자열 이어붙이기 SQL)
 *         // String sql = "INSERT ... VALUES(" + memberDto.getMemId() + ", " + ... + ")";
 *
 *         String sql = "INSERT INTO TBL_MEMBER(memId, memPw, memMail, memPhone) "
 *                    + "VALUES(?, ?, ?, ?)";                              // 2
 *         pstmt = conn.prepareStatement(sql);                            // 3
 *         pstmt.setString(1, memberDto.getMemId());                      // 4
 *         pstmt.setString(2, memberDto.getMemPw());
 *         pstmt.setString(3, memberDto.getMemMail());
 *         pstmt.setString(4, memberDto.getMemPhone());
 *         result = pstmt.executeUpdate();   // 0 또는 1                    // 5
 *     } catch (SQLException e) {
 *         e.printStackTrace();
 *     } finally {
 *         try { if (pstmt != null) pstmt.close(); if (conn != null) conn.close(); }  // 6
 *         catch (SQLException e) { e.printStackTrace(); }
 *     }
 *     return result;   // 정상 1, 실패 0, 예외 -1
 * }
 *
 *   [정상 — JDBC 6단계]
 *   [의도된 장치] 주석 처리된 "문자열 이어붙이기" SQL: 값을 + 로
 *   그냥 붙이면 문자열에 따옴표가 없어 SQL 이 깨지고, SQL 인젝션
 *   위험도 생깁니다. 그래서 아래 VALUES(?, ?, ?, ?) + setString
 *   방식을 씁니다. INSERT 라 executeUpdate() → 추가된 행 수(보통 1).
 *
 *
 * ###################################################################
 *  2. 나머지 두 메서드 (SQL 만 다르고 틀은 동일)
 * ###################################################################
 *
 *
 * selectMemberByMemId(String id)
 *   SQL: SELECT * FROM TBL_MEMBER WHERE memId = ?
 *   - executeQuery() 로 ResultSet(rs) 을 받아, while (rs.next()) 로
 *     각 행의 컬럼(memNo ~ memModDate)을 꺼내 7개짜리 생성자로
 *     MemberDto 를 만들어 ArrayList(dtos)에 담습니다(RowMapper).
 *   - memId 는 유일하므로 결과는 0행 또는 1행.
 *     `dtos.size() > 0 ? dtos.get(0) : null` 로 첫 DTO 또는 null 반환.
 *   - finally 에서 rs 도 함께 close().
 *
 * updateMember(MemberDto memberDto)
 *   SQL: UPDATE TBL_MEMBER SET memPw=?, memMail=?, memPhone=? WHERE memId=?
 *   - ? 순서: setString(1, pw), (2, mail), (3, phone), (4, id).
 *     WHERE 에 쓰는 memId 가 4번째 ? (SET 절 값들이 앞).
 *   - executeUpdate() → 수정된 행 수.
 *
 *
 * ###################################################################
 *  3. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   MySQL 서버에 db_member DB 와 TBL_MEMBER 테이블이 있으면 가입 시
 *   INSERT, 로그인 시 SELECT, 수정 시 UPDATE 가 실제로 실행됩니다.
 */
