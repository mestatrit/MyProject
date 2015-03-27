package com.sitemap.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTool {
	
	public static String getTimeForMat(String format){
		if("".equals(format) || format==null) format="yyyy-MM-dd HH:mm:ss";
		Date date=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	public static String getTimeZhuanHua(String format,String str){
		if("".equals(format) || format==null) format="yyyy-MM-dd HH:mm:ss";
		Date date=new Date(Long.parseLong(str)); 
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		return dateFormat.format(date);
	} 

	public static Date dataFormat(String time,String format){
			try {
				if(format==null || "".equals(format)) format="yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat dateFormat=new SimpleDateFormat(format);
				return dateFormat.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
				return new Date();
			}
	}
	

	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
	public static Date addMinutes(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.MINUTE, num);
		return startDT.getTime();
	}
	
	public static Date addSecend(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.SECOND, num);
		return startDT.getTime();
	}
	
	
	public static Date addMonth(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date); 
		startDT.add(Calendar.MONTH, num);
		return startDT.getTime();
	}
	
	
	public static String dataToStr(Date date,String format){
		if(format==null || "".equals(format)) format="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		return dateFormat.format(date);
	} 
	
	/**

	* 得到指定月的天数

	* */

	public static int getMonthLastDay(int year, int month){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	 
	public static int getFieldNum(Date date, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		return cal.get(field); 
	}
	
	public static void main(String[] args) {
		Date date=new Date();
		Date date2=TimeTool.addMinutes(date, 1);
		long cha=(date.getTime()-date2.getTime())/1000;
		System.out.println(cha);
		
		System.out.println(TimeTool.dataToStr(date, null));
		date=TimeTool.addSecend(date, 70);
		System.out.println(TimeTool.dataToStr(date, null));
		String t_filename=TimeTool.dataToStr(TimeTool.addDay(new Date(), -1), "yyyyMMdd");  
		System.out.println(t_filename);
	}
 
}
