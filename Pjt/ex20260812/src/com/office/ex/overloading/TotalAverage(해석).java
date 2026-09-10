/*
 * ===================================================================
 *  overloading/TotalAverage.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. main 메서드가 없고, 다른 패키지
 *   (multiargs)의 MainClass.java와 Score.java가 가져다 씁니다.
 *
 *   이 파일이 가르치려는 것:
 *   "static 메서드로 만든 도구(유틸리티) 클래스" — 객체를 안 만들고
 *   `클래스이름.메서드(값)` 로 바로 쓰는, 한 가지 계산만 전담하는
 *   메서드. 원본 주석의 "총점 전문가 메서드 / 평균 전문가 메서드"
 *   가 이 뜻입니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다. 다만 평균 계산에서
 *   "정수 나눗셈" 때문에 소수점이 버려집니다 — 아래에서 짚습니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● static 메서드 (클래스 메서드)
 *    객체를 만들지 않고 `클래스이름.메서드이름()` 으로 바로 부르는
 *    메서드. 여기선 `TotalAverage.getTotalScore(scores)` 처럼 씁니다.
 *    "값만 넣으면 계산해서 돌려주는 도구" 에 잘 맞습니다.
 *    참고: `static public` 과 `public static` 은 순서만 다르고
 *    똑같습니다(보통 `public static` 로 씀).
 *
 *  ● 매개변수(parameter) / 반환(return) / 반환타입
 *    매개변수 = 메서드가 받는 값(int[] scores).
 *    반환 = 계산 결과를 부른 쪽에 돌려주는 것(return 문).
 *    반환타입 = 돌려주는 값의 타입. getTotalScore 는 int,
 *    getAverageScore 는 float.
 *
 *  ● 배열(int[]) / .length
 *    int 값을 여러 개 담는 자료구조. `scores.length` 는 칸 개수.
 *
 *  ● 누적 합 패턴 (totalScore += scores[i])
 *    합을 담을 변수를 0으로 두고, for 로 배열을 훑으며 하나씩 더함.
 *    `totalScore += scores[i]` 는 `totalScore = totalScore +
 *    scores[i]` 의 줄임말.
 *
 *  ● 정수 나눗셈 (int / int)  ← 이 파일에서 배울 지점
 *    int 를 int 로 나누면 결과도 int 이고, 소수점 아래는 그냥
 *    버려집니다. 예: 65 / 3 → 21 (21.666... 아님). 소수까지
 *    얻으려면 나누기 "전에" 한쪽을 실수로 바꿔야 합니다((float) 캐스팅).
 *
 *  ● 0으로 나누기 방지
 *    `if (scores.length > 0)` : 배열이 비어 있는데 개수로 나누면
 *    오류가 나므로, 값이 하나라도 있을 때만 나눗셈을 합니다.
 *
 *  ● 메서드가 메서드를 부름
 *    getAverageScore 안에서 getTotalScore(scores) 를 부릅니다. 같은
 *    클래스의 static 메서드라 클래스이름 없이 바로 부를 수 있습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * static public int getTotalScore(int[] scores) {
 *     int totalScore = 0;
 *     for (int i = 0; i < scores.length; i++) {
 *         totalScore += scores[i];
 *     }
 *     return totalScore;
 * }
 *
 *   [정상]
 *   점수 배열을 받아 전부 더한 총점을 돌려주는 static 메서드입니다.
 *   totalScore 를 0으로 시작해 for 로 scores[0], scores[1], ... 를
 *   차례로 더하고(누적 합 패턴), 다 더한 값을 return. 돌려주는
 *   값이 정수라 반환타입 int.
 *
 *
 * static public float getAverageScore(int[] scores) {
 *     float averageScore = 0.0f;
 *     if (scores.length > 0) {
 *         averageScore = getTotalScore(scores) / scores.length;
 *     }
 *     return averageScore;
 * }
 *
 *   평균을 돌려주는 static 메서드. averageScore 를 0.0f 로 시작하고,
 *   배열에 값이 하나라도 있을 때만 나눗셈(0으로 나누기 방지).
 *   계산은 총점 ÷ 개수.
 *
 *   [단순 실수 — 다만 꼭 배워야 할 지점] 소수점이 버려집니다:
 *     averageScore = getTotalScore(scores) / scores.length;
 *     └─ getTotalScore(scores) 는 int, scores.length 도 int.
 *        int ÷ int 는 결과도 int → 나누는 순간 소수점이 버려집니다.
 *        그 버려진 값이 그 다음에 float averageScore 에 담깁니다.
 *        즉 왼쪽을 float 로 선언해도 이미 늦었습니다.
 *        예: 점수 {10, 20, 35} → 총점 65 → 65 / 3 = 21 (21.666...
 *        아님) → averageScore = 21.0
 *
 *   원작성자 의도: 변수도 float, 반환타입도 float 로 잡은 걸 보면
 *   소수까지 나오는 평균을 원한 게 분명합니다. 경고 주석도 비교쌍도
 *   없으니 "의도된 장치" 는 아니고, int 나눗셈 규칙을 아직 안 배운
 *   상태로 보입니다.
 *
 *   수정 (나누기 전에 한쪽을 실수로 캐스팅):
 *
 *     averageScore = (float) getTotalScore(scores) / scores.length;
 *
 *   이러면 float ÷ int = float 로 계산되어 21.666667 처럼 소수까지
 *   남습니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 것이 없습니다(설계도, main 없음).
 *   multiargs/MainClass.java 와 multiargs/Score.java 가 이 두
 *   메서드를 불러 씁니다 — 그쪽 해석본으로 이어집니다.
 */
