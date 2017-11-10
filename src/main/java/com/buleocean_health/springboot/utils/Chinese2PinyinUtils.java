package com.buleocean_health.springboot.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

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
	 * 将汉语翻译成全拼，多音字只取第一个
	 * @param chineseName 例如：林朝章(linzhaozhang)
	 * @return linchaozhang 
	 */
	public static String toAllPinyin(String chineseName) {
		// 去掉字符中的特殊字符
		chineseName = StringUtils.removeSpecialStr(chineseName);
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<chineseName.length(); i++) {
			char c = chineseName.charAt(i);
			if (c<=255) {
				sb.append(c);
			} else {
				String pinyin = null;
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
					if (!(pinyinArray==null || pinyinArray.length==0)) {
						pinyin = pinyinArray[0];
						if (pinyin!=null) {
							sb.append(pinyin);
						}
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将汉语翻译成缩写，多音字只取第一个
	 * @param chineseName 例如：林朝章(lcz)
	 * @return 例如：lcz 
	 */
	public static String toAbbPinyin(String chineseName) {
		// 去掉字符中的特殊字符
		chineseName = StringUtils.removeSpecialStr(chineseName);
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<chineseName.length(); i++) {
			char c = chineseName.charAt(i);
			if (c<=255) {
				sb.append(c);
			} else {
				char pinyin;
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
					if (!(pinyinArray==null || pinyinArray.length==0)) {
						pinyin = pinyinArray[0].charAt(0);
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
	 * @return 	例如：[linchaozhang, linzhaozhang]
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static Set<String> toPinyinFull(String chineseName) {
		// 去掉字符中的特殊字符
		chineseName = StringUtils.removeSpecialStr(chineseName);
		Set<String> sbList = new HashSet<>();
		for (int i=0; i<chineseName.length(); i++) {
			char c = chineseName.charAt(i);
			if (c<=255) {
				if (sbList.isEmpty()) {
					sbList.add(c+"");
				} else {
					Set<String> sbListCopy = new HashSet<>();
					sbListCopy.addAll(sbList);
					sbList.clear();
					for (String sbcopy : sbListCopy) {
						sbList.add(sbcopy+c);
					}
				}
			} else {
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
					if (!(pinyinArray==null || pinyinArray.length==0)) {
						if (sbList.isEmpty()) {
							for (int m=0; m<pinyinArray.length; m++) {
								sbList.add(pinyinArray[m]);
							} 
						} else {
							Set<String> sbListCopy = new HashSet<>();
							sbListCopy.addAll(sbList);
							sbList.clear();
							for (String sbcopy : sbListCopy) {
								for (int m=0; m<pinyinArray.length; m++) { 
									sbList.add(sbcopy+pinyinArray[m]);
								}
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
	
	/**
	 * 将汉语翻译成拼音，多音字也拼接成拼音缩写
	 * @param chineseName 例如：林朝章(lzz)
	 * @return 	例如：[lcz, lzz]
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static Set<String> toPinyinNotFull(String chineseName) {
		// 去掉字符中的特殊字符
		chineseName = StringUtils.removeSpecialStr(chineseName);
		Set<String> sbList = new HashSet<>();
		for (int i=0; i<chineseName.length(); i++) {
			char c = chineseName.charAt(i);
			if (c<=255) {
				if (sbList.isEmpty()) {
					sbList.add(c+"");
				} else {
					Set<String> sbListCopy = new HashSet<>();
					sbListCopy.addAll(sbList);
					sbList.clear();
					for (String sbcopy : sbListCopy) {
						sbList.add(sbcopy+c);
					}
				}
			} else {
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
					if (!(pinyinArray==null || pinyinArray.length==0)) {
						if (sbList.isEmpty()) {
							for (int m=0; m<pinyinArray.length; m++) {
								sbList.add(pinyinArray[m].charAt(0)+"");
							}
						} else {
							Set<String> sbListCopy = new HashSet<>();
							sbListCopy.addAll(sbList);
							sbList.clear();
							for (String sbcopy : sbListCopy) {
								for (int m=0; m<pinyinArray.length; m++) { 
									sbList.add(sbcopy+pinyinArray[m].charAt(0));
								}
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
	
	/**
	 * 将汉语翻译成拼音，中文全拼和缩写
	 * @param chineseName 例如：林朝章
	 * @return  例如：lcz,linchaozhang,linzhaozhang,lzz
	 */
	public static String toPinyinAll(String chineseName) {
		Set<String> pinyinFull = toPinyinFull(chineseName);
		pinyinFull.addAll(toPinyinNotFull(chineseName));
		return String.join(",", pinyinFull);
	}
	
	public static void main(String[] args) {
		// 林朝章   123 赵保成（大）122
		System.out.println("全拼："+toAllPinyin("林朝章"));
		System.out.println("缩写："+toAbbPinyin("林朝章"));
		System.out.println("所有全拼："+toPinyinFull("林朝章"));
		System.out.println("所有缩写："+toPinyinNotFull("林朝章"));
	}
	
}
