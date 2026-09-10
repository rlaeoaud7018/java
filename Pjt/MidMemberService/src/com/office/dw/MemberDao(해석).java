/*
 * ===================================================================
 *  MidMemberService / MemberDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 회원 명단을 들고 저장·조회·수정·
 *   삭제하는 담당입니다. 프로젝트 개요·HashMap 개념은
 *   MainClass(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것 (이 프로젝트의 핵심):
 *   회원 명단을 ArrayList 에서 HashMap 으로 바꾸면, 회원 ID 를 key
 *   로 쓸 수 있어서 저장·조회·수정·삭제가 전부 "for 문 없이 한 줄"
 *   이 된다. 네 메서드에 주석 처리된 옛 ArrayString 버전과 나란히
 *   비교하세요.
 *
 *   컴파일 상태: 이 파일은 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● HashMap<String, MemberDto> memberDB
 *    key = 회원 ID(String), value = 그 회원의 MemberDto. "ID 로
 *    회원을 바로 찾는" 구조입니다.
 *
 *  ● put / get / remove (전부 key 기준)
 *    put(id, dto) : id 이름표에 dto 를 매답니다. 같은 id 로 또
 *                   put 하면 값이 덮어써집니다 → 수정에도 그대로 씀.
 *    get(id)      : id 의 dto 를 꺼냅니다. 없으면 null.
 *    remove(id)   : id 짝을 지웁니다.
 *
 *  ● ArrayList 버전과의 대비 (주석 처리된 코드)
 *    각 메서드 위에 옛 ArrayList + for 문 버전이 주석으로 남아
 *    있습니다. "ID 로 훑어 찾기" 를 for 로 직접 하던 것을, HashMap
 *    은 key 접근 한 줄로 대체합니다.
 *
 *  ● "Ctrl + Shift + O" 주석
 *    맨 위 주석은 Eclipse 에서 "쓰는 import 만 자동 정리" 하는
 *    단축키 메모입니다. 코드와 무관합니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (네 메서드, 옛 버전 ↔ HashMap 버전 대비)
 * ###################################################################
 *
 *
 * // ArrayList<MemberDto> memberDB = new ArrayList<MemberDto>();
 * HashMap<String, MemberDto> memberDB = new HashMap<String, MemberDto>();
 *
 *   [정상] 명단을 HashMap 으로. 주석 줄이 옛 ArrayList 버전.
 *
 *
 * public void insertNewMember(MemberDto memberDto) {
 *     System.out.println("[MemberDao] insertNewMember()");
 * //     memberDB.add(memberDto);
 *     memberDB.put(memberDto.memId, memberDto);
 * }
 *
 *   [정상] 회원 추가. ArrayList 라면 add(dto) 였지만, HashMap 은
 *   "이 회원의 ID 를 이름표로" 저장합니다: put(id, dto).
 *
 *
 * public MemberDto selectMemberDtoByMemId(String memId) {
 *     System.out.println("[MemberDao] selectMemberDtoByMemId()");
 * //     for (int i = 0; i < memberDB.size(); i++) {
 * //         MemberDto dto = memberDB.get(i);
 * //         if (dto.memId.equals(memId)) return dto;
 * //     }
 * //     return null;
 *     MemberDto memberDto = memberDB.get(memId);
 *     return memberDto;
 * }
 *
 *   [정상 — 대비가 가장 뚜렷한 곳]
 *   [의도된 장치] 주석 처리된 for 문 버전: 명단을 처음부터 훑으며
 *   각 dto 의 ID 를 비교해 찾습니다(ArrayList 방식).
 *   HashMap 버전: `memberDB.get(memId)` 한 줄. key 가 곧 ID 라
 *   훑을 필요가 없습니다. 없으면 get 이 알아서 null 을 줍니다.
 *
 *
 * public void updateMemberForMailAndPhone(MemberDto memberDto) {
 *     System.out.println("[MemberDao] updateMemberForMailAndPhone()");
 * //     for (...) { if (dto.memId.equals(memberDto.memId)) { dto.memMail = ...; dto.memPhone = ...; break; } }
 *     memberDB.put(memberDto.memId, memberDto);
 * }
 *
 *   [정상] 수정. [의도된 장치] 주석 버전은 for 로 회원을 찾아
 *   memMail/memPhone 필드만 바꿉니다. HashMap 버전은 "같은 ID(key)
 *   로 새 dto 를 put" — 같은 key 로 put 하면 값이 통째로 덮어써
 *   지므로 이것으로 수정이 됩니다.
 *
 *
 * public void deleteMemberByMemId(String memId) {
 *     System.out.println("[MemberDao] deleteMemberByMemId()");
 * //     for (...) { if (dto.memId.equals(memId)) memberDB.remove(i); }
 *     memberDB.remove(memId);
 * }
 *
 *   [정상] 삭제. [의도된 장치] 주석 버전은 for 로 찾아 인덱스로
 *   remove. HashMap 버전은 `remove(memId)` 한 줄 — key 로 바로
 *   지웁니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   네 메서드 모두 로그 한 줄을 찍고, HashMap 을 key(회원 ID)
 *   기준으로 put/get/remove 합니다. DAO 가 하나로 유지되므로
 *   (MemberService(해석) 참고) 이 HashMap 명단도 프로그램 내내
 *   유지됩니다.
 */
