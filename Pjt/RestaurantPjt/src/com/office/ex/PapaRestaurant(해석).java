/*
 * ===================================================================
 *  RestaurantPjt / com.office.ex / PapaRestaurant.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 상속에서 "물려주는 쪽(부모
 *   클래스)" 입니다. 그룹 개요·상속/오버라이드/super 개념은
 *   MainClass(해석).java 의 "0. 개념 정리" 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것:
 *   부모 식당은 메뉴 3개(짜장면·짬뽕·볶음밥) 메서드를 갖습니다.
 *   여기에 "매개변수 있는 볶음밥" 이 하나 더 있는데, 그건 자식이
 *   재정의하려고 만들어둔 빈 껍데기입니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 부모 클래스 (superclass)
 *    상속에서 필드·메서드를 물려주는 쪽. ChildRestaurant 가 이걸
 *    extends 합니다.
 *
 *  ● public 메서드
 *    public 이라 다른 클래스·다른 패키지에서도 부를 수 있고, 자식이
 *    물려받아 씁니다.
 *
 *  ● 오버로딩된 makeBokkeumbap (인자 0개 / 1개)
 *    이름은 같고 매개변수만 다른 메서드 두 개. makeBokkeumbap() 과
 *    makeBokkeumbap(int family). (같은 클래스 안 = 오버로딩.)
 *
 *  ● 자동 생성된 빈 메서드 (// TODO Auto-generated method stub)
 *    개발 도구가 만들어 준 "뼈대만 있는" 메서드. 여기서는 자식
 *    (ChildRestaurant)에서 makeBokkeumbap(int family) 를 @Override
 *    하려면 "부모에도 그 메서드가 있어야" 하므로, 도구가 부모 쪽에
 *    빈 버전을 만들어 준 것입니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * public void makeJajangmyeon() {
 *     System.out.println("[PapaRestaurant] makeJajangmyeon");
 * }
 * public void makeJjamppong() {
 *     System.out.println("[PapaRestaurant] makeJjamppong");
 * }
 * public void makeBokkeumbap() {
 *     System.out.println("[PapaRestaurant] makeBokkeumbap");
 * }
 *
 *   [정상]
 *   메뉴 3개. 각자 "[PapaRestaurant] makeXXX" 한 줄을 출력합니다.
 *   이 세 메서드가 그대로 자식(ChildRestaurant)에게 상속됩니다.
 *
 *
 * public void makeBokkeumbap(int family) {
 *     // TODO Auto-generated method stub
 * }
 *
 *   [미완성 — 자동 생성된 빈 껍데기, 오류 아님]
 *   위의 makeBokkeumbap() 과 이름은 같고 매개변수(int family)가
 *   있어서, 부모 클래스 안에서는 오버로딩입니다. 본문이 비어 있어
 *   부모 쪽에서는 아무 일도 안 합니다.
 *   이게 여기 있는 이유: 자식 ChildRestaurant 가
 *   `@Override public void makeBokkeumbap(int family)` 를 하려면
 *   "부모에 그 시그니처가 존재" 해야 합니다. 그래서 도구가 부모
 *   쪽에 빈 버전을 자동으로 넣어 준 것입니다. 실제 로직은 자식이
 *   채웁니다(ChildRestaurant(해석) 참고).
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 게 없습니다(설계도, main 없음).
 *   MainClass 에서 papaRestaurant.makeJajangmyeon() 등을 부르면
 *   "[PapaRestaurant] ..." 줄들이 출력됩니다.
 */
