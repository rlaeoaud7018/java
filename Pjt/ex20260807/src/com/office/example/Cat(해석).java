/*
 * ===================================================================
 *  Cat.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. main()이 없는, 다른 파일
 *   (ClassEx001, Example01)에서 가져다 쓰는 "설계도" 파일이에요.
 *   클래스는 "붕어빵 틀"이라고 생각하면 돼요 — 이 틀 자체는 빵이
 *   아니고, new Cat()을 해야 실제 빵(객체) 하나가 만들어져요.
 *   파일 맨 위부터 순서대로 짚어보겠습니다.
 *
 *
 * static String breed;
 * String color;
 * int age = 10;
 *
 *   breed에만 static이 붙어 있어요. static이 안 붙은 color, age는
 *   "각자 빵마다 따로 가지는 개인 소지품"이에요 — Cat을 100마리
 *   만들면 color, age도 100세트가 생겨요. 반대로 static이 붙은
 *   breed는 "모든 빵이 다 같이 보는 공용 칠판" 같은 거예요 — Cat이
 *   몇 마리든 breed는 딱 하나만 존재하고, 어느 고양이가 바꾸면
 *   전체 고양이한테 다 보여요.
 *
 *
 * void eat() {
 *     System.out.println("eat() CALLED!!");
 * }
 *
 *   static이 없는 일반 메서드예요. 이런 건 실제로 빵을 하나 구운
 *   다음(new Cat()) 그 빵을 통해서만 부를 수 있어요 (cat1.eat()
 *   처럼).
 *
 *
 * static void eatStatic() {
 *     System.out.println("eatStatic() CALLED!!");
 * }
 *
 *   이건 static 메서드라서, 빵을 굽지 않고도(new 없이) "Cat.
 *   eatStatic()"처럼 틀 이름으로 바로 부를 수 있어요. (다만 빵을
 *   구운 뒤 cat1.eatStatic()처럼 불러도 되긴 해요 — Example01.java
 *   에서 실제로 이렇게 부르는 걸 볼 수 있어요.)
 *
 *
 * void scratch() { }
 * void meow() { }
 *
 *   안이 비어있는 메서드들이에요. 나중에 채워 넣을 자리만
 *   만들어둔 상태로 보여요.
 */
