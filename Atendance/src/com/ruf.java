package com;

import java.util.Calendar;

public class ruf {
	public static void main(String[] args) {
		Calendar myDate = Calendar.getInstance(); // set this up however you need it.
		int dow = myDate.get(Calendar.DAY_OF_WEEK);
		if (dow >= Calendar.MONDAY && dow <= Calendar.FRIDAY) {
			System.out.println("if"+dow);
		}
		else {
			System.out.println("else"+dow);
		}
	}

}
