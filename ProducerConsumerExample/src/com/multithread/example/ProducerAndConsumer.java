package com.multithread.example;

import java.util.LinkedList;

public class ProducerAndConsumer {
	
	LinkedList<Integer> list =  new LinkedList<Integer>();
	int cap = 2;
	
	
	public void produce() throws InterruptedException {
		
		System.out.println("PRODCUER INVOKED");
		
		int item = 0;
		
		while(true) {
			
			synchronized (this) {
				if(list.size()==cap) {
					System.out.println("waiting to produce");
					wait();
				}
				list.add(item++);
				notify();
				System.out.println("notified");
			}
			
			
			
		}
		
		
	}
	
	
	public void consume() throws InterruptedException {
		
		System.out.println("CONSUMER INVOKED");
		
		while(true) {
			
			
		synchronized (this) {
			
			if(list.size()==0) {
				System.out.println("waiting for messages to consume");
				wait();
			}
			
			int item = list.removeFirst();
			System.out.println("item consumed" +item);
			notify();
		}
			
			
			
		}
		
		
		
		
		
		
		
	}

}
