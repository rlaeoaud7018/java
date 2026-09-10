/*
 * ===================================================================
 *  RestaurantPjt / com.office.ex / MainClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 이 그룹(com.office.ex 패키지)의
 *   시작점(main)입니다. "아빠 중식당을 아들이 물려받는" 이야기로
 *   상속과 오버라이드(재정의), super 를 배웁니다. 원본에 달린
 *   한국어 주석이 곧 시나리오입니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 그룹에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 상속(inheritance) / extends
 *    자식 클래스가 부모 클래스의 필드·메서드를 물려받는 것.
 *    `class ChildRestaurant extends PapaRestaurant`. 자식은 자기
 *    코드를 안 써도 부모 메서드를 자기 것처럼 씁니다. (ex20260813
 *    에서 배운 내용.)
 *
 *  ● 오버라이드 / 재정의(override)
 *    부모에게 물려받은 메서드를, 자식이 "같은 이름 + 같은 매개변수"
 *    로 다시 정의해서 동작을 바꾸는 것. 자식 객체로 그 메서드를
 *    부르면 부모 버전이 아니라 자식이 새로 쓴 버전이 실행됩니다.
 *    자식 메서드 위에 @Override 를 붙여 "이건 부모 것을 다시 쓰는
 *    것" 이라고 표시합니다(철자·매개변수를 틀리면 컴파일 에러로
 *    잡아줌).
 *
 *  ● 오버로딩 vs 오버라이딩 (헷갈리지 말 것)
 *    오버로딩(overloading): "같은 클래스" 안에 이름 같고 매개변수
 *      목록이 다른 메서드 여러 개. (Greet 예제)
 *    오버라이딩(overriding): "부모-자식" 사이에서, 이름도 매개변수도
 *      같은 메서드를 자식이 다시 씀.
 *
 *  ● super
 *    자식 코드 안에서 "부모 버전" 을 가리키는 말. `super.makeBokkeumbap()`
 *    는 "부모(PapaRestaurant)의 makeBokkeumbap() 를 실행" 입니다.
 *    자식이 재정의했더라도 부모 것을 콕 집어 쓰고 싶을 때 씁니다.
 *
 *  ● static final 상수
 *    `static final int PAPA = 1;` — 클래스에 하나 있고 값이 안
 *    바뀌는 값. `ChildRestaurant.PAPA` 처럼 클래스 이름으로 씁니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로, 원본 주석 시나리오와 함께)
 * ###################################################################
 *
 *
 * // 아빠 중식당, 메뉴 짜장면 짬뽕
 * PapaRestaurant papaRestaurant = new PapaRestaurant();
 * papaRestaurant.makeJajangmyeon();   // [PapaRestaurant] makeJajangmyeon
 * papaRestaurant.makeJjamppong();     // [PapaRestaurant] makeJjamppong
 *
 *   [정상]
 *   아빠 식당 객체를 만들고 짜장면·짬뽕을 만듭니다. 각각 자기
 *   메서드가 문장을 출력합니다.
 *
 *
 * // 아빠 은퇴, 아들이 중식당을 물려받아 새로 오픈
 * ChildRestaurant childRestaurant = new ChildRestaurant();
 * childRestaurant.makeJajangmyeon();  // [PapaRestaurant] makeJajangmyeon
 * childRestaurant.makeJjamppong();    // [PapaRestaurant] makeJjamppong
 *
 *   [정상 — 상속]
 *   아들 식당은 짜장면·짬뽕 메서드를 직접 안 만들었지만, 아빠
 *   식당을 extends 했으므로 물려받아 그대로 씁니다. 그래서 출력도
 *   "[PapaRestaurant] ..." 그대로입니다 — 아직 아들이 바꾼 게
 *   없으니까요.
 *
 *
 * papaRestaurant.makeBokkeumbap();    // [PapaRestaurant] makeBokkeumbap
 * childRestaurant.makeBokkeumbap();   // [PapaRestaurant] makeBokkeumbap
 *
 *   [정상 — 아직은 상속만]
 *   볶음밥도 마찬가지. 아들이 인자 없는 makeBokkeumbap() 을
 *   재정의하지 않았으므로(ChildRestaurant 에서 그 부분은 주석
 *   처리됨), 아들 식당의 볶음밥도 아빠 것이 그대로 나옵니다.
 *
 *
 * // 아들이 아빠 볶음밥이 맛없다고 느낌 → 오버라이드(재정의)
 * // 단골손님: "아빠꺼 볶음밥 먹고 싶다"
 * childRestaurant.makeBokkeumbap(ChildRestaurant.PAPA);   // [PapaRestaurant] makeBokkeumbap
 * childRestaurant.makeBokkeumbap(ChildRestaurant.ME);     // [ChildRestaurant] makeBokkeumbap
 *
 *   [정상 — 오버라이드 + super]
 *   여기서 부르는 건 "매개변수 있는" makeBokkeumbap(int family)
 *   입니다. 이건 아들이 @Override 로 재정의해 뒀습니다
 *   (ChildRestaurant(해석) 참고).
 *     - 인자가 PAPA(1) → 아들 코드가 super.makeBokkeumbap() 을
 *       불러서 "아빠 볶음밥" 을 냅니다 → "[PapaRestaurant] makeBokkeumbap"
 *     - 인자가 ME(2)   → 아들이 직접 "[ChildRestaurant] makeBokkeumbap"
 *       을 출력합니다("내 볶음밥").
 *   손님 주문(PAPA/ME)에 따라 아빠 것과 내 것을 골라 내는 셈입니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   [PapaRestaurant] makeJajangmyeon
 *   [PapaRestaurant] makeJjamppong
 *   [PapaRestaurant] makeJajangmyeon    (아들 식당, 물려받음)
 *   [PapaRestaurant] makeJjamppong      (아들 식당, 물려받음)
 *   [PapaRestaurant] makeBokkeumbap
 *   [PapaRestaurant] makeBokkeumbap     (아들 식당, 물려받음)
 *   [PapaRestaurant] makeBokkeumbap     (makeBokkeumbap(PAPA) → super)
 *   [ChildRestaurant] makeBokkeumbap    (makeBokkeumbap(ME))
 */
