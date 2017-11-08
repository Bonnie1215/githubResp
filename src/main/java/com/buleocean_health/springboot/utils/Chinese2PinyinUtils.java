package com.buleocean_health.springboot.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
* 将中文转化成拼音工具类 ，使用Pinyin4j。
* @author huyanqiu
* @version 2017年11月8日 下午3:16:48
*/
public class Chinese2PinyinUtils {
	
	private static HanyuPinyinOutputFormat PINYIN_FORMAT;  
    
    static {  
        PINYIN_FORMAT = new HanyuPinyinOutputFormat();  
        PINYIN_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        PINYIN_FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);  
    }
	
	/**
	 * 将汉语翻译成拼音，多音字只取第一个
	 * @param chineseName 例如：林朝章(linzhaozhang)
	 * @return linchaozhang 
	 */
	public static String toPinyin(String chineseName) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<chineseName.length(); i++) {
			char c = chineseName.charAt(i);
			if (c<=255) {
				sb.append(c);
			} else {
				String pinyin = null;
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
					pinyin = pinyinArray[0];
					if (pinyin!=null) {
						sb.append(pinyin);
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将汉语翻译成拼音，多音字也拼接成拼音
	 * @param chineseName 例如：林朝章(linzhaozhang)
	 * @return 	[linchaozhang, linzhaozhang]
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static List<StringBuffer> toPinyinAll(String chineseName) throws IllegalAccessException, InvocationTargetException {
		List<StringBuffer> sbList = new LinkedList<>();
		for (int i=0; i<chineseName.length(); i++) {
			char c = chineseName.charAt(i);
			if (c<=255) {
				if (sbList.isEmpty()) {
					sbList.add(new StringBuffer(c));
				} else {
					sbList.forEach(sb->{sb.append(c);});
				}
			} else {
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
					if (sbList.isEmpty()) {
						for (int m=0; m<pinyinArray.length; m++) {
							sbList.add(new StringBuffer(pinyinArray[m]));
						} 
					} else {
						List<StringBuffer> sbListCopy = new LinkedList<>();
						sbListCopy.addAll(sbList);
						sbList.clear();
						for (StringBuffer sbcopy : sbListCopy) {
							for (int m=0; m<pinyinArray.length; m++) {
								sbList.add(new StringBuffer(new StringBuffer().append(sbcopy).append(pinyinArray[m])));
							}
						}
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
		}
		return sbList;
	}
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination, IllegalAccessException, InvocationTargetException {
//		System.out.println(toPinyin("林朝章"));		
//		System.out.println(toPinyinAll("林朝章"));
		System.out.println(toPinyinAll("林朝章"));
	}
	
}
