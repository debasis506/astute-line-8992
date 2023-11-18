package com.masai.entity;

public class SessionStd {

	private static Student currentStd;

	public static Student getCurrentStd() {
		return currentStd;
	}

	public static void setCurrentStd(Student std) {
		currentStd = std;
	}

}
