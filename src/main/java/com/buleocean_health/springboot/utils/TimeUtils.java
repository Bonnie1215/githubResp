package com.buleocean_health.springboot.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.format.datetime.DateFormatter;

import com.buleocean_health.springboot.common.em.TimeType;

public class TimeUtils {
	
	private static final ZoneId SYSTEM_ZONE_ID = ZoneId.systemDefault();
	
	/**
	 * 获取默认格式的当前时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getTime(){
		return getTime(TimeType.yyyy_MM_dd_HH_mm_ss);
	}

	/**
	 * 获取默认格式的当前时间戳 yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String getTimesTamp(){
		return getTime(TimeType.yyyy_MM_dd_HH_mm_ss_SSS);
	}
	
	/**
	 * 获得当前时间 yyyy-MM-dd
	 */
	public static String getTimeToDay() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA));
	}

	/**
	 *	根据对应枚举类型的格式获取时间
	 */
	public static String getTime(TimeType timeType){
		if(timeType.type() == 1)
			return LocalDateTime.now().format(patternFormat(timeType));
		else if(timeType.type() == 2)
			return LocalDate.now().format(patternFormat(timeType));
		else
			return LocalTime.now().format(patternFormat(timeType));
			
	}
	
	/**
	 * 把字符串类型的时间格式化为Date类型
	 */
	public static Date getDateByString(String time, TimeType timeType){
		if(timeType.type() == 1)
			return Date.from(LocalDateTime.parse(time, patternFormat(timeType)).atZone(SYSTEM_ZONE_ID).toInstant());
		else if(timeType.type() == 2)
			return Date.from(LocalDate.parse(time, patternFormat(timeType)).atStartOfDay().atZone(SYSTEM_ZONE_ID).toInstant());
		else
			return Date.from(LocalTime.parse(time, patternFormat(timeType)).atDate(LocalDate.now()).atZone(SYSTEM_ZONE_ID).toInstant());
	}
	
	/**
	 * 把Date类型的时间格式化为String类型
	 */
	public static String getStringByDate(Date time, TimeType timeType){
		LocalDateTime localDateTime = LocalDateTime.ofInstant(time.toInstant(), SYSTEM_ZONE_ID);
		if(timeType.type() == 1)
			return localDateTime.format(patternFormat(timeType));
		else if(timeType.type() == 2)
			return localDateTime.toLocalDate().format(patternFormat(timeType));
		else
			return localDateTime.toLocalTime().format(patternFormat(timeType));
	}
	
	/**
	 * 比较两个时间的大小 
	 * <pre>
	 * 	start <  end   ==> true
	 * 	end   >  start ==> false
	 * </pre>
	 * @param start	开始时间
	 * @param end	结束时间
	 */
	public static boolean compare(String start, String end, TimeType type){
		return compare(getDateByString(start, type), getDateByString(end, type));
	}
	
	/**
	 * 比较两个时间的大小 
	 * <pre>
	 * 	start <  end   ==> true
	 * 	end   >  start ==> false
	 * </pre>
	 * @param start	开始时间
	 * @param end	结束时间
	 */
	public static boolean compare(Date start, Date end){
		return end.getTime() >= start.getTime();
	}
	
	/**
	 * 与系统当前时间进行比较
	 * <pre>
	 * 	time  <  now  ==> true
	 * 	now   >  time ==> false
	 * </pre>
	 * @param time
	 * @return
	 */
	public static boolean compare(Date time){
		return System.currentTimeMillis() >= time.getTime();
	}
	
	/**
	 * 与系统当前时间进行比较
	 * <pre>
	 * 	time  <  now  ==> true
	 * 	now   >  time ==> false
	 * </pre>
	 * @param time
	 * @return
	 */
	public static boolean compare(String time, TimeType type){
		return System.currentTimeMillis() >= getDateByString(time, type).getTime();
	}
	
	/**
	 * 验证传入的日期是否符合指定格式 并验证是否合法
	 * @param time
	 * @param type
	 * @return
	 */
	public static boolean validate(String time, TimeType type){
		try {
			//TODO 试试能不能用JDK8模式改进一下~
			SimpleDateFormat dateFormat = new SimpleDateFormat(type.value());
			dateFormat.setLenient(false);
			dateFormat.parse(time);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	//转化时间类型
	private static DateTimeFormatter patternFormat(TimeType type){
		return DateTimeFormatter.ofPattern(type.value());
	}
	
	// 获得年份
	public static int getYear(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 校验日期格式 yyyy.MM.dd
	 * @param date
	 * @return
	 */
	public static boolean checkDate(String date) {
		boolean flag = false;
		String eL = "[0-9]{4}.[0-9]{2}.[0-9]{2}";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			flag = true;
		} 
		return flag;
	}
	
}
