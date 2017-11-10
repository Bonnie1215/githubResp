package com.buleocean_health.springboot.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *	字符串处理的相关类
 *	@date 2017年03月14日 10点42分00秒
 */
public class StringUtils {
	
	// 特殊字符
	private static Pattern specialStr = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
	
	/**
	 * 格式化字符串, 如果为null, 返回 "null"
	 * @param str	
	 * @return
	 */
	public static String value(String str){
		return isEmpty(str) ? "" : str;
	}

	/**
	 * 格式化字符串, 非String类型返回toString(), 如果为null, 返回 "null"
	 * @param obj	
	 * @return
	 */
	public static String value(Object obj){
		return isEmpty(obj) ? "" : obj.toString();
	}
	
	/**
	 * 检测是否为null, 如果是String, 检测是否为""
	 * @param str
	 * @return	""或者null 返回true
	 */
	public static boolean isEmpty(String str){
		return str == null || "".equals(str);
	}
	
	/**
	 * 检测多个值是否为null, 任何一个不符合条件返回false, 如果是String, 检测是否为""
	 * @param arr
	 * @return	""或者null 返回true
	 */
	public static boolean isEmpty(String... arr){
		if(arr == null || arr.length == 0) return true;
		for (String str : arr) {
			if(isEmpty(str)) return true;
		}
		return false;
	}
	
	/**
	 * 检测是否为null
	 * @param obj
	 * @return	null 返回true
	 */
	public static boolean isEmpty(Object obj){
		return obj == null || "".equals(obj.toString());
	}

	/**
	 * 检测多个值是否为null, 任何一个不符合条件返回false, 如果是String, 检测是否为""
	 * @param arr
	 * @return	""或者null 返回true
	 */
	public static boolean isEmpty(Object... arr){
		if(arr == null || arr.length == 0) return true;
		for (Object obj : arr) {
			if(isEmpty(obj)) return true;
		}
		return false;
	}
	
	/**
	 * 检查一个字符串中是否包含多个连续相同字符
	 * @param targetStr	目标字符串
	 * @param charNum	被检测数量
	 * @return	包含连续相同字符 true, 目标字符串为null或者"",false, 其他false
	 */
	public static boolean containsContinuousSameChar(String targetStr, int charNum){
		if(isEmpty(targetStr)) return false;
		int count = 0;
		char startChar = 0;
		for (int i = 0; i < targetStr.length(); i++) {
			char _tmp = targetStr.charAt(i);
			if(i == 0) startChar = _tmp;
			if(startChar == _tmp) count++;
			else count = 1;
			startChar = _tmp;
			if(count >= charNum) return true;
		}
		return false;
	}

	/**
	 * 检查一个字符串中是否包含多个升序或降序字符
	 * @param targetStr	目标字符串
	 * @param charNum	被检测数量
	 * @return	包含连续相同字符 true, 目标字符串为null或者"",false, 其他false
	 */
	public static boolean containsContinuousLiftChar(String targetStr, int charNum){
		if(isEmpty(targetStr)) return false;
		int count = 0;
		char startChar = 0;
		boolean liftFlag = true;
		for (int i = 0; i < targetStr.length(); i++) {
			char _tmp = targetStr.charAt(i);
			if(i == 0) startChar = _tmp;
			if(startChar == _tmp-1) {
				if(liftFlag) count++;
				else count = 1;
			}else if(startChar == _tmp+1){
				if(liftFlag){
					liftFlag = false;
					count = 1;
				}
				if(liftFlag) count = 1;
				else count++;
			}else{
				liftFlag = true;
				count = 1;
			}
			startChar = _tmp;
			if(count >= charNum) return true;
		}
		return false;
	}

	/**
	 * 检测字符串数组中是否包含目标字符串
	 * @param str 指定字符串数组
	 * @param targetStr 目标字符串
	 * @return
	 */
	public static boolean containsStr(String[] str, String targetStr) {
		for (String s : str) {
			if (targetStr.contains(s) && targetStr.indexOf(s)!=0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 *	unicode 转中文
	 */
	public static String unicodeToString(String str) {
	    Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
	    Matcher matcher = pattern.matcher(str);
	    char ch;
	    while (matcher.find()) {
	        ch = (char) Integer.parseInt(matcher.group(2), 16);
	        str = str.replace(matcher.group(1), ch + "");
	    }
	    return str;
	}

	/**
	 * 中文转 unicode
	 */
	public static String stringToUnicode(String cn) {
		char[] chars = cn.toCharArray();
		String returnStr = "";
		for (int i = 0; i < chars.length; i++) {
			returnStr += "%u" + Integer.toString(chars[i], 16);
		}
		return returnStr;
	}

	/**
	 * 字符串做encode编码
	 */
	public static String encode(String str) throws Exception{
		return URLEncoder.encode(str, "UTF-8");
	}

	/**
	 * 字符串做decode解码
	 */
	public static String decode(String str) throws Exception{
		return URLDecoder.decode(str, "UTF-8");
	}
	
	/**
	 * 检查email是否是邮箱格式，返回true表示是，反之为否
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (isEmpty(email)) {
			return false;
		}
		Pattern regex = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * 检验手机号码格式
	 * @param mobiles
	 * @return
	 */
	 public static boolean isMobile(String mobiles){
		if (isEmpty(mobiles)){
			return false;
		}
        Pattern p = Pattern.compile("^((13[0-9])|(14[5|7|9])|(15[^4,\\D])|(17[0|1|3-8])|(18[0-9]))\\d{8}$");     
        Matcher m = p.matcher(mobiles);      
        return m.matches();     
    } 
	 
	 /**
	 * 检验银行卡格式
	 * @param mobiles
	 * @return
	 */
	 public static boolean isBankCard(String bankCard){
		if (isEmpty(bankCard)){
			return false;
		} 
        Pattern p = Pattern.compile("^[0-9]{15,19}$");
        Matcher m = p.matcher(bankCard);
        return m.matches();
     } 
	 
	/**
	 * 检查身份证的格式，返回true表示是，反之为否
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isCard(String cardId) {
		if (isEmpty(cardId)) {
			return false;
		}
		//身份证正则表达式(15位) 
		Pattern isIDCard1=Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"); 
		//身份证正则表达式(18位) 
		Pattern isIDCard2=Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$"); 
		Matcher matcher1= isIDCard1.matcher(cardId);
		Matcher matcher2= isIDCard2.matcher(cardId);
		return matcher1.matches()||matcher2.matches();
	}

	/**
	 * 去除字符串中的特殊字符
	 * @param str
	 * @return
	 */
	public static String removeSpecialStr(String str) {
		Matcher m = specialStr.matcher(str);
		return m.replaceAll("").trim();
	}

}
