/*
 * ===================================================================
 *  MemberPjt / MemberDto.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 회원 정보를 담는 그릇(DTO)
 *   입니다. 프로젝트 개요는 MemberController(해석).java 의 "0. 개념
 *   정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   "필드 7개짜리 DTO 와, 상황별로 다르게 채우는 생성자 오버로딩
 *   4개." 앞의 ex20260810/812 에서 배운 생성자 오버로딩을 실제
 *   프로젝트 규모로 쓴 모습입니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 필드(field) / private
 *    객체가 저장하는 값. private 라 바깥에서 직접 못 건드리고
 *    getter/setter 로만 접근합니다(캡슐화, ex20260812 CoolingSystem
 *    참고).
 *
 *  ● DTO 의 필드 7개
 *    memNo(회원 번호, DB가 자동으로 매김), memId, memPw, memMail,
 *    memPhone, memRegDate(가입일), memModDate(수정일).
 *
 *  ● 생성자 오버로딩 (constructor overloading)
 *    이름 같은 생성자를 매개변수 개수·타입만 다르게 여러 개 두는 것.
 *    자바가 인자에 맞는 것을 고릅니다. 여기선 상황마다 아는 값이
 *    달라서 4가지 버전을 둡니다.
 *
 *  ● this / 섀도잉
 *    매개변수 이름이 필드 이름과 같아 필드가 가려지므로
 *    `this.memId = memId;` 로 필드를 콕 집습니다.
 *
 *  ● getter / setter
 *    필드 값을 읽는 메서드(get필드명)와 바꾸는 메서드(set필드명).
 *    이름 관습.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * private int memNo;
 * private String memId;
 * private String memPw;
 * private String memMail;
 * private String memPhone;
 * private String memRegDate;
 * private String memModDate;
 *
 *   회원 한 명의 정보 7칸. 전부 private.
 *
 *
 * public MemberDto(int memNo, String memId, String memPw, String memMail,
 *                  String memPhone, String memRegDate, String memModDate) { ... 7개 다 채움 ... }
 * public MemberDto(String memId, String memPw, String memMail, String memPhone) { ... 4개 채움 ... }
 * public MemberDto(String memId, String memPw) { ... 2개 채움 ... }
 * public MemberDto() { }   // 아무것도 안 채움
 *
 *   [정상 — 생성자 오버로딩 4종]
 *   상황마다 아는 값이 다르기 때문에 버전을 나눠 둡니다.
 *     - 7개짜리: DB 에서 회원을 통째로 읽어올 때(번호·가입일까지
 *       전부 있음).
 *     - 4개짜리: 회원가입 폼 입력값(아이디·비번·메일·전화)을 묶을 때.
 *     - 2개짜리: 로그인 시(아이디·비번만 있으면 됨).
 *     - 0개짜리: 일단 빈 객체를 만들고 나중에 setter 로 채울 때.
 *   안 채운 필드는 기본값(int 0, String null)으로 남습니다.
 *
 *
 * public int getMemNo() { return memNo; }
 * public void setMemNo(int memNo) { this.memNo = memNo; }
 * ... memId, memPw, memMail, memPhone, memRegDate, memModDate 도 같은 쌍 ...
 *
 *   [정상] 필드 7개 각각의 getter/setter. 반복되는 패턴이라 하나만
 *   보면 나머지도 같습니다. private 필드에 바깥에서 접근하는 유일한
 *   통로입니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 것이 없습니다(설계도, main 없음).
 *   MemberService 가 회원가입 값을 4개짜리 생성자로 묶는 데 씁니다.
 */
