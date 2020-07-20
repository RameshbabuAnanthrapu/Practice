package com.work.collection.comonElements;

import java.util.*;
import java.util.stream.Collectors;

public class RepeatedElementsWithCountJ8 {
	
	public static void main(String args[]) {
		
		List<String> list =  new ArrayList<>();	
		list.add("green");
		list.add("yellow");
		list.add("black");
		list.add("green");
		list.add("yellow");
		list.add("black");
		HashMap<String, Long> map =  new HashMap<>();
		
		/*using map.compute
		 * 
		 * */
		list.forEach(e-> map.compute(e, (k,v)-> v==null?1L:v+1L));
		map.forEach((k,v)-> System.out.println("key :"+k+" count is :"+v));
		Map<String, Long> countMap = list.stream().collect(Collectors.groupingBy(k->k, Collectors.counting()));
		countMap.forEach((k,v)-> System.out.println("key :"+k+" count is :"+v));
		
		
	}

}
