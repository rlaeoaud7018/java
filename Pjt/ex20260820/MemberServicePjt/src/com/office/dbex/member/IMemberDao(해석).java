/*
 * ===================================================================
 *  MemberServicePjt / member/IMemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 프로젝트 개요는 IConfig(해석).java
 *   의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것 (이 프로젝트의 핵심 개념):
 *   "인터페이스를 메서드 규격서로 쓰기." IMemberDao 는 "회원 DAO
 *   라면 이 4가지 메서드를 할 줄 알아야 한다" 만 정하고, 실제로
 *   어떻게 하는지는 안 적습니다. 그 구현은 MemberDao(MySQL 용)와
 *   MemberDaoForOracle(Oracle 용)이 각각 채웁니다. 그래서 나중에
 *   DB 를 바꿔도 이 규격을 따르는 다른 구현으로 "갈아끼우기" 만
 *   하면 됩니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 인터페이스(interface) — 메서드 규격서
 *    구현(본문)이 없는 메서드 "선언" 만 담을 수 있습니다. 세미콜론
 *    으로 끝나는 메서드 한 줄은 "이런 모양의 메서드를 갖춰야 한다"
 *    는 약속이고, 실제 코드는 이 인터페이스를 implements 하는
 *    클래스가 씁니다.
 *
 *  ● implements
 *    `class MemberDao implements IMemberDao` : "나는 IMemberDao 가
 *    요구하는 메서드 4개를 전부 실제 코드로 만들겠다" 는 선언.
 *    하나라도 안 만들면 컴파일 에러입니다.
 *
 *  ● 인터페이스 타입 변수 + 다형성(polymorphism)
 *    `IMemberDao memberDao;` 처럼 변수 타입을 인터페이스로 두면,
 *    그 변수에는 IMemberDao 를 구현한 "어떤 클래스의 객체든" 담을
 *    수 있습니다(MemberDao 든 MemberDaoForOracle 든). 쓰는 쪽
 *    (MemberService)은 "IMemberDao 라는 규격" 만 알면 되고, 실제로
 *    뭐가 들어왔는지는 몰라도 됩니다. → 구현체 갈아끼우기가 쉬워짐.
 *
 *  ● 의존성 주입 (dependency injection)
 *    쓸 객체를 안에서 new 하지 않고 밖에서 받아 오는 방식.
 *    (ex20260812 CoolingController 에서 처음 나왔음. 여기서는
 *    "인터페이스 타입으로" 받는다는 점이 더해짐.)
 *
 *  ● 인터페이스에 담은 상수 (URL, USER, PASSWORD)
 *    인터페이스 필드는 자동으로 public static final(공개 상수).
 *    DB 접속 정보 3개를 여기 두면, 이 인터페이스를 implements 하는
 *    DAO 들이 `URL` 이라고 이름만으로 씁니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * public interface IMemberDao {
 *
 *     final String URL = "jdbc:mysql://localhost:3306/db_member";
 *     final String USER = "root";
 *     final String PASSWORD = "1234";
 *
 *   [정상] DB 접속에 필요한 값 3개를 상수로 둡니다. 내 컴퓨터
 *   (localhost)의 MySQL, db_member 데이터베이스, 계정 root /
 *   비번 1234 라는 뜻입니다. MemberDao 가 `URL`, `USER`, `PASSWORD`
 *   로 그대로 씁니다.
 *
 *
 *     public int insertNewMember(MemberDto memberDto);
 *     public MemberDto selectMemberByMemId(String id);
 *     public int updateMember(MemberDto memberDto);
 *     public int deleteMemberByMemId(String memId);
 * }
 *
 *   [정상 — 규격 선언, 본문 없음]
 *   회원 DAO 가 갖춰야 할 메서드 4개(추가·조회·수정·삭제)입니다.
 *   전부 `{ }` 없이 `;` 로 끝납니다 — "이런 모양으로 만들어라" 만
 *   정하고 방법은 안 적었다는 뜻입니다.
 *     - insertNewMember : 회원 하나를 넣고, 추가된 행 수(int)를 돌려줌
 *     - selectMemberByMemId : 아이디로 회원을 찾아 MemberDto 로 돌려줌
 *                             (없으면 null)
 *     - updateMember : 회원 정보를 고치고, 고쳐진 행 수(int)를 돌려줌
 *     - deleteMemberByMemId : 아이디로 회원을 지우고, 지워진 행 수를 돌려줌
 *   이 4개를 MemberDao(MySQL)와 MemberDaoForOracle(Oracle)이 각각
 *   자기 방식으로 채웁니다.
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 게 없습니다. 이 규격을 실제로 채운
 *   코드는 MemberDao(해석).java(MySQL, JDBC 로 진짜 구현)와
 *   MemberDaoForOracle(해석).java(아직 빈 껍데기)에서 이어집니다.
 */
