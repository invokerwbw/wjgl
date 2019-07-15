package com.inspur.tax.query.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitKeyWord {
	public static Map<String, Object> splitBzhAndZwmc(String keywords) {
		
	/*	检索数据样例
		String inputStr1 = "GB/T 1234-5 标准化导则";
		String inputStr2 = "GB 1234~5标准化导则";
		String inputStr3 = "GB1.234~5 标准化导则 第五部分";
		String inputStr4 = "ASTM 4754:2016~5 标准化导则 第五部分";
		String inputStr5 = "GB/T1234";
		String inputStr6 = "1234";
		String inputStr7 = "ANSI ISO/IEC 2382-7-1989  玻璃的结构力学性能试验";
		String inputStr8 = "AAMA CW-12-1984      玻璃的结构力学性能试验";
		String inputStr9= "标准化导则 第五部分";
		String inputStr10 = "标准化导则";
		String inputStr11 = "GB";*/
		
		String regex = "^[a-zA-Z0-9\\(\\)\\.~/\\s:-]+";
		
		Pattern mPattern = Pattern.compile(regex);
		Matcher mMatcher = mPattern.matcher(keywords);
		
		String bzh = ""; //未加工的标准号
		String keyword = ""; //中文关键词
		
		while (mMatcher.find()) {
			bzh += mMatcher.group();
		}
		
		keyword = keywords.substring(bzh.length());
		String[] zwmc = keyword.split("\\s+");
		
		System.out.print("关键词：");
		for(String s : zwmc){
			System.out.print("%"+s+"%   ");
		}
		
		//处理标准组织，遇到空格截止
		String regex_a006 = "^[a-zA-Z\\(\\)/]+";
		
		Pattern mPattern2 = Pattern.compile(regex_a006);
		Matcher mMatcher2 = mPattern2.matcher(bzh.trim());
		
		String a006_1 = ""; //标准组织
		String a006_2 = ""; //国内推荐、强制处理;国外不处理
		while (mMatcher2.find()) {
			a006_1 += mMatcher2.group();
		}
		if(a006_1.contains("/T")){
			a006_2 = a006_1.replace("/T", "");
		}else if(!"".equals(a006_1) && !a006_1.contains("/")){ //后条件排除ISO/IEC 情况，只处理国内标准
			a006_2 = a006_1+"/T";
		}
		//处理标准序号，截掉标准组织。匹配字母、数字、空格：ANSI ISO/IEC 2382-7-1989
		bzh = bzh.substring(a006_1.length()).trim();
		String regex_xh = "[a-zA-Z0-9\\(\\)\\s/\\.~:-]+";
				
		Pattern mPattern3 = Pattern.compile(regex_xh);
		Matcher mMatcher3 = mPattern3.matcher(bzh.trim());
		
		String bzxh = ""; //取标准序号
		while (mMatcher3.find()) {
			bzxh += mMatcher3.group();
		}
		
		String a100_1 = null;
		String a100_2 = null;
		
		if(bzxh != null && !"".equals(bzxh) && a006_1 != null && !"".equals(a006_1)){
			a100_1 = a006_1 +" " + bzxh;
			a100_2 = a006_2 +" " + bzxh;
		}else if(bzxh != null && !"".equals(bzxh) && (a006_1 == null || "".equals(a006_1))){
			a100_1 =  bzxh;
			a100_2 =  bzxh;
		}else{
			a100_1 = a006_1;
			a100_2 = a006_2;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a100_1", a100_1);
		map.put("a100_2", a100_2);
		map.put("keyword", zwmc);
		
		return map;
	}
}