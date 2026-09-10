/*
 * ===================================================================
 *  StringFormat.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 문법 오류 없이 컴파일되는
 *   파일이에요. 파일 맨 위부터 순서대로 짚어보겠습니다.
 *
 *
 * String name = "Mr. Hong";
 * System.out.println("name: " + name);
 * System.out.printf("name: %s\n", name);
 *
 *   println("..." + name)은 문자열을 그냥 이어붙이는 방식이고,
 *   printf("...%s...", name)은 "틀(형식)을 먼저 만들고 %s 자리에
 *   나중에 값을 끼워 넣는" 방식이에요. printf가 존재하는 이유는
 *   출력 서식(자리수, 소수점 등)을 세밀하게 조절할 수 있어서예요.
 *
 *
 * System.out.printf("이름 : %s%n", "Mr. Hong");
 * System.out.printf("성구분 : %c%n", 'w');
 * System.out.printf("나이 : %d%n", 20);
 * System.out.printf("신장 : %f%n", 187.7);
 * System.out.printf("결혼유무 : %b%n", false);
 *
 *   %s(문자열), %c(문자), %d(정수), %f(실수), %b(논리) 지정자
 *   자리에 뒤에 나온 값이 순서대로 끼워 넣어져요. %n은 줄바꿈이고
 *   \n이랑 똑같은 역할을 해요. (참고로 %o는 8진수, %x는 16진수예요
 *   — 원본 위쪽 주석에 있는 목록이에요.)
 *
 *
 * float rate = 3.131592f;
 * System.out.printf("rate: " + rate);
 * System.out.printf("rate: %.2f", + rate);
 * System.out.printf("rate: %.4f", + rate);
 *
 *   첫 줄은 printf를 썼지만 지정자가 아예 없어요 — "rate: " + rate
 *   로 미리 합쳐진 문자열을 그냥 통째로 출력할 뿐이라 println이랑
 *   똑같이 동작해요. 이건 "자릿수 제한 없는 원래 값"을 먼저 보여준
 *   거고, 그 다음 %.2f(소수점 2자리), %.4f(소수점 4자리)로 자릿수를
 *   깎아서 비교시켜주는 흐름으로 보여요. (참고: "+ rate"의 앞에
 *   붙은 +는 단항 덧셈 연산자인데, 값에 아무 영향도 없어요 — 문법
 *   적으로만 허용되는 군더더기예요.)
 *
 *
 * int num1 = 1;
 * int num2 = 12;
 * int num  = 123;
 * printf("num1: %d\n", num1);
 * printf("num2: %d\n", num1);
 * printf("num3: %d\n", num1);
 * printf("num1: %3d\n", num1);   ... %5d ... %-5d ... %05d 도 전부 동일 패턴
 *
 *   %5d는 "전체 5칸 확보하고 오른쪽 정렬", %-5d는 "왼쪽 정렬",
 *   %05d는 "빈칸을 0으로 채움"이라는 뜻이에요. num1(1자리)/num2
 *   (2자리)/num(3자리)로 자릿수가 다른 값을 일부러 준비해둔 걸
 *   보면, 자릿수가 다른 숫자들이 같은 폭으로 정렬되는 걸 비교해서
 *   보여주려던 의도로 보여요.
 *
 *   그런데 num2, num3라는 라벨을 달고도 실제로 넣은 값은 전부
 *   num1이에요. 그래서 원래 하려던 비교(1자리 vs 2자리 vs 3자리
 *   정렬)는 코드에 반영되지 못했고, 그냥 "1"이라는 같은 값이 서식만
 *   바뀌어서 여러 번 찍히는 결과가 돼요. 고치려면 두 번째·세 번째
 *   줄의 num1을 각각 num2, num으로 바꾸면 원래 의도가 완성돼요.
 *
 *
 * String result = String.format("res: %s", res);
 * System.out.println(result);
 *
 *   printf는 결과를 화면에 바로 찍고 끝나지만, String.format()은
 *   같은 서식 규칙을 쓰면서 결과를 "문자열로" 돌려받아서 나중에 또
 *   쓸 수 있게 해줘요.
 *
 *
 * String inputData = scanner.next();
 * System.out.println("inputData: " + inputData);
 *
 *   next()는 nextLine()과 달리 "공백 전까지 한 단어"만 받아와요.
 *   바로 아래 두 줄은 주석 처리돼서 실제로 실행되지 않아요.
 *
 *
 * System.out.print("나이: ");
 * int age = scanner.nextInt();
 * System.out.print("이름: ");
 * scanner.nextLine();
 * String userName = scanner.nextLine();
 *
 *   nextInt()로 숫자만 읽으면, 사용자가 엔터 칠 때 생긴 줄바꿈이
 *   입력 버퍼에 그대로 남아요. 그 상태에서 곧바로 nextLine()을
 *   부르면 "아직 안 읽은 그 줄바꿈"만 읽고 즉시 끝나버려서, 진짜
 *   사용자가 입력한 이름을 놓쳐요. 그래서 nextLine()을 한 번 먼저
 *   "버리는 용도"로 호출해 남은 줄바꿈을 치운 다음, 두 번째
 *   nextLine()으로 진짜 이름을 받아요 — 이건 실수가 아니라 숫자·
 *   문자열 입력을 섞을 때 흔히 필요한 정석적인 처리예요.
 *
 *
 * 실행하면 어떻게 될까
 *
 *   화면에 여러 서식 출력이 쭉 나온 다음, 콘솔 입력을 기다려요.
 *   순서대로 한 단어(next용) → 나이(정수) → 이름(문자열)을
 *   입력하면 끝까지 진행돼요.
 */
