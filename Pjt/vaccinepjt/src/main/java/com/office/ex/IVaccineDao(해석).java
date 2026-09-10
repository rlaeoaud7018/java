/*
 * ===================================================================
 *  vaccinepjt / IVaccineDao.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. "백신 DAO 규격서" 인터페이스
 *   입니다. 프로젝트 개요는 VaccineConfig(해석).java 를 먼저 보세요.
 *
 *   이 파일이 가르치려는 것 (이 프로젝트의 새 개념):
 *   "인터페이스가 다른 인터페이스를 extends 할 수 있다." IVaccineDao
 *   는 IVaccineConfig 를 물려받아서, DB 접속 상수 4개 + 자기가
 *   선언한 메서드 1개를 함께 가집니다.
 *
 *   컴파일 상태: 문법 오류 없이 컴파일됩니다.
 *
 *
 * ###################################################################
 *  0. 이 파일에 나오는 개념·용어 정리 (먼저 읽으세요)
 * ###################################################################
 *
 *  ● 인터페이스 extends 인터페이스
 *    클래스끼리는 상속에 `extends`, 클래스가 인터페이스를 구현할
 *    땐 `implements` 를 씁니다. 그런데 "인터페이스가 인터페이스를
 *    물려받을" 때는 `extends` 를 씁니다(implements 아님).
 *    `interface IVaccineDao extends IVaccineConfig` → IVaccineDao 는
 *    IVaccineConfig 의 상수(DRIVER/URL/USER/PASSWORD)를 자기 것처럼
 *    갖고, 거기에 메서드 규격을 더합니다.
 *
 *  ● 이걸 하면 좋은 점
 *    VaccineDao 는 `implements IVaccineDao` 하나만 붙이면, DB 접속
 *    상수와 메서드 규격을 한꺼번에 얻습니다. (설정과 규격을 따로
 *    implements 할 필요가 없음.)
 *
 *  ● 규격에 일부 메서드만 선언 (실수 아님)
 *    이 인터페이스에는 insertVaccineInjection 하나만 있습니다.
 *    그런데 VaccineDao 에는 select/delete 메서드도 있습니다. 그
 *    둘은 규격에 안 넣었을 뿐이고, VaccineDao 만의 메서드로 존재
 *    합니다. 그래서 insert 에만 @Override 가 붙습니다.
 *
 *
 * ###################################################################
 *  1. 코드 해석 (파일 전체)
 * ###################################################################
 *
 *
 * public interface IVaccineDao extends IVaccineConfig {
 *
 *     public int insertVaccineInjection(VaccineDto vaccineDto);
 * }
 *
 *   [정상]
 *   - extends IVaccineConfig : DB 접속 상수 4개를 물려받습니다.
 *   - insertVaccineInjection : "백신 접종 기록 하나를 넣고, 추가된
 *     행 수(int)를 돌려줘라" 는 규격. 본문 없이 `;` 로 끝납니다.
 *   실제 코드는 VaccineDao 가 채웁니다(VaccineDao(해석).java).
 *
 *
 * ###################################################################
 *  2. 정리
 * ###################################################################
 *
 *   실행되는 게 없습니다. 이 규격 + 물려받은 상수를 VaccineDao 가
 *   MySQL + JDBC 로 구현합니다.
 */
