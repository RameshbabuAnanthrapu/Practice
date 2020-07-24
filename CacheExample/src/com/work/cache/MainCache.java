package com.work.cache;

public class MainCache {

	public static void main(String[] args) {
		
		
		ICache<String, Integer> cacheIn = new CacheImpl<>();
		cacheIn.put("Hi", 1);
		cacheIn.put("Hello", 2);
		System.out.println("value of key Hello is"+cacheIn.get("Hello"));
		
		
		
		

	}
	
	
}
