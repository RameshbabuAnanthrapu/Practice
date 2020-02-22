package com.work.string;



public class TestSubString {
	
	
	/* 
	 * This method generates all possible substrings that can be 
	formed from given String.
	* Here I am printing the count and possible substrings.
	
	*/
	
	
	public void printAllSubStrings(String s1){
		
		int subStringCount = 0;
		for (int i=0; i< s1.length();i++) {
			
			 for(int j=i;j<s1.length();j++) {
				 System.out.println("start and end  indexes " +i +","+j);
				 System.out.println( s1.substring(i, j));
				 subStringCount++;
			 }
			
		}
		
		System.out.println("Number of substrings are::" +subStringCount);
		
	}

	
}
