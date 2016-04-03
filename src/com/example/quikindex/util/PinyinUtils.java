package com.example.quikindex.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {

	/**
	 * 根据传入的字符串（包含汉字），得到拼音
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinyin(String str) {

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		StringBuilder sb = new StringBuilder();

		char[] charArray = str.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (Character.isWhitespace(c)) {
				// 如果是空格，跳过
				continue;
			} else if (c > -127 && c < 127) {
				// 非汉字
				sb.append(c);
			} else {
				String s = "";
				try {
					// 通过char得到拼音集合（多音字）
					s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					sb.append(s);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
					sb.append(s);
				}
			}

		}
		return sb.toString();
	}

}
