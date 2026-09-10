/*
 * ===================================================================
 *  multiargs/MainClass.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. Score.java와 TotalAverage.java를
 *   가져다 씁니다 (각각 Score(해석).java, TotalAverage(해석).java
 *   참고).
 *
 *   이 파일이 가르치려는 것:
 *     (1) 가변인자 메서드를 인자 개수를 바꿔가며 부를 수 있다
 *     (2) static 메서드는 `클래스이름.메서드()` 로 부른다
 *     (3) 그 도구 메서드(getTotalScore/getAverageScore)가 어느
 *         클래스 소속인지 — 주석 처리된 "틀린 호출" 들로 대비시킴
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● main 메서드
 *    실행 시작점. `public static void main(String[] args)`.
 *
 *  ● import
 *    다른 패키지 클래스를 가져오는 선언.
 *    `import com.office.ex.overloading.TotalAverage;` (같은 패키지의
 *    Score 는 import 없이 바로 씀).
 *
 *  ● 객체 메서드 호출 vs static 메서드 호출
 *    객체 메서드: 먼저 `new` 로 객체를 만들고 `객체.메서드()`.
 *                여기선 score.printStudentsTotalAndAverageScore(...).
 *    static 메서드: 객체 없이 `클래스이름.메서드()`.
 *                여기선 TotalAverage.getTotalScore(...).
 *    → 그 메서드가 static 으로 선언됐는지에 따라 부르는 방식이
 *      정해집니다. static 이 아닌 걸 클래스이름으로 부르거나, 그
 *      반대로 하면 컴파일 에러입니다.
 *
 *  ● 가변인자 호출
 *    Score 의 메서드는 (int classNo, int ...scores) 라서
 *    (3, 95, 90, 100) 처럼 첫 값은 반 번호, 나머지는 점수로 개수
 *    제한 없이 넘깁니다.
 *
 *  ● 반환값을 안 받는 호출
 *    `TotalAverage.getTotalScore(scores);` 처럼 결과를 변수에 안
 *    담고 부르면, 계산은 되지만 그 결과는 어디에도 안 쓰이고
 *    사라집니다(출력도 안 됨).
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 위에서 아래로)
 * ###################################################################
 *
 *
 * Score score = new Score();
 *
 * // score.printStudentsTotalAndAverageScore();
 * // score.printStudentsTotalAndAverageScore(80, 90);
 * // score.printStudentsTotalAndAverageScore(80, 90, 100);
 * // score.printStudentsTotalAndAverageScore(80, 90, 100, 85);
 *
 *   [의도된 장치 — 가변인자의 유연함]
 *   Score 객체를 만들고, 주석 처리된 네 줄은 "가변인자면 인자
 *   개수를 마음대로(0, 2, 3, 4개) 넘길 수 있다" 를 보여주려던
 *   호출들입니다. (단 Score 의 메서드는 첫 매개변수가 int classNo
 *   로 고정이라, 실제로는 최소 1개는 넘겨야 합니다 — 그래서 진짜
 *   실행은 아래 한 줄로 남겨둔 것으로 보입니다.)
 *
 *
 * score.printStudentsTotalAndAverageScore(3, 95, 90, 100);
 *
 *   [정상]
 *   실제로 실행되는 호출. 3 은 classNo(반 번호), 95/90/100 은
 *   가변인자 scores 로 들어갑니다. Score 안에서 이 점수 배열을
 *   TotalAverage 에 넘겨 총점·평균을 계산해 출력합니다.
 *
 *
 * (원본에서 아래 4줄은 /* *(별) 로 통째 주석 처리돼 있음)
 *
 * Score score2 = new Score();
 * int[] scores = {10, 20, 30};
 * score2.getTotalScore(scores);
 * score2.getAverageScore(scores);
 *
 *   [의도된 장치 — "틀린 호출 1"]
 *   "길동 학생이 총합·평균을 쓰고 싶다" 는 메모와 함께 주석 처리돼
 *   있습니다. 주석인 이유: getTotalScore / getAverageScore 는 Score
 *   클래스에 없습니다. 이 메서드들은 TotalAverage 클래스에 있고,
 *   게다가 static 이라 객체(score2)로 부르는 게 아니라 클래스
 *   이름으로 불러야 합니다. 그대로 두면 컴파일 에러라 막아둔
 *   것입니다.
 *
 *
 * int[] scores = {10, 20, 30};
 * TotalAverage.getTotalScore(scores);
 * TotalAverage.getAverageScore(scores);
 *
 *   [정상 — 올바른 사용법, 다만 결과를 버림]
 *   getTotalScore / getAverageScore 는 TotalAverage 의 static
 *   메서드이므로 `TotalAverage.메서드(배열)` 형태로 부릅니다.
 *   다만 여기서는 돌려받은 총점·평균을 변수에 담지도, 출력하지도
 *   않아서 결과가 그냥 버려집니다. 값을 보려면
 *   System.out.println(TotalAverage.getTotalScore(scores)); 처럼
 *   감싸야 합니다.
 *
 *
 * // Score.getTotalScore(scores);   // 총합
 * // Score.getAverageScore(scores); // 평균
 *
 *   [의도된 장치 — "틀린 호출 2"]
 *   주석인 이유도 위와 같습니다 — Score 에는 getTotalScore /
 *   getAverageScore 가 없습니다(TotalAverage 에 있음). "이 도구
 *   메서드들이 어느 클래스 소속인지" 헷갈리지 말라고 일부러 틀린
 *   호출을 주석으로 남겨둔 것입니다.
 *
 *
 * ###################################################################
 *  2. 실행하면 어떻게 될까
 * ###################################################################
 *
 *   "3반 총점: 285" 와 "3반 평균: 95.0" 두 줄이 출력됩니다
 *   (95+90+100 = 285, 285 / 3 = 95 → TotalAverage 의 int 나눗셈
 *   때문에 95.0). 그 아래 TotalAverage 직접 호출 두 줄은 계산만
 *   하고 출력이 없어 화면에는 아무것도 안 남깁니다.
 */
