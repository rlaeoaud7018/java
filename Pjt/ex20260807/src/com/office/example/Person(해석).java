/*
 * ===================================================================
 *  Person.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. main()이 없는 설계도 파일이고,
 *   ClassEx001.java에서 가져다 써요. 이 파일은 컴파일·실행에는
 *   문제없지만, 원본에 적힌 "예상 출력값" 주석이 실제 결과와 달라요
 *   — 이유는 아래에서 순서대로 설명할게요. 파일 맨 위부터
 *   짚어보겠습니다.
 *
 *
 * String name;         // 멤버 변수 or 멤버 필드
 * int regNum;          // 멤버 변수 or 멤버 필드
 * int setAtrribute = setData();
 * int setAtrribute1 = setData1();
 *
 *   new Person()으로 빵을 구울 때, 이 4줄이 위에서 아래 순서
 *   그대로 딱 한 번씩 실행돼요 (생성자를 따로 안 만들어도 자바가
 *   기본 생성자를 하나 만들어주고, 그 안에서 이 초기화들이
 *   자동으로 돌아가요). 즉 setData()가 먼저, setData1()이 그
 *   다음에 실행된다는 게 핵심이에요 — 이 순서가 나중에 결과를
 *   뒤집어요.
 *
 *
 * int setData() {
 *     String name = "홍길동";
 *     this.name = name;
 *     regNum = 1000;
 *     return 0;
 * }
 *
 *   여기서 "String name = "홍길동";"는 메서드 안에서만 사는
 *   "임시 이름표"예요. 클래스 맨 위에 있는 진짜 소지품(필드) name
 *   과 이름이 우연히 똑같아서, 이 메서드 안에서는 임시 이름표가
 *   진짜 소지품을 가려버려요(이걸 "섀도잉"이라고 불러요). 그래서
 *   "this.name = name;"이라고 "this."를 앞에 붙여서 "임시 말고
 *   진짜 소지품 쪽에 넣어줘"라고 콕 집어 말해준 거예요. regNum은
 *   임시 이름표가 따로 없어서 "regNum = 1000;"만 써도 바로 진짜
 *   소지품에 들어가요. 이 시점에는 name="홍길동", regNum=1000
 *   이에요.
 *
 *
 * int setData1() {
 *     name = "박찬호";
 *     regNum = 10000000;
 *     return 1;
 * }
 *
 *   이번엔 임시 이름표가 없어서 "name = "박찬호";"만 써도 바로
 *   진짜 소지품에 들어가요. setAtrribute1의 초기화가 setAtrribute
 *   보다 나중에 실행되니까, 이 메서드가 바로 위 setData()가
 *   만들어둔 값을 덮어써버려요 — name은 "박찬호"로, regNum은
 *   10000000으로 바뀌어요.
 *
 *
 * 그래서 원본 주석이 놓친 부분:
 *
 *   ClassEx001.java의 아래 코드를 보면,
 *
 *     System.out.println(person1.name);       // 홍길동  ← 실제로는 박찬호
 *     System.out.println(person1.regNum);     // 1000    ← 실제로는 10000000
 *     System.out.println(person1.setAtrribute);   // 0   (맞음)
 *     System.out.println(person1.setAtrribute1);  // 1   (맞음)
 *
 *   "홍길동"과 "1000"은 setData()만 실행된 "중간 상태"의 값이에요.
 *   근데 그 직후 setData1()이 또 실행돼서 name과 regNum을
 *   덮어쓰기 때문에, 최종적으로 person1.name은 "박찬호",
 *   person1.regNum은 10000000이 돼요. setAtrribute(0)과
 *   setAtrribute1(1)은 각 메서드의 return 값을 그대로 담은
 *   것뿐이라 예상대로 맞아요.
 *
 *
 * void walk() {
 *     System.out.println("걷다.");
 *     int regNum;   // 변수
 * }
 *
 *   여기도 setData()처럼 regNum이라는 임시 이름표를 만들어뒀는데,
 *   이번엔 아무 값도 안 넣고 쓰지도 않아요 — 그냥 만들어만 둔
 *   상태예요. walk()는 이 파일 안 어디서도 실제로 불리지 않아요.
 *
 *
 * void walkForEng() { System.out.println("walk"); }
 * void run() { }
 *
 *   walkForEng()도 ClassEx001.java에서 주석 처리돼 있어서 실제로
 *   호출되진 않아요. run()은 안이 비어있는 채로 남아있어요.
 *
 *
 * 실행하면 어떻게 될까 (ClassEx001.java 기준)
 *
 *   person1.name → 박찬호, person1.regNum → 10000000,
 *   person1.setAtrribute → 0, person1.setAtrribute1 → 1
 *   순서로 출력돼요.
 */
