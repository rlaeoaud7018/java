/*
 * ===================================================================
 *  Car.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. main 메서드가 없는 "설계도"
 *   파일이고, Example003.java가 가져다 씁니다.
 *
 *   이 파일이 가르치려는 것:
 *     (1) 생성자 오버로딩 (Car() vs Car(int, int, int))
 *     (2) 메서드 오버로딩, 그리고 "만들어두지 않은 모양으로는 못
 *         부른다" (testDrive 안의 주석 처리된 goForward())
 *     (3) private 메서드 = 클래스 내부 전용 부품
 *     (4) return 을 만나면 메서드는 끝난다 → 그 뒤 코드는 죽은 코드
 *     (5) (float) 캐스팅으로 정수 계산을 소수 계산으로 바꾸기
 *
 *   컴파일 상태: 이 파일 자체는 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 클래스(class) / 객체(object) / new
 *    클래스 = 설계도(Car). 객체 = 그것으로 찍어낸 물건 하나.
 *    `new Car()` 로 객체를 만듭니다.
 *
 *  ● 필드(field)
 *    객체가 저장하는 값. 여기선 length, price, displacement.
 *    int 라서 안 채우면 기본값 0.
 *
 *  ● 생성자(constructor) / 디폴트 생성자
 *    `new` 하는 순간 자동 실행되어 객체 초기 세팅을 하는 코드 덩어리.
 *    이름이 클래스 이름과 같음. 매개변수 없는 것이 디폴트 생성자.
 *
 *  ● 매개변수(parameter) / 인자(argument)
 *    매개변수 = 괄호 안에서 값을 받는 변수.
 *    인자 = 부를 때 넣는 값. new Car(4000, 3000, 1600) 의 4000,
 *    3000, 1600.
 *
 *  ● 생성자 오버로딩 / 메서드 오버로딩
 *    이름이 같은 생성자(또는 메서드)를 매개변수의 개수·타입만
 *    다르게 여러 개 두는 것. 자바는 부를 때 넘긴 인자의 개수·타입을
 *    보고 맞는 것을 고릅니다. "만들어두지 않은 모양" 으로 부르면
 *    (예: goForward() — 인자 없이) 컴파일 에러입니다.
 *
 *  ● this / 섀도잉(shadowing)
 *    매개변수 이름이 필드 이름과 같으면 필드가 가려짐(섀도잉).
 *    `this.length` 로 필드를 콕 집습니다.
 *
 *  ● 반환타입 / void / return
 *    반환타입 = 메서드가 돌려주는 값의 타입. 여기선 대부분 void
 *    (돌려줄 값 없음, 출력만), getDistance 만 float.
 *    return 문을 만나면 메서드는 그 자리에서 즉시 끝납니다.
 *
 *  ● 죽은 코드 / 실행 불가 코드(unreachable code)
 *    return 다음 줄처럼 절대 실행될 수 없는 위치의 코드. 자바가
 *    컴파일 에러로 막으므로, 그런 줄은 주석 처리해 둡니다.
 *
 *  ● public / private (접근제어자)
 *    public = 어느 클래스에서든 부를 수 있음.
 *    private = 이 클래스 안에서만 부를 수 있음(바깥에서 직접 못 씀).
 *
 *  ● 캐스팅 / 형변환 (float)
 *    값의 타입을 강제로 바꾸는 것. `(float)speed` 는 int speed 를
 *    실수로 바꿔서, 나눗셈·곱셈이 소수 계산이 되게 합니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * int length;          // 길이
 * int price;           // 가격
 * int displacement;    // 배기량
 *
 *   Car 객체마다 갖는 필드 3개. 안 채우면 전부 0 입니다.
 *
 *
 * public Car() {
 *     System.out.println("--- Default Constructor ---");
 * }
 * public Car(int length, int price, int displacement) {
 *     this.length = length;
 *     this.price = price;
 *     this.displacement = displacement;
 * }
 *
 *   [정상 — 생성자 오버로딩]
 *   이름은 둘 다 Car 인데 매개변수 목록이 다릅니다(0개 / 3개).
 *   자바가 골라 씁니다:
 *     new Car()                  → 위쪽. "--- Default Constructor ---"
 *                                  출력, 필드는 0 그대로.
 *     new Car(4000, 3000, 1600)  → 아래쪽. 세 필드를 그 값으로 채움.
 *   아래쪽은 매개변수 이름이 필드와 같아 섀도잉이 일어나므로
 *   `this.length = length;` 로 필드를 콕 집습니다.
 *
 *
 * public void goForward(int speed) {
 *     System.out.println(speed + "km/h goForward() CALLED!!");
 * }
 * public void goBack()  { System.out.println("goBack() CALLED!!"); }
 * public void doStop()  { System.out.println("doStop() CALLED!!"); }
 *
 *   일반 메서드 3개. 전부 void(출력만). goForward 는 매개변수
 *   speed 를 "반드시" 받아야 합니다 — goForward(60) 처럼 불러야
 *   하고, goForward() 처럼 빈 괄호로는 못 부릅니다(그 모양은
 *   안 만들어졌으니까).
 *
 *
 * public void testDrive() {
 * //     goForward();
 *     goBack();
 *     doStop();
 * }
 *
 *   [의도된 장치]
 *   goForward() 가 주석 처리된 이유가 바로 위 설명입니다. speed
 *   인자를 안 넘겼으니 이대로 두면 컴파일 에러 → 주석으로 막아
 *   "인자 없는 버전은 없다" 를 보여줍니다. goForward(60); 처럼
 *   인자를 넣으면 살릴 수 있습니다.
 *
 *
 * public void printCarSpec(String language) {
 *     if (language.equals("kor")) printCarSpecForKor();
 *     else                        printCarSpecForEng();
 * }
 * private void printCarSpecForKor() { ... "길이:"/"가격:"/"배기량:" ... }
 * private void printCarSpecForEng() { ... "length:"/"price:"/"displacement:" ... }
 *
 *   바깥에서 부르는 창구는 public 인 printCarSpec(String language)
 *   하나뿐입니다. 그 안에서 language 값을 .equals() 로 확인해 한글판/
 *   영문판 중 하나를 부릅니다(문자열 내용 비교는 == 가 아니라
 *   .equals()).
 *   한글판·영문판에 private 가 붙은 이유: "이 클래스 안에서만 쓰는
 *   내부 부품" 이라는 표시입니다. 그래서 Example003 같은 다른
 *   파일에서는 이 둘을 직접 못 부르고 printCarSpec() 만 부릅니다.
 *
 *
 * public float getDistance(int hour, int speed) {
 * //     System.out.println("이동 거리: " + (hour * speed) + "km");
 *     float distance = hour * (float)speed;
 *     return distance;      // 여기서 끝
 * //     System.out.println("메서드 실행 끝~~~~");
 * }
 *
 *   거리 = 시간 x 속도 를 계산해 돌려줍니다.
 *   - 맨 위 주석(println) : "결과를 그 자리서 출력" 하던 이전 방식.
 *     지금은 값을 return 으로 돌려주는 방식으로 바꿨습니다.
 *   - `(float)speed` : speed 는 int 인데, 정수끼리만 계산하면
 *     소수점이 잘릴 수 있어 실수 계산으로 바꾸려고 캐스팅했습니다.
 *     결과 타입이 실수라 메서드 반환타입도 float.
 *   - [의도된 장치] 마지막 줄(println)이 주석인 이유: return 을
 *     만나면 메서드는 그 자리서 끝납니다. 그 아래 진짜 코드를
 *     적으면 절대 실행될 수 없는 죽은 코드가 되어 자바가 컴파일
 *     에러를 냅니다. 그래서 //로 막아 "return 뒤는 실행 안 됨" 을
 *     보여줍니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 것이 없습니다(설계도, main 없음).
 *   Example003 에서 new Car() → "--- Default Constructor ---" 가
 *   찍히는 것부터 이어집니다. 자세한 흐름은 Example003(해석).java.
 */
