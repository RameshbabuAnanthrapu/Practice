package com.work.string;



public class TestSubString {
	
	
	/* 
	 * This method generates all possible substrings that can be 
	formed from given String.
	* Here I am printing the count and possible substrings.
	
	*/
	
	String tmp ;
	int distCount = 0;
	int longestSubStringLength = 0;
	String longestSubString;
	
	
	public void printAllSubStrings(String s1){
		
		int subStringCount = 0;
		for (int i=0; i< s1.length();i++) {
			
			 for(int j=i;j<s1.length();j++) {
				 System.out.println("start and end  indexes " +i +","+j);
				 System.out.println( s1.substring(i, j));
				  printLongestSubString(s1.substring(i, j));
				 subStringCount++;
			 }
			
		}
		
		System.out.println("Number of substrings are::" +subStringCount);
		System.out.println("longest substring with two distint chars" +longestSubString);
		
	}
	
	private void  printLongestSubString(String s2) {
		
		char[] charArray = s2.toCharArray();
		
		
		
		for (int i=0;i<s2.length();i++) {
			
			for(int j=i+1; j< s2.length(); j++) {
				tmp = String.valueOf(s2.charAt(j-1));
				if(!String.valueOf(charArray[i]).equals(String.valueOf(charArray[j]))
						&& tmp.equals(String.valueOf(charArray
								[j]))) 
				{
					tmp = String.valueOf(charArray[j]);
					distCount++;	
				}
				
			}
			
		}
		
		if(distCount ==2  && longestSubStringLength< s2.length()) {
			
			longestSubStringLength = s2.length();
			longestSubString = s2;
		}
		
		
		
		
		 
		
		
		
		
	}
	
	public void testStringWithSpaces(String st){
		
		char[] input =  st.toCharArray();
		char[] output = new char[input.length];
		
		for(int i=0; i<input.length;i++) {
			if(input[i] == ' ') {
				output[i] = ' ';
			}
		}
		
		int j = input.length-1;
		for(int i =0; i<input.length;i++) {
			
			if(input[i]!=' ') {
				
				if(output[j]==' ') {
					j--;	
				}
				output[j]=input[i];
				j--;
				
				
			}
			
		}
		
		//String str;
		System.out.println(String.copyValueOf(output));
		
		
		
	}

	
}
