/*
 * ===================================================================
 *  MemberServicePjt / MainClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 프로젝트의 시작점(main)입니다.
 *   프로젝트 개요는 IConfig(해석).java 의 "0. 개념 정리" 를 먼저
 *   보세요.
 *
 *   이 파일이 가르치려는 것:
 *   시작점은 딱 한 줄 — Controller 를 만들어 실행시키는 것. 그리고
 *   아래 주석 블록은 "JDBC 로 DB 에 직접 붙어 보는 실험" 을 먼저
 *   해 본 흔적입니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● main 메서드
 *    `public static void main(String[] args)`. 실행 시작점.
 *
 *  ● import
 *    다른 패키지 클래스를 가져오는 선언. 여기선 member 패키지의
 *    MemberController 를 가져옵니다.
 *
 *  ● JDBC 연결의 최소 형태 (아래 주석 블록에 나옴)
 *    - DriverManager.getConnection(url, user, password)
 *      : DB 서버에 접속해 Connection(연결 통로) 객체를 얻는다.
 *    - try / catch / finally
 *      : 접속 중 오류가 나면 catch 로 잡고, 성공하든 실패하든
 *        finally 에서 conn.close() 로 연결을 반드시 닫는다(외부
 *        자원은 안 닫으면 계속 물고 있게 되므로).
 *    (진짜로 쓰이는 JDBC 코드는 MemberDao(해석).java 에서 자세히.)
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public static void main(String[] args) {
 *     new MemberController().execute();
 *
 *   [정상]
 *   MemberController 객체를 하나 만들고 execute() 를 바로 실행
 *   합니다. 이 다음부터 벌어지는 일은 전부 member/MemberController
 *   (해석).java 로 이어집니다.
 *
 *
 *     /*
 *     // MySQL Connect TEST
 *     String url = "jdbc:mysql://localhost:3306/db_member";
 *     String user = "root";
 *     String password = "1234";
 *     Connection conn = null;
 *     try {
 *         conn = DriverManager.getConnection(url, user, password);
 *         System.out.println("Connection SUCCESS!!");
 *     } catch (Exception e) {
 *         System.out.println("Connection FAIL!!");
 *         e.printStackTrace();
 *     } finally {
 *         try { if (conn != null) conn.close(); }
 *         catch (Exception e2) { e2.printStackTrace(); }
 *     }
 *     *(주석 끝)
 * }
 *
 *   [의도된 장치 — JDBC 연결 실험 흔적]
 *   통째로 주석 처리돼 있습니다. "이 프로그램을 짜기 전에, DB 에
 *   진짜로 붙는지부터 확인해 보던" 코드입니다. url 형식
 *   (jdbc:mysql://호스트:포트/DB이름), 계정/비번, 그리고
 *   try-catch-finally 로 연결하고 반드시 닫는 뼈대를 보여줍니다.
 *   이 뼈대가 나중에 MemberDao 의 각 메서드 안으로 들어갑니다.
 *   지금은 Controller 흐름만 돌리므로 주석으로 빼둔 것입니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   MemberController.execute() 가 돌면서 메뉴가 뜹니다. 자세한
 *   흐름은 member/MemberController(해석).java.
 */
