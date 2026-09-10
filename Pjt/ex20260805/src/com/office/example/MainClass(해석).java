/*
 * ===================================================================
 *  MainClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 파일 맨 위부터 순서대로
 *   짚어보겠습니다.
 *
 *
 * import java.util.ArrayList;
 * import java.util.Scanner;
 *
 *   자바 창고에서 부품 2개를 가져오는 줄이에요. 근데 ArrayList는
 *   가져오기만 하고 아래에서 한 번도 안 써요 — 안 써도 오류는 안
 *   나고, 그냥 "필요 없는 걸 가져왔다"는 정도로 넘어가면 돼요.
 *
 *
 * int num = 10;
 * System.out.println("num = " + num);
 * System.out.println("num = " + num);
 * System.out.print("num = \t\n" + num + "\n");
 * System.out.print("num = " + num);
 *
 *   println과 print의 차이가 딱 하나 있어요 — println은 찍고 나서
 *   자동으로 줄을 바꾸고, print는 안 바꿔요. 마지막 print 줄은
 *   줄바꿈이 없어서, 그 다음에 나오는 println()이 실행되면 그
 *   줄바꿈 하나가 이 print 결과 뒤에 그대로 붙어요. 그래서 화면
 *   에서는 "num = 10" 한 줄로 자연스럽게 끝난 것처럼 보이지만,
 *   사실은 서로 다른 두 문장이 합작한 결과예요.
 *
 *
 * System.out.print("어머님께서는 \"밥 먹고 설거지 해놔라\"라고 하셨습니다.\n");
 *
 *   \" 는 "이 따옴표는 문자열을 끝내는 게 아니라 진짜로 출력할
 *   글자다"라고 알려주는 표시예요. 이게 없으면 큰따옴표가 문자열의
 *   "끝"으로 오해받아서 컴파일이 안 돼요.
 *
 *
 * System.out.print("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n");
 * System.out.print("\\\\\\2020-08-08\\\\\\\n");
 *
 *   \\ 하나가 화면에는 역슬래시 한 글자로 찍혀요. 이 규칙을 이용해서
 *   바로 위 주석에 있는 "//////...2020-08-01...//////" 모양의
 *   테두리를, 실제 코드로 그대로 재현해본 거예요.
 *
 *
 * Scanner scanner = new Scanner(System.in);
 * String str = scanner.nextLine();
 * System.out.println("str = " + str);
 *
 *   이제부터 콘솔 입력을 받아요. new Scanner(System.in)로 "입력
 *   받는 도구" 하나를 만들고, nextLine()으로 사용자가 엔터 치기
 *   전까지 입력한 한 줄을 통째로 받아와요.
 *
 *
 * Byte byteNum = scanner.nextByte();
 * int intNum = scanner.nextByte();
 * long longNum = scanner.nextByte();
 * float floatNum = scanner.nextByte();
 * boolean booleanData = scanner.nextBoolean();
 *
 *   바로 위 주석에 nextByte/nextInt/nextLong/nextFloat/nextDouble
 *   /nextLine/nextBoolean 7개 메서드를 전부 나열해뒀고, 변수
 *   이름도 byteNum/intNum/longNum/floatNum으로 다르게 지어놨어요.
 *   "타입마다 맞는 메서드를 하나씩 보여주겠다"는 의도가 뚜렷해요.
 *
 *   근데 실제로는 intNum, longNum, floatNum 세 줄 다 nextByte()를
 *   그대로 썼어요. byte 값은 int/long/float로 자동으로 늘어날 수
 *   있어서 컴파일은 되지만, 이름이 암시하는 의도(각 타입 전용
 *   메서드 사용)와는 다르게 동작해요 — nextInt(), nextLong(),
 *   nextFloat()로 바꿔야 했을 자리에 nextByte()를 복붙한 실수로
 *   보여요.
 *
 *   고치면 각 줄을 이름에 맞는 메서드로 바꾸면 돼요: intNum은
 *   scanner.nextInt(), longNum은 scanner.nextLong(), floatNum은
 *   scanner.nextFloat()로.
 *
 *
 * 실행하면 어떻게 될까
 *
 *   이 프로그램은 콘솔 입력을 기다리는 "대화형" 프로그램이에요.
 *   순서대로 문자열 → byte 범위(-128~127) 정수를 4번 → true/false
 *   를 입력해야 끝까지 진행돼요. 원본 그대로 두면 intNum 자리에
 *   30000처럼 byte 범위를 벗어난 값을 넣는 순간
 *   InputMismatchException이 터져요 — 변수 이름은 int인데 실제
 *   검사는 byte 기준으로 하기 때문이에요.
 */
