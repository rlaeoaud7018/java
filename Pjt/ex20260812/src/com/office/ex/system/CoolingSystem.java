package com.office.ex.system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CoolingSystem {
	
	private boolean power;			// 전원(외부 접근불가)
	private int targetTemperature;		// 희망 온도(외부 접근불가)
	private String dateOfMenuFacture;	// 제조일(외부 접근불가)
	private String color;				// 색상(외부 접근불가)
	
	public CoolingSystem(String color) {
		
		power = false;
		targetTemperature = 18;
		dateOfMenuFacture = getSystemDateTime();
		this.color = color;
	}
	
	// 기능(메서스)
	// 에어컨의 전체 정보를 출력하는 메서드
	public void printSystemInfomation() {
		System.out.println("========== SystemInformation ==========");
		System.out.println("Power: " + isPower());
		System.out.println("TargetTemperature: " + getTargetTemperature());
		System.out.println("DateofMenufacture: " + getDateOfMenuFacture());
		System.out.println("Color: " + getColor());
		System.out.println("=======================================");
		
	}
	
	
	
	public boolean isPower() {
		return power;
	}



	public void setPower(boolean power) {
		this.power = power;
	}



	//기능(메서스)
	public int getTargetTemperature() {
		return targetTemperature;
	}

	public void setTargetTemperature(int targetTemperature) {
		
		if (!power) power = true;
		
		if(targetTemperature < 18) {
			targetTemperature = 18;
		} else if(targetTemperature > 30) {
			targetTemperature = 30;
		}
		
		this.targetTemperature = targetTemperature;
	}
	
	
	
	/*
	// name convention
	// 조회 = get(getter)
	public int getTargetTemperature() {
		return this.targetTemperature;
	}
	// 설정 = set(setter)
	public void setTargetTemperature(int targetTemperature) {
		this.targetTemperature = targetTemperature;
	}
	*/
	
	
	
	
	public String getDateOfMenuFacture() {
		return dateOfMenuFacture;
	}

//	public void setDateOfMenuFacture(String dateOfMenuFacture) {
//		this.dateOfMenuFacture = dateOfMenuFacture;
//	}

	public String getColor() {
		return color;
	}

//	public void setColor(String color) {
//		this.color = color;
//	}

	// 현재 시스템 날짜, 시간 구하기 메서드
	private String getSystemDateTime() {
		
		LocalDateTime now = 
				LocalDateTime.now();		// 현재 시스템의 날짜와 시간 구하기
		String dateTime = 
				now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));	// 날짜와 시간 형식을 맞춤
		
		return dateTime;
		
	}
	
}
