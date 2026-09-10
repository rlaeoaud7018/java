/*
 * ===================================================================
 *  MemberServicePjt(루트) / member/IMemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 프로젝트는 ex20260820 의
 *   MemberServicePjt 와 같은 이름·같은 주제인데, 그보다 이른/단순한
 *   형태입니다. 이 파일이 그 차이가 가장 잘 보이는 곳입니다.
 *
 *   이 파일이 (이 버전에서) 가르치려는 것:
 *   지금 IMemberDao 는 "메서드 규격서" 가 아니라 그냥 "DB 접속 상수
 *   묶음" 입니다. 메서드 선언이 하나도 없습니다. ex20260820 버전은
 *   여기에 insertNewMember 등 메서드 4개 규격을 추가해서 진짜
 *   "규격서 인터페이스" 로 발전시킵니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 인터페이스(interface)
 *    보통은 "구현 없는 메서드 선언(규격)" 을 담지만, 이렇게 상수만
 *    담을 수도 있습니다. new 로 객체는 못 만듭니다.
 *
 *  ● 인터페이스 필드 = 자동 public static final(상수)
 *    URL, USER, PASSWORD 는 아무 수식어가 없어도 자동으로 공개
 *    상수입니다. (`final String` 이라고 일부만 적었지만 결과는 같음.)
 *
 *  ● implements 로 상수만 가져다 쓰기
 *    MemberDao 가 `implements IMemberDao` 라고 붙인 이유는, 이
 *    버전에서는 "규격을 지키려고" 가 아니라 URL/USER/PASSWORD 를
 *    이름만으로 쓰려고 입니다. (MemberController 가 IMember/IConfig
 *    를 implements 하는 것과 같은 목적.)
 *
 *  ● ex20260820 버전과의 차이 (핵심)
 *    ex20260820 IMemberDao 에는 아래 4줄이 더 있습니다:
 *        public int insertNewMember(MemberDto memberDto);
 *        public MemberDto selectMemberByMemId(String id);
 *        public int updateMember(MemberDto memberDto);
 *        public int deleteMemberByMemId(String memId);
 *    이게 생기면 IMemberDao 가 "DAO 라면 이 4가지를 할 줄 알아야
 *    한다" 는 진짜 규격서가 되고, MemberDao 의 각 메서드에
 *    @Override 가 붙게 됩니다. 이 버전에는 그게 아직 없습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * public interface IMemberDao {
 *     final String URL = "jdbc:mysql://localhost:3306/db_member";
 *     final String USER = "root";
 *     final String PASSWORD = "1234";
 * }
 *
 *   [정상 — 다만 "규격서" 로서는 미완성]
 *   DB 접속 상수 3개만 있습니다. MemberDao 가 이 상수들을 `URL`,
 *   `USER`, `PASSWORD` 로 씁니다. 메서드 규격이 없으므로 지금은
 *   "인터페이스" 라기보다 "상수 보관함" 에 가깝습니다.
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 게 없습니다. 인터페이스가 상수만 담다가
 *   "메서드 규격서 + 구현체 갈아끼우기(다형성)" 로 발전하는 모습은
 *   ex20260820 MemberServicePjt 의 IMemberDao(해석) / MemberDao(해석)
 *   에서 이어집니다.
 */
