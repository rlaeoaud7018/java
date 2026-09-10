/*
 * ===================================================================
 *  member/MemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. "데이터에 직접 손대는 담당"
 *   파일입니다(DAO = Data Access Object). 폴더 전체 구조와 공통
 *   용어는 MainClass(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   DAO 의 역할 — 진짜 DB 대신 ArrayList 를 "회원 명단" 으로 쓰고,
 *   저장(insert)과 조회(select)를 담당한다. 그리고 조회 반복문에서
 *   못 찾으면 null 을 돌려주는 패턴.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● DAO (Data Access Object)
 *    데이터를 저장·조회·수정·삭제하는 코드만 모아둔 자리. 다른
 *    파일들은 "어떻게 저장되는지" 는 몰라도 되고, DAO 에게 시키기만
 *    하면 됩니다.
 *
 *  ● import / 미사용 import
 *    다른 패키지 클래스를 가져오는 선언. 여기선 ArrayList 와
 *    Iterator 를 가져오는데, Iterator 는 이 파일에서 안 씁니다
 *    (지우다 만 흔적, 오류 아님).
 *
 *  ● ArrayList<MemberDto>
 *    MemberDto(회원 봉투) 객체를 여러 개 담는, 크기가 자동으로 늘고
 *    주는 목록. 여기서 "회원 명단" 역할을 합니다.
 *
 *  ● 인스턴스 필드로 선언한 ArrayList (이 파일의 핵심 포인트)
 *    `ArrayList<MemberDto> memberDB = new ArrayList<>();` 가 메서드
 *    바깥, 클래스 안에 있으므로 이건 MemberDao "객체마다" 갖는
 *    필드입니다. → new MemberDao() 를 할 때마다 이 줄이 다시
 *    실행되어, 명단이 새 빈 목록으로 태어납니다. (뒤의 "왜 로그인이
 *    안 되나" 의 핵심)
 *
 *  ● 주요 메서드
 *    add(값)     : 목록 맨 뒤에 추가
 *    size()      : 들어있는 개수
 *    get(인덱스)  : 그 자리 값 꺼내기
 *
 *  ● for 반복문
 *    `for (int i = 0; i < memberDB.size(); i++)` : i 를 0 부터
 *    개수 직전까지 1씩 늘리며 목록을 처음부터 끝까지 훑습니다.
 *
 *  ● return (즉시 종료)
 *    메서드가 값을 돌려주고 그 자리서 끝나는 것. for 문 안에서
 *    return 을 만나면 반복도 즉시 멈추고 메서드가 종료됩니다.
 *
 *  ● .equals() (문자열 내용 비교)
 *    `dto.memId.equals(memId)` : 저장된 아이디와 찾는 아이디의
 *    "내용" 이 같은지 확인. == 가 아니라 .equals().
 *
 *  ● null 반환
 *    끝까지 못 찾았을 때 `return null;` → 부른 쪽(MemberService)이
 *    "없구나" 로 판단합니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * import java.util.ArrayList;
 * import java.util.Iterator;
 *
 *   ArrayList 는 아래에서 씁니다. [미사용 — 오류 아님] Iterator 는
 *   import 만 해두고 이 파일 어디서도 안 씁니다.
 *
 *
 * ArrayList<MemberDto> memberDB = new ArrayList<MemberDto>();
 *
 *   회원 봉투(MemberDto)를 담는 목록. 이름이 memberDB 인 건 "이게
 *   DB 역할" 이라는 뜻입니다. 이 줄은 메서드 바깥·클래스 안에
 *   있으므로 MemberDao "객체마다" 갖는 인스턴스 필드입니다. 즉
 *   new MemberDao() 할 때마다 이 줄이 다시 실행되어 명단이 새 빈
 *   목록으로 시작합니다.
 *
 *
 * public void insertMember(MemberDto memberDto) {
 *     System.out.println("[MemberDao] insertMember()");
 *     memberDB.add(memberDto);
 * }
 *
 *   [정상] 받은 봉투를 목록 맨 뒤에 add() 로 붙입니다. 이것이
 *   "회원가입 저장" 의 실제 동작입니다. 반환타입 void.
 *
 *
 * public MemberDto selectMemberByMemId(String memId) {
 *     System.out.println("[MemberDao] selectMemberByMemId()");
 *     for (int i = 0; i < memberDB.size(); i++) {
 *         MemberDto dto = memberDB.get(i);
 *         if (dto.memId.equals(memId)) {
 *             return dto;
 *         }
 *     }
 *     return null;
 * }
 *
 *   [정상] 목록을 처음부터 끝까지 for 로 훑으면서, 각 봉투의 아이디
 *   (dto.memId)가 찾는 memId 와 내용이 같은지 .equals() 로 비교
 *   합니다. 같은 것을 찾으면 그 봉투를 즉시 return(반복도 메서드도
 *   그 자리서 끝). 끝까지 못 찾으면 마지막 줄 return null 로 "없음"
 *   을 돌려줍니다. MemberService 의 doSignIn 이 이 null 여부로
 *   "존재하는 아이디인가" 를 판단합니다.
 *
 *
 * ###################################################################
 *  2. 왜 가입 후 로그인이 안 되나 (member 폴더 3개 파일 종합)
 * ###################################################################
 *
 *   [미완성 — 실수가 아니라 다음 단계 주제]
 *   - memberDB 는 MemberDao 객체가 생길 때 빈 목록으로 시작한다.
 *   - MemberService 는 doSignUp 에서도 doSignIn 에서도
 *     `new MemberDao()` 로 DAO 를 새로 만든다.
 *   그래서 흐름은:
 *     가입   : DAO(A)를 만들어 그 목록에 회원을 add → 메뉴 한 바퀴가
 *              끝나면 DAO(A)는 아무도 안 가리켜서 가비지가 됨
 *     로그인 : DAO(B)를 새로 만듦 → 목록이 비어 있음 → 못 찾음 →
 *              "Sorry. SIGN-IN FAIL!!"
 *   고치려면 DAO(또는 그 목록)를 딱 하나만 만들어 계속 돌려써야
 *   합니다(예: static 으로 두기, 또는 위에서 한 번 만들어 넘겨주기
 *   = 의존성 주입). 이 단계의 목적은 호출 사슬을 익히는 것이라
 *   그 개선은 다음 단계로 남겨둔 것입니다.
 *
 *
 * ###################################################################
 *  3. 실행하면 어떻게 될까 (현재 코드 그대로)
 * ###################################################################
 *
 *   1번(가입)을 하면
 *     [MemberController] execute()
 *     [MemberService] doSignUp()
 *     [MemberDao] insertMember()
 *   로그가 찍히고 저장됩니다. 이어서 2번(로그인)을 하면
 *     [MemberService] doSignIn()
 *     [MemberDao] selectMemberByMemId()
 *   로그가 찍히지만, 새 빈 목록이라 "Sorry. SIGN-IN FAIL!!" 이
 *   나옵니다. 99번을 누르면 "Good bye~" 로 끝납니다.
 */
