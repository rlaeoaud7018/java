/*
 * ===================================================================
 *  MemberServicePjt(루트) / member/MemberDto.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 파일은 ex20260820
 *   MemberServicePjt 의 MemberDto.java 와 내용이 완전히 같습니다.
 *   자세한 해설은 ex20260820 의 MemberDto(해석).java 에 있습니다.
 *
 *   이 파일이 가르치려는 것:
 *   필드 7개짜리 DTO + 상황별 생성자 오버로딩 4개(0/2/4/7인자) +
 *   전체 getter/setter.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리
 * ###################################################################
 *
 *  ● DTO / private 필드 / getter·setter (캡슐화)
 *    값 묶음 그릇. 필드를 private 로 감추고 get/set 으로만 접근.
 *
 *  ● 필드 7개
 *    memNo(회원 번호, DB 자동 부여), memId, memPw, memMail,
 *    memPhone, memRegDate(가입일), memModDate(수정일).
 *
 *  ● 생성자 오버로딩
 *    이름 같은 생성자를 매개변수만 다르게 여러 개. 상황마다 아는
 *    값이 다르므로 나눔.
 *
 *  ● this / 섀도잉
 *    매개변수 이름 = 필드 이름 → `this.memId = memId;`.
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
 * public MemberDto() { }                                              // 0개
 * public MemberDto(String memId, String memPw) { ... }                // 2개 (로그인)
 * public MemberDto(String memId, String memPw, String memMail, String memPhone) { ... }  // 4개 (가입)
 * public MemberDto(int memNo, ... , String memModDate) { ... }        // 7개 (DB 조회 결과)
 *
 * // + getMemNo/setMemNo ~ getMemModDate/setMemModDate 전체 쌍
 *
 *   [정상 — 생성자 오버로딩 4종]
 *     - 0개 : 빈 객체 후 setter 로 채우기
 *     - 2개 : 로그인(id, pw)
 *     - 4개 : 회원가입(id, pw, mail, phone)
 *     - 7개 : DB 에서 회원 통째로 읽어올 때(MemberDao 의 RowMapper)
 *   안 채운 필드는 기본값(int 0, String null).
 *   getter/setter 는 private 필드에 바깥에서 접근하는 유일한 통로.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   실행되는 게 없습니다(설계도). Controller/Service/Dao 가 상황에
 *   맞는 생성자를 골라 이 그릇을 만들고 주고받습니다.
 */
