package com.office.ex.multiargs;

import com.office.ex.overloading.TotalAverage;

public class MainClass {

	public static void main(String[] args) {
		
		Score score = new Score();
//		score.printStudentsTotalAndAverageScore();
//		score.printStudentsTotalAndAverageScore(80, 90);
//		score.printStudentsTotalAndAverageScore(80, 90, 100);
//		score.printStudentsTotalAndAverageScore(80, 90, 100, 85);
		
		score.printStudentsTotalAndAverageScore(3, 95, 90, 100);
		
		// 길동학생이 사용하고 싶다. 총합 과 평균
		/*
		Score score2 = new Score();
		int[] scores = {10, 20, 30};
		score2.getTotalScore(scores);
		score2.getAverageScore(scores);
		*/
		
		int[] scores = {10, 20, 30};
		TotalAverage.getTotalScore(scores);
		TotalAverage.getAverageScore(scores);
		
//		Score.getTotalScore(scores);		// 총합
//		Score.getAverageScore(scores);		// 평균
		
	}
	
}
