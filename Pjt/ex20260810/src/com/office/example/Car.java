package com.office.example;

public class Car {

   // 멤버 필드
      int length;            // 길이
      int price;            // 가격
      int displacement;      // 배기량
      
      // 생성자
      public Car() {
         System.out.println("--- Default Constructor ---");
         
      }
      
      // 생성자 오버로딩
      public Car(int length, int price, int displacement) {
         this.length = length;
         this.price = price;
         this.displacement = displacement;
      }
      
      // 메서드 작명시 동사+ 명사 없으면 동사만 ㄱㄱ
      // 메서드 오버로딩
      public void goForward(int speed) {
         System.out.println(speed + "km/h goForward() CALLED!!");
      }
      
      public void goBack() {
         System.out.println("goBack() CALLED!!");
      }
      
      public void doStop() {
         System.out.println("doStop() CALLED!!");
      }
      
      public void testDrive() {
//         goForward();
         goBack();
         doStop();
      }
      
      public void printCarSpec(String language) {
         if (language.equals("kor")) {
            printCarSpecForKor();
         } else {
            printCarSpecForEng();
         }
      }
      
      // 다국어(kor || end)
      private void printCarSpecForKor() {
         System.out.println("길이: " + this.length);
         System.out.println("가격: " + this.price);
         System.out.println("배기량: " + this.displacement);
      }
      
      private void printCarSpecForEng() {
         System.out.println("length: " + this.length);
         System.out.println("price: " + this.price);
         System.out.println("displacement: " + this.displacement);
      }
      
      // 거리 = 시간 * 속도
      public float getDistance(int hour, int speed) {
//         System.out.println("이동 거리: " + (hour * speed) + "km");
         float distance = hour * (float)speed;
         
         return distance;      // 여기서 끝
         
//         System.out.println("메서드 실행 끝~~~~");
         
      }
   
}
