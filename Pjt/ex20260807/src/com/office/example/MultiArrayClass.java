package com.office.example;

public class MultiArrayClass {

	public static void main(String[] args) {
		
//		int[] ns = new int[3];
//		System.out.println("ns: " + ns);		// [I@5aaa6d82
//		
//		int[][] mns = new int[3][2];
//		System.out.println("mns: " + mns);		// [[I@5aaa6d82
//		
//		int [][][] mn3s = new int[3][2][1];
//		System.out.println("mn3s: " + mn3s);	// [[[I@3ada9e37
		
		// 다차원 배열 선언 - I	(3 x 2)
//		int[][] mns = {{1, 2}, {3, 4}, {5,6}};
		
		// 다차원 배열 선언 - II	(3 x 2)
//		int[][] mns = new int[3][2];
//		mns[0][0] = 1;
//		mns[0][1] = 2;
//		mns[1][0] = 3;
//		mns[2][0] = 4;
//		mns[2][1] = 5;
		
		// 다차원 배열 선언 - III	(3 x 2)
//		int[][] mns = new int[][] {{1, 2}, {3, 4}, {5, 6}};
//		
////		System.out.println("mns: " + mns);		// [[I@5aaa6d82
////		System.out.println("mns[0]: " + mns[0]);		// [I@198e2867
////		System.out.println("mns[0][0]: " + mns[0][0]);		// 1
//		
//		for (int i = 0; i < mns.length; i++) {
//			for (int j = 0; j < mns[i].length; j++) {
//				System.out.println(mns[i][j]);
//			}
//		}
		
		// 0: 1
		// 1: 2
		// 2: 3
		
//		int[][] mns2 = new int[3][];
//		mns2[0] = new int[1];
//		mns2[1] = new int[2];
//		mns2[2] = new int[3];
//		
//		// {{1}, {2, 3} {4,5,6}}
//		
//		mns2[0][0] = 1;
//		mns2[1][0] = 2;
//		mns2[1][1] = 3;
//		mns2[2][0] = 4;
//		mns2[2][1] = 5;
//		mns2[2][2] = 6;
//		
//		for (int i = 0; i < mns2.length; i++) {
//			int[] tempArr = mns2[i];
//			for (int j = 0; j < tempArr.length; j++) {
//				System.out.printf("mns2[%d][%d] = %d \n", i, j, tempArr[j]);
//			}
//		}
		
		// ----------------------------------------------------------------------------------
		
		int[][] scores = {
				{85, 90, 90}, 	// 1학기
				{90,80,85}, 	// 2학기
				{90, 90, 100}, 	// 3학기
				{95, 85, 100}	// 4학기
				};	
		
		for (int i = 0; i < scores.length; i++) {
			int[] tempArr = scores[i];
			
			for (int j = 0; j < tempArr.length; j++) {
				
				String str = "";
				switch (j) {
				case 0:
					str = "국어";
					break;
					
				case 1:
					str = "영어";
					break;
					
				case 2:
					str = "수학";
					break;
				}
				
				System.out.printf("%d학기 %s 점수: %d \n", (i+1), str, scores[i][j]);
				
			}
			
		}
		
		
	}
	
}
