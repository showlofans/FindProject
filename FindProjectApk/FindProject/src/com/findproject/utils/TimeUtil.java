package com.findproject.utils;

import java.util.Calendar;

public class TimeUtil {
	/*
	 * ��ȡ��ǰϵͳʱ�书��
	 * 
	 * */
	public static String getDate(){
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		int minute=calendar.get(Calendar.MINUTE);
		int hour=calendar.get(Calendar.HOUR_OF_DAY);
		int second=calendar.get(Calendar.SECOND);
		String date=""+year+"/"+(month+1)+"/"+(day)+" "+((hour<10)?"0":"")+hour+":"+((minute<10)?"0":"")+minute+":"+((second<10)?"0":"")+second;
		return date;
		
	}
	


}
