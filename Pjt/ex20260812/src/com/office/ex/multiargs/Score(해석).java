/*
 * ===================================================================
 *  multiargs/Score.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. main 메서드가 없는 "설계도"
 *   파일이고, 같은 패키지의 MainClass.java가 가져다 씁니다. 다른
 *   패키지의 TotalAverage.java도 import 해서 씁니다.
 *
 *   이 파일이 가르치려는 것:
 *   "가변인자(varargs, int ...)" — 매개변수 개수를 미리 못 박지
 *   않고, 부를 때 몇 개든 넘길 수 있게 하는 방식. 그리고 계산은
 *   직접 안 하고 TotalAverage 도구 클래스에 맡기기.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 클래스(class) / 객체(object) / new
 *    클래스 = 설계도(Score). 객체 = 그것으로 만든 물건. `new Score()`.
 *
 *  ● import
 *    다른 패키지 클래스를 가져오는 선언.
 *    `import com.office.ex.overloading.TotalAverage;` 로 다른
 *    패키지의 TotalAverage 를 씁니다.
 *
 *  ● 메서드 / 반환타입 void / 매개변수
 *    printStudentsTotalAndAverageScore 는 void 라 값을 돌려주지
 *    않고 출력만 합니다.
 *
 *  ● 고정 매개변수 (int score1, int score2, int score3)
 *    매개변수 개수가 딱 정해진 방식. 학생이 3명이면 3개, 4명이면
 *    메서드를 또 만들어야 합니다. 불편합니다.
 *
 *  ● 가변인자 / varargs (int ...scores)
 *    "int 를 0개 이상 몇 개든 받는다" 는 뜻. 부를 때 (3, 95, 90, 100)
 *    처럼 개수를 마음대로 넘길 수 있습니다.
 *      - 메서드 안에서 scores 는 int 배열(int[])로 취급됩니다 →
 *        scores.length, scores[i] 를 그대로 씀.
 *      - 규칙: 가변인자는 매개변수 목록에서 "맨 뒤에 하나만" 올 수
 *        있습니다. 그래서 (int classNo, int ...scores) 처럼 고정
 *        매개변수(classNo)를 앞에, 가변인자(scores)를 뒤에 둡니다.
 *
 *  ● static 메서드 호출 (TotalAverage.getTotalScore(...))
 *    객체를 안 만들고 `클래스이름.메서드이름()` 으로 부르는 방식.
 *    총점·평균 계산은 이 파일에서 직접 안 하고 TotalAverage 에
 *    맡깁니다. (TotalAverage(해석).java 참고.)
 *
 *  ● 가변인자를 "배열 받는 메서드" 에 넘기기
 *    TotalAverage.getTotalScore(int[] scores) 는 배열을 받습니다.
 *    이 파일의 scores 는 가변인자지만 안에서는 배열이므로 그대로
 *    넘길 수 있습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * (원본에서 아래 메서드는 /* *(별) 로 통째 주석 처리돼 있음)
 *
 * public void printStudentsTotalAndAverageScore(int score1, int score2, int score3) {
 *     int totalScore = score1 + score2 + score3;
 *     System.out.println("총점: " + totalScore);
 *     float averageScore = totalScore / 3;
 *     System.out.println("평균: " + averageScore);
 * }
 *
 *   [의도된 장치 — "가변인자 배우기 전" 버전]
 *   매개변수가 딱 3개로 고정이라 학생 3명만 처리할 수 있습니다.
 *   (여기 `totalScore / 3` 도 int 나눗셈이라 평균 소수점이
 *   버려집니다 — TotalAverage(해석) 에서 설명한 것과 같은 현상.)
 *   아래 활성 버전과 비교하라고 남겨둔 것입니다.
 *
 *
 * public void printStudentsTotalAndAverageScore(int classNo, int ...scores) {
 * //     int totalScore = 0;
 * //     for (int i = 0; i < scores.length; i++) {
 * //         totalScore += scores[i];
 * //     }
 *     System.out.println(classNo + "반 총점: " + TotalAverage.getTotalScore(scores));
 * //     float averageScore = 0.0f;
 * //     if (scores.length > 0) {
 * //         averageScore = totalScore / scores.length;
 * //     }
 *     System.out.println(classNo + "반 평균: " + TotalAverage.getAverageScore(scores));
 * }
 *
 *   [정상 — 가변인자 버전]
 *   매개변수가 (int classNo, int ...scores) — 앞의 classNo 는 반
 *   번호(고정 1개), 뒤의 scores 는 가변인자라 점수를 몇 개든 받습니다.
 *   [의도된 장치] 주석 처리된 부분(직접 for 로 합 구하기, 직접
 *   평균 구하기)은, 그 계산을 이 메서드에서 직접 하지 않고
 *   TotalAverage 도구에 맡기기로 하면서 지운 흔적입니다. 실제로
 *   도는 건 TotalAverage.getTotalScore(scores) 와
 *   TotalAverage.getAverageScore(scores) 를 불러 그 결과를 바로
 *   출력하는 두 줄입니다.
 *   가변인자 scores 를, 배열을 받는 TotalAverage 메서드에 그대로
 *   넘길 수 있는 이유는 메서드 안에서 scores 가 int[] 이기
 *   때문입니다.
 *
 *
 * (원본 맨 아래, 클래스 닫는 괄호 "}" 바깥에 주석으로 파킹된 메서드)
 *
 * // public void printStudentsTotalAndAverageScore(String classNo, int ...scores) {
 * //     ... (첫 매개변수만 String 으로 바꾼 버전) ...
 * // }
 *
 *   [파킹된 코드]
 *   클래스 본문 "밖" 에 주석으로 세워둔 또 다른 버전입니다. 첫
 *   매개변수를 int classNo 대신 String classNo 로 바꾼 것인데,
 *   타입이 다르므로 살려도 오버로딩으로 성립은 합니다. 다만 위치가
 *   클래스 괄호 밖이라, 되살리려면 클래스 안으로 옮겨야 합니다.
 *   지금은 쓰지 않아 파킹만 해둔 상태입니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   이 파일만으로는 실행되는 것이 없습니다(설계도, main 없음).
 *   multiargs/MainClass.java 에서 이 메서드를 어떻게 부르는지
 *   이어집니다.
 */
