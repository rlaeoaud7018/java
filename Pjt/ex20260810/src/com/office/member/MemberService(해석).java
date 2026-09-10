/*
 * ===================================================================
 *  member/MemberService.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. "실제 처리 로직 담당" 파일
 *   입니다. 폴더 전체 구조와 공통 용어는 MainClass(해석).java 의
 *   "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   Service 의 역할 — Controller 가 넘긴 DTO 를 받아, 판단할 것은
 *   판단하고(비번이 맞나 등), 저장·조회는 DAO 에 시킨다. 그리고
 *   문자열 비교는 == 가 아니라 .equals() 로 한다는 것.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● Service (역할)
 *    "무엇을 한다" 는 실제 로직이 들어가는 자리. 가입시키기, 로그인
 *    판정 같은 일. 데이터에 직접 손대지 않고 DAO 에게 시킵니다.
 *
 *  ● 매개변수(parameter)
 *    메서드 괄호 안에서 값을 받는 변수. 여기선 memberDto — Controller
 *    가 만들어 넘긴 값 봉투를 받습니다.
 *
 *  ● 지역변수(local variable)
 *    메서드 안에서만 사는 변수. memberDao, selectedMemberDto.
 *
 *  ● 객체.필드 접근 (점 문법)
 *    `memberDto.memId` 는 그 봉투 객체 안의 memId 필드 값을 꺼내는
 *    것. MemberDto 의 필드에 private 가 없고 같은 패키지라 접근됩니다.
 *
 *  ● null 체크
 *    `if (selectedMemberDto != null)` 은 "찾은 결과가 있는가" 확인.
 *    DAO 가 회원을 못 찾으면 null 을 돌려주므로, null 이 아니면
 *    "그 아이디의 회원이 존재한다" 는 뜻입니다.
 *
 *  ● 문자열 비교: .equals() vs ==
 *    문자열 "내용이 같은가" 를 볼 때는 반드시 `.equals()`.
 *    `==` 는 "같은 메모리 객체인가" 를 보기 때문에, 내용이 같아도
 *    다른 객체면 false 가 나올 수 있습니다. 그래서 비번·아이디
 *    비교에 == 를 쓰면 안 됩니다.
 *
 *  ● MemberDao (다른 파일)
 *    데이터에 직접 손대는 담당. insertMember(저장),
 *    selectMemberByMemId(아이디로 조회). MemberDao(해석) 참고.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public void doSignUp(MemberDto memberDto) {
 *     System.out.println("[MemberService] doSignUp()");
 *     MemberDao memberDao = new MemberDao();
 *     memberDao.insertMember(memberDto);
 * }
 *
 *   [정상]
 *   회원가입 처리. 첫 줄은 흐름 추적용 로그. 하는 일은 MemberDao
 *   객체를 하나 만들고, 받은 봉투(memberDto)를 그대로 insertMember()
 *   에 넘겨 "명단에 올려라" 라고 시키는 것뿐입니다. 가입은 따로
 *   검사할 게 없어 짧습니다. 반환타입 void.
 *
 *
 * public void doSignIn(MemberDto memberDto) {   // 봉투엔 memId, memPw 만 채워져 있음
 *     System.out.println("[MemberService] doSignIn()");
 *     MemberDao memberDao = new MemberDao();
 *     MemberDto selectedMemberDto = memberDao.selectMemberByMemId(memberDto.memId);
 *
 *   [정상]
 *   로그인 처리. MemberDao 를 만들고, "이 아이디로 된 회원 봉투를
 *   찾아줘" 라며 selectMemberByMemId() 에 아이디만(memberDto.memId)
 *   넘깁니다. DAO 는 찾으면 그 회원의 MemberDto 를, 못 찾으면 null
 *   을 돌려줍니다. 그 결과를 selectedMemberDto 에 받습니다.
 *
 *
 *     if (selectedMemberDto != null) {
 *         if (memberDto.memPw.equals(selectedMemberDto.memPw)) {
 *             System.out.println("SIGN-IN SUCCESS!!");
 *         } else {
 *             System.out.println("Sorry. SIGN-IN FAIL!!");
 *         }
 *     } else {
 *         System.out.println("Sorry. SIGN-IN FAIL!!");
 *     }
 * }
 *
 *   두 단계로 확인합니다.
 *     1) selectedMemberDto 가 null 이 아니다 = 그 아이디를 가진
 *        회원이 명단에 있다.
 *     2) 그 회원의 저장된 비번(selectedMemberDto.memPw)과 사용자가
 *        방금 친 비번(memberDto.memPw)이 같은가 → .equals() 로 비교.
 *   아이디 자체가 없으면(바깥 else) 비번은 볼 것도 없이 실패입니다.
 *
 *
 * ###################################################################
 *  2. 지금 구조의 한계 (MemberController(해석)에서 이어짐)
 * ###################################################################
 *
 *   doSignUp 도 doSignIn 도 첫 줄에서 `new MemberDao()` 로 DAO 를
 *   새로 만듭니다. MemberDao 의 회원 명단은 그 객체가 생길 때마다
 *   빈 상태로 시작합니다. 그래서 doSignUp 에서 올린 회원은,
 *   doSignIn 때 만든 다른 DAO 의 명단에는 없습니다. 결국 로그인은
 *   항상 "존재하지 않은 ID" 로 빠집니다.
 *   해법은 "DAO(또는 그 명단)를 한 번만 만들어 계속 돌려쓰기" 인데,
 *   그건 다음 단계 주제입니다(ex20260812 CoolingController 의
 *   의존성 주입). 지금은 호출이 Service → Dao 로 넘어가는 모양만
 *   눈에 익히면 됩니다.
 */
