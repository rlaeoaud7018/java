/*
 * ===================================================================
 *  SimpleMemberService / MemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 회원 명단(ArrayList)을 들고
 *   저장·조회하는 담당입니다. 프로젝트 개요는 MainClass(해석).java
 *   의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   ArrayList 하나를 임시 DB 로 쓰고, 추가(add)와 순차 조회(for +
 *   equals)를 하는 것. 이 DAO 가 딱 하나로 유지되기 때문에 명단도
 *   유지됩니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● ArrayList<MemberDto> memberDB (인스턴스 필드)
 *    회원 봉투(MemberDto)를 담는 목록. 이름이 memberDB 인 건 "DB
 *    역할" 이라는 뜻. 이 줄이 필드라서, MemberDao 객체가 만들어질
 *    때 한 번 생기고 그 객체가 사는 동안 유지됩니다.
 *
 *  ● add / size / get
 *    add(값) = 맨 뒤 추가, size() = 개수, get(인덱스) = 그 자리 값.
 *
 *  ● for 순차 조회 + .equals()
 *    목록을 처음부터 끝까지 돌며, 각 봉투의 아이디가 찾는 아이디와
 *    "내용" 이 같은지 .equals() 로 확인합니다.
 *
 *  ● return (즉시 종료) / null 반환
 *    찾으면 그 봉투를 바로 return(반복도 메서드도 끝). 끝까지 못
 *    찾으면 null 을 돌려줍니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * ArrayList<MemberDto> memberDB = new ArrayList<MemberDto>();
 *
 *   [정상]
 *   회원 명단. 필드라서 이 MemberDao 객체와 수명을 같이합니다.
 *   MemberService 가 이 DAO 를 하나만 만들어 재사용하므로, 이
 *   명단도 프로그램 내내 유지됩니다.
 *
 *
 * public void insertMember(MemberDto memberDto) {
 *     System.out.println("[MemberDao] insertMember()");
 *     memberDB.add(memberDto);
 * }
 *
 *   [정상] 받은 봉투를 명단 맨 뒤에 추가합니다. 이게 "회원가입
 *   저장" 의 실제 동작입니다.
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
 *   [정상] 명단을 처음부터 끝까지 돌며 아이디가 일치하는 봉투를
 *   찾습니다. 찾으면 즉시 return, 끝까지 없으면 null. MemberService
 *   의 doSignIn 이 이 null 여부로 "존재하는 아이디인가" 를
 *   판단합니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   가입할 때마다 memberDB 에 회원이 하나씩 쌓입니다. 로그인 시
 *   그 memberDB 를 훑어 아이디를 찾습니다. DAO 가 하나로 유지되니
 *   가입한 회원이 로그인 때도 그대로 남아 있습니다.
 */
