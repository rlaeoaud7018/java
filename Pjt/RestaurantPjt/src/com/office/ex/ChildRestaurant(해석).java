/*
 * ===================================================================
 *  RestaurantPjt / com.office.ex / ChildRestaurant.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 상속에서 "물려받는 쪽(자식
 *   클래스)" 이고, 이 그룹의 핵심 파일입니다. 그룹 개요·상속/
 *   오버라이드/super 개념은 MainClass(해석).java 의 "0. 개념 정리"
 *   를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   (1) extends 로 아빠 식당의 메뉴를 물려받는다,
 *   (2) 볶음밥은 @Override 로 재정의해서 "손님 주문에 따라" 아빠
 *       것과 내 것을 골라 낸다,
 *   (3) 아빠 것을 낼 때는 super 로 부모 메서드를 부른다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● extends PapaRestaurant
 *    ChildRestaurant 는 PapaRestaurant 를 상속합니다. 그래서
 *    makeJajangmyeon(), makeJjamppong(), makeBokkeumbap() 을 직접
 *    안 써도 물려받아 씁니다.
 *
 *  ● @Override / 재정의
 *    부모에게 물려받은 메서드를, 이름·매개변수를 똑같이 맞춰 자식이
 *    다시 정의하는 것. 자식 객체로 부르면 이 자식 버전이 실행됩니다.
 *    @Override 는 "이건 부모 것을 다시 쓰는 것" 이라는 표시이자
 *    안전장치(시그니처가 안 맞으면 컴파일 에러).
 *
 *  ● super.메서드()
 *    자식 안에서 "부모 버전" 을 콕 집어 부르는 것.
 *    `super.makeBokkeumbap()` = 부모 PapaRestaurant 의 인자 없는
 *    makeBokkeumbap() 실행.
 *
 *  ● static final 상수 (PAPA, ME)
 *    "누구 볶음밥이냐" 를 숫자 대신 이름으로 쓰기 위한 상수.
 *    바깥에서는 `ChildRestaurant.PAPA` 로 씁니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public class ChildRestaurant extends PapaRestaurant {
 *
 *     static final int PAPA = 1;
 *     static final int ME = 2;
 *
 *   [정상]
 *   extends 로 아빠 식당을 상속. 그리고 볶음밥 주문 구분용 상수
 *   두 개(PAPA=1, ME=2).
 *
 *
 *     @Override
 *     public void makeBokkeumbap(int family) {
 * //         super.makeBokkeumbap();
 *         switch (family) {
 *         case PAPA:
 *             super.makeBokkeumbap();
 *             break;
 *         case ME:
 *             System.out.println("[ChildRestaurant] makeBokkeumbap");
 *             break;
 *         }
 *     }
 *
 *   [정상 — 오버라이드 + super]
 *   부모의 makeBokkeumbap(int family)(빈 껍데기)를 재정의해서 실제
 *   로직을 채웠습니다.
 *     - family 가 PAPA 면 super.makeBokkeumbap() → 부모의 볶음밥
 *       ("[PapaRestaurant] makeBokkeumbap") 을 냅니다.
 *     - family 가 ME 면 자식이 직접 "[ChildRestaurant] makeBokkeumbap"
 *       을 출력합니다.
 *   [의도된 장치] 맨 위 `// super.makeBokkeumbap();` 주석은
 *   "주문 구분 없이 무조건 부모 것만 부르던" 이전 버전입니다.
 *   지금은 switch 로 갈라 냈으니 주석으로 남겨둔 것입니다.
 *
 *
 *     /*
 *     @Override
 *     public void makeBokkeumbap() {
 *         System.out.println("[ChildRestaurant] makeBokkeumbap");
 *     }
 *     *(주석 끝)
 *
 *   [의도된 장치 — 인자 없는 볶음밥의 재정의는 주석 처리됨]
 *   이걸 살리면, 인자 없는 childRestaurant.makeBokkeumbap() 도 아빠
 *   것이 아니라 아들 것("[ChildRestaurant] makeBokkeumbap")이
 *   나옵니다("아빠 볶음밥이 맛없어서 아들 것으로 완전히 바꿈").
 *   지금은 주석 상태라, 인자 없는 볶음밥은 여전히 부모에게 물려받은
 *   아빠 버전이 그대로 실행됩니다. "재정의를 하느냐 마느냐" 로
 *   결과가 달라지는 걸 비교하라고 남겨둔 것입니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까 (MainClass 기준)
 * ###################################################################
 *
 *   childRestaurant.makeBokkeumbap()          → [PapaRestaurant] makeBokkeumbap
 *                                               (재정의 주석 상태 → 물려받은 부모 것)
 *   childRestaurant.makeBokkeumbap(PAPA)      → [PapaRestaurant] makeBokkeumbap
 *                                               (super 호출)
 *   childRestaurant.makeBokkeumbap(ME)        → [ChildRestaurant] makeBokkeumbap
 */
