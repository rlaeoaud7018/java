package com.office.ex.multiargs;

import com.office.ex.overloading.TotalAverage;

public class Score {

	// 학생들의 시험 점수를 출력
	// 총점 & 평점
	
	/*
	// 학생 3명
	public void printStudentsTotalAndAverageScore(
			int score1, 
			int score2, 
			int score3) {
		
		int totalScore = score1 + score2 + score3;
		System.out.println("총점: " + totalScore);
		
		float averageScore = totalScore / 3;
		System.out.println("평균: " + averageScore);
		
	}
	*/
	
	public void printStudentsTotalAndAverageScore(int classNo, int ...scores) {
		
//		int totalScore = 0;
//		for (int i = 0; i < scores.length; i++) {
//			totalScore += scores[i];
//		}
		
		System.out.println(classNo + "반 총점: " + TotalAverage.getTotalScore(scores));
		
//		float averageScore = 0.0f;
//		if (scores.length > 0) {
//			averageScore = totalScore / scores.length;
//		}
		
		System.out.println(classNo + "반 평균: " + TotalAverage.getAverageScore(scores));
		
	}
	
}
	
	
	
//	public void printStudentsTotalAndAverageScore(String classNo, int ...scores) {
//		
//		int totalScore = 0;
//		for (int i = 0; i < scores.length; i++) {
//			totalScore += scores[i];
//		}
//		
//		System.out.println(classNo + "반 총점: " + totalScore);
//		
//		float averageScore = 0.0f;
//		if (scores.length > 0) {
//			averageScore = totalScore / scores.length;
//		}
//		
//		System.out.println(classNo + "반 평균: " + averageScore);
//		
//	}

