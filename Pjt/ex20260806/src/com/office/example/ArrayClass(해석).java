/*
 * ===================================================================
 *  ArrayClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. 문법 오류 없이 컴파일되는
 *   파일이에요. 배열은 "이름표 하나에 칸이 여러 개 달린 사물함"
 *   이라고 생각하면 돼요. 칸 번호(인덱스)는 항상 0부터 시작해요.
 *   파일 맨 위부터 순서대로 짚어보겠습니다.
 *
 *
 * int[] myScores = {90, 50, 100};
 * System.out.println("myScores: " + myScores);   // [I@5aaa6d82
 *
 *   배열을 println에 그냥 넣으면 안의 내용물(90, 50, 100)이 아니라
 *   "[I@5aaa6d82" 같은 낯선 글자가 나와요. [I는 "int 배열"이라는
 *   뜻의 표기고, @ 뒤 숫자는 이 배열을 구분하는 식별용 번호예요
 *   (VariableCls에서 본 identityHashCode와 같은 종류의 값이에요 —
 *   진짜 주소가 아니라 그냥 구분용 번호라서, 실행할 때마다 달라질
 *   수 있어요).
 *
 *
 * System.out.println("myScores[0]: " + myScores[0]);   // 90
 *
 *   대괄호 안에 칸 번호를 적으면 그 칸의 내용물을 꺼낼 수 있어요.
 *
 *
 * for (int i = 0; i < myScores.length; i++) {
 *     System.out.println(myScores[i]);
 * }
 *
 *   0번 칸부터 마지막 칸까지 번호를 하나씩 늘려가며 다 꺼내
 *   찍어요. myScores.length는 "이 사물함에 칸이 몇 개 있나"를
 *   알려줘요.
 *
 *
 * for (int score : myScores) {
 *     System.out.println("score: " + score);
 * }
 *
 *   바로 위 for문이랑 결과는 똑같은데, 칸 번호를 몰라도 돼요 —
 *   "이 사물함 안의 물건을 하나씩 다 꺼내줘"라고만 하면 자바가
 *   알아서 순서대로 꺼내줘요. 이걸 "향상된 for문(for-each)"이라고
 *   불러요.
 *
 *
 * System.out.println(Arrays.toString(myScores));   // [90, 50, 100]
 *
 *   아까 println(myScores)가 이상한 글자를 찍었던 것과 달리,
 *   Arrays.toString()에 넣으면 내용물을 사람이 읽을 수 있는
 *   "[90, 50, 100]" 형태로 바꿔줘요. 배열 내용을 확인할 땐 이걸
 *   써야 해요.
 *
 *
 * myScores[2] = 1000;
 *
 *   2번 칸(세 번째 칸)의 내용물을 1000으로 바꿔치기해요.
 *
 *
 * String[] myTools = new String[3];
 * myTools[0] = "지우개"; myTools[1] = "공책"; myTools[2] = "만년필";
 *
 *   이번엔 처음부터 내용물을 안 채우고 "칸이 3개인 빈 사물함"만
 *   먼저 만들었어요. 그 다음 칸마다 하나씩 채워 넣었어요. (참고:
 *   String 배열이라 채우기 전까지 각 칸은 null — "아직 아무것도
 *   없음" 상태예요.)
 *
 *
 * float[] myFloats = new float[] {3.14f, 3.141592f, 3.141592876f};
 *
 *   배열을 만드는 세 번째 방식이에요 — "new float[]"로 만들면서
 *   동시에 내용물도 같이 넣어요. (이 변수는 이후로 한 번도 안
 *   쓰여요 — 배열 만드는 방법 세 가지를 보여주는 용도로만 쓰인
 *   것 같아요.)
 *
 *
 * int[] studentScores = {80, 95, 100, 70, 68, 74, 88, 98, 100, 76};
 * for (...) { total += studentScores[i]; }
 * average = total / (float)studentScores.length;
 *
 *   total은 정수(int)라서, 그냥 "total / studentScores.length"라고
 *   쓰면 정수 나누기 정수가 되어 소수점이 통째로 잘려나가요. 그래서
 *   studentScores.length 앞에 (float)를 붙여서 강제로 실수로 바꾼
 *   다음 나눠요 — Operator01에서 본 "산술 변환"과 같은 원리예요.
 *   총합은 849, 평균은 84.9가 나와요.
 *
 *
 * int[] numbers = {10, 20, 30};
 * int[] numberCopy = numbers;
 * numbers[2] = 999;
 * System.out.println(Arrays.toString(numbers));      // [10, 20, 999]
 * System.out.println(Arrays.toString(numberCopy));   // [10, 20, 999]
 *
 *   여기가 이 파일에서 제일 중요한 부분이에요. "numberCopy =
 *   numbers"라고 썼지만, 이건 내용물을 복사한 게 아니라 "같은
 *   사물함을 가리키는 이름표를 하나 더 만든" 거예요. 그래서
 *   numbers[2]를 999로 바꾸면, numberCopy로 봐도 똑같이 999로
 *   보여요 — 이름표는 두 개지만 사물함은 하나뿐이거든요.
 *   (Operator02에서 본 String의 == 비교와 같은 원리예요 — 배열도
 *   참조형이라, 대입할 때 물건 자체가 아니라 "가리키는 화살표"만
 *   복사돼요.)
 *
 *
 * 실행하면 어떻게 될까
 *
 *   myScores 관련 출력들 → 학생 점수 10개와 총합(849)·평균(84.9)
 *   → numbers와 numberCopy가 마지막에 둘 다 [10, 20, 999]로 똑같이
 *   찍히는 순서로 진행돼요.
 */
