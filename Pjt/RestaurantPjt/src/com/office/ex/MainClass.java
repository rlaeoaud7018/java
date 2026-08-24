package com.office.ex;

public class MainClass {

	public static void main(String[] args) {
		
		/*
		 * 아빠 중식당, 메뉴 짜장면 짬뽕
		 */
		
		PapaRestaurant papaRestaurant = new PapaRestaurant();
		papaRestaurant.makeJajangmyeon();		// 짜장면
		papaRestaurant.makeJjamppong();			// 짬뽕
		
		/*
		 * 아빠 은퇴, 사랑하는 아들아 중식당을 물려받아라.
		 * 가게 새로 오픈!
		 */
		
		ChildRestaurant childRestaurant = new ChildRestaurant();
		childRestaurant.makeJajangmyeon();
		childRestaurant.makeJjamppong();
		
		papaRestaurant.makeBokkeumbap();
		childRestaurant.makeBokkeumbap();
		
		/*
		 * 근데, 아들이 볶음바 맛을 보니까, 울 아빠 볶음밥이 맛 없어요.
		 * 오버라이드(override, 재정의)
		 */
		
		
		/*
		 * 아빠가게 단골손님이 오셔서 말하기를 
		 * '아들아 나는 너꺼 말고 아빠꺼 볶음밥 먹고 싶단다.'
		 */
		
		childRestaurant.makeBokkeumbap(ChildRestaurant.PAPA);
		childRestaurant.makeBokkeumbap(ChildRestaurant.ME);
		
		
		
	}
}
