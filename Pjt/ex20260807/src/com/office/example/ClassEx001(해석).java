/*
 * ===================================================================
 *  ClassEx001.java 해석본
 * ===================================================================
 *
 * ※ 참고 : 학습용 해석 문서입니다. Cat.java, Person.java 두 설계도를
 *   가져다 쓰는 파일이에요 — 그 두 파일의 내부 구조는
 *   Cat(해석).java, Person(해석).java에서 이미 짚었으니, 여기선
 *   "이 파일이 그걸 어떻게 갖다 쓰는지"에 집중할게요. 파일 맨
 *   위부터 순서대로 짚어보겠습니다.
 *
 *
 * Cat cat1 = new Cat();
 *
 *   Cat 틀로 실제 고양이 빵 하나를 굽는 거예요. 이렇게 만든
 *   것(cat1)을 "객체" 또는 "인스턴스"라고 불러요. 원본 주석에
 *   "Cat은 말이 클래스이지, 데이터타입으로 인식도 된다"고
 *   적혀있는데, 정확히 그 말이에요 — int, String처럼 Cat도
 *   "cat1이라는 상자가 어떤 종류의 물건을 담는지"를 정하는 타입
 *   역할을 해요.
 *
 *
 * System.out.println("cat1: " + cat1);
 *   // cat1: com.office.example.Cat@73a28541
 *
 *   객체를 println에 그냥 찍으면 "패키지.클래스이름@식별번호"
 *   형태가 나와요. 이건 어제 배열에서 본 "[I@..." 표기랑 같은
 *   원리예요 — 그냥 통째로 찍으면 내용물이 아니라 "이게 어떤
 *   클래스의, 몇 번째 물건인지"만 보여줘요.
 *
 *
 * System.out.println(cat1.breed);
 *
 *   cat1.breed는 static 필드예요 (Cat.java 해석본 참고). static
 *   필드는 값을 준 적이 없는데도 이렇게 cat1을 통해 접근할 수
 *   있어요 — 다만 이건 "cat1만의 breed"가 아니라 "Cat이라는 종류
 *   전체가 공유하는 breed"예요. 지금은 아무도 값을 안 넣어서
 *   기본값인 null이 찍혀요.
 *
 *
 * cat1.eat();
 *
 *   일반(인스턴스) 메서드는 이렇게 실제로 구운 빵(cat1)을 통해서만
 *   부를 수 있어요.
 *
 *
 * Person person1 = new Person();
 * // person1.walkForEng();
 *
 *   Person도 똑같이 빵을 하나 구워요. 근데 이 순간 Person.java의
 *   setAtrribute, setAtrribute1 필드 초기화가 자동으로 실행되면서
 *   setData() → setData1() 순서로 이름과 등록번호가 정해져요
 *   (Person(해석).java에 그 과정을 자세히 풀어뒀어요). walkForEng()
 *   호출은 주석 처리돼서 실행 안 돼요.
 *
 *
 * System.out.println(person1.name);          // 홍길동  ← 실제로는 박찬호
 * System.out.println(person1.regNum);         // 1000    ← 실제로는 10000000
 * System.out.println(person1.setAtrribute);   // 0
 * System.out.println(person1.setAtrribute1);  // 1
 *
 *   원본 주석에 적힌 예상값 중 name/regNum 두 개는 실제 결과와
 *   달라요. Person.java 해석본에서 짚었듯, setData()가 만든
 *   "홍길동/1000"은 그 직후 setData1()이 "박찬호/10000000"으로
 *   덮어써버리기 때문이에요. 나머지 두 줄(setAtrribute=0,
 *   setAtrribute1=1)은 각 메서드의 return 값 그대로라 예상이
 *   맞아요.
 *
 *
 * 실행하면 어떻게 될까
 *
 *   "cat1: com.office.example.Cat@..." → null(breed) → "eat()
 *   CALLED!!" → "박찬호" → 10000000 → 0 → 1 순서로 출력돼요.
 */
