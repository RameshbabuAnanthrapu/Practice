package com.work.collection.stack;

import java.util.Stack;

public class StackAsQueue {
	
	/*Queue works on FIFO principle
	 * Stack works on LIFO principle
	 * 
	 * Here I tried to achieve Queue operation using Stack
	 * 
	 * 
	 * */
	static Stack<String> st1 = new Stack<>();
	static Stack<String> st2 =  new Stack<>();
	
	public static void main(String[] args) {
		
		enQueue("s1");
		enQueue("s2");
		enQueue("s3");
		
		deQueue();
		
	}
	
	static void enQueue(String s1) {
		st1.push(s1);
		
	}
	
	static void deQueue() {
		while(!st1.isEmpty()) {
			st2.push(st1.pop());
		}
		String removed = st2.pop();
		System.out.println("String "+removed+" is Removed");
		
	}

}
