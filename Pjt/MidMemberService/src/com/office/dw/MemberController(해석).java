/*
 * ===================================================================
 *  MidMemberService / MemberController.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 메뉴를 보여주고 입력을 받아
 *   Service 에 넘기는 담당입니다. 프로젝트 개요·개념은
 *   MainClass(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   메뉴 4가지(가입/로그인/수정/삭제)를 상수 case 로 분기하고,
 *   각 case 에서 필요한 값만 입력받아 MemberDto 로 묶어 넘긴다.
 *   수정·삭제도 이번엔 실제로 호출한다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● final int 메뉴 상수 (필드)
 *    MEMBER_SIGN_UP = 1, ... , MEMBER_SYSTEM_OUT = 99. 클래스 안에
 *    직접 둔 상수라 `case MEMBER_SIGN_UP:` 처럼 씁니다. final 은
 *    "값 못 바꿈".
 *
 *  ● Scanner / nextInt() 다음 nextLine()
 *    숫자 읽은 뒤 남는 줄바꿈 버리기. (자세히는 ex20260810
 *    MemberController(해석).)
 *
 *  ● 반복 "밖" 에서 만든 MemberService
 *    `MemberService memberService = new MemberService();` 가 while
 *    앞에 한 번. 모든 메뉴가 같은 Service(→ 같은 DAO → 같은 HashMap
 *    명단)를 씁니다.
 *
 *  ● DTO 를 호출 인자로 바로 생성
 *    `memberService.doSignUp(new MemberDto(memId, memPw, memMail,
 *    memPhone))` — DTO 변수를 따로 안 두고 메서드 부르는 그 자리
 *    에서 new 합니다. 안 쓰는 자리는 null.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * final int MEMBER_SIGN_UP = 1;  ...  final int MEMBER_SYSTEM_OUT = 99;
 *
 *   [정상] 메뉴 번호 상수 5개.
 *
 *
 * public void execute() {
 *     Scanner scanner = new Scanner(System.in);
 *     boolean systemFlag = true;
 *     String memId = null, memPw = null, memMail = null, memPhone = null;
 *     MemberService memberService = new MemberService();   // ★ while 밖, 한 번만
 *
 *     while (systemFlag) {
 *         ... 메뉴 출력, nextInt() 로 번호 읽고 nextLine() 으로 줄바꿈 버림 ...
 *
 *   [정상] 입력값 담을 변수 4개, 그리고 Service 를 한 번만 만듭니다.
 *
 *
 *         case MEMBER_SIGN_UP:
 *             ... ID/PW/MAIL/PHONE 입력받기 ...
 * //           memberService = new MemberService();
 *             memberService.doSignUp(new MemberDto(memId, memPw, memMail, memPhone));
 *             break;
 *
 *   [정상] 가입. 4개 다 입력받아 DTO 로 묶어 doSignUp 에 넘깁니다.
 *   [의도된 장치] `// memberService = new MemberService();` 는 옛
 *   방식(메뉴마다 새로 만들어 명단이 초기화됨) — 주석 처리.
 *
 *
 *         case MEMBER_SIGN_IN:
 *             ... ID/PW 입력받기 ...
 *             memberService.doSignIn(new MemberDto(memId, memPw, null, null));
 *             break;
 *
 *   [정상] 로그인. ID/PW 만 받고 mail/phone 자리에 null.
 *
 *
 *         case MEMBER_MODIFY:
 *             /* 1. ID,PW,MAIL,PHONE 입력  2. ID,PW로 인증  3. 인증되면 MAIL,PHONE 업데이트 *(주석)
 *             ... ID/PW/MAIL/PHONE 입력받기 ...
 *             memberService.modify(new MemberDto(memId, memPw, memMail, memPhone));
 *             break;
 *
 *   [정상] 수정. 4개를 다 받는 이유는 주석에 적힌 흐름 그대로 —
 *   ID/PW 로 본인 확인을 한 뒤, MAIL/PHONE 을 새 값으로 바꾸기
 *   때문입니다. 실제 인증·업데이트는 MemberService.modify 가
 *   합니다(MemberService(해석) 참고).
 *
 *
 *         case MEMBER_DELETE:
 *             /* 1. ID,PW 입력  2. 인증  3. 삭제 *(주석)
 *             ... ID/PW 입력받기 ...
 *             memberService.delete(new MemberDto(memId, memPw, null, null));
 *             break;
 *
 *   [정상] 삭제. ID/PW 만 받습니다(인증만 하면 지울 수 있으니까).
 *
 *
 *         case MEMBER_SYSTEM_OUT:
 *             systemFlag = false;
 *             System.out.println("SYSTEM OUT!!");
 *             break;
 *     }
 *     scanner.close();
 *
 *   [정상] 99 로 반복 종료 후 Scanner 닫음.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   1 가입 → 2 로그인 → 3 수정(ID/PW 맞으면 MAIL/PHONE 갱신) →
 *   4 삭제(ID/PW 맞으면 명단에서 제거) → 99 종료. 각 단계의 로그
 *   출력은 MemberService(해석) / MemberDao(해석) 참고.
 */
