/*
 * ===================================================================
 *  MemberServicePjt / member/MemberDto.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 회원 한 명의 정보를 담는 그릇
 *   (DTO)입니다. 프로젝트 개요는 IConfig(해석).java 참고.
 *   (MemberPjt 의 MemberDto 와 거의 같습니다.)
 *
 *   이 파일이 가르치려는 것:
 *   필드 7개짜리 DTO + 상황별 생성자 오버로딩 4개 + 전체 getter/setter.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● DTO / 필드 / private / getter·setter
 *    관련 값을 묶어 옮기는 그릇. 필드는 private 로 감추고
 *    getter/setter 로만 접근(캡슐화). (ex20260812 CoolingSystem
 *    참고.)
 *
 *  ● 필드 7개
 *    memNo(회원 번호, DB 가 자동 부여), memId, memPw, memMail,
 *    memPhone, memRegDate(가입일), memModDate(수정일).
 *
 *  ● 생성자 오버로딩
 *    이름 같은 생성자를 매개변수만 다르게 여러 개. 상황마다 아는
 *    값이 달라서 버전을 나눕니다.
 *
 *  ● this / 섀도잉
 *    매개변수 이름 = 필드 이름이라 필드가 가려지므로 `this.memId =
 *    memId;` 로 필드를 콕 집습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * private int memNo;
 * private String memId, memPw, memMail, memPhone, memRegDate, memModDate;
 *
 *   회원 한 명의 정보 7칸. 전부 private.
 *
 *
 * public MemberDto() { }                                                  // 0개
 * public MemberDto(String memId, String memPw, String memMail, String memPhone) { ... }  // 4개
 * public MemberDto(String memId, String memPw) { ... }                    // 2개
 * public MemberDto(int memNo, String memId, String memPw, String memMail,
 *                  String memPhone, String memRegDate, String memModDate) { ... }  // 7개
 *
 *   [정상 — 생성자 오버로딩 4종]
 *     - 0개 : 빈 객체를 만들고 나중에 setter 로 채울 때
 *             (doModify 쪽에서 setMemId 로 채우는 식).
 *     - 4개 : 회원가입 입력값(id, pw, mail, phone)을 묶을 때.
 *     - 2개 : 로그인 시(id, pw 만).
 *     - 7개 : DB 에서 회원을 통째로 읽어올 때(memNo·가입일·수정일
 *             까지 다 있음) — MemberDao 의 selectMemberByMemId 안
 *             RowMapper 에서 이 생성자를 씁니다.
 *   안 채운 필드는 기본값(int 0, String null).
 *
 *
 * public int getMemNo() { return memNo; }
 * public void setMemNo(int memNo) { this.memNo = memNo; }
 * ... memId ~ memModDate 도 같은 getter/setter 쌍 ...
 *
 *   [정상] 필드 7개 각각의 getter/setter. private 필드에 바깥에서
 *   접근하는 유일한 통로입니다. 패턴이 반복되므로 하나만 보면
 *   나머지도 같습니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 게 없습니다(설계도, main 없음).
 *   Controller/Service/Dao 가 상황에 맞는 생성자를 골라 이 그릇을
 *   만들고 주고받습니다.
 */
