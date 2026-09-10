/*
 * ===================================================================
 *  MemberServicePjt / member/MemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. IMemberDao 규격을 "MySQL + JDBC"
 *   로 실제 구현한 파일입니다. 프로젝트 개요는 IConfig(해석).java,
 *   인터페이스 개념은 IMemberDao(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것 (이 프로젝트의 또 다른 핵심):
 *   "JDBC 로 DB 를 다루는 표준 6단계" 와, 값을 넣을 때 문자열
 *   이어붙이기 대신 물음표(?) 자리표시자를 쓰는 이유. 네 메서드가
 *   전부 같은 6단계 틀을 따르므로, insertNewMember 하나를 제대로
 *   보면 나머지는 SQL 만 다릅니다.
 *
 *   컴파일 상태: JDBC 는 자바 표준이라 컴파일됩니다(실행 시엔 MySQL
 *   드라이버와 DB 가 필요).
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● JDBC (Java Database Connectivity)
 *    자바에서 DB 에 연결해 SQL 을 실행하는 표준 방법.
 *
 *  ● @Override
 *    "이 메서드는 인터페이스(또는 부모)에 있는 걸 실제로 구현하는
 *    것" 이라는 표시. 이름·매개변수를 잘못 쓰면 컴파일 에러로
 *    알려줘서 실수를 막아 줍니다.
 *
 *  ● JDBC 표준 6단계 (네 메서드 공통 틀)
 *    1) Connection 얻기 : DriverManager.getConnection(URL, USER,
 *       PASSWORD) — 앱과 DB 서버를 잇는 통로.
 *    2) SQL 문자열 준비 : 실행할 명령. 값이 들어갈 자리는 ? 로 비워둠.
 *    3) PreparedStatement 만들기 : conn.prepareStatement(sql) —
 *       SQL 을 미리 컴파일한 "실행 담당" 객체.
 *    4) 값 주입 : pstmt.setString(1, 값) — 1번째 ? 에 값을 끼움
 *       (번호는 1부터).
 *    5) 실행 :
 *         - executeUpdate() : INSERT/UPDATE/DELETE 용. 영향받은
 *           행 수(int)를 돌려줌 (예: 1건 추가 → 1).
 *         - executeQuery()  : SELECT 용. 결과표(ResultSet)를 돌려줌.
 *    6) 자원 해제 : finally 에서 ResultSet → PreparedStatement →
 *       Connection 순으로 close(). 외부 자원은 안 닫으면 계속
 *       물고 있게 되므로 반드시 닫습니다(성공하든 실패하든).
 *
 *  ● ? 자리표시자 (PreparedStatement) vs 문자열 이어붙이기
 *    값을 SQL 문자열에 직접 이어붙이면 (a) 문자열 값에 따옴표를
 *    직접 챙겨야 하고, (b) 악의적 입력이 SQL 구조를 바꿔버리는
 *    "SQL 인젝션" 위험이 있습니다. ? 로 비워두고 setString 으로
 *    끼우면 DB 드라이버가 값을 안전하게 처리해 줍니다.
 *
 *  ● ResultSet / rs.next() / RowMapper
 *    ResultSet 은 SELECT 결과표. rs.next() 는 "다음 행으로 이동,
 *    있으면 true". while (rs.next()) 로 한 행씩 돌면서 rs.getString
 *    ("컬럼명") 등으로 값을 꺼내 MemberDto 로 옮겨 담습니다 — 이
 *    "표의 한 행 → 객체 하나" 변환을 RowMapper 라고 부릅니다.
 *
 *  ● try / catch (SQLException) / finally
 *    DB 작업 중 오류는 SQLException 으로 던져집니다. catch 에서
 *    e.printStackTrace() 로 내용을 찍고, finally 에서 자원을 닫습니다.
 *
 *  ● 삼항 연산자 (조건 ? A : B)
 *    조건이 true 면 A, false 면 B 를 값으로 씁니다.
 *    `dtos.size() > 0 ? dtos.get(0) : null` = "찾았으면 첫 번째,
 *    못 찾았으면 null".
 *
 *  ● int result = -1 초기값
 *    실행 전 -1 로 두면, 중간에 예외가 나서 5단계를 못 넘겼을 때
 *    -1 이 그대로 반환됩니다("정상 실행 안 됨" 표시).
 *
 *
 * ###################################################################
 *  1. 코드 해석 — insertNewMember (6단계를 그대로 보여줌)
 * ###################################################################
 *
 *
 * @Override
 * public int insertNewMember(MemberDto memberDto) {
 *     System.out.println("[MemberDao] insertNewMember()");
 *     Connection conn = null;
 *     PreparedStatement pstmt = null;
 *     int result = -1;
 *     try {
 *         conn = DriverManager.getConnection(URL, USER, PASSWORD);              // 1
 *
 *         // (원본에서 아래는 /* *(별) 로 주석 처리)
 *         // String sql = "INSERT INTO TBL_MEMBER(...) VALUES(" + memberDto.getMemId() + ", " + ... + ")";
 *
 *         String sql = "INSERT INTO TBL_MEMBER(memId, memPw, memMail, memPhone) "
 *                    + "VALUES(?, ?, ?, ?)";                                    // 2
 *         pstmt = conn.prepareStatement(sql);                                   // 3
 *         pstmt.setString(1, memberDto.getMemId());                             // 4
 *         pstmt.setString(2, memberDto.getMemPw());
 *         pstmt.setString(3, memberDto.getMemMail());
 *         pstmt.setString(4, memberDto.getMemPhone());
 *         result = pstmt.executeUpdate();   // 0 또는 1                          // 5
 *     } catch (SQLException e) {
 *         e.printStackTrace();
 *     } finally {
 *         try {
 *             if (pstmt != null) pstmt.close();
 *             if (conn != null) conn.close();                                   // 6
 *         } catch (SQLException e) { e.printStackTrace(); }
 *     }
 *     return result;  // 정상: 1(추가됨), 실패: 0, 예외: -1
 * }
 *
 *   [정상 — JDBC 6단계]
 *   위 "0. 개념 정리" 의 1~6단계가 이 메서드 안에 그대로 들어
 *   있습니다. INSERT 는 executeUpdate() 를 쓰고, 회원 1명이
 *   추가되면 1 을 돌려줍니다.
 *
 *   [의도된 장치] 주석 처리된 "문자열 이어붙이기" SQL:
 *   memberDto.getMemId() 등을 + 로 그냥 붙였습니다. 이러면 값에
 *   따옴표가 없어 SQL 문법이 깨지고, 입력값이 SQL 구조를 바꾸는
 *   위험(SQL 인젝션)도 생깁니다. 그래서 이걸 버리고 아래처럼
 *   VALUES(?, ?, ?, ?) + setString 방식을 쓰는 것입니다 — 이
 *   대비가 이 메서드의 학습 포인트입니다.
 *
 *
 * ###################################################################
 *  2. 나머지 세 메서드 (SQL 만 다르고 틀은 동일)
 * ###################################################################
 *
 *
 * selectMemberByMemId(String id)
 *   SQL: SELECT * FROM TBL_MEMBER WHERE memId = ?
 *   - 5단계에서 executeQuery() 를 써서 ResultSet(rs) 을 받습니다.
 *   - while (rs.next()) 로 결과 행을 돌며, 각 컬럼(memNo, memId, ...)
 *     을 꺼내 7개짜리 생성자로 MemberDto 를 만들어 ArrayList(dtos)에
 *     담습니다 (RowMapper).
 *   - memId 는 보통 유일하므로 결과는 0행 또는 1행입니다. 그래서
 *     마지막에 `dtos.size() > 0 ? dtos.get(0) : null` — 찾았으면
 *     첫 DTO, 못 찾았으면 null 을 돌려줍니다.
 *   - 6단계에서 rs 도 함께 close() 합니다.
 *
 * updateMember(MemberDto memberDto)
 *   SQL: UPDATE TBL_MEMBER SET memPw=?, memMail=?, memPhone=? WHERE memId=?
 *   - ? 순서에 맞춰 setString(1, pw), (2, mail), (3, phone), (4, id).
 *     WHERE 에 쓰는 memId 가 4번째 ? 라는 점에 주의(SET 절 값들이
 *     앞이므로).
 *   - executeUpdate() → 수정된 행 수. 보통 1.
 *
 * deleteMemberByMemId(String memId)
 *   SQL: DELETE FROM TBL_MEMBER WHERE memId = ?
 *   - setString(1, memId) 후 executeUpdate() → 지워진 행 수. 보통 1.
 *
 *   세 메서드 모두 insertNewMember 와 똑같이 1~6단계 틀을 따르고,
 *   result 초기값 -1, catch(SQLException), finally 에서 자원 해제도
 *   동일합니다.
 *
 *
 * ###################################################################
 *  3. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   MemberController 가 이 MemberDao 를 주입했고 MySQL 서버에
 *   db_member DB 와 TBL_MEMBER 테이블이 있으면, 가입 시 실제로
 *   INSERT 되어 1 이 반환되고 "SIGN-UP SUCCESS" 가 나옵니다. 조회·
 *   수정·삭제도 마찬가지로 실제 반영됩니다. (현재 기본값은 Oracle
 *   DAO 주입이라, 이 파일이 도는 걸 보려면 주입을 new MemberDao()
 *   로 바꿔야 합니다 — MemberController(해석) 참고.)
 */
