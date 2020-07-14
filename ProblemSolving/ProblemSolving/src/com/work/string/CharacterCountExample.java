package com.work.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CharacterCountExample {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input Here");
		String str = reader.readLine();
		int maxCount = 0;
		char result = ' ';
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i=0; i<str.length();i++) {
			char c = str.charAt(i);
			if(map.containsKey(c)) {
				int count = map.get(c);
				map.put(c, count+1);
			}else {
				map.put(c, 1);
			}
		}
		
		for(Map.Entry<Character, Integer> e: map.entrySet()) {
			if(maxCount< e.getValue()) {
				maxCount= e.getValue();
				result = e.getKey();
			}
		}
		
		System.out.println("max number of repeated character is :" +result);

	}

}
