package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
    public static Map<String, Integer> map=new IdentityHashMap<>();
    public static String searchString;

    public static void find(String string){
		List<Map.Entry<String, Integer>> list=new ArrayList<Map.Entry<String,Integer>>();
		boolean flag;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			flag=true;
			for (char ch : string.toCharArray()) {
				if(entry.getKey().indexOf(ch)==-1){
					flag=false;
					break;
				}
			}
			if(flag){
				list.add(entry);
			}
		}
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> e1,Entry<String, Integer> e2){
				int result=0;
				if(e1.getValue()>e2.getValue()){
					result=-1;
				}
				else if (e1.getValue()==e2.getValue()) {
					result=0;
				}
				else {
					result=1;
				}
				return result;
			}
		});
        for (Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
    }
    
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        List<String> list=new ArrayList<>();
        while(true){
        	String string=scanner.nextLine();
        	if(string.indexOf(" ")!=-1){
        		list.add(string);
        	}
        	else {
				searchString=string;
				break;
			}
        }
    	long start=new Date().getTime();
    	for (String string : list) {
        	if(string.indexOf(" ")!=-1){
        		map.put(string.split(" ")[0], Integer.parseInt(string.split(" ")[1]));
        	}
        	else {
				searchString=string;
				break;
			}
		}
        find(searchString);
        long stop=new Date().getTime();
        System.out.println(map.size());
        System.out.println(stop-start);
    }

}
