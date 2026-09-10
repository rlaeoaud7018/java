/*
 * ===================================================================
 *  mempjt / IMemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. "회원 DAO 규격서" 인터페이스
 *   입니다. 프로젝트 개요는 MemberConfig(해석).java 를, 인터페이스
 *   기본 개념은 ex20260820 IMemberDao(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   DAO 가 갖춰야 할 메서드 3개를 규격으로 정하고, DB 접속 정보
 *   (드라이버 포함)를 상수로 담는다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 인터페이스(interface) / 메서드 규격 선언
 *    `;` 로 끝나는 메서드 = "이런 모양으로 만들어라" 는 약속. 실제
 *    코드는 MemberDao 가 implements 로 채웁니다.
 *
 *  ● 인터페이스 필드 = 자동 public static final(상수)
 *    DRIVER, URL, USER, PASSWORD 는 DB 접속에 필요한 값입니다.
 *    이 인터페이스를 implements 하는 MemberDao 가 `DRIVER`, `URL`
 *    처럼 이름만으로 씁니다.
 *
 *  ● DRIVER 상수 (ex20260820 엔 없던 것)
 *    "com.mysql.cj.jdbc.Driver" 는 MySQL JDBC 드라이버 클래스의
 *    전체 이름입니다. MemberDao 가 Class.forName(DRIVER) 로 이
 *    클래스를 메모리에 먼저 불러옵니다(JDBC 준비 단계).
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * public final String DRIVER   = "com.mysql.cj.jdbc.Driver";
 * public final String URL      = "jdbc:mysql://localhost:3306/db_member";
 * public final String USER     = "root";
 * public final String PASSWORD = "1234";
 *
 *   [정상] DB 접속용 상수 4개. 내 컴퓨터의 MySQL, db_member DB,
 *   계정 root/1234, 그리고 사용할 드라이버 클래스 이름.
 *
 *
 * public int insertNewMember(MemberDto memberDto);
 * public MemberDto selectMemberByMemId(String memId);
 * public int updateMemberByMemNo(MemberDto memberDto);
 *
 *   [정상 — 규격 선언, 본문 없음]
 *     - insertNewMember : 회원 추가, 추가된 행 수(int) 반환
 *     - selectMemberByMemId : 아이디로 회원 조회, MemberDto 반환(없으면 null)
 *     - updateMemberByMemNo : 회원 번호(memNo)를 기준으로 정보 수정,
 *                             수정된 행 수(int) 반환
 *   (이전 프로젝트들과 달리 삭제 메서드는 규격에 없습니다 — 이
 *   프로젝트는 가입·로그인·수정까지만 다룹니다.)
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   이 규격을 실제 JDBC 코드로 채운 파일은 MemberDao(해석).java
 *   입니다.
 */
