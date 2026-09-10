/*
 * ===================================================================
 *  MemberPjt / MemberService.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. MemberController(서블릿)가 넘긴
 *   요청에서 회원가입 값을 꺼내는 담당입니다. 프로젝트 개요는
 *   MemberController(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   서블릿에서 넘어온 request 에서 `getParameter("이름")` 으로 폼
 *   값을 꺼내 DTO 로 묶는 것.
 *
 *   컴파일 상태: 서블릿 라이브러리가 있으면 문법 오류 없이
 *   컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● HttpServletRequest.getParameter("이름")
 *    브라우저가 폼(또는 URL 쿼리)으로 보낸 값 하나를 이름으로 꺼내는
 *    메서드. 폼 입력칸의 name 속성과 이 "이름" 이 맞아야 합니다.
 *    값이 없으면 null 을 돌려줍니다. 항상 String 으로 나옵니다.
 *
 *  ● DTO (Data Transfer Object)
 *    관련된 값 여러 개를 한 객체로 묶어 옮기는 그릇. 여기선
 *    MemberDto 에 아이디·비번·메일·전화를 담습니다.
 *    (MemberDto(해석).java 참고.)
 *
 *  ● 반환값 int 로 성공/실패 표시
 *    memberRegistConfirm 은 int 를 돌려줍니다. 보통 이런 처리
 *    메서드는 "성공하면 1(또는 양수), 실패하면 0" 같은 약속으로
 *    결과를 알립니다. 지금은 그냥 0 을 돌려줍니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public int memberRegistConfirm(HttpServletRequest request, HttpServletResponse response) {
 *     System.out.println("[MemberService] memberRegistConfirm()");
 *     String memId = request.getParameter("memId");
 *     String memPw = request.getParameter("memPw");
 *     String memMail = request.getParameter("memMail");
 *     String memPhone = request.getParameter("memPhone");
 *     System.out.println("memId: " + memId);
 *     ... memPw / memMail / memPhone 도 출력 ...
 *
 *   [정상]
 *   Controller 가 넘긴 request 에서, 회원가입 폼의 입력값 4개를
 *   getParameter 로 꺼냅니다. 괄호 안의 "memId" 등은 폼 입력칸의
 *   name 과 같아야 값이 제대로 들어옵니다. 이어지는 println 은 잘
 *   들어왔는지 확인하는 로그입니다.
 *
 *
 *     MemberDto memberDto = new MemberDto(memId, memPw, memMail, memPhone);
 *     return 0;
 * }
 *
 *   [미완성]
 *   꺼낸 값 4개를 MemberDto 4개짜리 생성자로 묶어 memberDto 를
 *   만듭니다. 그런데 이 memberDto 를 그 뒤에 아무 데도 안 씁니다 —
 *   원래대로면 여기서 DAO 를 불러 DB 에 INSERT 하고, 그 결과(성공
 *   1 / 실패 0)를 return 해야 합니다. 지금은 DB 연동 전 단계라
 *   무조건 0 을 돌려줍니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   콘솔에 "[MemberService] memberRegistConfirm()" 와 memId/memPw/
 *   memMail/memPhone 값이 출력되고, 0 을 돌려주며 끝납니다. 실제
 *   저장은 아직 안 됩니다.
 */
