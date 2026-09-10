/*
 * ===================================================================
 *  MemberServicePjt / member/MemberDaoForOracle.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. IMemberDao 규격을 "Oracle 용"
 *   으로 구현하려고 만든 파일인데, 아직 내용이 비어 있습니다.
 *   인터페이스 개념은 IMemberDao(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   "인터페이스만 지키면 빈 껍데기라도 컴파일은 된다" 는 것과,
 *   "같은 규격의 구현체를 여러 개 두고 골라 쓸 수 있다" 는 것.
 *   MemberDao(MySQL)와 이 파일은 둘 다 IMemberDao 를 구현하므로,
 *   MemberService 입장에서는 완전히 맞바꿀 수 있습니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● implements IMemberDao
 *    "IMemberDao 가 요구하는 메서드 4개를 전부 만들겠다" 는 선언.
 *    4개를 다 적기만 하면(본문이 비어도) 컴파일 조건은 충족됩니다.
 *
 *  ● @Override
 *    인터페이스에 있는 메서드를 구현한다는 표시.
 *
 *  ● 메서드 스텁(stub) / TODO Auto-generated method stub
 *    개발 도구(Eclipse)가 "인터페이스를 구현해라" 했을 때 자동으로
 *    만들어 주는 "뼈대만 있는 빈 메서드". 본문은 `return 0;` 이나
 *    `return null;` 같은 아무 값만 있고, "// TODO ..." 는 "여기
 *    실제 코드를 채워라" 는 자동 주석입니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * public class MemberDaoForOracle implements IMemberDao {
 *
 *     @Override public int insertNewMember(MemberDto memberDto) {
 *         // TODO Auto-generated method stub
 *         return 0;
 *     }
 *     @Override public MemberDto selectMemberByMemId(String id) {
 *         // TODO Auto-generated method stub
 *         return null;
 *     }
 *     @Override public int updateMember(MemberDto memberDto) {
 *         // TODO Auto-generated method stub
 *         return 0;
 *     }
 *     @Override public int deleteMemberByMemId(String memId) {
 *         // TODO Auto-generated method stub
 *         return 0;
 *     }
 * }
 *
 *   [미완성 — 오류 아님, 자동 생성된 빈 껍데기]
 *   메서드 4개가 규격대로 있긴 한데 안이 전부 비어 있습니다.
 *   Oracle 용 JDBC 코드(접속 정보·SQL 방언 등)를 여기 채워 넣는
 *   것이 원래 할 일인데, 아직 안 했습니다.
 *
 *   지금 상태의 의미:
 *   - insertNewMember / updateMember / deleteMemberByMemId 는 항상
 *     0 을 돌려줍니다 → MemberService 는 "처리된 행 0개 = 실패" 로
 *     판단합니다.
 *   - selectMemberByMemId 는 항상 null → 로그인은 "ID 없음" 으로
 *     실패합니다.
 *   그래서 MemberController 가 이 클래스를 주입한 상태에서는 모든
 *   기능이 FAIL 로 끝납니다. 그건 버그가 아니라 "Oracle 구현을
 *   아직 안 만들었기 때문" 입니다.
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   이 파일이 보여주는 것: 구현체를 여러 개 두는 구조에서는, 아직
 *   준비 안 된 구현체도 규격(인터페이스)만 지키면 프로그램이 돌긴
 *   돕니다(결과만 비어 있을 뿐). 실제 동작은 MemberDao(MySQL, JDBC
 *   구현 완료)를 주입하면 나옵니다.
 */
