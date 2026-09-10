/*
 * ===================================================================
 *  system/CoolingSystem.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. main 메서드가 없는 "설계도"
 *   파일이고, controller/CoolingController.java 와 main/MainClass.java
 *   가 가져다 씁니다.
 *
 *   이 파일이 가르치려는 것:
 *   "캡슐화(encapsulation)" — 필드를 private 로 감추고, 값을 읽거나
 *   바꾸려면 정해진 메서드(getter/setter)를 거치게 하는 것. 그렇게
 *   해서 (1) 잘못된 값을 setter 에서 막고, (2) 아예 못 바꾸는 필드도
 *   만듭니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 필드(field) / private
 *    필드 = 객체가 저장하는 값. private = "이 클래스 안에서만 접근
 *    가능, 바깥에서 `객체.필드` 로 직접 못 건드림". 이 파일은 필드
 *    4개(power, targetTemperature, dateOfMenuFacture, color)가 전부
 *    private.
 *
 *  ● 캡슐화(encapsulation)
 *    필드를 private 로 숨기고, 값 읽기/바꾸기를 반드시 클래스가
 *    제공하는 메서드로만 하게 하는 설계.
 *
 *  ● getter / setter
 *    getter = 필드 값을 읽어서 돌려주는 메서드. 이름 관습:
 *             get필드명() (boolean 은 is필드명()).
 *    setter = 필드 값을 바꾸는 메서드. 이름 관습: set필드명(새값).
 *    자바 문법이 강제하는 건 아니고 다들 지키는 이름 규칙입니다.
 *
 *  ● 읽기 전용 필드 (setter 없음)
 *    getter 만 있고 setter 가 없으면(또는 주석 처리하면), 그 필드는
 *    바깥에서 못 바꿉니다. 여기선 제조일과 색상이 그렇게 돼 있습니다
 *    — 한번 정해지면 바뀌면 안 되는 값이라서.
 *
 *  ● 생성자(constructor) / 매개변수 / this / 섀도잉
 *    생성자 = `new` 하는 순간 자동 실행되어 필드 초기값을 세팅하는
 *    코드 덩어리. 이름이 클래스 이름(CoolingSystem)과 같음.
 *    매개변수 이름이 필드 이름과 같으면 그 안에서 필드가 가려지므로
 *    (섀도잉), `this.color = color;` 처럼 this. 로 필드를 콕 집습니다.
 *
 *  ● ! (논리 부정 연산자)
 *    boolean 값을 뒤집습니다. `!power` = power 가 true 면 false,
 *    false 면 true. `if (!power)` = "전원이 꺼져 있으면".
 *
 *  ● 값 범위 제한 (clamping)
 *    setTargetTemperature 는 18 미만이면 18로, 30 초과면 30으로
 *    끌어당깁니다. 캡슐화 덕분에 이런 검사를 setter 한곳에서
 *    강제할 수 있습니다.
 *
 *  ● LocalDateTime / DateTimeFormatter
 *    자바 기본 제공 날짜·시간 도구.
 *    LocalDateTime.now() = 지금 날짜와 시간.
 *    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") = 출력
 *    형식 틀. now.format(틀) 로 문자열을 만듭니다.
 *
 *  ● 미사용 import
 *    맨 위 `import java.time.LocalDate;` 는 이 파일에서 안 씁니다
 *    (쓰는 건 LocalDateTime, DateTimeFormatter). 지우다 만 흔적,
 *    오류 아님.
 *
 *  ● 이름의 오타 (Infomation, MenuFacture)
 *    printSystemInfomation → Information,
 *    dateOfMenuFacture → Manufacture(제조) 가 맞는 철자입니다.
 *    파일 전체에서 같은 철자로 일관되게 썼기 때문에 프로그램은
 *    정상 동작합니다. 알고만 넘어가세요.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * private boolean power;              // 전원
 * private int targetTemperature;      // 희망 온도
 * private String dateOfMenuFacture;   // 제조일
 * private String color;               // 색상
 *
 *   [정상 — 캡슐화의 출발점]
 *   필드 4개 전부 private. 그래서 CoolingController 같은 다른
 *   클래스는 `coolingSystem.power` 처럼 직접 못 읽고 못 씁니다 —
 *   아래 getter/setter 를 거쳐야만 합니다.
 *
 *
 * public CoolingSystem(String color) {
 *     power = false;
 *     targetTemperature = 18;
 *     dateOfMenuFacture = getSystemDateTime();
 *     this.color = color;
 * }
 *
 *   [정상 — 생성자]
 *   new CoolingSystem("white") 로 만들 때 실행되어 초기 상태를
 *   잡습니다: 전원 꺼짐, 희망 온도 18, 제조일은 지금 시각(아래
 *   getSystemDateTime() 이 만들어 줌), 색상은 넘겨받은 값.
 *   color 매개변수는 필드 color 와 이름이 같아 섀도잉이 일어나므로
 *   `this.color` 로 필드를 콕 집었습니다. 나머지 셋은 같은 이름의
 *   매개변수가 없어 this. 없이 그냥 씁니다.
 *
 *
 * public void printSystemInfomation() {
 *     ... "Power: " + isPower() / "TargetTemperature: " + getTargetTemperature() /
 *         "DateofMenufacture: " + getDateOfMenuFacture() / "Color: " + getColor() ...
 * }
 *
 *   [정상]
 *   현재 상태를 한 번에 출력합니다. 눈여겨볼 점: 필드를 직접 쓰지
 *   않고 isPower(), getTargetTemperature() 같은 getter 로 읽습니다.
 *   같은 클래스 안이라 필드를 직접 써도 되지만, "값은 getter 로
 *   읽는다" 는 습관을 일관되게 지킨 것입니다.
 *
 *
 * public boolean isPower()              { return power; }
 * public void setPower(boolean power)   { this.power = power; }
 *
 *   [정상] power 필드의 getter/setter. boolean 필드의 getter 는
 *   관습상 get 이 아니라 is 로 시작(isPower). setter 는 매개변수
 *   power 가 필드 power 와 이름이 같아 `this.power = power;` 로
 *   필드를 콕 집습니다.
 *
 *
 * public int getTargetTemperature() { return targetTemperature; }
 * public void setTargetTemperature(int targetTemperature) {
 *     if (!power) power = true;
 *     if (targetTemperature < 18)      { targetTemperature = 18; }
 *     else if (targetTemperature > 30) { targetTemperature = 30; }
 *     this.targetTemperature = targetTemperature;
 * }
 *
 *   [정상 — 로직이 들어간 setter]
 *   이 setter 는 단순 대입이 아닙니다:
 *     1) if (!power) power = true; — 전원이 꺼져 있으면 먼저 켭니다
 *        (온도를 설정한다는 건 켜서 쓰겠다는 뜻이니까).
 *     2) 넘어온 값이 18 미만이면 18로, 30 초과면 30으로 끌어당깁니다
 *        (값 범위 제한). 여기서 바꾸는 targetTemperature 는 매개변수
 *        입니다 — 매개변수 값을 먼저 다듬고,
 *     3) 마지막에 `this.targetTemperature = targetTemperature;` 로
 *        다듬어진 값을 필드에 넣습니다.
 *   캡슐화(필드 private + setter 강제) 덕분에, 누가 어떤 경로로
 *   온도를 바꾸든 이 검사를 반드시 통과합니다.
 *
 *
 * (원본에서 아래 블록은 /* *(별) 로 통째 주석 처리돼 있음)
 *
 * // 조회 = get(getter)
 * public int getTargetTemperature() { return this.targetTemperature; }
 * // 설정 = set(setter)
 * public void setTargetTemperature(int targetTemperature) {
 *     this.targetTemperature = targetTemperature;
 * }
 *
 *   [의도된 장치 — "검사 로직 넣기 전" 버전]
 *   단순 대입만 하는 getter/setter 입니다. 위의 활성 setter 와
 *   비교하라고 남겨둔 것입니다 — 같은 이름이라 주석을 풀면 중복
 *   선언 에러가 나므로 주석 상태로 둡니다.
 *
 *
 * public String getDateOfMenuFacture() { return dateOfMenuFacture; }
 * // public void setDateOfMenuFacture(String ...) { ... }
 *
 * public String getColor() { return color; }
 * // public void setColor(String ...) { ... }
 *
 *   [의도된 장치 — 읽기 전용 필드]
 *   제조일과 색상은 getter 만 있고 setter 는 주석 처리돼 있습니다.
 *   → 바깥에서 읽을 수는 있지만 바꿀 수는 없는 필드가 됩니다.
 *   제조일·색상은 한번 정해지면 바뀌면 안 되는 값이라 일부러
 *   setter 를 막아둔 것입니다. 캡슐화가 "아예 못 바꾸게" 하는 데도
 *   쓰인다는 예시입니다.
 *
 *
 * private String getSystemDateTime() {
 *     LocalDateTime now = LocalDateTime.now();
 *     String dateTime = now.format(
 *         DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
 *     return dateTime;
 * }
 *
 *   [정상 — private 내부 도우미]
 *   지금 날짜·시간을 "yyyy-MM-dd HH:mm:ss" 모양의 문자열로 만들어
 *   돌려줍니다. private 이라 이 클래스 안(생성자)에서만 부르고,
 *   바깥에서는 못 부릅니다. 생성자가 이걸 불러 제조일 필드를
 *   채웁니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 것이 없습니다(설계도, main 없음).
 *   main/MainClass.java 가 new CoolingSystem("white") 로 객체를
 *   만들고, CoolingController 가 그 객체의 setPower /
 *   setTargetTemperature / printSystemInfomation 을 부르는 흐름으로
 *   이어집니다.
 */
