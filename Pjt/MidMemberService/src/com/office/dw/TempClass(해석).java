/*
 * ===================================================================
 *  MidMemberService / TempClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 파일은 회원 서비스와 무관한
 *   "개인 연습 파일" 입니다(이름부터 Temp). 자기만의 main 을 갖고,
 *   ArrayList / Vector / HashMap 을 손으로 눌러 보는 스크래치
 *   노트입니다.
 *
 *   이 파일이 가르치려는 것:
 *   세 가지 컨테이너의 사용법 비교 —
 *     ArrayList : 순서 있는 목록 (인덱스로 접근)
 *     Vector    : ArrayList 와 거의 같은, 더 오래된 클래스
 *     HashMap   : key-value 저장 (파이썬 딕셔너리)
 *
 *   컴파일 상태: 맨 아래 한 줄에 진짜 문법 오류가 있습니다(아래 [단순
 *   실수] 참고). 그 줄을 고치기 전까지 이 파일은 컴파일되지 않고,
 *   같은 프로젝트의 다른 파일들까지 빌드가 막힙니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● ArrayList<타입>
 *    순서 있는 목록. add(값) 추가, get(인덱스) 조회, remove(인덱스)
 *    삭제, size() 개수.
 *
 *  ● Vector<타입>
 *    ArrayList 와 쓰는 법이 거의 같은, 자바 초창기 클래스. 요즘은
 *    보통 ArrayList 를 씁니다(Vector 는 내부적으로 동기화 처리가
 *    있어 조금 느림). 여기서는 "같은 방식으로 쓸 수 있다" 는 걸
 *    보여주려고 나란히 눌러 본 것입니다.
 *
 *  ● HashMap<key타입, value타입>
 *    이름표(key)와 값(value)의 짝을 저장. put(key, val) / get(key) /
 *    remove(key) / size() / clear().
 *
 *  ● put() 의 반환값
 *    HashMap.put(key, val) 은 "그 key 에 원래 들어 있던 값" 을
 *    돌려줍니다. 처음 넣는 key 면 null, 이미 있던 key 면 옛 값.
 *
 *  ● remove(인덱스)를 배열 범위 밖으로 부르면
 *    ArrayList/Vector 에서 없는 인덱스를 remove 하면 실행 도중
 *    IndexOutOfBoundsException 이 납니다(컴파일 에러가 아니라
 *    런타임 오류).
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * ArrayList<Integer> nums = new ArrayList<Integer>();
 * nums.add(10); nums.add(20); nums.add(30);
 * nums.get(0); nums.get(1); nums.get(2);
 * nums.remove(0); nums.remove(1); nums.remove(2);
 * nums.size();
 *
 *   [정상 문법, 다만 실행하면 문제]
 *   add 로 [10, 20, 30] 을 만들고, get 3번은 값을 꺼내지만 변수에
 *   안 담아 결과는 버려집니다.
 *   remove 3번이 위험합니다:
 *     remove(0) → [20, 30]  (남은 개수 2)
 *     remove(1) → [20]       (30 제거, 남은 개수 1)
 *     remove(2) → 인덱스 2 는 없음 → IndexOutOfBoundsException (여기서 멈춤)
 *   즉 이 파일을 (문법 오류를 고친 뒤) 실행하면 이 지점에서
 *   예외로 중단됩니다. 연습하며 무심코 적은 줄로 보입니다.
 *
 *
 * Vector<Integer> nums2 = new Vector<Integer>();
 * nums2.add(10); ... nums2.get(...); ... nums2.remove(0); nums2.remove(1); nums2.remove(2);
 * nums2.size();
 *
 *   [정상 문법, 실행 시 위와 동일한 문제]
 *   Vector 도 ArrayList 와 똑같은 메서드 이름으로 씁니다("Vector 도
 *   이렇게 되는구나" 확인). remove(2) 에서 역시 범위 밖이라
 *   실행하면 예외가 납니다.
 *
 *
 * HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
 * hashMap.put(1, "박찬호"); hashMap.put(2, "박지성"); hashMap.put(3, "박세리");
 *
 * System.out.println(hashMap.put(1, "박찬호"));   // 원본 주석: 1. 박찬호
 * System.out.println(hashMap.put(2, "박지성"));   // 원본 주석: 2. 박지성
 * System.out.println(hashMap.put(3, "박세리"));   // 원본 주석: 3. 박세리
 *
 *   [정상 — put 의 반환값 확인]
 *   key 1, 2, 3 에 이름을 넣습니다. 그 다음 같은 key 로 같은 값을
 *   다시 put 하면서 그 반환값을 출력합니다. put 은 "원래 있던 값"
 *   을 돌려주므로, 화면에는 "박찬호", "박지성", "박세리" 만
 *   찍힙니다(원본 주석의 "1. " 같은 접두어는 안 나옴 — 주석이 조금
 *   부정확).
 *
 *
 * hashMap.remove(1);
 * System.out.println(hashMap.remove(1));   // null
 * System.out.println(hashMap.size());      // 2
 *
 *   [정상]
 *   key 1 을 지웁니다. 이미 지워진 뒤 다시 remove(1) → 돌려줄 값이
 *   없으므로 null 출력. 남은 key 는 2, 3 이라 size() 는 2.
 *
 *
 * hashMap.clear();
 * System.out.println(hashMap.size());      // 0
 *
 *   [정상] 전부 비우면 size() 는 0.
 *
 *
 * hashMap.put(1, "박찬호"); hashMap.put(2, "박지성"); hashMap.put(3, "박세리");
 * hashMap.put(1. "박길동");
 *   └─ [단순 실수 — 진짜 컴파일 에러] 인자 구분자가 쉼표(,)가 아니라
 *      마침표(.)입니다. `1.` 을 자바가 "소수점 붙은 숫자" 로 읽으려다
 *      "박길동" 과 이어지는 부분에서 문법이 깨집니다.
 *
 *   원작성자 의도:
 *   바로 윗줄 `hashMap.put(1, "박찬호")` 옆의 "// 박길동" 주석이
 *   힌트입니다. "이미 key 1 이 있는 상태에서 put(1, "박길동") 을
 *   하면 값이 박찬호 → 박길동 으로 바뀐다(수정된다)" 를 보여주려던
 *   것입니다. 그런데 쉼표를 마침표로 잘못 눌렀습니다.
 *
 *   수정:
 *
 *     hashMap.put(1, "박길동");
 *
 *   이러면 key 1 의 값이 "박찬호" 에서 "박길동" 으로 덮어써집니다
 *   (HashMap 은 같은 key 로 put 하면 값이 교체됨).
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   마지막 줄의 마침표를 쉼표로 고쳐야 컴파일이 됩니다. 그 뒤
 *   실행하면 앞쪽 ArrayList 의 remove(2) 지점에서
 *   IndexOutOfBoundsException 으로 멈춥니다(그 세 줄도 정리해야
 *   끝까지 돕니다). HashMap 부분만 따로 돌리면 위 주석에 적은 대로
 *   출력됩니다.
 */
