package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {	
	
	public static String[] findData(String data) {
		String[] result = new String[2];
		String AP="";
		String mac="";
		String pat="";
		if(data.indexOf("Client(")!=-1){
			pat="(Client\\(\\S+\\))";
			mac=find(data, pat).split("[\\(\\)]+")[1];
			pat="(AP\\s?\\(\\S+\\))";
			AP=find(data, pat).split("[\\(\\)]+")[1];
		}
		else if(data.indexOf("MAC Adress")!=-1){
			pat="(MAC Address[:]?\\s{1}\\S+)";
			mac=find(data, pat).split("[\\s,]")[2];
			pat="(AP Name \\S+)";
			AP=find(data, pat).split("[\\s,]")[2];
		}
		result[0]=AP;
		result[1]=mac;
		return result;
	}
	
	private static String find(String str,String pattern) {

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		String result="";
		while (m.find()) {
			result=m.group();
		}
		return result;
	}

}
